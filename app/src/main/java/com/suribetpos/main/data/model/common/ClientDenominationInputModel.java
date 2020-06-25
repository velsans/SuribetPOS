package com.suribetpos.main.data.model.common;

import java.util.ArrayList;

public class ClientDenominationInputModel {
    String m_Item1;
    ArrayList<ClientDenominationModel> m_Item2 = new ArrayList();

    public String getM_Item1() {
        return m_Item1;
    }

    public void setM_Item1(String m_Item1) {
        this.m_Item1 = m_Item1;
    }

    public ArrayList<ClientDenominationModel> getM_Item2() {
        return m_Item2;
    }

    public void setM_Item2(ArrayList<ClientDenominationModel> m_Item2) {
        this.m_Item2 = m_Item2;
    }

}
