package com.suribetpos.main.model.dailygame;

public class DGdrawlist {
    private int ID;
    private int DrawId;
    private int DrawPickNumber;
    private int DrawNumber;
    private int BetNumberID;
    private Double Multiplier;
    private Double WinningAmt;

    public Double getWinningAmt() {
        return WinningAmt;
    }

    public void setWinningAmt(Double winningAmt) {
        WinningAmt = winningAmt;
    }


    public Double getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(Double multiplier) {
        Multiplier = multiplier;
    }

    public Double getStake() {
        return Stake;
    }

    public void setStake(Double stake) {
        Stake = stake;
    }

    private Double Stake;

    public void setBetNumberID(int betNumberID) {
        BetNumberID = betNumberID;
    }

    public int getBetNumberID() {
        return BetNumberID;
    }

    public DGdrawlist(int iD, int drawId, int drawPickNumber, int drawNumber, Double stake, int betNumberId, Double multiplier, Double winningAmt) {
        ID = iD;
        DrawId = drawId;
        DrawPickNumber = drawPickNumber;
        DrawNumber = drawNumber;
        Stake = stake;
        BetNumberID = betNumberId;
        Multiplier = multiplier;
        WinningAmt = winningAmt;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public void setDrawId(int drawId) {
        DrawId = drawId;
    }

    public void setDrawPickNumber(int drawPickNumber) {
        DrawPickNumber = drawPickNumber;
    }

    public void setDrawNumber(int drawNumber) {
        DrawNumber = drawNumber;
    }

    public int getID() {
        return ID;
    }

    public int getDrawId() {
        return DrawId;
    }

    public int getDrawPickNumber() {
        return DrawPickNumber;
    }

    public int getDrawNumber() {
        return DrawNumber;
    }

}
