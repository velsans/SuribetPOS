package com.suribetpos.main.data.model.common;

import java.util.ArrayList;

public class StatusModel {
    String Message, Currency, MobileNumber, ReplaceMgs, ErrorCode, extraparm,CountryCode;
    int Status, JournalId;
    boolean SMSIsactive;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getReplaceMgs() {
        return ReplaceMgs;
    }

    public void setReplaceMgs(String replaceMgs) {
        ReplaceMgs = replaceMgs;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getExtraparm() {
        return extraparm;
    }

    public void setExtraparm(String extraparm) {
        this.extraparm = extraparm;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getJournalId() {
        return JournalId;
    }

    public void setJournalId(int journalId) {
        JournalId = journalId;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public boolean isSMSIsactive() {
        return SMSIsactive;
    }

    public void setSMSIsactive(boolean SMSIsactive) {
        this.SMSIsactive = SMSIsactive;
    }


}

