package com.suribetpos.main.data.model.etopup;

public class TodaysTransectionModel {
    String AccountID, VoucherID, Barcode, VoucherTime, VoucherStatus, Cancel, receipt,CustomerCare,SiteUrl;
    double Amount;
    int VoucherStatusID;

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public String getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(String voucherID) {
        VoucherID = voucherID;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getVoucherTime() {
        return VoucherTime;
    }

    public void setVoucherTime(String voucherTime) {
        VoucherTime = voucherTime;
    }

    public String getVoucherStatus() {
        return VoucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        VoucherStatus = voucherStatus;
    }

    public String getCancel() {
        return Cancel;
    }

    public void setCancel(String cancel) {
        Cancel = cancel;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getCustomerCare() {
        return CustomerCare;
    }

    public void setCustomerCare(String customerCare) {
        CustomerCare = customerCare;
    }

    public String getSiteUrl() {
        return SiteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        SiteUrl = siteUrl;
    }

    public int getVoucherStatusID() {
        return VoucherStatusID;
    }

    public void setVoucherStatusID(int voucherStatusID) {
        VoucherStatusID = voucherStatusID;
    }
}
