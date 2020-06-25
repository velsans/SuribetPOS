package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 2/8/2018.
 */

public class LGPaidCommisDetails {
    int SlipID;
    String Barcode,Time;
    double AmountPaid;

    public int getSlipID() {
        return SlipID;
    }

    public void setSlipID(int slipID) {
        SlipID = slipID;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        AmountPaid = amountPaid;
    }

}
