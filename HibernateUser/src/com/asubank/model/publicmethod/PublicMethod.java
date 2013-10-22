package com.asubank.model.publicmethod;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PublicMethod {
	public static void sendEmail(String reciepient, String subject, String content){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("asubankgroup6","asubank123");
				}
			}); 
		try { 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("asubankgroup6@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(reciepient));
			message.setSubject(subject);
			String text = content;
			message.setText(text); 
			Transport.send(message); 
//			System.out.println("Done"); 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
