package com.crossover.techtrial.java.se.application.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

	private static Properties settings = new Properties(); 
	
	static{
		loadConfiguration();
		System.out.println("loading properties");
	}

	public static void loadConfiguration(){
		InputStream settingsStream = Settings.class.getClassLoader().getResourceAsStream("settings.properties");	
		try {
			settings.load(settingsStream);
			System.out.println("setting Stream " + settingsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static String  getSetting(String key){
		return settings.getProperty(key);
	}
}
