package com.suribetpos.main.data.model.topup;

/**
 * Created by DEV 27 on 03/02/2017.
 */

public class CancelSalesVocuherVerify {
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

    String Barcode, CurrencyCode;
    Double Amount, CurrencyAmount;

    public int getVoucherId() {
        return VoucherId;
    }

    public void setVoucherId(int voucherId) {
        VoucherId = voucherId;
    }

    int VoucherId;

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public Double getCurrencyAmount() {
        return CurrencyAmount;
    }

    public void setCurrencyAmount(Double currencyAmount) {
        CurrencyAmount = currencyAmount;
    }
}
