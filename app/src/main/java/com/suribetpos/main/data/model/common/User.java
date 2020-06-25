package com.suribetpos.main.data.model.common;

public class User {
	
int UserId;
String Password;
String ErrorMessage;
String Location;
String UserName;


public void setUserId(int userId)
{
	this.UserId = userId;
}

public void setPassword(String password)
{
	this.Password = password;
}

 public int getUserId()
    {
    	return UserId;
    }
 public String getPassword() {
        return Password;
    }
 
public String getErrorMessage() {
    return ErrorMessage;
}

public String getUserName() {
    return UserName;
}
public String getLocation() {
    return Location;
}

public static final String USERID = "userid";
public static final String USERPASSWORD = "password";
}
