package com.suribetpos.main.data.model.topup;

/**
 * Created by DEV 27 on 03/02/2017.
 */

public class CancelSalesVoucherverifylimits {
    int TillId;
    int UserId;
    String MacAddress;

    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getVoucherBarcode() {
        return VoucherBarcode;
    }

    public void setVoucherBarcode(String voucherBarcode) {
        VoucherBarcode = voucherBarcode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    String ErrorMessage;
    String VoucherBarcode;
}
