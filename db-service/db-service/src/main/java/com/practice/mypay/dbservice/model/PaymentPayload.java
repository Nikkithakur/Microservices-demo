package com.practice.mypay.dbservice.model;

import java.math.BigDecimal;

public class PaymentPayload {

	private String benefactor;
	
	private String beneficiary;
	
	private BigDecimal transferAmount;

	public String getBenefactor() {
		return benefactor;
	}

	public void setBenefactor(String benefactor) {
		this.benefactor = benefactor;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}
	
	
	
}
