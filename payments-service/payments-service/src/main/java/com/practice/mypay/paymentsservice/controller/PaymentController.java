package com.practice.mypay.paymentsservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.practice.mypay.paymentsservice.services.IPaymentService;
import com.practice.mypay.paymentsservices.model.Customer;

@RestController
public class PaymentController {
	
	@Autowired
	private IPaymentService iPaymentService;
	
	@GetMapping(value="/paymentServices/makePayment/{benefactor}/{beneficiary}/{amount}",produces=MediaTypes.HAL_JSON_VALUE)
	public Customer makePayment(@PathVariable("benefactor") final String number1,@PathVariable("beneficiary") final String number2,@PathVariable("amount") final BigDecimal amount)
	{
		Customer customer = iPaymentService.makePaymentService(number1, number2, amount);
		Link selfLink = ControllerLinkBuilder.
				linkTo(PaymentController.class).
				slash("/paymentServices/makePayment/"+number1+"/"+number2+"/"+amount).       				
				withSelfRel();
		customer.add(selfLink);
		return customer;

	}
	
}
