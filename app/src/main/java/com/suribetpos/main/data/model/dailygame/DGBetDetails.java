package com.suribetpos.main.model.dailygame;

public class DGBetDetails {
    private String ID;
    private String TillID;
    private String UserID;
    private String BetNumberID;
    private String CurrencyID;
    private String StakeAmount;
    private String DrawID;
    private String Multiplier;

    public String getWinningAmt() {
        return WinningAmt;
    }

    public void setWinningAmt(String winningAmt) {
        WinningAmt = winningAmt;
    }

    private String WinningAmt;


    public DGBetDetails(String iD, String tillId, String userID, String betNumberId, String currencyId, String stakeAmount, String drawID,
                        String multiplier, String winningAmt) {
        ID = iD;
        TillID = tillId;
        UserID = userID;
        BetNumberID = betNumberId;
        CurrencyID = currencyId;
        StakeAmount = stakeAmount;
        DrawID = drawID;
        Multiplier = multiplier;
        WinningAmt = winningAmt;
    }


    public String getID() {
        return ID;
    }

    public String getTillId() {
        return TillID;
    }

    public String getUserID() {
        return UserID;
    }

    public String getBetNumberId() {
        return BetNumberID;
    }

    public String getCurrencyId() {
        return CurrencyID;
    }

    public String getStakeAmount() {
        return StakeAmount;
    }

    public String getDrawID() {
        return DrawID;
    }

    public String getMultiplier() {
        return Multiplier;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public void setTillId(String tillId) {
        TillID = tillId;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setBetNumberId(String betNumberId) {
        BetNumberID = betNumberId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyID = currencyId;
    }

    public void setStakeAmount(String stakeAmount) {
        StakeAmount = stakeAmount;
    }

    public void setDrawID(String drawID) {
        DrawID = drawID;
    }

    public void setMultiplier(String multiplier) {
        Multiplier = multiplier;
    }


}
