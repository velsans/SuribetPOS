package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/15/2018.
 */

public class DGBetPrintDetailsCount {
    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }


    public String getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(String multiplier) {
        Multiplier = multiplier;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getSlipTime() {
        return SlipTime;
    }

    public void setSlipTime(String slipTime) {
        SlipTime = slipTime;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public Double[] getStake() {
        return Stake;
    }

    public void setStake(Double[] stake) {
        Stake = stake;
    }

    public Double[] getWinningAmount() {
        return WinningAmount;
    }

    public void setWinningAmount(Double[] winningAmount) {
        WinningAmount = winningAmount;
    }

    public String getLocationDescription() {
        return LocationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        LocationDescription = locationDescription;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getTillCode() {
        return TillCode;
    }

    public void setTillCode(String tillCode) {
        TillCode = tillCode;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getContactAddress() {
        return ContactAddress;
    }

    public void setContactAddress(String contactAddress) {
        ContactAddress = contactAddress;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        TelephoneNumber = telephoneNumber;
    }

    public String getSlipID() {
        return SlipID;
    }

    public void setSlipID(String slipID) {
        SlipID = slipID;
    }

    public String getPrintingTime() {
        return PrintingTime;
    }

    public void setPrintingTime(String printingTime) {
        PrintingTime = printingTime;
    }

    public String getLocalCurrencyCode() {
        return LocalCurrencyCode;
    }

    public void setLocalCurrencyCode(String localCurrencyCode) {
        LocalCurrencyCode = localCurrencyCode;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDrawID() {
        return DrawID;
    }

    public void setDrawID(String drawID) {
        DrawID = drawID;
    }

    public String getDrawDate() {
        return DrawDate;
    }

    public void setDrawDate(String drawDate) {
        DrawDate = drawDate;
    }

    public String getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(String currencyID) {
        CurrencyID = currencyID;
    }

    public String getDrawTime() {
        return DrawTime;
    }

    public void setDrawTime(String drawTime) {
        DrawTime = drawTime;
    }

    public String getErrormessage() {
        return Errormessage;
    }

    public void setErrormessage(String errormessage) {
        Errormessage = errormessage;
    }

    public String getSlipStatus() {
        return SlipStatus;
    }

    public void setSlipStatus(String slipStatus) {
        SlipStatus = slipStatus;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getTransactionDateTime() {
        return TransactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        TransactionDateTime = transactionDateTime;
    }

    public String getIsValid() {
        return IsValid;
    }

    public void setIsValid(String isValid) {
        IsValid = isValid;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public double getStakeInLocalCurrency() {
        return StakeInLocalCurrency;
    }

    public void setStakeInLocalCurrency(double stakeInLocalCurrency) {
        StakeInLocalCurrency = stakeInLocalCurrency;
    }


    private String Barcode, Multiplier, CurrencyCode, SlipTime, ExpiryDate, LocationDescription, CountryName,
            TillCode, LocationName, ContactAddress, TelephoneNumber, SlipID, PrintingTime, LocalCurrencyCode, FullName, DrawID,
            DrawDate, CurrencyID, DrawTime, Errormessage, SlipStatus, UserName, TransactionDateTime, IsValid;
    public int StatusId;
    public double StakeInLocalCurrency;
    public String[] BetNumber;
    public Double[] Stake, WinningAmount;

    public String[] getBetNumber() {
        return BetNumber;
    }

    public void setBetNumber(String[] betNumber) {
        BetNumber = betNumber;
    }
}
