package com.crossover.techtrial.java.se.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Deposit {
	
	private String accountId;
	private MonetaryAmount monetaryAmount;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public MonetaryAmount getMonetaryAmount() {
		return monetaryAmount;
	}
	public void setMonetaryAmount(MonetaryAmount monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}
	@Override
	public String toString() {
		return "Deposit [accountId=" + accountId + ", monetaryAmount=" + monetaryAmount + "]";
	}
	
	

}
