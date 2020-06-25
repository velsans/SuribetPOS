package com.suribetpos.main.data.model.topup;

public class Cancelsalevoucherhistorylist {
    String Barcode;
    String TransactionDateTime;
    String FirstName;
    String CurrencyCode;
    String CurrencyAmount;
    String Comments;
    String VoucherID, TillID;

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getTillID() {
        return TillID;
    }

    public void setTillID(String tillID) {
        TillID = tillID;
    }

    public String getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(String voucherID) {
        VoucherID = voucherID;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        TransactionDateTime = transactionDateTime;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public void setCurrencyAmount(String currencyAmount) {
        CurrencyAmount = currencyAmount;
    }

    public String getBarcode() {
        return Barcode;
    }

    public String getTransactionDateTime() {
        return TransactionDateTime;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public String getCurrencyAmount() {
        return CurrencyAmount;
    }
}
