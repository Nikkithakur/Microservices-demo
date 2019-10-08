package com.practice.mypay.dbservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.PaymentPayload;
import com.practice.mypay.dbservice.model.Transactions;
import com.practice.mypay.dbservice.services.IDatabaseService;

import lombok.extern.slf4j.Slf4j;

/*
@RestController specifies that all the mappings will produce json response, @ResponseBody is not required

beneficiary - destination
benefactor - source

consumes = "application/json",produces = "application/json" Not required, but if added
while calling the endpoint consumes value should be sent as header
     

 */
@RestController
@Slf4j
public class DbController {
 
    @Autowired
    private IDatabaseService iDatabaseService;

    

    @PostMapping(value="db/createAccount")
    public Customer createAccount(@RequestBody final Customer customer)
    {
    	log.info("\n\n"+"Inserting customer information "+customer+"\n\n");
       return iDatabaseService.createAccountService(customer);
    }

    @GetMapping(value = "db/{phoneNumber}")
    public Customer getAccountDetailsByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
    	log.info("\n\n"+"Searching info for phonenumber: "+phoneNumber+"\n\n");
        return iDatabaseService.getAccountDetailsByPhoneNumberService(phoneNumber);
    }

    @GetMapping(value = "db/{phoneNumber}/transactions")
    public List<Transactions> getTransactionsListByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
    	log.info("\n\n"+"Searching txns for phonenumber: "+phoneNumber+"\n\n");
        return iDatabaseService.getTransactionsListByPhoneNumberService(phoneNumber);
    }


    @PutMapping(value = "db/makePayment")
    public Customer makePayment(@RequestBody PaymentPayload payload)
    {
    	log.info("\n\n"+payload.getBenefactor()+" Transferring amount: "+payload.getTransferAmount().intValue()+" to "+payload.getBeneficiary()+"\n\n");
    	return iDatabaseService.makePaymentService(payload);
    }
}
