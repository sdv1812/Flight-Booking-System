package com.crossover.techtrial.java.se.gamma.service;

import java.util.List;

import com.crossover.techtrial.java.se.gamma.domain.AirlineOffer;
import com.crossover.techtrial.java.se.gamma.domain.AirlineTicket;

public interface GammaService {
	
	public List<AirlineOffer> getAirlineOffers();
	
	public List<AirlineTicket> getAirlineTickets();
	
	public AirlineTicket buyAirlineTicket(String from, String to, String currency, Long amount);
	

}
