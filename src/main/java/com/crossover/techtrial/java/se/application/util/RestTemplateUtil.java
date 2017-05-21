package com.crossover.techtrial.java.se.application.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestTemplateUtil {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);
	
	public static HttpEntity<?> getHttpEntityRequest(String json) {
		logger.info("Getting header for " + json);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(json, headers);
		
	}

}
