package com.crossover.techtrial.java.se.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.techtrial.java.se.security.service.UserService;

@Controller
public class AccountController {
	
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	public ModelAndView getAllAccounts() {
		logger.info("AccountController.getAllAccounts");
		
		
		return null;
		
		
	}

}
