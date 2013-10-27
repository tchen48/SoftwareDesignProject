package com.asubank.controller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import com.asubank.model.combinedcommand.UserInformation;
import com.asubank.model.recipient.RecipientInput;
import com.asubank.model.user.ContactSet;
import com.asubank.model.user.PasswordSet;
import com.blogspot.radialmind.html.HTMLParser;
import com.blogspot.radialmind.html.HandlingException;
import com.blogspot.radialmind.xss.XSSFilter;

public class XSSProtection {

//	public static void main(String[] args) {
//		String html = "<html><head> <title> New Document </title> " +
//				"<script type='text/javascript'>  alert('dddd');   </script> " +
//				"</head> <body>" +
//				"222 <iframe  src='www.google.com'/>  1111" +
//				"<a href=http://www.xxx.com/detail.asp?id=2008 onclick='javascrpt:alert('haha')'>"+
//				"<embed ></embed>" +
//				"<link>ddd</link>" +
//				"</body></html>";
//		String v = protectAgainstXSS(html);
//		System.out.println(v);
//
//	}
	
	public static void filterUserInfo(UserInformation userInformation){
		userInformation.setFirstname(protectAgainstXSS(userInformation.getFirstname()));
		userInformation.setLastname(protectAgainstXSS(userInformation.getLastname()));
		userInformation.setAddress(protectAgainstXSS(userInformation.getAddress()));
		userInformation.setEmail(protectAgainstXSS(userInformation.getEmail()));
		userInformation.setPassword(protectAgainstXSS(userInformation.getPassword()));
		userInformation.setTransPwd(protectAgainstXSS(userInformation.getTransPwd()));
	}
	
	public static void filterPasswordSet(PasswordSet passwordSet){
		passwordSet.setOldPassword(protectAgainstXSS(passwordSet.getOldPassword()));
		passwordSet.setNewPassword(protectAgainstXSS(passwordSet.getNewPassword()));
		passwordSet.setConfirmPassword(protectAgainstXSS(passwordSet.getConfirmPassword()));
	}
	
	public static void filterContactSet(ContactSet contactSet){
		contactSet.setAddress(protectAgainstXSS(contactSet.getAddress()));
		contactSet.setEmail(protectAgainstXSS(contactSet.getEmail()));
		contactSet.setPassword(protectAgainstXSS(contactSet.getPassword()));
	}
	
	public static void filterRecipientInput(RecipientInput recipientInput){
		recipientInput.setRecipient_lastnameInput(protectAgainstXSS(recipientInput.getRecipient_lastnameInput()));
		recipientInput.setRecipient_nicknameInput(protectAgainstXSS(recipientInput.getRecipient_nicknameInput()));
	}
	
	private static String protectAgainstXSS( String html ) {
	    StringReader reader = new StringReader( html );
	    StringWriter writer = new StringWriter();
	    String text = null;
	    try {
	        // Parse incoming string from the "html" variable
	        HTMLParser.process( reader, writer, new XSSFilter(), true );
	        // Return the parsed and cleaned up string
	        text =  writer.toString();
	    } catch (HandlingException e) {
	        // Handle the error here in accordance with your coding policies...
	    }finally{
	    	try {
				writer.close();
				reader.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}	    	
	    }
	    return text;
	}
}
