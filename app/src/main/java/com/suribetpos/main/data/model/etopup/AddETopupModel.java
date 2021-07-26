package com.suribetpos.main.data.model.etopup;

import com.suribetpos.main.data.model.common.StatusModel;

import java.math.BigInteger;
import java.util.ArrayList;

public class AddETopupModel {

    int UserId,TillId,ClientId;
    String AccountId,MacAddress,MobileNumber,AlternativeMobileNumber,ClientName,CustomerCardAccountID;
    double TotalPaid;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public double getTotalPaid() {
        return TotalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        TotalPaid = totalPaid;
    }

    public String getAlternativeMobileNumber() {
        return AlternativeMobileNumber;
    }

    public void setAlternativeMobileNumber(String alternativeMobileNumber) {
        AlternativeMobileNumber = alternativeMobileNumber;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }
    ArrayList<StatusModel> Table=new ArrayList<>();

    public ArrayList<StatusModel> getTable() {
        return Table;
    }

    public void setTable(ArrayList<StatusModel> table) {
        Table = table;
    }

    public String getCustomerCardAccountID() {
        return CustomerCardAccountID;
    }

    public void setCustomerCardAccountID(String customerCardAccountID) {
        CustomerCardAccountID = customerCardAccountID;
    }
}


