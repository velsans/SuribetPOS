package com.suribetpos.main.data.model.topup;

/**
 * Created by DEV 27 on 03/02/2017.
 */

public class SalesVoucher2 {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }

    public int getDenominationId() {
        return DenominationId;
    }

    public void setDenominationId(int denominationId) {
        DenominationId = denominationId;
    }

    public int getDenomination() {
        return Denomination;
    }

    public void setDenomination(int denomination) {
        Denomination = denomination;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    private int ID;
    private int CurrencyId;
    private int DenominationId;
    private int Denomination;
    private int Quantity;
    private int Total;
}
