package com.practice.mypay.accountdetailsservice.services;

import java.util.List;

import com.practice.mypay.accountdetailsservice.model.Customer;

public interface IAccountDetailsService {

	Customer addAccountDetailsService(final Customer customer);
	Customer getAccountDetailsService(final  String phoneNumber) throws NoSuchMethodException, SecurityException;
	List getTransactionDetailsService(final  String phoneNumber);
	
}
