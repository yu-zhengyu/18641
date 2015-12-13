package com.example.zhengyu.mortgage.UI;

/**
 * Created by zhengyu on 15/11/4.
 * This is Add mortgage Activity, user can input the data through this activity
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.CursorAdapter;
import android.util.Log;


import com.example.zhengyu.mortgage.Model.Calculator;
import com.example.zhengyu.mortgage.R;
import com.example.zhengyu.mortgage.Util.DatabaseConnector;
import com.example.zhengyu.mortgage.exception.UserException;

public class Add_Mortgage extends Activity {
    private String[] MonthListString = {"0", "Jan", "Feb", "Mar", "Apr", "May",
            "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


    private EditText Name, PurchasePrice, DownPayment, MortgageTerm, InterestRate;
    private EditText PropertyTax, PropertyInsurance, ZIP;
    private Button AddButton;
    private Spinner YearList, MonthList;
    private Calculator calculator;
    private double MonthlyPayment, TotalPayment;
    private int year, month;
    private String payOffDate;
    private CursorAdapter MortgageAdapter;  // adapter

    private String NameString, PurchasePriceString, DownPaymentString, MortgageTermString
            , InterestRateString, PropertyTaxString, PropertyInsuranceString, ZIPString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__mortgage);

        // get all UI
        // Edit Text
        Name = (EditText)findViewById(R.id.editname);
        PurchasePrice = (EditText)findViewById(R.id.purchaseprice);
        DownPayment = (EditText)findViewById(R.id.Downpayment);
        MortgageTerm = (EditText)findViewById(R.id.Mortgageterm);
        InterestRate = (EditText)findViewById(R.id.Interestrate);
        PropertyTax = (EditText)findViewById(R.id.Propertytax);
        PropertyInsurance = (EditText)findViewById(R.id.Propertyinsurance);
        ZIP = (EditText)findViewById(R.id.zip);

        // Spinner
        YearList = (Spinner)findViewById(R.id.yearlist);
        MonthList = (Spinner)findViewById(R.id.montylist);

        // Button
        AddButton = (Button)findViewById(R.id.adddata);
        AddButton.setOnClickListener(addMortgageButtonClicked);
        calculator = new Calculator();
    }

    OnClickListener addMortgageButtonClicked = new OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                if(!testInputLegal()) {
                    throw new UserException();
                }
                else {
                    System.out.println("Create");
                    insertData(MonthlyPayment, TotalPayment, payOffDate);
                    showData();
                    Bundle sendData = new Bundle();
                    sendData.putString("name", Name.getText().toString());
                    sendData.putString("total", "$" + String.format("%.3f", TotalPayment));
                    sendData.putString("monthly", "$" + String.format("%.3f", MonthlyPayment));
                    sendData.putString("dayoff", payOffDate);
                    Intent viewMortgage = new Intent(Add_Mortgage.this, ViewResult_Activity.class);
                    viewMortgage.putExtras(sendData);
                    startActivity(viewMortgage);
                }
            } catch (UserException e) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(Add_Mortgage.this);

                // set dialog title & message, and provide Button to dismiss
                builder.setTitle("Error");
                builder.setMessage("Input Error, pl. Check it");
                builder.setPositiveButton("Back", null);
                builder.show(); // display the Dialog
            }
        }
    };

    public boolean testInputLegal() {
        if (Name.getText().length() == 0 || PurchasePrice.getText().length() == 0
                || DownPayment.getText().length() == 0 || MortgageTerm.getText().length() == 0
                || InterestRate.getText().length() == 0 || PropertyTax.getText().length() == 0
                || PropertyInsurance.getText().length() == 0|| ZIP.getText().length() == 0)
            return false;

        else {
            NameString = Name.getText().toString();
            PurchasePriceString = PurchasePrice.getText().toString();
            DownPaymentString = DownPayment.getText().toString();
            MortgageTermString = MortgageTerm.getText().toString();
            InterestRateString = InterestRate.getText().toString();
            PropertyInsuranceString = PropertyInsurance.getText().toString();
            PropertyTaxString = PropertyTax.getText().toString();
            ZIPString = ZIP.getText().toString();
        }

        if (Double.parseDouble(PurchasePriceString) < 0
                || Double.parseDouble(DownPaymentString) < 0
                || Double.parseDouble(MortgageTermString) < 0
                || Double.parseDouble(InterestRateString) < 0
                || Double.parseDouble(PropertyTaxString) < 0
                || Integer.parseInt(ZIPString) < 0
                || Double.parseDouble(PropertyInsuranceString) < 0
                || Double.parseDouble(InterestRateString) > 100
                || Double.parseDouble(DownPaymentString) > 100)
            return false;


        int firstYear =  Integer.parseInt((String)YearList.getSelectedItem());
        String firstMonthString = (String)MonthList.getSelectedItem();
        int firstMonth = 0;
        for(int i = 0; i < 13; i++) {
            if(firstMonthString.equals(MonthListString[i])) {
                firstMonth = i;
                break;
            }
        }

        int totalYear = Integer.parseInt(MortgageTermString);
        double loadAmount = Double.parseDouble(PurchasePriceString)
                - ((Double.parseDouble(DownPaymentString) / 100) * Double.parseDouble(PurchasePriceString));
        double rate = Double.parseDouble(InterestRateString);
        double tax = Double.parseDouble(PropertyTaxString);
        double insurance = Double.parseDouble(PropertyInsuranceString);
        if (loadAmount < 0)
            return false;

        payOffDate = calculator.getPayoffDate(firstMonth, firstYear, totalYear);
        TotalPayment = calculator.getTotalPayment(loadAmount, totalYear, rate, tax, insurance);
        MonthlyPayment = calculator.getMonthPayment(loadAmount, totalYear, rate, tax, insurance);

        return true;
    }

    // show the database situation in log
    public void showData() {
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        databaseConnector.open();
        Cursor cur = databaseConnector.getAllInformation();
        if (cur.moveToFirst()) {
            do {
                String showdatastring = "";
                for (int i = 0; i < cur.getColumnCount(); i++) {
                    showdatastring = showdatastring + cur.getString(i) + "\t";
                }
                Log.d("DataBase", showdatastring);
            } while(cur.moveToNext());

        }
        Log.d("DataBase", "Show Data successful");


    }

    // insert the data into the database
    public void insertData(double total, double monthpay, String paydayoff) {
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        databaseConnector.insertContact(Name.getText().toString()
                , String.format("%.3f", monthpay)
                , String.format("%.3f", total)
                , paydayoff);
        Log.d("DataBase", "Insert data into Databese");
    }

}
