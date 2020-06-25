package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 2/2/2018.
 */

public class DGPayoutDetails {
    double Multiplier, WinningStackAmount, WinningInSRD;
    int BetNumber, CurrencyID, LocalCurrencyId, PaidStatus, SlipStatus, DrawID, ResultNumber,TillId;
    String StackAmount, CurrencyCode, LocalCurrencyCode, LocationName, TillName, SlipTime, DrawDate, DrawTypeName, PaidLocation,Barcode;

    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public double getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(double multiplier) {
        Multiplier = multiplier;
    }

    public double getWinningStackAmount() {
        return WinningStackAmount;
    }

    public void setWinningStackAmount(double winningStackAmount) {
        WinningStackAmount = winningStackAmount;
    }

    public double getWinningInSRD() {
        return WinningInSRD;
    }

    public void setWinningInSRD(double winningInSRD) {
        WinningInSRD = winningInSRD;
    }

    public int getBetNumber() {
        return BetNumber;
    }

    public void setBetNumber(int betNumber) {
        BetNumber = betNumber;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public int getLocalCurrencyId() {
        return LocalCurrencyId;
    }

    public void setLocalCurrencyId(int localCurrencyId) {
        LocalCurrencyId = localCurrencyId;
    }

    public int getPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(int paidStatus) {
        PaidStatus = paidStatus;
    }

    public int getSlipStatus() {
        return SlipStatus;
    }

    public void setSlipStatus(int slipStatus) {
        SlipStatus = slipStatus;
    }

    public int getDrawID() {
        return DrawID;
    }

    public void setDrawID(int drawID) {
        DrawID = drawID;
    }

    public int getResultNumber() {
        return ResultNumber;
    }

    public void setResultNumber(int resultNumber) {
        ResultNumber = resultNumber;
    }

    public String getStackAmount() {
        return StackAmount;
    }

    public void setStackAmount(String stackAmount) {
        StackAmount = stackAmount;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getLocalCurrencyCode() {
        return LocalCurrencyCode;
    }

    public void setLocalCurrencyCode(String localCurrencyCode) {
        LocalCurrencyCode = localCurrencyCode;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getTillName() {
        return TillName;
    }

    public void setTillName(String tillName) {
        TillName = tillName;
    }

    public String getSlipTime() {
        return SlipTime;
    }

    public void setSlipTime(String slipTime) {
        SlipTime = slipTime;
    }

    public String getDrawDate() {
        return DrawDate;
    }

    public void setDrawDate(String drawDate) {
        DrawDate = drawDate;
    }

    public String getDrawTypeName() {
        return DrawTypeName;
    }

    public void setDrawTypeName(String drawTypeName) {
        DrawTypeName = drawTypeName;
    }

    public String getPaidLocation() {
        return PaidLocation;
    }

    public void setPaidLocation(String paidLocation) {
        PaidLocation = paidLocation;
    }
}
