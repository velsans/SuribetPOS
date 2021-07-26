package com.suribetpos.main.data.model.commission;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 16/02/2017.
 */

public class Wdrpaidcommissionlimits {
    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

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

    public ArrayList<CashoutCommissionDetails> getObjCashoutCommissionDetails() {
        return objCashoutCommissionDetails;
    }

    public void setObjCashoutCommissionDetails(ArrayList<CashoutCommissionDetails> objCashoutCommissionDetails) {
        this.objCashoutCommissionDetails = objCashoutCommissionDetails;
    }

    public ArrayList<Wdrpaidcommissiondetails> getObjWDRPaidCommissionDetails() {
        return objWDRPaidCommissionDetails;
    }

    public void setObjWDRPaidCommissionDetails(ArrayList<Wdrpaidcommissiondetails> objWDRPaidCommissionDetails) {
        this.objWDRPaidCommissionDetails = objWDRPaidCommissionDetails;
    }

    int TillID;
    String MacAddress,ErrorMessage;
    ArrayList<CashoutCommissionDetails> objCashoutCommissionDetails;
    ArrayList<Wdrpaidcommissiondetails> objWDRPaidCommissionDetails;
}
