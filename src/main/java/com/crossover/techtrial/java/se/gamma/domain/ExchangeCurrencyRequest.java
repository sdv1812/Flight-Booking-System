package com.crossover.techtrial.java.se.gamma.domain;

import com.crossover.techtrial.java.se.security.domain.MonetaryAmount;

public class ExchangeCurrencyRequest {
	
	private MonetaryAmount monetaryAmount;
	
	private String targetCurrency;

	public MonetaryAmount getMonetaryAmount() {
		return monetaryAmount;
	}

	public void setMonetaryAmount(MonetaryAmount monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	@Override
	public String toString() {
		return "ExchangeCurrencyRequest [monetaryAmount=" + monetaryAmount + ", targetCurrency=" + targetCurrency + "]";
	}
	
	

}
