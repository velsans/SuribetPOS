package com.suribetpos.main.model.balance;

/**
 * Created by DEV 27 on 28/06/2017.
 */

public class Balance_TotalDenomPojo {
    public int getDenominationTypeID() {
        return DenominationTypeID;
    }

    public void setDenominationTypeID(int denominationTypeID) {
        DenominationTypeID = denominationTypeID;
    }

    public String getDenominationType() {
        return DenominationType;
    }

    public void setDenominationType(String denominationType) {
        DenominationType = denominationType;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getUSDTotal() {
        return USDTotal;
    }

    public void setUSDTotal(double USDTotal) {
        this.USDTotal = USDTotal;
    }

    int DenominationTypeID;
    String DenominationType;
    double Total,USDTotal;
}
