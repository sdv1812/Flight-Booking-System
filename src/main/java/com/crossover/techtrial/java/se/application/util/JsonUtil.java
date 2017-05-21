package com.crossover.techtrial.java.se.application.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	public static String toJson(Object response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String getField(String json, String fieldName) {
		try {
			ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
			if (node.has(fieldName)) {
				return node.get(fieldName).toString();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> List<T> toObjectList(  TypeReference<List<T>> t1, String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString,t1);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static <T> List<T> toList(TypeReference<List<T>> t1, String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString,t1);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static <T> T toObject(Class<T> class1, String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, class1);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
