package com.practice.mypay.dbservice.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.Transactions;
import com.practice.mypay.dbservice.repo.AccountRepository;

@Service
public class DatabaseServiceImpl implements IDatabaseService {

	@Autowired
    private AccountRepository accountRepository;
	
	@Override
	@Transactional
	public Customer createAccountService(final Customer customer) {
		
		return accountRepository.save(customer);
		
	}

	@Override
	public Customer getAccountDetailsByPhoneNumberService(final String phoneNumber) {
		
		return accountRepository.findCustomerByPhoneNumber(phoneNumber);
	}

	@Override
	public List getTransactionsListByPhoneNumberService(final String phoneNumber) {
		
		return accountRepository.findCustomerByPhoneNumber(phoneNumber).getWallet().getTransactions();
	}

	@Override
	@Transactional
	public Customer makePaymentService(final String number1, final String number2, final BigDecimal transferAmount) {
		
		Customer benefactor,beneficiary;
        BigDecimal credit, debit;
        Transactions txn1=new Transactions();
        Transactions txn2=new Transactions();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        benefactor=accountRepository.findCustomerByPhoneNumber(number1);
        beneficiary=accountRepository.findCustomerByPhoneNumber(number2);


        debit=benefactor.getWallet().getBalance().subtract(transferAmount);
        benefactor.getWallet().setBalance(debit);
        txn1.setTransactionMsg(timestamp+" "+transferAmount+" has been sent to "+number2);
        benefactor.getWallet().getTransactions().add(txn1);

        credit=beneficiary.getWallet().getBalance().add(transferAmount);
        beneficiary.getWallet().setBalance(credit);
        txn2.setTransactionMsg(timestamp+"... Rs "+transferAmount+" has been sent to you by "+number1);
        beneficiary.getWallet().getTransactions().add(txn2);

        accountRepository.save(beneficiary);
        return accountRepository.save(benefactor);
	}

}
