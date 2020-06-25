package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 1/31/2018.
 */

public class LGCancellBetDetails {
    String LocationCode, TillCode, FullName, TransactionDateTime, CurrencyCode, Barcode;
    Double DenominationTypeTotal;
    long SlipID;

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

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public Double getDenominationTypeTotal() {
        return DenominationTypeTotal;
    }

    public void setDenominationTypeTotal(Double denominationTypeTotal) {
        DenominationTypeTotal = denominationTypeTotal;
    }

    public long getSlipID() {
        return SlipID;
    }

    public void setSlipID(long slipID) {
        SlipID = slipID;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    int CurrencyID;
    int UserID;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    int TillID;
}
