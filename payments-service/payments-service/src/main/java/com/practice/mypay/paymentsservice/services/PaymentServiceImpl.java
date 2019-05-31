package com.practice.mypay.paymentsservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.mypay.paymentsservices.model.Customer;
import com.practice.mypay.paymentsservices.model.PaymentPayload;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Customer makePaymentService(PaymentPayload payload) {
		
		if(userDetails(payload.getBenefactor(), payload.getBeneficiary()))
		{
			final String dbUrl ="https://db-service/db/makePayment";
			HttpEntity<PaymentPayload> requestBody = new HttpEntity<PaymentPayload>(payload);
			return restTemplate.exchange(dbUrl, HttpMethod.PUT, requestBody, Customer.class).getBody();
		}
		return null;
	}
	
	protected boolean userDetails(String benefactor, String beneficiary) 
	{
		final String url1 ="https://accountdetails-service/accountsService/getDetails/"+benefactor;
		Customer customer1=restTemplate.getForObject(url1, Customer.class);
		final String url2 ="https://accountdetails-service/accountsService/getDetails/"+beneficiary;
		Customer customer2=restTemplate.getForObject(url2, Customer.class);
		if(customer1!=null && customer2!=null )
			return true;
		else
			return false;
	}

}
