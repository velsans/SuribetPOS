package com.suribetpos.main.data.model.etopup;

import com.suribetpos.main.model.etopup.CancelOutpuyModel;

import java.util.ArrayList;

public class CancelInputModel {
    int UserId, TillId;
    String MacAddress;

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

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    ArrayList<com.suribetpos.main.model.etopup.CancelSaleVoucherModel> CancelSaleVoucher=new ArrayList<>();

    public ArrayList<com.suribetpos.main.model.etopup.CancelSaleVoucherModel> getCancelSaleVoucher() {
        return CancelSaleVoucher;
    }

    public void setCancelSaleVoucher(ArrayList<com.suribetpos.main.model.etopup.CancelSaleVoucherModel> cancelSaleVoucher) {
        CancelSaleVoucher = cancelSaleVoucher;
    }

    ArrayList<CancelOutpuyModel> status=new ArrayList<>();

    public ArrayList<CancelOutpuyModel> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<CancelOutpuyModel> status) {
        this.status = status;
    }
}
