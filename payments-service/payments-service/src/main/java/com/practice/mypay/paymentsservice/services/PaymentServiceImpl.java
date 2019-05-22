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
		
		final String dbUrl ="http://db-service/db/makePayment";
		HttpEntity<PaymentPayload> requestBody = new HttpEntity<PaymentPayload>(payload);
		return restTemplate.exchange(dbUrl, HttpMethod.PUT, requestBody, Customer.class).getBody();
	}

}
