package com.suribetpos.main.model.onlinecashout;

import java.math.BigInteger;

/**
 * Created by DEV 27 on 09/02/2017.
 */

public class Cashoutwdrpaymentdetails {

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getReplaceMgs() {
        return ReplaceMgs;
    }

    public void setReplaceMgs(String replaceMgs) {
        ReplaceMgs = replaceMgs;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
    public int getUserAccountTypeID() {
        return UserAccountTypeID;
    }

    public void setUserAccountTypeID(int userAccountTypeID) {
        UserAccountTypeID = userAccountTypeID;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }


    Double Amount;

    public Double getAmount() {
        return Amount;
    }

    String Message;
    String UserType;
    String ReplaceMgs;
    String CurrencyCode;

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    String ErrorCode;
    int Status,UserAccountTypeID,CurrencyID;

    public BigInteger getAccountID() {
        return AccountID;
    }

    public void setAccountID(BigInteger accountID) {
        AccountID = accountID;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    BigInteger AccountID;

}
