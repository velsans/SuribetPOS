package com.suribetpos.main.model.onlinecashout;

import com.suribetpos.main.data.model.onlinecashout.Cashouttransactiondetails;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 09/02/2017.
 */

public class Cashouttransactionlimits {

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ArrayList<Cashouttransactiondetails> getObjCashOutTransationDetails() {
        return objCashOutTransationDetails;
    }

    public void setObjCashOutTransationDetails(ArrayList<Cashouttransactiondetails> objCashOutTransationDetails) {
        this.objCashOutTransationDetails = objCashOutTransationDetails;
    }

    String  MacAddress,
            ErrorMessage;
    ArrayList<Cashouttransactiondetails> objCashOutTransationDetails;

    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    int TillId;
}
