package com.practice.mypay.accountdetailsservice.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.mypay.accountdetailsservice.exceptions.AccountBalanceException;
import com.practice.mypay.accountdetailsservice.exceptions.AccountExistException;
import com.practice.mypay.accountdetailsservice.exceptions.PhoneNumberFormatException;
import com.practice.mypay.accountdetailsservice.exceptions.UserAccountNotFoundException;
import com.practice.mypay.accountdetailsservice.model.Customer;

@Service
public class AccountDetailsServiceImpl implements IAccountDetailsService{

	@Autowired
    private RestTemplate restTemplate;
	
	@Override
	public Customer addAccountDetailsService(final Customer customer) throws AccountExistException, AccountBalanceException, PhoneNumberFormatException 
	{
		if(!Pattern.matches("[1-9]{1}[0-9]{9}", customer.getPhoneNumber()))
			throw new PhoneNumberFormatException("Mobile Number should contain 10 digts and should not begin with zero");
		if(userFound(customer.getPhoneNumber()))
			throw new AccountExistException("An account with this phoneNumber already exists");
		else if(customer.getWallet().getBalance().doubleValue()<=0)
			throw new AccountBalanceException("Initial balance should be greater than 0");
		String url="https://db-service/db/createAccount";
    	return restTemplate.postForObject(url, customer, Customer.class);
	}

	@Override
	public Customer getAccountDetailsService(final String phoneNumber) throws NoSuchMethodException, SecurityException, UserAccountNotFoundException, PhoneNumberFormatException 
	{
		if(!Pattern.matches("[1-9]{1}[0-9]{9}", phoneNumber))
			throw new PhoneNumberFormatException("Mobile Number should contain 10 digts and should not begin with zero");
    	String url="https://db-service/db/"+phoneNumber;
        Customer customer = restTemplate.getForObject(url, Customer.class);
        if(customer==null)	throw new UserAccountNotFoundException("No Account found associated with: "+phoneNumber);
        else return customer;
	}

	@Override
	public List getTransactionDetailsService(final String phoneNumber) throws UserAccountNotFoundException, PhoneNumberFormatException {
		
		if(!Pattern.matches("[1-9]{1}[0-9]{9}", phoneNumber))
			throw new PhoneNumberFormatException("Mobile Number should contain 10 digts and should not begin with zero");
		String url="https://db-service/db/"+phoneNumber+"/"+"transactions";
		if(userFound(phoneNumber))	return restTemplate.getForObject(url,List.class);
		else throw new UserAccountNotFoundException("No Account found associated with: "+phoneNumber);
	}
	
	
	// validation methods
	private boolean userFound(final String phoneNumber)
	{
		String url="https://db-service/db/"+phoneNumber;
        if( restTemplate.getForObject(url, Customer.class)!=null)	return true;
        else	return false;
	}

}
