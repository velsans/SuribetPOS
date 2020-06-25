package com.suribetpos.main.model.lotto;

/**
 * Created by DEV 27 on 07/01/2017.
 */

public class LGBetCancel {

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

    public String getDenominationTypeTotal() {
        return DenominationTypeTotal;
    }

    public void setDenominationTypeTotal(String denominationTypeTotal) {
        DenominationTypeTotal = denominationTypeTotal;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getSlipID() {
        return SlipID;
    }

    public void setSlipID(String slipID) {
        SlipID = slipID;
    }

    public String getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(String currencyID) {
        CurrencyID = currencyID;
    }

    String LocationCode, TillCode, FullName ,TransactionDateTime,CurrencyCode,DenominationTypeTotal,Barcode,SlipID,CurrencyID;
}
