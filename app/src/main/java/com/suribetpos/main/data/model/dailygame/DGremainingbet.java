package com.suribetpos.main.model.dailygame;

import java.util.ArrayList;

public class DGremainingbet {
    public int TillId;
    public String MacAddress;
    public String ErrorMessage;
    ArrayList<DGremainingbetlimit> objDailyGameRemainingBetLimit;

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

    public void setObjDailyGameRemainingBetLimit(
            ArrayList<DGremainingbetlimit> objDailyGameRemainingBetLimit) {
        this.objDailyGameRemainingBetLimit = objDailyGameRemainingBetLimit;
    }


    public int getTillId() {
        return TillId;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public ArrayList<DGremainingbetlimit> getObjDailyGameRemainingBetLimit() {
        return objDailyGameRemainingBetLimit;
    }

}
