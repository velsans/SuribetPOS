package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 1/31/2018.
 */

public class LGCancelValitaion {
    String Barcode,CurrentDateTime,SlipTime,CurrencyCode;
    double TotalAmount;
    int BetNumber;
    int CurrencyID;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    int TillID;

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public String getSlipTime() {
        return SlipTime;
    }

    public void setSlipTime(String slipTime) {
        SlipTime = slipTime;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getBetNumber() {
        return BetNumber;
    }

    public void setBetNumber(int betNumber) {
        BetNumber = betNumber;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    boolean IsValid;
}
