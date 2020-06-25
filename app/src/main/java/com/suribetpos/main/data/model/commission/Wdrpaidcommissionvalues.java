package com.suribetpos.main.data.model.commission;

/**
 * Created by DEV 27 on 16/02/2017.
 */

public class Wdrpaidcommissionvalues {
    String UWRCode;
    String Time;

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    String CurrencyCode;

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

    Double Amount;
}
