package com.suribetpos.main.data.model.cashout;

import java.util.ArrayList;

public class TransactionInputModel {
    Object MacAddress,TillId,CollectedDate;

    public Object getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(Object macAddress) {
        MacAddress = macAddress;
    }

    public Object getTillId() {
        return TillId;
    }

    public void setTillId(Object tillId) {
        TillId = tillId;
    }

    public Object getCollectedDate() {
        return CollectedDate;
    }

    public void setCollectedDate(Object collectedDate) {
        CollectedDate = collectedDate;
    }

    ArrayList<TransactionDetails> m_Item1 = new ArrayList<>();

    public ArrayList<TransactionDetails> getM_Item1() {
        return m_Item1;
    }

    public void setM_Item1(ArrayList<TransactionDetails> m_Item1) {
        this.m_Item1 = m_Item1;
    }
}
