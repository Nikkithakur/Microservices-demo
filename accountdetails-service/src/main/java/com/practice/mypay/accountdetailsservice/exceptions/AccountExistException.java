package com.practice.mypay.accountdetailsservice.exceptions;

public class AccountExistException extends Exception {

	public AccountExistException() 
	{}
	
	public AccountExistException(String message) 
	{
		super(message);
	}

}
