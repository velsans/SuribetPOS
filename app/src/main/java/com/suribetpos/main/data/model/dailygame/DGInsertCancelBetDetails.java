package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/5/2018.
 */

public class DGInsertCancelBetDetails {
    String Barcode, LocationCode, TillCode, FullName, TransactionDateTime, CurrencyCode;
    int TillID, UserID, SlipIDCurrencyID;

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public String getTillCode() {
        return TillCode;
    }

    public void setTillCode(String tillCode) {
        TillCode = tillCode;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getTransactionDateTime() {
        return TransactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        TransactionDateTime = transactionDateTime;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public int getSlipIDCurrencyID() {
        return SlipIDCurrencyID;
    }

    public void setSlipIDCurrencyID(int slipIDCurrencyID) {
        SlipIDCurrencyID = slipIDCurrencyID;
    }

    public double getDenominationTypeTotal() {
        return DenominationTypeTotal;
    }

    public void setDenominationTypeTotal(double denominationTypeTotal) {
        DenominationTypeTotal = denominationTypeTotal;
    }

    public double DenominationTypeTotal;
}
