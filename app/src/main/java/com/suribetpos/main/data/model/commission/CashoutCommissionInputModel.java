package com.suribetpos.main.data.model.commission;

import java.util.ArrayList;

public class CashoutCommissionInputModel {
    Object TillId,CollectedDate;
    public ArrayList<CashoutCommissionDetails> Table;
    public ArrayList<CashoutCommissonReportDetails> Table1;

    public ArrayList<CashoutCommissionDetails> getTable() {
        return Table;
    }

    public void setTable(ArrayList<CashoutCommissionDetails> table) {
        Table = table;
    }

    public ArrayList<CashoutCommissonReportDetails> getTable1() {
        return Table1;
    }

    public void setTable1(ArrayList<CashoutCommissonReportDetails> table1) {
        Table1 = table1;
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
}
