package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 2/7/2018.
 */

public class DGCommPlacedDetails {
    int SlipID,BetNumber,DrawID;
    String Multiplier,LocalCurrency,SlipTime,Barcode;
    double WinningInSRD;

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

    public String getLocalCurrency() {
        return LocalCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        LocalCurrency = localCurrency;
    }

    public String getSlipTime() {
        return SlipTime;
    }

    public void setSlipTime(String slipTime) {
        SlipTime = slipTime;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public double getWinningInSRD() {
        return WinningInSRD;
    }

    public void setWinningInSRD(double winningInSRD) {
        WinningInSRD = winningInSRD;
    }
}
