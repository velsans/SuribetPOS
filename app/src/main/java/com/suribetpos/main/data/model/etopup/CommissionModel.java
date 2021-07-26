package com.suribetpos.main.data.model.etopup;

import java.util.ArrayList;

public class CommissionModel {
    int TillId, ClientId, UserId;
    String MacAddress, CollectedDate;
    ArrayList<SaleCommissionModel> SaleCommission = new ArrayList<>();
    ArrayList<TotalCommissionModel> TotalCommission = new ArrayList<>();
    ArrayList<CancelCommissionModel> CancelCommission = new ArrayList<>();

    public ArrayList<SaleCommissionModel> getSaleCommission() {
        return SaleCommission;
    }

    public void setSaleCommission(ArrayList<SaleCommissionModel> saleCommission) {
        SaleCommission = saleCommission;
    }

    public ArrayList<TotalCommissionModel> getTotalCommission() {
        return TotalCommission;
    }

    public void setTotalCommission(ArrayList<TotalCommissionModel> totalCommission) {
        TotalCommission = totalCommission;
    }

    public ArrayList<CancelCommissionModel> getCancelCommission() {
        return CancelCommission;
    }

    public void setCancelCommission(ArrayList<CancelCommissionModel> cancelCommission) {
        CancelCommission = cancelCommission;
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

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getCollectedDate() {
        return CollectedDate;
    }

    public void setCollectedDate(String collectedDate) {
        CollectedDate = collectedDate;
    }
}
