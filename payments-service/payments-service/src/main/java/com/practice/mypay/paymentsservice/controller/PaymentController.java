package com.practice.mypay.paymentsservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.practice.mypay.paymentsservice.exception.InSufficientAccountBalanceException;
import com.practice.mypay.paymentsservice.exception.TransferAmountException;
import com.practice.mypay.paymentsservice.exception.UserNotFoundException;
import com.practice.mypay.paymentsservice.exception.PhoneNumberFormatException;
import com.practice.mypay.paymentsservice.model.Customer;
import com.practice.mypay.paymentsservice.model.PaymentPayload;
import com.practice.mypay.paymentsservice.services.IPaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	private IPaymentService iPaymentService;
	
	@PutMapping(value="/paymentsService/makePayment",produces=MediaTypes.HAL_JSON_VALUE)
	public Customer makePayment(@RequestBody PaymentPayload payload) throws RestClientException, PhoneNumberFormatException, TransferAmountException, InSufficientAccountBalanceException, UserNotFoundException
	{
		Customer customer = iPaymentService.makePaymentService(payload);
		/*
		 * Link selfLink = ControllerLinkBuilder. linkTo(PaymentController.class).
		 * slash("/paymentsService/makePayment/"). withSelfRel();
		 * customer.add(selfLink);
		 */
		return customer;

	}
	
}
