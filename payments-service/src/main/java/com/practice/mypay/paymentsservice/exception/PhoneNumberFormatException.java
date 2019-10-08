package com.practice.mypay.paymentsservice.exception;

public class PhoneNumberFormatException extends Exception{

	public PhoneNumberFormatException() {}

	public PhoneNumberFormatException (String message) 
	{
		super(message);
	}
}
