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
import com.practice.mypay.paymentsservice.services.IPaymentService;
import com.practice.mypay.paymentsservices.model.Customer;
import com.practice.mypay.paymentsservices.model.PaymentPayload;

@RestController
public class PaymentController {
	
	@Autowired
	private IPaymentService iPaymentService;
	
	@PutMapping(value="/paymentServices/makePayment",produces=MediaTypes.HAL_JSON_VALUE)
	public Customer makePayment(@RequestBody PaymentPayload payload)
	{
		Customer customer = iPaymentService.makePaymentService(payload);
		Link selfLink = ControllerLinkBuilder.
				linkTo(PaymentController.class).
				slash("/paymentServices/makePayment/").       				
				withSelfRel();
		customer.add(selfLink);
		return customer;

	}
	
}
