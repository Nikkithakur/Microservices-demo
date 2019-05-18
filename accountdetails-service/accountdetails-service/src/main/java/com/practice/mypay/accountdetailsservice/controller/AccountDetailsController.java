package com.practice.mypay.accountdetailsservice.controller;


import com.practice.mypay.accountdetailsservice.model.Customer;
import com.practice.mypay.accountdetailsservice.services.IAccountDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;

// produces=MediaTypes.HAL_JSON_VALUE will remove null json fields.

@RestController
public class AccountDetailsController {
    
    @Autowired
    private IAccountDetailsService iAccountDetailsService;

    @GetMapping(value="accountsService/getDetails/{phoneNumber}",produces=MediaTypes.HAL_JSON_VALUE)
    public Customer getAccountDetails(@PathVariable("phoneNumber") final  String phoneNumber) throws NoSuchMethodException, SecurityException
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
    public Customer addAccountDetails(@RequestBody Customer customer)
    {
    	return iAccountDetailsService.addAccountDetailsService(customer);
    }
    
    @GetMapping(value="accountsService/{phoneNumber}/transactions",produces=MediaTypes.HAL_JSON_VALUE)
    public List getTransactionDetails(@PathVariable("phoneNumber") final String phoneNumber)
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
