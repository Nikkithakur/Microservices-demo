package com.practice.mypay.accountdetailsservice.controller;


import com.practice.mypay.accountdetailsservice.exceptions.AccountBalanceException;
import com.practice.mypay.accountdetailsservice.exceptions.AccountExistException;
import com.practice.mypay.accountdetailsservice.exceptions.PhoneNumberFormatException;
import com.practice.mypay.accountdetailsservice.exceptions.UserAccountNotFoundException;
import com.practice.mypay.accountdetailsservice.model.Customer;
import com.practice.mypay.accountdetailsservice.model.Transactions;
import com.practice.mypay.accountdetailsservice.services.IAccountDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;

// produces=MediaTypes.HAL_JSON_VALUE will remove null json fields.

@Api(value = "AccountDetailsController",description = "Accounts API providing account related services")
@RestController
public class AccountDetailsController {
    
    @Autowired
    private IAccountDetailsService iAccountDetailsService;

    @GetMapping(value="accountsService/getDetails/{phoneNumber}",produces=MediaTypes.HAL_JSON_VALUE)
    public Customer getAccountDetails(@ApiParam(name="phoneNumber",required=true,value="Enter a 10 digit mobile number")
    								  @PathVariable("phoneNumber") final  String phoneNumber)
    								  throws NoSuchMethodException, SecurityException, UserAccountNotFoundException, PhoneNumberFormatException
    {
    	Customer customerData = iAccountDetailsService.getAccountDetailsService(phoneNumber);
    	
    	Link selfLink = ControllerLinkBuilder.
				linkTo(AccountDetailsController.class).
				slash("accountsService/getDetails/"+phoneNumber).       				
				withSelfRel();

    	Method method=AccountDetailsController.class.getMethod("getTransactionDetails",String.class);
    	Link txnLink=ControllerLinkBuilder.linkTo(method, phoneNumber).withSelfRel();
    	
    	customerData.add(selfLink,txnLink);
    	
    	return customerData;
    }
    
    @PostMapping(value="accountsService/addAccountDetails")
    public Customer addAccountDetails(@RequestBody Customer customer) throws AccountExistException, NoSuchMethodException, SecurityException, AccountBalanceException, PhoneNumberFormatException
    {
    	Customer customerData = iAccountDetailsService.addAccountDetailsService(customer);
    	
    	Method method1=AccountDetailsController.class.getMethod("getAccountDetails",String.class);
    	Link detailsLink=ControllerLinkBuilder.linkTo(method1, customer.getPhoneNumber()).withSelfRel();
    	
    	Method method2=AccountDetailsController.class.getMethod("getTransactionDetails",String.class);
    	Link txnLink=ControllerLinkBuilder.linkTo(method2, customer.getPhoneNumber()).withSelfRel();
    	
    	customerData.add(detailsLink,txnLink);
    	
    	return customerData;
    }
    
    @GetMapping(value="accountsService/{phoneNumber}/transactions",produces=MediaTypes.HAL_JSON_VALUE)
    public List getTransactionDetails(@ApiParam(name="phoneNumber",required=true,value="Enter a 10 digit mobile number")
    								  @PathVariable("phoneNumber") final String phoneNumber)
    								  throws UserAccountNotFoundException, PhoneNumberFormatException
    {
    	List list = iAccountDetailsService.getTransactionDetailsService(phoneNumber);
    	
    	Link selfLink = ControllerLinkBuilder.
    				linkTo(AccountDetailsController.class).
    				slash("accountsService/"+phoneNumber+"/transactions").       				
    				withSelfRel();
        
    	list.add(selfLink);
        
    	return list;
    }



}
