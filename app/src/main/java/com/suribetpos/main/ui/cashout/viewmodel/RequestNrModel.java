package com.suribetpos.main.ui.cashout.viewmodel;

import androidx.databinding.BaseObservable;

import java.math.BigInteger;

public class RequestNrModel extends BaseObservable {
    public Double Amount;
    public int Status, UserAccountTypeID, CurrencyID;
    public String Message, ReplaceMgs, CurrencyCode, ErrorCode;
    int UserTypeID;
    public BigInteger AccountID;

    public RequestNrModel(Double amount, int status, BigInteger accountID, int userAccountTypeID, int currencyID, String errorCode, int userTypeID, String message, String replaceMgs, String currencyCode) {
        this.Amount = amount;
        this.Status = status;
        this.AccountID = accountID;
        this.UserAccountTypeID = userAccountTypeID;
        this.CurrencyID = currencyID;
        this.ErrorCode = errorCode;
        this.UserTypeID = userTypeID;
        this.Message = message;
        this.ReplaceMgs = replaceMgs;
        this.CurrencyCode = currencyCode;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public BigInteger getAccountID() {
        return AccountID;
    }

    public void setAccountID(BigInteger accountID) {
        AccountID = accountID;
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

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public int getUserTypeID() {
        return UserTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        UserTypeID = userTypeID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getReplaceMgs() {
        return ReplaceMgs;
    }

    public void setReplaceMgs(String replaceMgs) {
        ReplaceMgs = replaceMgs;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }
}