package com.suribetpos.main.data.model.etopup;

public class SaleCommissionModel {
    public int getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(int voucherID) {
        VoucherID = voucherID;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

   /* public String getDenom() { return Denom;}

    public void setDenom(String denom) {Denom = denom;}*/

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    int VoucherID;
    String Barcode, Time;
    double Denom;

    public void setDenom(double denom) {
        Denom = denom;
    }

    public double getDenom() {
        return Denom;
    }
}
