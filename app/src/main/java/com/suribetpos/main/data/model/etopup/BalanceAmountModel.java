package com.suribetpos.main.data.model.etopup;

public class BalanceAmountModel {
    double TotalTransactionAmount;
    String GamingDate,CollectedDate;

    public String getGamingDate() {
        return GamingDate;
    }

    public void setGamingDate(String gamingDate) {
        GamingDate = gamingDate;
    }

    public double getTotalTransactionAmount() {
        return TotalTransactionAmount;
    }

    public void setTotalTransactionAmount(double totalTransactionAmount) {
        TotalTransactionAmount = totalTransactionAmount;
    }

    public String getCollectedDate() {
        return CollectedDate;
    }

    public void setCollectedDate(String collectedDate) {
        CollectedDate = collectedDate;
    }
}
