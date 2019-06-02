package com.practice.mypay.accountdetailsservice.services;

import java.util.List;

import com.practice.mypay.accountdetailsservice.exceptions.AccountBalanceException;
import com.practice.mypay.accountdetailsservice.exceptions.AccountExistException;
import com.practice.mypay.accountdetailsservice.exceptions.PhoneNumberFormatException;
import com.practice.mypay.accountdetailsservice.exceptions.UserAccountNotFoundException;
import com.practice.mypay.accountdetailsservice.model.Customer;

public interface IAccountDetailsService {

	Customer addAccountDetailsService(final Customer customer) throws AccountExistException, AccountBalanceException, PhoneNumberFormatException;
	Customer getAccountDetailsService(final  String phoneNumber) throws NoSuchMethodException, SecurityException, UserAccountNotFoundException, PhoneNumberFormatException;
	List getTransactionDetailsService(final  String phoneNumber) throws UserAccountNotFoundException, PhoneNumberFormatException;
	
}
