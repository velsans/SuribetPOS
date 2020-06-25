package com.suribetpos.main.model.lotto;

import java.util.List;

/**
 * Created by DEV 27 on 22/12/2016.
 */

public class LGupdateprintvoucher {
    private Double TotalAmount;
    private int UserId;
    private int TillId;
    private int NumberofBets;
    private int CurrencyId;
    private int IsFreeTicket;
    String ErrorMessage, MacAddress, FreeTicketBarcode;
    public List<LGAddPrintCount> LottoAddPrintCount;
    private List<LGaddupdateprintvouchersdetails> LottoPaymentPrintVouchersDetails;


    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getFreeTicketBarcode() {
        return FreeTicketBarcode;
    }

    public void setFreeTicketBarcode(String freeTicketBarcode) {
        FreeTicketBarcode = freeTicketBarcode;
    }

    public List<LGaddupdateprintvouchersdetails> getLottoPaymentPrintVouchersDetails() {
        return LottoPaymentPrintVouchersDetails;
    }

    public void setLottoPaymentPrintVouchersDetails(List<LGaddupdateprintvouchersdetails> lottoPaymentPrintVouchersDetails) {
        LottoPaymentPrintVouchersDetails = lottoPaymentPrintVouchersDetails;
    }

    public int getIsFreeTicket() {
        return IsFreeTicket;
    }

    public void setIsFreeTicket(int isFreeTicket) {
        IsFreeTicket = isFreeTicket;
    }

    public int getNumberofBets() {
        return NumberofBets;
    }

    public void setNumberofBets(int numberofBets) {
        NumberofBets = numberofBets;
    }

    public int getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }

    public List<LGDetails> getLottoDetails() {
        return LottoDetails;
    }

    public void setLottoDetails(List<LGDetails> lottoDetails) {
        LottoDetails = lottoDetails;
    }

    private List<LGDetails> LottoDetails;


    public List<LGAddPrintCount> getLottoAddPrintCount() {
        return LottoAddPrintCount;
    }

    public void setLottoAddPrintCount(List<LGAddPrintCount> lottoAddPrintCount) {
        LottoAddPrintCount = lottoAddPrintCount;
    }


}
