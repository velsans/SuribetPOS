package com.suribetpos.main.model.transaction;

/**
 * Created by DEV 27 on 23/06/2017.
 */

public class TransactionSave {
    public int getAccountTypeID() {
        return AccountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        AccountTypeID = accountTypeID;
    }

    public int getTransactionTypeID() {
        return TransactionTypeID;
    }

    public void setTransactionTypeID(int transactionTypeID) {
        TransactionTypeID = transactionTypeID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getGamingDateUI() {
        return GamingDateUI;
    }

    public void setGamingDateUI(String gamingDateUI) {
        GamingDateUI = gamingDateUI;
    }

    int AccountTypeID, TransactionTypeID, Amount, CurrencyID, TillID, UserID, CustomerID;
    String Comments, GamingDateUI;
}
