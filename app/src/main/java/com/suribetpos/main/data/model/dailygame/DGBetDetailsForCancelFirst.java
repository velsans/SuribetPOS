package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/5/2018.
 */

public class DGBetDetailsForCancelFirst {
    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getSlipTime() {
        return SlipTime;
    }

    public void setSlipTime(String slipTime) {
        SlipTime = slipTime;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getBetNumber() {
        return BetNumber;
    }

    public void setBetNumber(String betNumber) {
        BetNumber = betNumber;
    }

    public String getSlipStatus() {
        return SlipStatus;
    }

    public void setSlipStatus(String slipStatus) {
        SlipStatus = slipStatus;
    }

    public double getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(double multiplier) {
        Multiplier = multiplier;
    }

    public double getTotalStake() {
        return TotalStake;
    }

    public void setTotalStake(double totalStake) {
        TotalStake = totalStake;
    }

    public double getWinningAmount() {
        return WinningAmount;
    }

    public void setWinningAmount(double winningAmount) {
        WinningAmount = winningAmount;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    public String Barcode,SlipTime,CurrencyCode,BetNumber,SlipStatus;
    public double Multiplier,TotalStake,WinningAmount;
    public boolean IsValid;

}
