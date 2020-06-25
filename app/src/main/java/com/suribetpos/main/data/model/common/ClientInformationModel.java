package com.suribetpos.main.data.model.common;

import java.util.ArrayList;

public class ClientInformationModel {

    ArrayList<StatusModel> m_Item1 = new ArrayList<>();
    ArrayList<Clientinformation> m_Item2 = new ArrayList<>();
    ArrayList<Clientproductname> m_Item3 = new ArrayList<>();
    ArrayList<Clientcurrency> m_Item4 = new ArrayList<>();

    public ArrayList<StatusModel> getM_Item1() {
        return m_Item1;
    }

    public void setM_Item1(ArrayList<StatusModel> m_Item1) {
        this.m_Item1 = m_Item1;
    }

    public ArrayList<Clientinformation> getM_Item2() {
        return m_Item2;
    }

    public void setM_Item2(ArrayList<Clientinformation> m_Item2) {
        this.m_Item2 = m_Item2;
    }

    public ArrayList<Clientproductname> getM_Item3() {
        return m_Item3;
    }

    public void setM_Item3(ArrayList<Clientproductname> m_Item3) {
        this.m_Item3 = m_Item3;
    }

    public ArrayList<Clientcurrency> getM_Item4() {
        return m_Item4;
    }

    public void setM_Item4(ArrayList<Clientcurrency> m_Item4) {
        this.m_Item4 = m_Item4;
    }
}
