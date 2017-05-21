package com.crossover.techtrial.java.se.gamma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.techtrial.java.se.gamma.domain.AirlineOffer;
import com.crossover.techtrial.java.se.gamma.service.GammaService;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private GammaService gammaService;

    @RequestMapping("/")
    public ModelAndView getHomePage() {
        LOGGER.info("Getting home page");
        List<AirlineOffer> airlineOffers = gammaService.getAirlineOffers();
        
        return new ModelAndView("home", "airlineOffers", airlineOffers);
    }

}