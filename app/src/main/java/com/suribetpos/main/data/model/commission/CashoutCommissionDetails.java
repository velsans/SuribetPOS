package com.suribetpos.main.data.model.commission;

/**
 * Created by DEV 27 on 16/02/2017.
 */

public class CashoutCommissionDetails {
    String UWRCode;
    String Time;
    String CurrencyCode;
    Double Amount;

    public CashoutCommissionDetails(String UWRCode, String time, String currencyCode, Double amount) {
        this.UWRCode = UWRCode;
        Time = time;
        CurrencyCode = currencyCode;
        Amount = amount;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }


    public String getUWRCode() {
        return UWRCode;
    }

    public void setUWRCode(String UWRCode) {
        this.UWRCode = UWRCode;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }




}
