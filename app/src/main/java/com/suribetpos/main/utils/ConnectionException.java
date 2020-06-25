package com.suribetpos.main.utils;

public class ConnectionException extends Exception 
{
	
	private static final long serialVersionUID = 1L;
	String error = "";
	public ConnectionException(String msg)
	{
		super(msg);
		error = msg;
	}
	
	public String getError() 
	{
		return error;
	}

}
