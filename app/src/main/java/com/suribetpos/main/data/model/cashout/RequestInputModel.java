package com.suribetpos.main.data.model.cashout;

import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.ui.cashout.viewmodel.RequestNrModel;

import java.util.ArrayList;

public class RequestInputModel {
    String UWRcode;

    ArrayList<RequestNrModel> m_Item2 = new ArrayList<>();
    ArrayList<StatusModel> m_Item1 = new ArrayList<>();

    public String getUWRcode() {
        return UWRcode;
    }

    public void setUWRcode(String UWRcode) {
        this.UWRcode = UWRcode;
    }

    public ArrayList<RequestNrModel> getM_Item2() {
        return m_Item2;
    }

    public void setM_Item2(ArrayList<RequestNrModel> m_Item2) {
        this.m_Item2 = m_Item2;
    }

    public ArrayList<StatusModel> getM_Item1() {
        return m_Item1;
    }

    public void setM_Item1(ArrayList<StatusModel> m_Item1) {
        this.m_Item1 = m_Item1;
    }
}
