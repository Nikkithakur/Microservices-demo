package com.practice.mypay.dbservice.services;

import java.util.List;

import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.PaymentPayload;
import com.practice.mypay.dbservice.model.Transactions;

public interface IDatabaseService {

	Customer createAccountService(Customer customer);
	Customer getAccountDetailsByPhoneNumberService(final String phoneNumber);
	List<Transactions> getTransactionsListByPhoneNumberService(final String phoneNumber);
	Customer makePaymentService(PaymentPayload payload);
}
