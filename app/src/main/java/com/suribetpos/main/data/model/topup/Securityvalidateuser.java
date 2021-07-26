package com.suribetpos.main.data.model.topup;

import com.suribetpos.main.data.model.common.Bettypiddetails_user;
import com.suribetpos.main.data.model.common.Currenydetails_user;
import com.suribetpos.main.data.model.common.ProductName_User;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.common.Userdetails_user;

import java.util.ArrayList;

public class Securityvalidateuser {

    public int Id, UserID, LocationID, TillID, LangID;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int locationID) {
        LocationID = locationID;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getCurrentVersion() {
        return CurrentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        CurrentVersion = currentVersion;
    }

    public int getLangID() {
        return LangID;
    }

    public void setLangID(int langID) {
        LangID = langID;
    }

    public String ErrorMessage, UserPassword, MacAddress, CurrentVersion, IPAddress;

    ArrayList<Userdetails_user> m_Item2 = new ArrayList();
    ArrayList<Bettypiddetails_user> m_Item3 = new ArrayList();
    ArrayList<ProductName_User> m_Item4 = new ArrayList();
    ArrayList<Currenydetails_user> m_Item5 = new ArrayList();
    ArrayList<StatusModel> m_Item1 = new ArrayList<>();

    public ArrayList<StatusModel> getM_Item1() {
        return m_Item1;
    }

    public void setM_Item1(ArrayList<StatusModel> m_Item1) {
        this.m_Item1 = m_Item1;
    }

    public ArrayList<Userdetails_user> getM_Item2() {
        return m_Item2;
    }

    public void setM_Item2(ArrayList<Userdetails_user> m_Item2) {
        this.m_Item2 = m_Item2;
    }

    public ArrayList<Bettypiddetails_user> getM_Item3() {
        return m_Item3;
    }

    public void setM_Item3(ArrayList<Bettypiddetails_user> m_Item3) {
        this.m_Item3 = m_Item3;
    }

    public ArrayList<ProductName_User> getM_Item4() {
        return m_Item4;
    }

    public void setM_Item4(ArrayList<ProductName_User> m_Item4) {
        this.m_Item4 = m_Item4;
    }

    public ArrayList<Currenydetails_user> getM_Item5() {
        return m_Item5;
    }

    public void setM_Item5(ArrayList<Currenydetails_user> m_Item5) {
        this.m_Item5 = m_Item5;
    }

}
