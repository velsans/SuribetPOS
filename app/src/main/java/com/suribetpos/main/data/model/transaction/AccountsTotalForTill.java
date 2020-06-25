package com.suribetpos.main.model.transaction;

/**
 * Created by DEV 27 on 21/06/2017.
 */

public class AccountsTotalForTill {
    public int getAccountTypeID() {
        return AccountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        AccountTypeID = accountTypeID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public double getReceived() {
        return Received;
    }

    public void setReceived(double received) {
        Received = received;
    }

    public double getPaid() {
        return Paid;
    }

    public void setPaid(double paid) {
        Paid = paid;
    }

    public double getNet() {
        return Net;
    }

    public void setNet(double net) {
        Net = net;
    }

    int AccountTypeID;
    String Account; double Received, Paid, Net;

}
