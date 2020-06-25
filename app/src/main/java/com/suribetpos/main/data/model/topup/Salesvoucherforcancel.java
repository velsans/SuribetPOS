package com.suribetpos.main.data.model.topup;

public class Salesvoucherforcancel
{
	 String VoucherBarcode ;
     int UserId ;
     int TillId ;
     String MacAddress ;
     String ErrorMessage ;
     
     public void setVoucherBarcode(String voucherBarcode) {
		VoucherBarcode = voucherBarcode;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public void setTillId(int tillId) {
		TillId = tillId;
	}
	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public void setVoucherID(int voucherID) {
		VoucherID = voucherID;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public void setCurrencyAmount(String currencyAmount) {
		CurrencyAmount = currencyAmount;
	}
	public String getVoucherBarcode() {
		return VoucherBarcode;
	}
	public int getUserId() {
		return UserId;
	}
	public int getTillId() {
		return TillId;
	}
	public String getMacAddress() {
		return MacAddress;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public int getVoucherID() {
		return VoucherID;
	}
	public String getBarcode() {
		return Barcode;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public String getAmount() {
		return Amount;
	}
	public String getCurrencyAmount() {
		return CurrencyAmount;
	}
	int VoucherID ;
     String Barcode ;
     String CurrencyCode ;
     String Amount ;
      String CurrencyAmount ;
}
