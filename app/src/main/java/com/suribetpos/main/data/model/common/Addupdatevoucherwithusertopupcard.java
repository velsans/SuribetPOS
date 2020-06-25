package com.suribetpos.main.data.model.common;


import com.suribetpos.main.data.model.topup.Salesvouchers;

import java.util.List;

public class Addupdatevoucherwithusertopupcard {
    private int UserId;
    private int TillId;
    private List<Salesvouchers> SalesVouchers;
    private Double TotalPaid;
    private Double TotalReturn;
    private String IPAddress;
    private int ClientId;
    private long AccountId;
    private String ErrorMessage;
    private int ErrorStatusCode;

    private String BalanceAmount;
    private String SuccessMessage;
    private String MacAddress;

    public String getAlternativeMobileNumber() {
        return AlternativeMobileNumber;
    }

    public void setAlternativeMobileNumber(String alternativeMobileNumber) {
        AlternativeMobileNumber = alternativeMobileNumber;
    }

    private String AlternativeMobileNumber;
    private List<Addupdateprintvouchersdetails> AddUpdatePrintVouchersDetails;


    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getClientName() {
        return ClientName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    private String ClientName;
    private String CountryCode;
    private String MobileNumber;


    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public void setSalesVouchers(List<Salesvouchers> salesVouchers) {
        SalesVouchers = salesVouchers;
    }

    public void setTotalPaid(Double totalPaid) {
        TotalPaid = totalPaid;
    }

    public void setTotalReturn(Double totalReturn) {
        TotalReturn = totalReturn;
    }

    public void setIPAddress(String iPAddress) {
        IPAddress = iPAddress;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public void setAccountId(long accountId) {
        AccountId = accountId;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public void setErrorStatusCode(int errorStatusCode) {
        ErrorStatusCode = errorStatusCode;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public void setSuccessMessage(String successMessage) {
        SuccessMessage = successMessage;
    }


    public int getUserId() {
        return UserId;
    }

    public int getTillId() {
        return TillId;
    }

    public List<Salesvouchers> getSalesVouchers() {
        return SalesVouchers;
    }

    public Double getTotalPaid() {
        return TotalPaid;
    }

    public Double getTotalReturn() {
        return TotalReturn;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public int getClientId() {
        return ClientId;
    }

    public long getAccountId() {
        return AccountId;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public int getErrorStatusCode() {
        return ErrorStatusCode;
    }

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public String getSuccessMessage() {
        return SuccessMessage;
    }

    public void setaddupdateprintvouchersdetails(List<Addupdateprintvouchersdetails> addUpdatePrintVouchersDetails) {
        AddUpdatePrintVouchersDetails = addUpdatePrintVouchersDetails;
    }

    public List<Addupdateprintvouchersdetails> getaddupdateprintvouchersdetails() {
        return AddUpdatePrintVouchersDetails;
    }

}
