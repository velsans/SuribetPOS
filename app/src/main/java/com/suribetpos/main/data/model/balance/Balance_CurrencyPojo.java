package com.suribetpos.main.model.balance;

/**
 * Created by DEV 27 on 28/06/2017.
 */

public class Balance_CurrencyPojo {
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

    public String getDenominationtypeCode() {
        return DenominationtypeCode;
    }

    public void setDenominationtypeCode(String denominationtypeCode) {
        DenominationtypeCode = denominationtypeCode;
    }

    int DenominationTypeID;
    String DenominationType,DenominationtypeCode;
}