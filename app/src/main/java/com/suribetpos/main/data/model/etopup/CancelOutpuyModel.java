package com.suribetpos.main.model.etopup;

public class CancelOutpuyModel {
    String SalesVoucher,CurrencyCode,LocationName,LocationAddress,TillCode,LocationPhone,UserName,PrintingDateTime,ErrorMessage;
    Double CurrencyAmount;
    int StatusId;

    public String getSalesVoucher() {
        return SalesVoucher;
    }

    public void setSalesVoucher(String salesVoucher) {
        SalesVoucher = salesVoucher;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        LocationAddress = locationAddress;
    }

    public String getTillCode() {
        return TillCode;
    }

    public void setTillCode(String tillCode) {
        TillCode = tillCode;
    }

    public String getLocationPhone() {
        return LocationPhone;
    }

    public void setLocationPhone(String locationPhone) {
        LocationPhone = locationPhone;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPrintingDateTime() {
        return PrintingDateTime;
    }

    public void setPrintingDateTime(String printingDateTime) {
        PrintingDateTime = printingDateTime;
    }

    public Double getCurrencyAmount() {
        return CurrencyAmount;
    }

    public void setCurrencyAmount(Double currencyAmount) {
        CurrencyAmount = currencyAmount;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }
}

