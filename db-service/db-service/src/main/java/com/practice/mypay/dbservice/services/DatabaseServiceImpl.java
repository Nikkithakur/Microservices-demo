package com.practice.mypay.dbservice.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.PaymentPayload;
import com.practice.mypay.dbservice.model.Transactions;
import com.practice.mypay.dbservice.repo.DBRepository;

@Service
public class DatabaseServiceImpl implements IDatabaseService {

	@Autowired
    private DBRepository accountRepository;
	
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
	public List<Transactions> getTransactionsListByPhoneNumberService(final String phoneNumber) {
		
		return accountRepository.findCustomerByPhoneNumber(phoneNumber).getWallet().getTransactions();
	}

	@Override
	@Transactional
	public Customer makePaymentService(PaymentPayload payload) {
		
		Customer benefactor,beneficiary;
        BigDecimal credit, debit, transferAmount;
        Transactions txn1=new Transactions();
        Transactions txn2=new Transactions();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        benefactor=getAccountDetailsByPhoneNumberService(payload.getBenefactor());
        beneficiary=getAccountDetailsByPhoneNumberService(payload.getBeneficiary());
        transferAmount=payload.getTransferAmount();

        debit=benefactor.getWallet().getBalance().subtract(transferAmount);
        benefactor.getWallet().setBalance(debit);
        txn1.setTransactionMsg(timestamp+" "+transferAmount+" has been sent to "+beneficiary.getName()+", with phoneno: "+beneficiary.getPhoneNumber());
        benefactor.getWallet().getTransactions().add(txn1);

        credit=beneficiary.getWallet().getBalance().add(transferAmount);
        beneficiary.getWallet().setBalance(credit);
        txn2.setTransactionMsg(timestamp+"... Rs "+transferAmount+" has been sent to you by "+benefactor.getName()+", with phoneno: "+benefactor.getPhoneNumber());
        beneficiary.getWallet().getTransactions().add(txn2);

        accountRepository.save(beneficiary);
        return accountRepository.save(benefactor);
	}

}
