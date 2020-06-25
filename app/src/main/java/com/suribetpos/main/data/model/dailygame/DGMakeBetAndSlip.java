package com.suribetpos.main.model.dailygame;

import java.util.ArrayList;


public class DGMakeBetAndSlip {

    private int TillId;
    private int UserId;
    private String MacAddress;
    private String MobileNumber;
    private String ErrorMessage;
    private ArrayList<DGBetDetails> objBetDetails;
    private ArrayList<DGBetPrintDetails> objBetPrintDetails;
    private ArrayList<DGBetSavedDetails> objBetSavedDetails;

    public ArrayList<DGBetSavedDetails> getObjBetSavedDetails() {
        return objBetSavedDetails;
    }

    public void setObjBetSavedDetails(ArrayList<DGBetSavedDetails> objBetSavedDetails) {
        this.objBetSavedDetails = objBetSavedDetails;
    }

    public ArrayList<DGBetDetails> getObjBetDetails() {
        return objBetDetails;
    }

    public void setObjBetDetails(ArrayList<DGBetDetails> objBetDetails) {
        this.objBetDetails = objBetDetails;
    }

    public ArrayList<DGBetPrintDetails> getObjBetPrintDetails() {
        return objBetPrintDetails;
    }

    public void setObjBetPrintDetails(ArrayList<DGBetPrintDetails> objBetPrintDetails) {
        this.objBetPrintDetails = objBetPrintDetails;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getTillId() {
        return TillId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }


}
