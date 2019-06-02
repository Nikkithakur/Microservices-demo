package com.practice.mypay.accountdetailsservice.exceptions;

public class UserAccountNotFoundException extends Exception {

	public UserAccountNotFoundException() {}
	
	public UserAccountNotFoundException(String message) 
	{
		super(message);
	}

}
