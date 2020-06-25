package com.suribetpos.main.data.model.common;

/**
 * Created by DEV 27 on 07/03/2017.
 */

public class Currenydetails_user {
    public int CurrencyId;
    public String CurrencyCode;

    public int getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public double getCurrencyRate() {
        return CurrencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        CurrencyRate = currencyRate;
    }

    public double getCurrencyRateMain() {
        return CurrencyRateMain;
    }

    public void setCurrencyRateMain(double currencyRateMain) {
        CurrencyRateMain = currencyRateMain;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public double CurrencyRate, CurrencyRateMain, AMOUNT;
}
