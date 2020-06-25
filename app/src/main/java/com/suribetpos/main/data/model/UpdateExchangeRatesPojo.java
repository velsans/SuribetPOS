package com.suribetpos.main.model;

/**
 * Created by DEV 27 on 01/07/2017.
 */

public class UpdateExchangeRatesPojo {

    public double getCurrencyRate() {
        return CurrencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        CurrencyRate = currencyRate;
    }

    public int getIsReferingUSD() {
        return IsReferingUSD;
    }

    public void setIsReferingUSD(int isReferingUSD) {
        IsReferingUSD = isReferingUSD;
    }

    public int getIsReferingForeignCurrency() {
        return IsReferingForeignCurrency;
    }

    public void setIsReferingForeignCurrency(int isReferingForeignCurrency) {
        IsReferingForeignCurrency = isReferingForeignCurrency;
    }

    public int getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    double CurrencyRate;
    int IsReferingUSD,
            IsReferingForeignCurrency,
            CurrencyId,
            UserID,
            TillID;


}
