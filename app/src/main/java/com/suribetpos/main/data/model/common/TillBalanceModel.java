package com.suribetpos.main.data.model.common;
public class TillBalanceModel
{
	 public String TillId;
     public String MacAddress;
     public String BalanceAmount;
     public String GamingDate;
     public String IsUpdateAvailable;
     public String ErrorMessage;
     public String StatusId;
     public String UserId;
     
     
     public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserId() {
		return UserId;
	}
	public String getTillId() {
		return TillId;
	}
	public void setTillId(String tillId) {
		TillId = tillId;
	}
	public String getMacAddress() {
		return MacAddress;
	}
	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}
	public String getBalanceAmount() {
		return BalanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		BalanceAmount = balanceAmount;
	}
	public String getGamingDate() {
		return GamingDate;
	}
	public void setGamingDate(String gamingDate) {
		GamingDate = gamingDate;
	}
	public String getIsUpdateAvailable() {
		return IsUpdateAvailable;
	}
	public void setIsUpdateAvailable(String isUpdateAvailable) {
		IsUpdateAvailable = isUpdateAvailable;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public String getStatusId() {
		return StatusId;
	}
	public void setStatusId(String statusId) {
		StatusId = statusId;
	}
	
	
}
