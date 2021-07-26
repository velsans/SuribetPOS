package com.suribetpos.main.data.model.cashout;

import com.suribetpos.main.data.model.common.StatusModel;

import java.util.ArrayList;

public class CashoutInputPaymentModel {

    Object UWRCode,AccountId,UserTypeID,TillID,CurrencyId,UserId,MacAddress,IdNumber,IdName;
    ArrayList<StatusModel> m_Item1 = new ArrayList<>();
    ArrayList<CashoutPaymentDetailsModel> m_Item2 = new ArrayList<>();

    public Object getUWRCode() {
        return UWRCode;
    }

    public void setUWRCode(Object UWRCode) {
        this.UWRCode = UWRCode;
    }

    public Object getAccountId() {
        return AccountId;
    }

    public void setAccountId(Object accountId) {
        AccountId = accountId;
    }

    public Object getUserTypeID() {
        return UserTypeID;
    }

    public void setUserTypeID(Object userTypeID) {
        UserTypeID = userTypeID;
    }

    public Object getTillID() {
        return TillID;
    }

    public void setTillID(Object tillID) {
        TillID = tillID;
    }

    public Object getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(Object currencyId) {
        CurrencyId = currencyId;
    }

    public Object getUserId() {
        return UserId;
    }

    public void setUserId(Object userId) {
        UserId = userId;
    }

    public Object getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(Object macAddress) {
        MacAddress = macAddress;
    }

    public ArrayList<StatusModel> getM_Item1() {
        return m_Item1;
    }

    public void setM_Item1(ArrayList<StatusModel> m_Item1) {
        this.m_Item1 = m_Item1;
    }

    public ArrayList<CashoutPaymentDetailsModel> getM_Item2() {
        return m_Item2;
    }

    public void setM_Item2(ArrayList<CashoutPaymentDetailsModel> m_Item2) {
        this.m_Item2 = m_Item2;
    }

    public Object getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(Object idNumber) {
        IdNumber = idNumber;
    }

    public Object getIdName() {
        return IdName;
    }

    public void setIdName(Object idName) {
        IdName = idName;
    }
}
