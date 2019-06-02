package com.practice.mypay.paymentsservice.services;


import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.practice.mypay.paymentsservice.exception.InSufficientAccountBalanceException;
import com.practice.mypay.paymentsservice.exception.TransferAmountException;
import com.practice.mypay.paymentsservice.exception.PhoneNumberFormatException;
import com.practice.mypay.paymentsservice.model.Customer;
import com.practice.mypay.paymentsservice.model.PaymentPayload;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Customer makePaymentService(PaymentPayload payload) throws RestClientException, PhoneNumberFormatException, TransferAmountException, InSufficientAccountBalanceException {
		
		if(!Pattern.matches("[1-9]{1}[0-9]{9}", payload.getBenefactor()))
			throw new PhoneNumberFormatException("Incorrect format: "+payload.getBenefactor()+" , Mobile Number should contain 10 digts and should not begin with zero");
		
		else if(!Pattern.matches("[1-9]{1}[0-9]{9}", payload.getBeneficiary()))
			throw new PhoneNumberFormatException("Incorrect format: "+payload.getBeneficiary()+" , Mobile Number should contain 10 digts and should not begin with zero");
		
		
		else if(!(payload.getTransferAmount().doubleValue()>0))
			throw new TransferAmountException("Transfer amount should be greater than zero");
		
		else if(payload.getBenefactor().equalsIgnoreCase(payload.getBeneficiary()))
			throw new PhoneNumberFormatException("Benefactor and Beneficiary mobile numbers can't be same");
		
		if(userDetails(payload.getBenefactor(), payload.getBeneficiary(),payload.getTransferAmount()))
		{
			final String dbUrl ="https://db-service/db/makePayment";
			HttpEntity<PaymentPayload> requestBody = new HttpEntity<PaymentPayload>(payload);
			return restTemplate.exchange(dbUrl, HttpMethod.PUT, requestBody, Customer.class).getBody();
		}
		else return null;
	}
	
	
	
	
	private boolean userDetails(String benefactor, String beneficiary, BigDecimal amount) throws InSufficientAccountBalanceException 
	{
		final String url1 ="https://accountdetails-service/accountsService/getDetails/"+benefactor;
		final String url2 ="https://accountdetails-service/accountsService/getDetails/"+beneficiary;

		
		try 
		{	
			Customer customer1 = restTemplate.getForObject(url1, Customer.class);
			if(customer1.getWallet().getBalance().doubleValue()<amount.doubleValue())
				throw new InSufficientAccountBalanceException("InSufficient Balance");
		}
		catch(HttpClientErrorException e)
		{
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account associated with number: "+benefactor+" not found");
		}
		
		try
		{
			Customer customer2 = restTemplate.getForObject(url2, Customer.class);
		}
		
		catch(HttpClientErrorException e)
		{
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account associated with number: "+ beneficiary+" not found");
		}
		
		return true;	
	}

}
