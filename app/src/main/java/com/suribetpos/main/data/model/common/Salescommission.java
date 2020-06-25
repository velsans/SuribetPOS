package com.suribetpos.main.data.model.common;

public class Salescommission {
	
	public double getSalesCommission() {
		return SalesCommission;
	}
	public double getTotalSold() {
		return TotalSold;
	}
	public double getLocationSoldCommission() {
		return LocationSoldCommission;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public double getLocationBalance() {
		return LocationBalance;
	}
	public int getCancelCount() {
		return CancelCount;
	}
	public double getCancelAmount() {
		return CancelAmount;
	}
	
	public double SalesCommission;
    public double TotalSold;
    public double LocationSoldCommission;
    public double LocationBalance;
    public int CancelCount;
    public double CancelAmount;
    public String ErrorMessage;
    
}
