package com.suribetpos.main.model.dailygame;

import java.util.ArrayList;

/**
 * Created by Velmurugan on 14/11/2016.
 */

public class DGrecentresult {
    public int TillId;
    public String MacAddress;
    public String ErrorMessage;
    ArrayList<DGrecentresultlimits> objDailyGameRecentResultLimit;

    public ArrayList<DGrecentresultlimits> getObjDailyGameRecentResultLimit() {
        return objDailyGameRecentResultLimit;
    }

    public void setObjDailyGameRecentResultLimit(ArrayList<DGrecentresultlimits> objDailyGameRecentResultLimit) {
        this.objDailyGameRecentResultLimit = objDailyGameRecentResultLimit;
    }


    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }


    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }


    public int getTillId() {
        return TillId;
    }

    public String getMacAddress() {
        return MacAddress;
    }


}
