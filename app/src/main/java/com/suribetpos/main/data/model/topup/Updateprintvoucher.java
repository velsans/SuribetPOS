package com.suribetpos.main.data.model.topup;

import com.suribetpos.main.data.model.common.Addupdateprintvouchersdetails;

import java.util.List;

public class Updateprintvoucher {
  private int UserId;
  private int TillId;
  private List<Salesvouchers> SalesVouchers;
  private Double TotalPaid;
  private Double TotalReturn;
  private String ErrorMessage;
  private String MacAddress;
  private List<Addupdateprintvouchersdetails> AddUpdatePrintVouchersDetails;
  
  
	public void setUserId(int userId) {
		UserId = userId;
	}
	public void setTillId(int tillId) {
		TillId = tillId;
	}
	public void setSalesVouchers(List<Salesvouchers> salesVouchers) {
		SalesVouchers = salesVouchers;
	}
	public void setTotalPaid(Double totalPaid) {
		TotalPaid = totalPaid;
	}
	public void setTotalReturn(Double totalReturn) {
		TotalReturn = totalReturn;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}
	
	
	public void setaddupdateprintvouchersdetails(List<Addupdateprintvouchersdetails> addUpdatePrintVouchersDetails) {
		AddUpdatePrintVouchersDetails = addUpdatePrintVouchersDetails;
	}
	
	
	
	
	public int getUserId() {
		return UserId;
	}
	public int getTillId() {
		return TillId;
	}
	public List<Salesvouchers> getSalesVouchers() {
		return SalesVouchers;
	}
	public Double getTotalPaid() {
		return TotalPaid;
	}
	public Double getTotalReturn() {
		return TotalReturn;
	}
  
	public String getErrorMessage() {
		return ErrorMessage;
	}
	
	public String getMacAddress() {
		return MacAddress;
	}
	
	public List<Addupdateprintvouchersdetails> getaddupdateprintvouchersdetails() {
		return AddUpdatePrintVouchersDetails;
	}
}
