package com.suribetpos.main.data.model.common;

public class Rechargeaccountnumber {
	
	String AccountNumber;
	Double Amount;
	String CustomMessage;
	String ErrorMessage;
	int CreatedBy;
	String CustomerFirstName;
	String CustomerLastName;
	
	public void setAccountNumber(String accountnumber)
	{
		this.AccountNumber = accountnumber;
	}
	
	public void setAmount(Double amount)
	{
		this.Amount = amount;
	}
	
	public void setCustomMessage(String custommessage)
	{
		this.CustomMessage = custommessage;
	}
	
	public void setErrorMessage(String errormessage)
	{
		this.ErrorMessage = errormessage;
	}
	
	public void setCreatedBy(int createdby)
	{
		this.CreatedBy = createdby;
	}
	
	public void setCustomerFirstName(String customerfirstname)
	{
		this.CustomerFirstName = customerfirstname;
	}
	
	public void setCustomerLastName(String customerlastname)
	{
		this.CustomerLastName = customerlastname;
	}
	
	
	
	
	public String getAccountNumber()
	{
		return AccountNumber;
	}
	
	public Double getAmount()
	{
		return Amount;
	}
	
	public String getCustomMessage()
	{
		return CustomMessage;
	}
	
	public String getErrorMessage()
	{
		return ErrorMessage;
	}
	
	public int getCreatedBy()
	{
		return CreatedBy;
	}
	
	public String getCustomerFirstName()
	{
		return CustomerFirstName;
	}
	
	public String getCustomerLastName()
	{
		return CustomerLastName;
	}
	
}
