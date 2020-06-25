package com.suribetpos.main.data.model.onlinecashout;

import com.suribetpos.main.utils.Common;

/**
 * Created by DEV 27 on 09/02/2017.
 */

public class Cashouttransactiondetails {
    int ID,TillId;
    String COMMENT;
    String CURR;
    String VALUE;
    String USER;
    String TIME;
    double PAIDdoller;
    String CASHOUTCODE,MacAddress;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
    }

    public String getCURR() {
        return CURR;
    }

    public void setCURR(String CURR) {
        this.CURR = CURR;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getCASHOUTCODE() {
        return CASHOUTCODE;
    }

    public void setCASHOUTCODE(String CASHOUTCODE) {
        this.CASHOUTCODE = CASHOUTCODE;
    }

    public double getPAIDdoller() {
        return PAIDdoller;
    }

    public void setPAIDdoller(double PAIDdoller) {
        this.PAIDdoller = PAIDdoller;
    }

    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }
}
