package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/6/2018.
 */

public class DGAccountTransaction {
    public int getCashDeskJournal() {
        return CashDeskJournal;
    }

    public void setCashDeskJournal(int cashDeskJournal) {
        CashDeskJournal = cashDeskJournal;
    }

    public int getAccountTypeId() {
        return AccountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        AccountTypeId = accountTypeId;
    }

    public int getDenominationTypeId() {
        return DenominationTypeId;
    }

    public void setDenominationTypeId(int denominationTypeId) {
        DenominationTypeId = denominationTypeId;
    }

    public int getTransactionTypeId() {
        return TransactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        TransactionTypeId = transactionTypeId;
    }

    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }

    public int getContra() {
        return Contra;
    }

    public void setContra(int contra) {
        Contra = contra;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public double getPaid() {
        return Paid;
    }

    public void setPaid(double paid) {
        Paid = paid;
    }

    public double getReceived() {
        return Received;
    }

    public void setReceived(double received) {
        Received = received;
    }

    public double getDenominationTypeTotal() {
        return DenominationTypeTotal;
    }

    public void setDenominationTypeTotal(double denominationTypeTotal) {
        DenominationTypeTotal = denominationTypeTotal;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getAmountLocal() {
        return AmountLocal;
    }

    public void setAmountLocal(double amountLocal) {
        AmountLocal = amountLocal;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getGamingDate() {
        return GamingDate;
    }

    public void setGamingDate(String gamingDate) {
        GamingDate = gamingDate;
    }

    public String getTransactionDateTime() {
        return TransactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        TransactionDateTime = transactionDateTime;
    }

    public int CashDeskJournal,AccountTypeId, DenominationTypeId, TransactionTypeId, TillId, UserId, CurrencyId, Contra, CustomerId;
    public double Paid, Received, DenominationTypeTotal, Amount, AmountLocal;
    public String Comments, GamingDate, TransactionDateTime;


}
