package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 2/8/2018.
 */

public class LGFreeTicketCommisDetails {
    int SlipID, NumberOfBets;
    String Barcode, CurrencyCode, SlipTime;
    double TotalAmount;

    public int getSlipID() {
        return SlipID;
    }

    public void setSlipID(int slipID) {
        SlipID = slipID;
    }

    public int getNumberOfBets() {
        return NumberOfBets;
    }

    public void setNumberOfBets(int numberOfBets) {
        NumberOfBets = numberOfBets;
    }

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

    public String getSlipTime() {
        return SlipTime;
    }

    public void setSlipTime(String slipTime) {
        SlipTime = slipTime;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }
}
