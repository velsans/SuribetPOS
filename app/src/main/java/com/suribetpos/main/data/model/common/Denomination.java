package com.suribetpos.main.data.model.common;

public class Denomination {
         
	private int DenominationId;
	private String DenominationValue;
	private String ErrorMessage;
	
	
	public void setDenominationId(int denominationId)
	{
		this.DenominationId = denominationId;
	}
	
	public void setDenominationValue(String denominationValue)
	{
		this.DenominationValue = denominationValue;
	}
	
	public void setErrorMessage(String errorMessage)
	{
		this.ErrorMessage = errorMessage;
	}
	
	
	public int getDenominationId()
	{
		return this.DenominationId;
	}
	
	public String getDenominationValue()
	{
		return this.DenominationValue;
	}
	
	
	public String getErrorMessage()
	{
		return this.ErrorMessage;
	}
}
