package com.suribetpos.main.model.transaction;

/**
 * Created by DEV 27 on 21/06/2017.
 */

public class TransactionsDefaultCurrency {
    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }

    public double getCurrencyRateMain() {
        return CurrencyRateMain;
    }

    public void setCurrencyRateMain(double currencyRateMain) {
        CurrencyRateMain = currencyRateMain;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    int CurrencyID;
    String CurrencyCode;
    double Rate, CurrencyRateMain, Amount;
}
