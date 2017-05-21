package com.crossover.techtrial.java.se.gamma.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crossover.techtrial.java.se.application.util.JsonUtil;
import com.crossover.techtrial.java.se.application.util.LoginUtil;
import com.crossover.techtrial.java.se.application.util.RestTemplateUtil;
import com.crossover.techtrial.java.se.application.util.Settings;
import com.crossover.techtrial.java.se.application.util.URL;
import com.crossover.techtrial.java.se.gamma.domain.AirlineOffer;
import com.crossover.techtrial.java.se.gamma.domain.AirlineRoute;
import com.crossover.techtrial.java.se.gamma.domain.AirlineTicket;
import com.crossover.techtrial.java.se.gamma.domain.BuyTicketRequest;
import com.crossover.techtrial.java.se.gamma.domain.ExchangeCurrencyRequest;
import com.crossover.techtrial.java.se.security.domain.CurrencyT;
import com.crossover.techtrial.java.se.security.domain.MonetaryAmount;
import com.crossover.techtrial.java.se.security.domain.User;
import com.crossover.techtrial.java.se.security.repository.AccountRepository;
import com.crossover.techtrial.java.se.security.domain.Account;

@Service
public class GammaServiceImpl implements GammaService {
	
	private static final Logger logger = LoggerFactory.getLogger(GammaServiceImpl.class);
	
	private final RestTemplate restTemplate;
	private final AccountRepository accountRepository;
	
	@Autowired
	public GammaServiceImpl(RestTemplateBuilder restTemplateBuilder, AccountRepository accountRepository) {
		this.restTemplate = restTemplateBuilder.build();
		this.accountRepository = accountRepository;
	}

	@Override
	public List<AirlineOffer> getAirlineOffers() {
		logger.info("GammaService.getAirlineOffers");
		ResponseEntity<AirlineOffer[]> responseEntity  = restTemplate.getForEntity(URL.GAMMAAIRLINES_OFFER, AirlineOffer[].class);
		AirlineOffer[] airlineOfferArray = responseEntity.getBody();
		List<AirlineOffer> airlineOfferList = Arrays.asList(airlineOfferArray);
		return airlineOfferList;
	}

	@Override
	public List<AirlineTicket> getAirlineTickets() {
		logger.info("GammaService.getAirlineTickets");
		ResponseEntity<AirlineTicket[]> responseEntity  = restTemplate.getForEntity(URL.GAMMAAIRLINES_TICKETS, AirlineTicket[].class);
		AirlineTicket[] airlineTicketArray = responseEntity.getBody();
		List<AirlineTicket> airlineTicketList = Arrays.asList(airlineTicketArray);
		return airlineTicketList;
	}

	@Override
	public AirlineTicket buyAirlineTicket(String from, String to, String currency, Long amount) {
		
		ExchangeCurrencyRequest req = createExchangeCurrencyRequest(amount, currency);
		
		MonetaryAmount amt = requestCurrencyExchange(req);
		
		BuyTicketRequest buyTicketRequest = new BuyTicketRequest();
		
		AirlineRoute route = new AirlineRoute();
		route.setFrom(from);
		route.setTo(to);
		
		User user = LoginUtil.getCurrentLoggedInUser();
		Optional<Account> account = accountRepository.findByUser(user);
		buyTicketRequest.setAccountId(account.get().getId());
		buyTicketRequest.setAmount(amt.getAmount());
		buyTicketRequest.setRoute(route);
		
		logger.info("GammaService.buyAirlineTicket");
		String requestJson = JsonUtil.toJson(buyTicketRequest);
		HttpEntity<?> httpRequest = RestTemplateUtil.getHttpEntityRequest(requestJson);
		return restTemplate.postForObject(URL.GAMMAAIRLINES_TICKETS_BUY, httpRequest, AirlineTicket.class);
	}

	private MonetaryAmount requestCurrencyExchange(ExchangeCurrencyRequest req) {
		logger.info("GammaService.requestCurrencyExchange");
		String requestJson = JsonUtil.toJson(req);
		HttpEntity<?> httpRequest = RestTemplateUtil.getHttpEntityRequest(requestJson);
		return restTemplate.postForObject(URL.EXCHANGE_CURRENCY, httpRequest, MonetaryAmount.class);
	}

	private ExchangeCurrencyRequest createExchangeCurrencyRequest(Long amount, String currency) {
		MonetaryAmount amt = new MonetaryAmount();
		amt.setAmount(amount);
		amt.setCurrency(currency);
		
		ExchangeCurrencyRequest req = new ExchangeCurrencyRequest();
		req.setMonetaryAmount(amt);
		String targetCurrency = Settings.getSetting("user.currency");
		req.setTargetCurrency(targetCurrency);
		
		return req;
	}
	
	

}
