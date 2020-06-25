package com.suribetpos.main.data.model.topup;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 16/02/2017.
 */

public class TopUpPaidCommissionLimits {
    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public ArrayList<TopUpPaidCommissionValues> getObjTopUpPaidCommissionValues() {
        return objTopUpPaidCommissionValues;
    }

    public void setObjTopUpPaidCommissionValues(ArrayList<TopUpPaidCommissionValues> objTopUpPaidCommissionValues) {
        this.objTopUpPaidCommissionValues = objTopUpPaidCommissionValues;
    }

    public ArrayList<TopUpPaidCommissionDetails> getObjTopupPaidCommissionDetails() {
        return objTopupPaidCommissionDetails;
    }

    public void setObjTopupPaidCommissionDetails(ArrayList<TopUpPaidCommissionDetails> objTopupPaidCommissionDetails) {
        this.objTopupPaidCommissionDetails = objTopupPaidCommissionDetails;
    }

    public int TillID;
    public String MacAddress,ErrorMessage;
    ArrayList<TopUpPaidCommissionValues> objTopUpPaidCommissionValues;
    ArrayList<TopUpPaidCommissionDetails> objTopupPaidCommissionDetails;
}
