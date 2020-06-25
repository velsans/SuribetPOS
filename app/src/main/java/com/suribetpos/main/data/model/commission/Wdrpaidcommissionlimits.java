package com.suribetpos.main.data.model.commission;

import com.suribetpos.main.data.model.commission.Wdrpaidcommissiondetails;
import com.suribetpos.main.data.model.commission.Wdrpaidcommissionvalues;

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

    public ArrayList<Wdrpaidcommissionvalues> getObjWDRPaidCommissionValues() {
        return objWDRPaidCommissionValues;
    }

    public void setObjWDRPaidCommissionValues(ArrayList<Wdrpaidcommissionvalues> objWDRPaidCommissionValues) {
        this.objWDRPaidCommissionValues = objWDRPaidCommissionValues;
    }

    public ArrayList<Wdrpaidcommissiondetails> getObjWDRPaidCommissionDetails() {
        return objWDRPaidCommissionDetails;
    }

    public void setObjWDRPaidCommissionDetails(ArrayList<Wdrpaidcommissiondetails> objWDRPaidCommissionDetails) {
        this.objWDRPaidCommissionDetails = objWDRPaidCommissionDetails;
    }

    int TillID;
    String MacAddress,ErrorMessage;
    ArrayList<Wdrpaidcommissionvalues> objWDRPaidCommissionValues;
    ArrayList<Wdrpaidcommissiondetails> objWDRPaidCommissionDetails;
}
