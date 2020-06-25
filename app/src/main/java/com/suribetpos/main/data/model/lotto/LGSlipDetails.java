package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 1/29/2018.
 */

public class LGSlipDetails {
    String BarCode, LocationName, TillName, SlipTime, LocalCurrencyCode, CashierName, Status, Amount, Draw;
    int TillID, ID, BetNumber1, BetNumber2, BetNumber3, BetNumber4, BetNumber5, BetNumber6, DrawID, CurrencyID, Paidstatus;
    double Total;

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
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

    public String getLocalCurrencyCode() {
        return LocalCurrencyCode;
    }

    public void setLocalCurrencyCode(String localCurrencyCode) {
        LocalCurrencyCode = localCurrencyCode;
    }

    public String getCashierName() {
        return CashierName;
    }

    public void setCashierName(String cashierName) {
        CashierName = cashierName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDraw() {
        return Draw;
    }

    public void setDraw(String draw) {
        Draw = draw;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBetNumber1() {
        return BetNumber1;
    }

    public void setBetNumber1(int betNumber1) {
        BetNumber1 = betNumber1;
    }

    public int getBetNumber2() {
        return BetNumber2;
    }

    public void setBetNumber2(int betNumber2) {
        BetNumber2 = betNumber2;
    }

    public int getBetNumber3() {
        return BetNumber3;
    }

    public void setBetNumber3(int betNumber3) {
        BetNumber3 = betNumber3;
    }

    public int getBetNumber4() {
        return BetNumber4;
    }

    public void setBetNumber4(int betNumber4) {
        BetNumber4 = betNumber4;
    }

    public int getBetNumber5() {
        return BetNumber5;
    }

    public void setBetNumber5(int betNumber5) {
        BetNumber5 = betNumber5;
    }

    public int getBetNumber6() {
        return BetNumber6;
    }

    public void setBetNumber6(int betNumber6) {
        BetNumber6 = betNumber6;
    }

    public int getDrawID() {
        return DrawID;
    }

    public void setDrawID(int drawID) {
        DrawID = drawID;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public int getPaidstatus() {
        return Paidstatus;
    }

    public void setPaidstatus(int paidstatus) {
        Paidstatus = paidstatus;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
}
