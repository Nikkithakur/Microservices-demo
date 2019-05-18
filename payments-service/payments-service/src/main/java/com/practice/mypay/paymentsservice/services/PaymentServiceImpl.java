package com.practice.mypay.paymentsservice.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.mypay.paymentsservices.model.Customer;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Customer makePaymentService(String number1, String number2, BigDecimal transferAmount) {
		
		String url ="http://db-service/db/makePayment/"+number1+"/"+number2+"/"+transferAmount;
		return restTemplate.getForObject(url, Customer.class);
	}

}
