package com.suribetpos.main.model.balance;

/**
 * Created by DEV 27 on 28/06/2017.
 */

public class Balance_DenominationPojo {

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getDenominationTypeID() {
        return DenominationTypeID;
    }

    public void setDenominationTypeID(int denominationTypeID) {
        DenominationTypeID = denominationTypeID;
    }

    public int getDenominationID() {
        return DenominationID;
    }

    public void setDenominationID(int denominationID) {
        DenominationID = denominationID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getDenominationValue() {
        return DenominationValue;
    }

    public void setDenominationValue(double denominationValue) {
        DenominationValue = denominationValue;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getCurrencyRateUSD() {
        return CurrencyRateUSD;
    }

    public void setCurrencyRateUSD(String currencyRateUSD) {
        CurrencyRateUSD = currencyRateUSD;
    }

    int TillID,DenominationTypeID,DenominationID,Quantity;
    double DenominationValue,Total;
    String CurrencyRateUSD;
}
