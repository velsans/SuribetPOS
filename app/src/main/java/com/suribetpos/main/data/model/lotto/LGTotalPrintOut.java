package com.suribetpos.main.model.lotto;

import java.util.List;

/**
 * Created by DEV 27 on 27/12/2016.
 */

public class LGTotalPrintOut {
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumberofBets() {
        return NumberofBets;
    }

    public void setNumberofBets(String numberofBets) {
        NumberofBets = numberofBets;
    }

    public String getSlipID() {
        return SlipID;
    }

    public void setSlipID(String slipID) {
        SlipID = slipID;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getPrintingTime() {
        return PrintingTime;
    }

    public void setPrintingTime(String printingTime) {
        PrintingTime = printingTime;
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

    public String getJackPot() {
        return JackPot;
    }

    public void setJackPot(String jackPot) {
        JackPot = jackPot;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
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

    public String getFreeLetter() {
        return FreeLetter;
    }

    public void setFreeLetter(String freeLetter) {
        FreeLetter = freeLetter;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getDrawTime() {
        return DrawTime;
    }

    public void setDrawTime(String drawTime) {
        DrawTime = drawTime;
    }

    public String getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        TicketPrice = ticketPrice;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getTillCode() {
        return TillCode;
    }

    public void setTillCode(String tillCode) {
        TillCode = tillCode;
    }

    public String getPromoText() {
        return PromoText;
    }

    public void setPromoText(String promoText) {
        PromoText = promoText;
    }

    public String getDraw() {
        return Draw;
    }

    public void setDraw(String draw) {
        Draw = draw;
    }


    String ID,NumberofBets,SlipID,Barcode,PrintingTime,DrawID,DrawDate,JackPot,FullName,
            LocationName,ContactAddress,TelephoneNumber,FreeLetter,CurrencyCode,DrawTime,TicketPrice,Amount,TillCode, PromoText,Draw;

    public List<LGBetBallsPrintPojo> getListOFLottobetballsPrintOut() {
        return ListOFLottobetballsPrintOut;
    }

    public void setListOFLottobetballsPrintOut(List<LGBetBallsPrintPojo> listOFLottobetballsPrintOut) {
        ListOFLottobetballsPrintOut = listOFLottobetballsPrintOut;
    }

    List<LGBetBallsPrintPojo> ListOFLottobetballsPrintOut;
}
