package com.suribetpos.main.model.balance;

/**
 * Created by DEV 27 on 29/06/2017.
 */

public class RateCalculationPojo {
    public String getFromCurrency() {
        return FromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        FromCurrency = fromCurrency;
    }


    String FromCurrency;
    double Currencyrate, CurrencyRateMain;

    public double getCurrencyrate() {
        return Currencyrate;
    }

    public void setCurrencyrate(double currencyrate) {
        Currencyrate = currencyrate;
    }

    public double getCurrencyRateMain() {
        return CurrencyRateMain;
    }

    public void setCurrencyRateMain(double currencyRateMain) {
        CurrencyRateMain = currencyRateMain;
    }
}
