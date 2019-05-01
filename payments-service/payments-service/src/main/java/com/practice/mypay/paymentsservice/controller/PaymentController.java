package com.practice.mypay.paymentsservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.practice.mypay.paymentservices.model.Customer;

@RestController
public class PaymentController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value="/paymentServices/makePayment/{benefactor}/{beneficiary}/{amount}",produces=MediaTypes.HAL_JSON_VALUE)
	public Customer makePayment(@PathVariable("benefactor") final String number1,@PathVariable("beneficiary") final String number2,@PathVariable("amount") final BigDecimal amount)
	{
		Customer customer;
		String url ="http://db-service/db/makePayment/"+number1+"/"+number2+"/"+amount;
		Link selfLink = ControllerLinkBuilder.
				linkTo(PaymentController.class).
				slash("/paymentServices/makePayment/"+number1+"/"+number2+"/"+amount).       				
				withSelfRel();
		customer = restTemplate.getForObject(url, Customer.class);
		customer.add(selfLink);
		return customer;

	}
	
}
