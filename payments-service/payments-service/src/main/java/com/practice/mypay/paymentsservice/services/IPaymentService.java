package com.practice.mypay.paymentsservice.services;


import com.practice.mypay.paymentsservices.model.Customer;
import com.practice.mypay.paymentsservices.model.PaymentPayload;

public interface IPaymentService {

	Customer makePaymentService(PaymentPayload payload);
}
