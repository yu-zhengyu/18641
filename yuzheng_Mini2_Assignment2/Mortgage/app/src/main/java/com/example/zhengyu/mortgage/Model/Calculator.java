package com.example.zhengyu.mortgage.Model;

/**
 * Created by zhengyu on 15/11/2.
 * This is a model Class, help application to compute the Mortgage
 */
public class Calculator {
    private String[] MonthListString = {"0", "Jan", "Feb", "Mar", "Apr", "May",
            "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private double PurchasePrice;
    private double DownPayment;
    private int MortTerm;
    private double IntereRate;
    private double propertyTax;
    private double PropInsuran;
    private double PMI;
    private String Zipcode;
    private String FirstPayDate;

    public Calculator(double purchasePrice, double downPayment,
                      int mortTerm, double intereRate, double propertyTax,
                      double propInsuran, double PMI,
                      String zipcode, String firstPayDate) {
        PurchasePrice = purchasePrice;
        DownPayment = downPayment;
        MortTerm = mortTerm;
        IntereRate = intereRate;
        this.propertyTax = propertyTax;
        PropInsuran = propInsuran;
        this.PMI = PMI;
        Zipcode = zipcode;
        FirstPayDate = firstPayDate;
    }

    public Calculator() {

    }

    // compute the payoff date
    public String getPayoffDate(int firstMonth, int firstYear, int totalYear) {
        String result = "";

        int payoffYear = firstYear + totalYear;
        int payoffMon = firstMonth;
        if (firstMonth == 1) {
            payoffMon = 12;
            payoffYear = payoffYear - 1;
        } else {
            payoffMon = payoffMon - 1;
        }
        result = MonthListString[payoffMon] + ", " + payoffYear;
        return result;
    }

    // compute the Month Payment
    public double getMonthPayment(double loanAmount, int termInYears
            , double interestRate, double PropertyTax, double PropertyInsurance) {

        interestRate /= 100.0;

        double monthlyRate = interestRate / 12.0;

        int termInMonths = termInYears * 12;

        double monthlyPayment =
                (loanAmount*monthlyRate) /
                        (1-Math.pow(1+monthlyRate, -termInMonths))
                        + (PropertyTax / 12.0) + (PropertyInsurance / 12.0);


        return monthlyPayment;
    }

    // compute the total payment
    public double getTotalPayment(double loanAmount, int termInYears
            , double interestRate, double PropertyTax, double PropertyInsurance) {
        interestRate /= 100.0;

        double monthlyRate = interestRate / 12.0;


        int termInMonths = termInYears * 12;


        double monthlyPayment =
                (loanAmount*monthlyRate) /
                        (1-Math.pow(1+monthlyRate, -termInMonths))
                        + (PropertyTax / 12.0) + (PropertyInsurance / 12.0);

        double totalPayment = monthlyPayment * 12 * termInYears;


        return totalPayment;
    }

    public int getMortTerm() {
        return MortTerm;
    }

    public void setMortTerm(int mortTerm) {
        MortTerm = mortTerm;
    }

    public double getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(double propertyTax) {
        this.propertyTax = propertyTax;
    }

    public double getIntereRate() {
        return IntereRate;
    }

    public void setIntereRate(double intereRate) {
        IntereRate = intereRate;
    }

    public double getDownPayment() {
        return DownPayment;
    }

    public void setDownPayment(double downPayment) {
        DownPayment = downPayment;
    }

    public double getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public double getPropInsuran() {
        return PropInsuran;
    }

    public void setPropInsuran(double propInsuran) {
        PropInsuran = propInsuran;
    }

    public double getPMI() {
        return PMI;
    }

    public void setPMI(double PMI) {
        this.PMI = PMI;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getFirstPayDate() {
        return FirstPayDate;
    }

    public void setFirstPayDate(String firstPayDate) {
        FirstPayDate = firstPayDate;
    }

}
