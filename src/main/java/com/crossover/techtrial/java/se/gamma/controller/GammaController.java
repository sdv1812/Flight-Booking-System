package com.crossover.techtrial.java.se.gamma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.techtrial.java.se.gamma.domain.AirlineRoute;
import com.crossover.techtrial.java.se.gamma.domain.AirlineTicket;
import com.crossover.techtrial.java.se.gamma.domain.BuyTicketRequest;
import com.crossover.techtrial.java.se.gamma.service.GammaService;

@Controller
public class GammaController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(GammaController.class);
    
    @Autowired
    private GammaService gammaService;
    
    @RequestMapping(value = "/user/tickets", method=RequestMethod.GET)
    public ModelAndView getAirlineTickets() {
    	LOGGER.info("GammaController.getAirlineTickets");
    	List<AirlineTicket> airlineTickets = gammaService.getAirlineTickets();
    	ModelAndView model = new ModelAndView("my-tickets");
    	model.addObject("airlineTickets", airlineTickets);
    	return model;
    }
    
    @RequestMapping(value = "/user/buy-ticket", method=RequestMethod.GET)
    public ModelAndView buyTicket(@RequestParam("from")String from, 
    								@RequestParam("to")String to,
    								@RequestParam("currency") String currency,
    								@RequestParam("amount")String amount) {
    	LOGGER.info("GammaController.buyTicket");
    	AirlineTicket ticket = gammaService.buyAirlineTicket(from,to,currency,Long.parseLong(amount));
    	LOGGER.info("ticket = " + ticket);
		return new ModelAndView("success");
    }

}
