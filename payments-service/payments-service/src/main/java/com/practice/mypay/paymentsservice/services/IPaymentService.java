package com.practice.mypay.paymentsservice.services;


import org.springframework.web.client.RestClientException;

import com.practice.mypay.paymentsservice.exception.InSufficientAccountBalanceException;
import com.practice.mypay.paymentsservice.exception.TransferAmountException;
import com.practice.mypay.paymentsservice.exception.UserNotFoundException;
import com.practice.mypay.paymentsservice.exception.PhoneNumberFormatException;
import com.practice.mypay.paymentsservice.model.Customer;
import com.practice.mypay.paymentsservice.model.PaymentPayload;

public interface IPaymentService {

	Customer makePaymentService(PaymentPayload payload) throws RestClientException, PhoneNumberFormatException, TransferAmountException, InSufficientAccountBalanceException, UserNotFoundException;
}
