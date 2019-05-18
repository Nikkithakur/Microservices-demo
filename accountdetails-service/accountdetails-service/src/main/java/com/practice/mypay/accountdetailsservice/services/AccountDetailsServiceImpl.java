package com.practice.mypay.accountdetailsservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.mypay.accountdetailsservice.model.Customer;

@Service
public class AccountDetailsServiceImpl implements IAccountDetailsService{

	@Autowired
    private RestTemplate restTemplate;
	
	@Override
	public Customer addAccountDetailsService(final Customer customer) {
		
		String url="https://db-service/db/createAccount";
    	return restTemplate.postForObject(url, customer, Customer.class);
	}

	@Override
	public Customer getAccountDetailsService(final String phoneNumber) throws NoSuchMethodException, SecurityException {
		
    	String url="https://db-service/db/"+phoneNumber;
        return restTemplate.getForObject(url, Customer.class);
	}

	@Override
	public List getTransactionDetailsService(final String phoneNumber) {
		
		String url="https://db-service/db/"+phoneNumber+"/"+"transactions";
    	return restTemplate.getForObject(url,List.class);
	}

}
