package com.suribetpos.main.data.model.etopup;

import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.etopup.BalanceAmountModel;

import java.util.ArrayList;

public class TillTransactionAmountModel {
    int UserId, TillId, ClientId;
    String MacAddress;

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

    ArrayList<StatusModel> Table = new ArrayList<>();
    ArrayList<BalanceAmountModel> Table1 = new ArrayList<>();

    public ArrayList<StatusModel> getTable() {
        return Table;
    }

    public void setTable(ArrayList<StatusModel> table) {
        Table = table;
    }

    public ArrayList<BalanceAmountModel> getTable1() {
        return Table1;
    }

    public void setTable1(ArrayList<BalanceAmountModel> table1) {
        Table1 = table1;
    }
}
