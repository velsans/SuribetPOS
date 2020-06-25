package com.suribetpos.main.data.model.common;

public class Clientinformation {
	
	private int ClientID;
	private String ClientName;
	private int TillId;
	private int LocationId;
	private int LocationTypeId;
	private String ClientLogo;
	private String ErrorMessage;
	private String Physical;

	public String setClientName(String ClientName)
	{
		return this.ClientName = ClientName;
	}
	
	public int setTillId(int TillId)
	{
		return this.TillId = TillId;
	}
	
	public int setLocationId(int LocationId)
	{
		return this.LocationId = LocationId;
	}
	
	public int setLocationTypeId(int LocationTypeId)
	{
		return this.LocationTypeId = LocationTypeId;
	}
	
	public String setClientLogo(String ClientLogo)
	{
		return this.ClientLogo = ClientLogo;
	}
	
	public String setErrorMessage(String ErrorMessage)
	{
		return this.ErrorMessage = ErrorMessage;
	}
	
	public String setPhysical(String Physical)
	{
		return this.Physical = Physical;
	}
	

	public String getClientName()
	{
		return ClientName;
	}
	
	public int getTillId()
	{
		return TillId;
	}
	
	public int getLocationId()
	{
		return LocationId;
	}
	
	public int getLocationTypeId()
	{
		return LocationTypeId;
	}
	
	public String getClientLogo()
	{
		return ClientLogo;
	}
	
	public String getErrorMessage()
	{
		return ErrorMessage;
	}
	
	public String getPhysical()
	{
		return Physical;
	}

	public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientID) {
		ClientID = clientID;
	}
}
