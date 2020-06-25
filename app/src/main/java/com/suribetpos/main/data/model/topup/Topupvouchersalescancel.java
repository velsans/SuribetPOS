package com.suribetpos.main.data.model.topup;


import com.suribetpos.main.data.model.common.Printcancelsalevouchers;

import java.util.ArrayList;

public class Topupvouchersalescancel {
    int TillId;
    int UserId;
    String MacAddress;
    String ErrorMessage;
    String VoucherBarcode;

    public String getVoucherBarcode() {
        return VoucherBarcode;
    }

    public void setVoucherBarcode(String voucherBarcode) {
        VoucherBarcode = voucherBarcode;
    }


    public ArrayList<CancelSaleVoucher> getCancelSaleVoucher() {
        return CancelSaleVoucher;
    }

    public void setCancelSaleVoucher(ArrayList<CancelSaleVoucher> cancelSaleVoucher) {
        CancelSaleVoucher = cancelSaleVoucher;
    }

    ArrayList<CancelSaleVoucher> CancelSaleVoucher;
    ArrayList<Printcancelsalevouchers> printCancelSaleVouchers;

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }


    public void setPrintCancelSaleVouchers(
            ArrayList<Printcancelsalevouchers> printCancelSaleVouchers) {
        this.printCancelSaleVouchers = printCancelSaleVouchers;
    }

    public int getTillId() {
        return TillId;
    }

    public int getUserId() {
        return UserId;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }


    public ArrayList<Printcancelsalevouchers> getPrintCancelSaleVouchers() {
        return printCancelSaleVouchers;
    }
}
