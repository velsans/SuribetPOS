package com.suribetpos.main.data.model.common;

import java.util.ArrayList;

public class Changepassword {

    String OldPassword, NewPassword, MacAddress;
    int UserId;

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    ArrayList<StatusModel> Table = new ArrayList<>();

    public ArrayList<StatusModel> getTable() {
        return Table;
    }

    public void setTable(ArrayList<StatusModel> table) {
        Table = table;
    }
}
