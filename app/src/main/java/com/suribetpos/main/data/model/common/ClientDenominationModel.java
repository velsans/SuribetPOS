package com.suribetpos.main.data.model.common;

import java.util.ArrayList;

public class ClientDenominationModel {

    private int DenominationID, Denomination, CurrencyID;
    private String CurrencyCode;

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    private int Quantity;


    public int setDenominationID(int DenominationID) {
        return this.DenominationID = DenominationID;
    }


    public int setCurrencyID(int CurrencyID) {
        return this.CurrencyID = CurrencyID;
    }

    public String setCurrencyCode(String CurrencyCode) {
        return this.CurrencyCode = CurrencyCode;
    }


    public int getDenominationID() {
        return DenominationID;
    }


    public int getCurrencyID() {
        return CurrencyID;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public int getDenomination() {
        return Denomination;
    }

    public void setDenomination(int denomination) {
        Denomination = denomination;
    }
}
