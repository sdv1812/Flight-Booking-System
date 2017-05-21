package com.crossover.techtrial.java.se.application.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotificationUtil {
	
	private static final int port = 587;	
	private static final String host = "mail.wellness-gps.com";
	private static final String from = "sanskar@wellness-gps.com";
	private static final boolean auth = true;
	private static final String username = "sanskar@wellness-gps.com";
	private static final String password = "vishuo1234!";
	private static final Protocol protocol = Protocol.SMTP;
	private static final boolean debug = true;
	

	public static String sendEmail(String to, String subject, String body) {
		String emailSetting = Settings.getSetting("emailNotificaiton");
		if (emailSetting.equals("true")){
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			switch (protocol) {
			    case SMTPS:
			        props.put("mail.smtp.ssl.enable", true);
			        break;
			    case TLS:
			        props.put("mail.smtp.starttls.enable", true);
			        break;
			}
			
			Authenticator authenticator = null;
			if (auth) {
			    props.put("mail.smtp.auth", true);
			    authenticator = new Authenticator() {
			        private PasswordAuthentication pa = new PasswordAuthentication(username, password);
			        @Override
			        public PasswordAuthentication getPasswordAuthentication() {
			            return pa;
			        }
			    };
			}
			
			Session session = Session.getInstance(props, authenticator);
			session.setDebug(debug);
			
			MimeMessage message = new MimeMessage(session);
			try {
			    message.setFrom(new InternetAddress(from));
			    InternetAddress[] address = {new InternetAddress(to)};
			    message.setRecipients(Message.RecipientType.TO, address);
			    message.setSubject(subject);
			    message.setSentDate(new Date());
			    message.setText(body);
			    Transport.send(message);
			} catch (MessagingException ex) {
			    ex.printStackTrace();
			    return ex.getMessage();
			}
			return "success";
		} else
			return "";

		
	}
	
}
