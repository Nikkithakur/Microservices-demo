package com.practice.mypay.paymentsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.practice.mypay.paymentsservice.model.CustomErrorMessage;

@ControllerAdvice
public class CustomExceptionHandler 
{

	@ExceptionHandler(value= {HttpClientErrorException.class})
	public ResponseEntity accountException(Exception e)
	{
		CustomErrorMessage error =new CustomErrorMessage();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setException(e.getClass().getSimpleName());
		return new ResponseEntity(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {InSufficientAccountBalanceException.class,TransferAmountException.class,PhoneNumberFormatException.class})
	public ResponseEntity amountException(Exception e)
	{
		CustomErrorMessage error =new CustomErrorMessage();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setException(e.getClass().getSimpleName());
		return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
	}
	
}
