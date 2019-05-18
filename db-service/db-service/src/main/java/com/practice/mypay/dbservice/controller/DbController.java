package com.practice.mypay.dbservice.controller;

import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.Transactions;

import com.practice.mypay.dbservice.services.IDatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/*
@RestController species that all the mappings will produce json response, @ResponseBody is not required

beneficiary - destination
benefactor - source
 */
@RestController
public class DbController {
 
    @Autowired
    private IDatabaseService iDatabaseService;

    /*
        consumes = "application/json",produces = "application/json" Not required, but if added
        while calling the endpoint consumes value should be sent as header
     */

    @PostMapping(value="db/createAccount")
    public Customer createAccount(@RequestBody final Customer customer)
    {
       return iDatabaseService.createAccountService(customer);
    }

    @GetMapping(value = "db/{phoneNumber}")
    public Customer getAccountDetailsByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
        return iDatabaseService.getAccountDetailsByPhoneNumberService(phoneNumber);
    }

    @GetMapping(value = "db/{phoneNumber}/transactions")
    public List<Transactions> getTransactionsListByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
        return iDatabaseService.getTransactionsListByPhoneNumberService(phoneNumber);
    }


    @GetMapping(value = "db/makePayment/{number1}/{number2}/{amount}")
    public Customer makePayment(@PathVariable("number1") final String number1,@PathVariable("number2") final String number2,@PathVariable("amount") final BigDecimal transferAmount)
    {
    	return iDatabaseService.makePaymentService(number1, number2, transferAmount);
    }
}
