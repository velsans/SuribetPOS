package com.suribetpos.main.data.model.topup;

import com.suribetpos.main.ui.topup.model.CancelledVoucherModel;

import java.util.ArrayList;

public class Cancelsalevoucherhistory
{
    int TillID ;
	public void setTillID(int tillID) {
		TillID = tillID;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public int getTillID() {
		return TillID;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	String ErrorMessage;

	public String getMacAddress() {
		return MacAddress;
	}

	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}

	String MacAddress ;
    ArrayList<CancelledVoucherModel> objListCancelSalesVouchers ;

	public ArrayList<CancelledVoucherModel> getobjListCancelSalesVouchers() {
		return objListCancelSalesVouchers;
	}

	public void setObjListCancelSalesVouchers(ArrayList<CancelledVoucherModel> objListCancelSalesVouchers) {
		this.objListCancelSalesVouchers = objListCancelSalesVouchers;
	}
}
