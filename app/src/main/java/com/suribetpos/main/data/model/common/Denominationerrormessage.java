package com.suribetpos.main.data.model.common;

import com.suribetpos.main.ui.denomination.DenominationModel;

import java.util.ArrayList;

public class Denominationerrormessage {
    
	private String ErrorMessage;
	private int ClientId;
	
	private ArrayList<DenominationModel> listclientdenomination;
	
	public String setErrorMessage(String ErrorMessage)
	{
		return this.ErrorMessage = ErrorMessage;
	}
	
	public int setClientId(int ClientId)
	{
		return this.ClientId = ClientId;
	}
	
	
	public ArrayList<DenominationModel> setclientdenomination(ArrayList<DenominationModel> listclientdenomination)
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
	
	
	public ArrayList<DenominationModel> getclientdenomination()
	{
		return listclientdenomination;
	}
}
