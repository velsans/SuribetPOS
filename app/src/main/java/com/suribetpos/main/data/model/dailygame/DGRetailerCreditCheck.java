package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/6/2018.
 */

public class DGRetailerCreditCheck {
    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getExceed() {
        return Exceed;
    }

    public void setExceed(int exceed) {
        Exceed = exceed;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getMacaddress() {
        return Macaddress;
    }

    public void setMacaddress(String macaddress) {
        Macaddress = macaddress;
    }

    public String getFormType() {
        return FormType;
    }

    public void setFormType(String formType) {
        FormType = formType;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getAuthorized() {
        return Authorized;
    }

    public void setAuthorized(String authorized) {
        Authorized = authorized;
    }

    public int TillId, UserID, Exceed;
    double Amount;
    String Macaddress, FormType, ErrorMessage, Authorized;
}
