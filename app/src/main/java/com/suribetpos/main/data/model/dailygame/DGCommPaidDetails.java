package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 2/7/2018.
 */

public class DGCommPaidDetails {
    int SlipID, BetNumber, DrawID;
    String Multiplier, StakeInLocalCurrency, PayoutTime, Barcode;
    public double WinningAmount;

    public int getSlipID() {
        return SlipID;
    }

    public void setSlipID(int slipID) {
        SlipID = slipID;
    }

    public int getBetNumber() {
        return BetNumber;
    }

    public void setBetNumber(int betNumber) {
        BetNumber = betNumber;
    }

    public int getDrawID() {
        return DrawID;
    }

    public void setDrawID(int drawID) {
        DrawID = drawID;
    }

    public String getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(String multiplier) {
        Multiplier = multiplier;
    }

    public String getStakeInLocalCurrency() {
        return StakeInLocalCurrency;
    }

    public void setStakeInLocalCurrency(String stakeInLocalCurrency) {
        StakeInLocalCurrency = stakeInLocalCurrency;
    }

    public String getPayoutTime() {
        return PayoutTime;
    }

    public void setPayoutTime(String payoutTime) {
        PayoutTime = payoutTime;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public double getWinningAmount() {
        return WinningAmount;
    }

    public void setWinningAmount(double winningAmount) {
        WinningAmount = winningAmount;
    }
}
