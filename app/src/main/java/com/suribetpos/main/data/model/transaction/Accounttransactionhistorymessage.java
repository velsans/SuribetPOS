package com.suribetpos.main.model.transaction;

import java.util.ArrayList;

public class Accounttransactionhistorymessage {
	private String ErrorMessage;
	private int StatusId;
	private int TillId;
	private Double TotalUSDPaid;
	private Double TotalUSDReceived;
	private Double TotalCurrencyPaid;
	private Double TotalCurrencyReceived;
	private Double TotalBalanceInUSD;
	private Double TotalBalanceInCurrency;
	private int Count;
	private String MacAddress;
	
	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}
	public String getMacAddress() {
		return MacAddress;
	}
	public void setCount(int count) {
		Count = count;
	}
	public int getCount() {
		return Count;
	}
	public void setTotalUSDPaid(Double totalUSDPaid) 
	{
		TotalUSDPaid = totalUSDPaid;
	}
	public void setTotalUSDReceived(Double totalUSDReceived) 
	{
		TotalUSDReceived = totalUSDReceived;
	}
	public void setTotalCurrencyPaid(Double totalCurrencyPaid) 
	{
		TotalCurrencyPaid = totalCurrencyPaid;
	}
	public void setTotalCurrencyReceived(Double totalCurrencyReceived) 
	{
		TotalCurrencyReceived = totalCurrencyReceived;
	}
	public void setTotalBalanceInUSD(Double totalBalanceInUSD) 
	{
		TotalBalanceInUSD = totalBalanceInUSD;
	}
	public void setTotalBalanceInCurrency(Double totalBalanceInCurrency) 
	{
		TotalBalanceInCurrency = totalBalanceInCurrency;
	}
	public Double getTotalUSDPaid() {
		return TotalUSDPaid;
	}
	public Double getTotalUSDReceived() {
		return TotalUSDReceived;
	}
	public Double getTotalCurrencyPaid() {
		return TotalCurrencyPaid;
	}
	public Double getTotalCurrencyReceived() {
		return TotalCurrencyReceived;
	}
	public Double getTotalBalanceInUSD() {
		return TotalBalanceInUSD;
	}
	public Double getTotalBalanceInCurrency() {
		return TotalBalanceInCurrency;
	}

	ArrayList<Accounttransactionhistory> listAccountTransactionHistory;
	
	
	
	
	
	public void setErrorMessage(String errorMessage) 
	{
		ErrorMessage = errorMessage;
	}
	public void setStatusId(int statusId) 
	{
		StatusId = statusId;
	}
	public void setTillId(int tillId) 
	{
		TillId = tillId;
	}
	
	public void setListAccountTransactionHistory(
			ArrayList<Accounttransactionhistory> listAccountTransactionHistory) {
		this.listAccountTransactionHistory = listAccountTransactionHistory;
	}
	
	public String getErrorMessage() 
	{
		return ErrorMessage;
	}
	
	public int getTillId() 
	{
		return TillId;
	}
	
	public int getStatusId() 
	{
		return StatusId;
	}
	
	public ArrayList<Accounttransactionhistory> getListAccountTransactionHistory() {
		return listAccountTransactionHistory;
	}
	
}
