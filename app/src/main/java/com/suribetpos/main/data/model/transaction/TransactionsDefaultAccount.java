package com.suribetpos.main.model.transaction;

/**
 * Created by DEV 27 on 22/06/2017.
 */

public class TransactionsDefaultAccount {
    public int getAccountTypeID() {
        return AccountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        AccountTypeID = accountTypeID;
    }

    public String getAccountTypeCode() {
        return AccountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        AccountTypeCode = accountTypeCode;
    }

    public String getAccounttypeDescription() {
        return AccounttypeDescription;
    }

    public void setAccounttypeDescription(String accounttypeDescription) {
        AccounttypeDescription = accounttypeDescription;
    }

    int AccountTypeID;
    String AccountTypeCode,AccounttypeDescription;
}
