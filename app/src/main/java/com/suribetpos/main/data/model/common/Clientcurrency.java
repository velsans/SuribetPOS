package com.suribetpos.main.data.model.common;

public class Clientcurrency {
   
	private int CurrencyId;
	private String CurrencyCode;
	private Double CurrencyRate;
	private Double CurrencyRateMain;
	private Double Amount;
	
	
	
	public int setCurrencyId(int CurrencyId)
	{
		return this.CurrencyId = CurrencyId;
	}
	
	public String setCurrencyCode(String CurrencyCode)
	{
		return this.CurrencyCode = CurrencyCode;
	}
	
	public Double setCurrencyRate(Double CurrencyRate)
	{
		return this.CurrencyRate = CurrencyRate;
	}
	
	public Double setCurrencyRateMain(Double CurrencyRateMain)
	{
		return this.CurrencyRateMain = CurrencyRateMain;
	}
	
	public Double setAmount(Double Amount)
	{
		return this.Amount = Amount;
	}
	
	
	
	public int getCurrencyId()
	{
		return CurrencyId;
	}
	
	public String getCurrencyCode()
	{
		return CurrencyCode;
	}
	
	public Double getCurrencyRate()
	{
		return CurrencyRate;
	}
	
	public Double getCurrencyRateMain()
	{
		return CurrencyRateMain;
	}
	
	public Double getAmount()
	{
		return Amount;
	}
	
	
}
