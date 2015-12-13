package com.example.zhengyu.mortgage.UI;

/**
 * Created by zhengyu on 15/11/4.
 * This is Activity would help user review the result
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.zhengyu.mortgage.R;
import com.example.zhengyu.mortgage.Util.DatabaseConnector;

public class ViewResult_Activity extends Activity {

    private String total;
    private String monthly;
    private String payDayoff;
    private String name;
    private TextView totalview, monthlyview, dayoffview, Nameview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_result_);


        // get the data from last Activity
        Bundle bundle = getIntent().getExtras();
        total = bundle.getString("total");
        monthly = bundle.getString("monthly");
        payDayoff = bundle.getString("dayoff");
        name = bundle.getString("name");
        totalview = (TextView)findViewById(R.id.DisplayTotal);
        monthlyview = (TextView)findViewById(R.id.DisplayMonth);
        dayoffview = (TextView)findViewById(R.id.Displayoffday);
        Nameview = (TextView)findViewById(R.id.view_Name);

        // set Data into UI
        totalview.setText(total);
        monthlyview.setText(monthly);
        dayoffview.setText(payDayoff);
        Nameview.setText(name);

    }

    public void showDataBase() {
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        Log.d("DateBase", "Query data from Databese");
    }



}
