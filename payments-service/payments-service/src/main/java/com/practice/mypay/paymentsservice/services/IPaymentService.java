package com.practice.mypay.paymentsservice.services;

import java.math.BigDecimal;

import com.practice.mypay.paymentsservices.model.Customer;

public interface IPaymentService {

	Customer makePaymentService(final String number1, final String number2, final BigDecimal transferAmount);
}
