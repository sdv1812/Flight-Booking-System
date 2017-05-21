package com.crossover.techtrial.java.se.gamma.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuyTicketRequest {
	private String accountId;
	private Long amount;
	private AirlineRoute route;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public AirlineRoute getRoute() {
		return route;
	}
	public void setRoute(AirlineRoute route) {
		this.route = route;
	}
	@Override
	public String toString() {
		return "BuyTicketRequest [accountId=" + accountId + ", amount=" + amount + ", route=" + route + "]";
	}
	
}
