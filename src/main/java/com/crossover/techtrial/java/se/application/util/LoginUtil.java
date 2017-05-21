package com.crossover.techtrial.java.se.application.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.crossover.techtrial.java.se.security.domain.CurrentUser;
import com.crossover.techtrial.java.se.security.domain.User;

public class LoginUtil {	
	public static User getCurrentLoggedInUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return ((CurrentUser)principal).getUser();
	}
		
}
