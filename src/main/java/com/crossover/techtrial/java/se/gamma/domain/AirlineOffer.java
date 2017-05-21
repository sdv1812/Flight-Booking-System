package com.crossover.techtrial.java.se.gamma.domain;

import com.crossover.techtrial.java.se.security.domain.MonetaryAmount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirlineOffer {
	
	private MonetaryAmount price;
	private AirlineRoute route;
	public MonetaryAmount getPrice() {
		return price;
	}
	public void setPrice(MonetaryAmount price) {
		this.price = price;
	}
	public AirlineRoute getRoute() {
		return route;
	}
	public void setRoute(AirlineRoute route) {
		this.route = route;
	}
	@Override
	public String toString() {
		return "AirlineOffer [price=" + price + ", route=" + route + "]";
	}

	
}
