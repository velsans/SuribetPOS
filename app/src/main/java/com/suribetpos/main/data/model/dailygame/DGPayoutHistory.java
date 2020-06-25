package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/30/2018.
 */

public class DGPayoutHistory {

    int TillID, ID;
    String CURR;
    String TIME;
    String BARCODE;

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    String USER;
    double VALUE, PAIDUSD;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCURR() {
        return CURR;
    }

    public void setCURR(String CURR) {
        this.CURR = CURR;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getBARCODE() {
        return BARCODE;
    }

    public void setBARCODE(String BARCODE) {
        this.BARCODE = BARCODE;
    }

    public double getVALUE() {
        return VALUE;
    }

    public void setVALUE(double VALUE) {
        this.VALUE = VALUE;
    }

    public double getPAIDUSD() {
        return PAIDUSD;
    }

    public void setPAIDUSD(double PAIDUSD) {
        this.PAIDUSD = PAIDUSD;
    }
}
