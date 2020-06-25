package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 1/24/2018.
 */

public class LGPayoutTransactionAll {

    String WinnerName;
    int TillID;
    int CurrencyID;

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getPaidon() {
        return Paidon;
    }

    public void setPaidon(String paidon) {
        Paidon = paidon;
    }

    public String getPaidby() {
        return Paidby;
    }

    public void setPaidby(String paidby) {
        Paidby = paidby;
    }

    public String getRetailer() {
        return Retailer;
    }

    public void setRetailer(String retailer) {
        Retailer = retailer;
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

    public String getDrawTime() {
        return DrawTime;
    }

    public void setDrawTime(String drawTime) {
        DrawTime = drawTime;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public Double getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        AmountPaid = amountPaid;
    }

    public Double getJackpot() {
        return Jackpot;
    }

    public void setJackpot(Double jackpot) {
        Jackpot = jackpot;
    }

    public Double getMatch5Amount() {
        return Match5Amount;
    }

    public void setMatch5Amount(Double match5Amount) {
        Match5Amount = match5Amount;
    }

    public Double getMatch4Amount() {
        return Match4Amount;
    }

    public void setMatch4Amount(Double match4Amount) {
        Match4Amount = match4Amount;
    }

    public Double getMatch5TotalAmount() {
        return Match5TotalAmount;
    }

    public void setMatch5TotalAmount(Double match5TotalAmount) {
        Match5TotalAmount = match5TotalAmount;
    }

    public Double getMatch4TotalAmount() {
        return Match4TotalAmount;
    }

    public void setMatch4TotalAmount(Double match4TotalAmount) {
        Match4TotalAmount = match4TotalAmount;
    }

    public long getSlipID() {
        return SlipID;
    }

    public void setSlipID(long slipID) {
        SlipID = slipID;
    }

    public int getDrawID() {
        return DrawID;
    }

    public void setDrawID(int drawID) {
        DrawID = drawID;
    }

    public int getMatch5Count() {
        return Match5Count;
    }

    public void setMatch5Count(int match5Count) {
        Match5Count = match5Count;
    }

    public int getMatch4Count() {
        return Match4Count;
    }

    public void setMatch4Count(int match4Count) {
        Match4Count = match4Count;
    }

    public String getWinnerName() {
        return WinnerName;
    }

    public void setWinnerName(String winnerName) {
        WinnerName = winnerName;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    int UserID;


    String Barcode, CurrencyCode, Paidon, Paidby, Retailer, TillName, SlipTime, DrawDate, DrawTime, ErrorMessage;
    Double AmountPaid, Jackpot, Match5Amount, Match4Amount, Match5TotalAmount, Match4TotalAmount;
    long SlipID;
    int DrawID, Match5Count, Match4Count;
}

