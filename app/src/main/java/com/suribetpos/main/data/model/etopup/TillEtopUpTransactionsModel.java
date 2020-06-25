package com.suribetpos.main.data.model.etopup;

import com.suribetpos.main.data.model.common.StatusModel;

import java.util.ArrayList;

public class TillEtopUpTransactionsModel {
    int UserId, TillId, ClientId;
    String MacAddress,CollectedDate;

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

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }
    ArrayList<StatusModel> Table=new ArrayList<>();
    ArrayList<TodaysTransectionModel> Table1=new ArrayList<>();

    public ArrayList<StatusModel> getTable() {
        return Table;
    }

    public void setTable(ArrayList<StatusModel> table) {
        Table = table;
    }

    public ArrayList<TodaysTransectionModel> getTable1() {
        return Table1;
    }

    public void setTable1(ArrayList<TodaysTransectionModel> table1) {
        Table1 = table1;
    }

    public String getCollectedDate() {
        return CollectedDate;
    }

    public void setCollectedDate(String collectedDate) {
        CollectedDate = collectedDate;
    }
}
