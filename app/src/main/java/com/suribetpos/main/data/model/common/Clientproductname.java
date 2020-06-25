package com.suribetpos.main.data.model.common;

import android.graphics.Bitmap;

public class Clientproductname {
   
	private int ProductId;
	private String ProductName;
	private String FooterContent;


	private String ProductLogo;
	private int ValidateDays;
	private String Terms;

	public void setProductLogo(String productLogo) {
		ProductLogo = productLogo;
	}

	public Bitmap getClientLogs() {
		return ClientLogs;
	}

	public void setClientLogs(Bitmap clientLogs) {
		ClientLogs = clientLogs;
	}

	private Bitmap ClientLogs;
	
	public void setTerms(String terms) {
		Terms = terms;
	}

	public String getProductLogo() {
		return ProductLogo;
	}

	public String getTerms() {
		return Terms;
	}

	public int setProductId(int ProductId)
	{
		return this.ProductId = ProductId;
	}
	
	public String setProductName(String ProductName)
	{
		return this.ProductName = ProductName;
	}
	
	public String setFooterContent(String FooterContent)
	{
		return this.FooterContent = FooterContent;
	}
	
	public int setValidateDays(int ValidateDays)
	{
		return this.ValidateDays = ValidateDays;
	}
	
	
	
	public int getProductId()
	{
		return ProductId;
	}
	
	public String getProductName()
	{
		return ProductName;
	}
	
	public String getFooterContent()
	{
		return FooterContent;
	}
	
	public int getValidateDays() 
	{
		return ValidateDays;
	}
	
	
}
