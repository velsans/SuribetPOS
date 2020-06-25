package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 1/31/2018.
 */

public class LGCancelledHistory {
    long SlipID;

    public long getSlipID() {
        return SlipID;
    }

    public void setSlipID(long slipID) {
        SlipID = slipID;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getTransactionDateTime() {
        return TransactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        TransactionDateTime = transactionDateTime;
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getNumberOfBets() {
        return NumberOfBets;
    }

    public void setNumberOfBets(int numberOfBets) {
        NumberOfBets = numberOfBets;
    }

    String Barcode, TransactionDateTime, SlipTime, CurrencyCode, FullName;
    Double TotalAmount;
    int NumberOfBets;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    int TillID;
}
