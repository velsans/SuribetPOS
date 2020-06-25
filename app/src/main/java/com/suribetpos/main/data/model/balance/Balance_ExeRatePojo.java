package com.suribetpos.main.model.balance;

/**
 * Created by DEV 27 on 30/06/2017.
 */

public class Balance_ExeRatePojo {
    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getRefUsd() {
        return RefUsd;
    }

    public void setRefUsd(String refUsd) {
        RefUsd = refUsd;
    }

    public String getRefforeign() {
        return Refforeign;
    }

    public void setRefforeign(String refforeign) {
        Refforeign = refforeign;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }

    public boolean isMain() {
        return IsMain;
    }

    public void setMain(boolean main) {
        IsMain = main;
    }

    int CurrencyID;
    String Currency,RefUsd,Refforeign;
    double Rate;
    boolean IsMain;
}
