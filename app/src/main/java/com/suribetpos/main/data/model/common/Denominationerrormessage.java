package com.suribetpos.main.data.model.common;

import java.util.ArrayList;

public class Denominationerrormessage {
    
	private String ErrorMessage;
	private int ClientId;
	
	private ArrayList<ClientDenominationModel> listclientdenomination;
	
	public String setErrorMessage(String ErrorMessage)
	{
		return this.ErrorMessage = ErrorMessage;
	}
	
	public int setClientId(int ClientId)
	{
		return this.ClientId = ClientId;
	}
	
	
	public ArrayList<ClientDenominationModel> setclientdenomination(ArrayList<ClientDenominationModel> listclientdenomination)
	{
		return this.listclientdenomination = listclientdenomination;
	}
	
	public String getErrorMessage()
	{
		return ErrorMessage;
	}
	
	public int getClientId()
	{
		return ClientId;
	}
	
	
	public ArrayList<ClientDenominationModel> getclientdenomination()
	{
		return listclientdenomination;
	}
}
