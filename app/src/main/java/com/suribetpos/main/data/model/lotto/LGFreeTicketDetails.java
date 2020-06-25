package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 1/22/2018.
 */

public class LGFreeTicketDetails {
    String Barcode, SlipTime, JackpotWinStatus, FreeLetter, LocationDescription, JackpotCurrency, Freeticketstatus, Match4Status, Match5Status, PrintingTime, ErrorMessage;
    boolean IsValid;
    int NumberofTickets, Paidstatus, LocalCurrencyID, TillID;
    double WinningAmount, Total;

    public String getBarcode() {
        return Barcode;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
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

    public String getJackpotWinStatus() {
        return JackpotWinStatus;
    }

    public void setJackpotWinStatus(String jackpotWinStatus) {
        JackpotWinStatus = jackpotWinStatus;
    }

    public String getFreeLetter() {
        return FreeLetter;
    }

    public void setFreeLetter(String freeLetter) {
        FreeLetter = freeLetter;
    }

    public String getLocationDescription() {
        return LocationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        LocationDescription = locationDescription;
    }

    public String getJackpotCurrency() {
        return JackpotCurrency;
    }

    public void setJackpotCurrency(String jackpotCurrency) {
        JackpotCurrency = jackpotCurrency;
    }

    public String getFreeticketstatus() {
        return Freeticketstatus;
    }

    public void setFreeticketstatus(String freeticketstatus) {
        Freeticketstatus = freeticketstatus;
    }

    public String getMatch4Status() {
        return Match4Status;
    }

    public void setMatch4Status(String match4Status) {
        Match4Status = match4Status;
    }

    public String getMatch5Status() {
        return Match5Status;
    }

    public void setMatch5Status(String match5Status) {
        Match5Status = match5Status;
    }

    public String getPrintingTime() {
        return PrintingTime;
    }

    public void setPrintingTime(String printingTime) {
        PrintingTime = printingTime;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    public int getNumberofTickets() {
        return NumberofTickets;
    }

    public void setNumberofTickets(int numberofTickets) {
        NumberofTickets = numberofTickets;
    }

    public int getPaidstatus() {
        return Paidstatus;
    }

    public void setPaidstatus(int paidstatus) {
        Paidstatus = paidstatus;
    }

    public int getLocalCurrencyID() {
        return LocalCurrencyID;
    }

    public void setLocalCurrencyID(int localCurrencyID) {
        LocalCurrencyID = localCurrencyID;
    }

    public double getWinningAmount() {
        return WinningAmount;
    }

    public void setWinningAmount(double winningAmount) {
        WinningAmount = winningAmount;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
}
