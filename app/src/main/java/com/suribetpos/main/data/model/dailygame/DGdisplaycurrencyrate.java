package com.suribetpos.main.model.dailygame;

public class DGdisplaycurrencyrate
{
	private String CurrencyId ;
	private String CurrencyCode ;
	private String CurrencyRate ;
	private String CurrencyRateMain ;
	private String IsReferingMain ;
	private String TillId ;

	public String getTillId() {
		return TillId;
	}

	public void setTillId(String tillId) {
		TillId = tillId;
	}


	
	
	public String getCurrencyId() {
		return CurrencyId;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public String getCurrencyRate() {
		return CurrencyRate;
	}
	public String getCurrencyRateMain() {
		return CurrencyRateMain;
	}
	public String getIsReferingMain() {
		return IsReferingMain;
	}
	public void setCurrencyId(String currencyId) {
		CurrencyId = currencyId;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public void setCurrencyRate(String currencyRate) {
		CurrencyRate = currencyRate;
	}
	public void setCurrencyRateMain(String currencyRateMain) {
		CurrencyRateMain = currencyRateMain;
	}
	public void setIsReferingMain(String isReferingMain) {
		IsReferingMain = isReferingMain;
	}
	
	
}
