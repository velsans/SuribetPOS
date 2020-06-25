package com.suribetpos.main.data.model.topup;

public class  Salesvouchers {

    private int ID;
    private int CurrencyId;
    private int DenominationId;
    private String Denomination;
    private int Quantity;
    private String Total;

    public Salesvouchers(int iD, int currencyId, int denominationId,
                         String denomination, int quantity, String total) {
        super();
        ID = iD;
        CurrencyId = currencyId;
        DenominationId = denominationId;
        Denomination = denomination;
        Quantity = quantity;
        Total = total;
    }


    public void setID(int iD) {
        ID = iD;
    }
    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }
    public void setDenominationId(int denominationId) {
        DenominationId = denominationId;
    }
    public void setDenomination(String denomination) {
        Denomination = denomination;
    }
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
    public void setTotal(String total) {
        Total = total;
    }

    public int getID() {
        return ID;
    }
    public int getCurrencyId() {
        return CurrencyId;
    }
    public int getDenominationId() {
        return DenominationId;
    }
    public String getDenomination() {
        return Denomination;
    }
    public int getQuantity() {
        return Quantity;
    }
    public String getTotal() {
        return Total;
    }


}
