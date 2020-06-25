package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 12/28/2017.
 */

public class DGcancelbetdetails {
    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    int TillId;
    String Barcode;

}
