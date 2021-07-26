package com.suribetpos.main.data.model.etopup;

import androidx.databinding.BaseObservable;

public class BalanceAmountModel extends BaseObservable {
    double TotalTransactionAmount;
    String GamingDate, CollectedDate;
    double LastCollectedAmt,OpeningBal,ETopupTranamt,ETopupCommPercent,ETopupTrCommamt,ECashoutTranamt,ECashoutCommPercent,
    ECashoutTrCommamt ,ClosingBalance,ECashoutNonComTransactionAmount,TopupTranAmt,TopupSaleCommAmt ,LocationtopupSalesCommissionPercent;



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

    public double getLastCollectedAmt() {
        return LastCollectedAmt;
    }

    public void setLastCollectedAmt(double lastCollectedAmt) {
        LastCollectedAmt = lastCollectedAmt;
    }

    public double getOpeningBal() {
        return OpeningBal;
    }

    public void setOpeningBal(double openingBal) {
        OpeningBal = openingBal;
    }

    public double getETopupTranamt() {
        return ETopupTranamt;
    }

    public void setETopupTranamt(double ETopupTranamt) {
        this.ETopupTranamt = ETopupTranamt;
    }

    public double getETopupCommPercent() {
        return ETopupCommPercent;
    }

    public void setETopupCommPercent(double ETopupCommPercent) {
        this.ETopupCommPercent = ETopupCommPercent;
    }

    public double getETopupTrCommamt() {
        return ETopupTrCommamt;
    }

    public void setETopupTrCommamt(double ETopupTrCommamt) {
        this.ETopupTrCommamt = ETopupTrCommamt;
    }

    public double getECashoutTranamt() {
        return ECashoutTranamt;
    }

    public void setECashoutTranamt(double ECashoutTranamt) {
        this.ECashoutTranamt = ECashoutTranamt;
    }

    public double getECashoutCommPercent() {
        return ECashoutCommPercent;
    }

    public void setECashoutCommPercent(double ECashoutCommPercent) {
        this.ECashoutCommPercent = ECashoutCommPercent;
    }

    public double getECashoutTrCommamt() {
        return ECashoutTrCommamt;
    }

    public void setECashoutTrCommamt(double ECashoutTrCommamt) {
        this.ECashoutTrCommamt = ECashoutTrCommamt;
    }

    public double getClosingBalance() {
        return ClosingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        ClosingBalance = closingBalance;
    }

    public double getECashoutNonComTransactionAmount() {
        return ECashoutNonComTransactionAmount;
    }

    public void setECashoutNonComTransactionAmount(double ECashoutNonComTransactionAmount) {
        this.ECashoutNonComTransactionAmount = ECashoutNonComTransactionAmount;
    }

    public double getTopupTranAmt() {
        return TopupTranAmt;
    }

    public void setTopupTranAmt(double topupTranAmt) {
        TopupTranAmt = topupTranAmt;
    }

    public double getTopupSaleCommAmt() {
        return TopupSaleCommAmt;
    }

    public void setTopupSaleCommAmt(double topupSaleCommAmt) {
        TopupSaleCommAmt = topupSaleCommAmt;
    }

    public double getLocationtopupSalesCommissionPercent() {
        return LocationtopupSalesCommissionPercent;
    }

    public void setLocationtopupSalesCommissionPercent(double locationtopupSalesCommissionPercent) {
        LocationtopupSalesCommissionPercent = locationtopupSalesCommissionPercent;
    }
}
