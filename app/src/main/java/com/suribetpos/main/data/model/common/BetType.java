package com.suribetpos.main.data.model.common;

public class BetType {

	private int BetTypeID;
	private String PickDescription;
	
	public int setBetTypeID(int BetTypeID)
	{
		return this.BetTypeID = BetTypeID;
	}
	
	public String setPickDescription(String PickDescription)
	{
		return this.PickDescription = PickDescription;
	}
	
	public int getBetTypeID()
	{
		return this.BetTypeID;
	}
	
	public String getPickDescription()
	{
		return this.PickDescription;
	}
}
