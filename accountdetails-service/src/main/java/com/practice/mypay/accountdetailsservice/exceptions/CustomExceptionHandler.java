package com.practice.mypay.accountdetailsservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.practice.mypay.accountdetailsservice.model.CustomErrorMessage;

@ControllerAdvice
public class CustomExceptionHandler 
{

	@ExceptionHandler(value={UserAccountNotFoundException.class})
	public ResponseEntity accountException(Exception e)
	{
		CustomErrorMessage error = new CustomErrorMessage();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setException(e.getClass().getSimpleName());
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value={AccountBalanceException.class,PhoneNumberFormatException.class,AccountExistException.class})
	public ResponseEntity balanceException(Exception e)
	{
		CustomErrorMessage error = new CustomErrorMessage();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setException(e.getClass().getSimpleName());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
