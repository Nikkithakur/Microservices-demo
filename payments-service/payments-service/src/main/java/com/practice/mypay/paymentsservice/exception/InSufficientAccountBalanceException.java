package com.practice.mypay.paymentsservice.exception;

public class InSufficientAccountBalanceException extends Exception 
{

	public InSufficientAccountBalanceException() {}

	public InSufficientAccountBalanceException(String message) 
	{
		super(message);
	}

}
