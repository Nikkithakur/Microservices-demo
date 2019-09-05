package com.practice.mypay.dbservice.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PaymentPayload {

	private String benefactor;
	
	private String beneficiary;
	
	private BigDecimal transferAmount;

	}
