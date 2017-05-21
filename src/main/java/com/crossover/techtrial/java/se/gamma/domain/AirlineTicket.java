package com.crossover.techtrial.java.se.gamma.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AirlineTicket {
	private Long amount;
	private AirlineOffer details;
	
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public AirlineOffer getDetails() {
		return details;
	}
	public void setDetails(AirlineOffer details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "AirlineTicket [amount=" + amount + ", details=" + details + "]";
	}
		
}
