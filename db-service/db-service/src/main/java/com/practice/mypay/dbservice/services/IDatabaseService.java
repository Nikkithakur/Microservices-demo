package com.practice.mypay.dbservice.services;

import java.math.BigDecimal;
import java.util.List;

import com.practice.mypay.dbservice.model.Customer;

public interface IDatabaseService {

	Customer createAccountService(Customer customer);
	Customer getAccountDetailsByPhoneNumberService(final String phoneNumber);
	List getTransactionsListByPhoneNumberService(final String phoneNumber);
	Customer makePaymentService(final String number1, final String number2, final BigDecimal amount);
}
