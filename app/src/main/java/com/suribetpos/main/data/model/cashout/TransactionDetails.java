package com.suribetpos.main.data.model.cashout;

/**
 * Created by DEV 27 on 09/02/2017.
 */

public class TransactionDetails {
    public TransactionDetails(int tillId, String COMMENT, String CURR, double VALUE, String USER, String TIME, double PAIDdoller, String CASHOUTCODE, String macAddress,String IDNumber,String IDName) {
        this.TillId = tillId;
        this.COMMENT = COMMENT;
        this.CURR = CURR;
        this.VALUE = VALUE;
        this.USER = USER;
        this.TIME = TIME;
        this.PAIDdoller = PAIDdoller;
        this.CASHOUTCODE = CASHOUTCODE;
        this.MacAddress = macAddress;
        this.IdNumber=IDNumber;
        this.IdName=IDName;
    }

    public int ID, TillId;
    public String COMMENT;
    public String CURR;
    public String USER;
    public String TIME;
    public double PAIDdoller,VALUE;
    public String CASHOUTCODE, MacAddress,IdNumber,IdName;

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

    public double getVALUE() {
        return VALUE;
    }

    public void setVALUE(double VALUE) {
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

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getIdName() {
        return IdName;
    }

    public void setIdName(String idName) {
        IdName = idName;
    }
}
