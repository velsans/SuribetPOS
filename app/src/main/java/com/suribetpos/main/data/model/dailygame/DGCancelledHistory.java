package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/8/2018.
 */

public class DGCancelledHistory {
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getSlipTime() {
        return SlipTime;
    }

    public void setSlipTime(String slipTime) {
        SlipTime = slipTime;
    }

    public String getTransactionDateTime() {
        return TransactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        TransactionDateTime = transactionDateTime;
    }

    public int getSlipID() {
        return SlipID;
    }

    public void setSlipID(int slipID) {
        SlipID = slipID;
    }

    public double getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(double multiplier) {
        Multiplier = multiplier;
    }

    public double getStake() {
        return Stake;
    }

    public void setStake(double stake) {
        Stake = stake;
    }

    public double getTotalStake() {
        return TotalStake;
    }

    public void setTotalStake(double totalStake) {
        TotalStake = totalStake;
    }

    String Barcode, CurrencyCode, FullName, SlipTime, TransactionDateTime;
    int SlipID;
    double Multiplier;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    int TillID;
    double Stake, TotalStake;
}
