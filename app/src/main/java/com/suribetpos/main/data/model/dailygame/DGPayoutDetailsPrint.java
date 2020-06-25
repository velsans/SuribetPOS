package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 2/2/2018.
 */

public class DGPayoutDetailsPrint {

    String Barcode,CurrencyCode,PayoutTime,FirstName,LocationName,TillName,Errormessage;
    int TillID, CurrencyID, UserID,StatusId;
    double AmountPaid,CurrencyAmount;
    long SlipID;

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getPayoutTime() {
        return PayoutTime;
    }

    public void setPayoutTime(String payoutTime) {
        PayoutTime = payoutTime;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getTillName() {
        return TillName;
    }

    public void setTillName(String tillName) {
        TillName = tillName;
    }

    public String getErrormessage() {
        return Errormessage;
    }

    public void setErrormessage(String errormessage) {
        Errormessage = errormessage;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public double getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        AmountPaid = amountPaid;
    }

    public double getCurrencyAmount() {
        return CurrencyAmount;
    }

    public void setCurrencyAmount(double currencyAmount) {
        CurrencyAmount = currencyAmount;
    }

    public long getSlipID() {
        return SlipID;
    }

    public void setSlipID(long slipID) {
        SlipID = slipID;
    }
}
