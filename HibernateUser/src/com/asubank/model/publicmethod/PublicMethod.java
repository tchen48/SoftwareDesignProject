package com.asubank.model.publicmethod;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.asubank.model.security.ImagePath;
import com.asubank.model.user.Roletype;

public class PublicMethod {
	public static void sendEmail(String reciepient, String subject, String content, int roletype){
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
			if(roletype != Roletype.MERCHANTUSER){
				message.setText(text); 
			}
			else{
				text = text.concat("\nPlease download the attached digital certificate");
				MimeBodyPart messageTextPart = new MimeBodyPart();
				messageTextPart.setText(text);
				MimeBodyPart messageBodyPart = new MimeBodyPart();
		        Multipart multipart = new MimeMultipart();
		        messageBodyPart = new MimeBodyPart();
		        String file = ImagePath.CERT;
		        String fileName = "clientKeysigned.cer";
		        DataSource source = new FileDataSource(file);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(fileName);
		        multipart.addBodyPart(messageTextPart);
		        multipart.addBodyPart(messageBodyPart);
		        message.setContent(multipart);
			}
	        
			Transport.send(message); 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
