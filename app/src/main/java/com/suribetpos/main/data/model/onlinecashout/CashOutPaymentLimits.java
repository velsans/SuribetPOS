package com.suribetpos.main.model.onlinecashout;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 10/02/2017.
 */

public class CashOutPaymentLimits {


    public String getUWRCode() {
        return UWRCode;
    }

    public void setUWRCode(String UWRCode) {
        this.UWRCode = UWRCode;
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

    public int getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public ArrayList<CashOutPaymentDetails> getObjCashOutPaymentDetails() {
        return objCashOutPaymentDetails;
    }

    public void setObjCashOutPaymentDetails(ArrayList<CashOutPaymentDetails> objCashOutPaymentDetails) {
        this.objCashOutPaymentDetails = objCashOutPaymentDetails;
    }

    public long getAccountId() {
        return AccountId;
    }

    public void setAccountId(long accountId) {
        AccountId = accountId;
    }

    String UWRCode, MacAddress, ErrorMessage;
    int UserTypeID;

    public int getUserTypeID() {
        return UserTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        UserTypeID = userTypeID;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    int TillID;
    int CurrencyId;
    int UserId;
    public ArrayList<CashOutPaymentDetails> objCashOutPaymentDetails;
    long AccountId;
}
