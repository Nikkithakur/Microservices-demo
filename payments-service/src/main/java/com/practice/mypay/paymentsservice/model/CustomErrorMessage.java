package com.practice.mypay.paymentsservice.model;

import java.util.Date;

public class CustomErrorMessage {

	private Date timeStamp;
	private String message;
	private int statusCode;
	private String exception;
	
	
	public CustomErrorMessage() 
	{
		this.timeStamp = new Date();
	}

	
	
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
