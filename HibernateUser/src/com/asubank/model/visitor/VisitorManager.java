package com.asubank.model.visitor;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;     

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.controller.InputValidation;
import com.asubank.model.combinedcommand.UserInformation;
import com.asubank.model.publicmethod.PublicMethod;
import com.asubank.model.security.ImagePath;
import com.asubank.model.security.OneTimePasswordAlgorithm;
import com.asubank.model.security.Security;
import com.asubank.model.security.SessionFactoryUtil;
import com.asubank.model.security.StatusCode;
import com.asubank.model.security.SecurityManager;
import com.asubank.model.user.Roletype;
import com.asubank.model.user.User;
import com.asubank.model.user.UserManager;

public class VisitorManager {
	private static Date defaultDate;
	private static SimpleDateFormat df;
	private static Session session;
	
	static{
		String date0 = "1970-01-01 00:00:00";
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			defaultDate = df.parse(date0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static Visitor createVisitor(){
		String machineID = createMachineID();
		createSession();
		Visitor visitor = new Visitor(machineID);
		session.save(visitor);
		session.getTransaction().commit();
		session.close();
		return visitor;
	}
	
	private static String createMachineID(){
		UUID uuid = UUID.randomUUID();  
		return uuid.toString();
	}
	
	public static void deleteVisitor(String machineID){
		createSession();
		String hql = "Delete FROM Visitor Where machineID=:machineID";
		Query query = session.createQuery(hql);
		query.setString("machineID", machineID);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public static Visitor queryVisitor(String machineID){
		createSession();
		Query query = session.createQuery("from Visitor as v where v.machineID=:machineID");
		query.setString("machineID", machineID);
		List <Visitor>list = query.list();
		Visitor visitor = null;
		java.util.Iterator<Visitor> iter = list.iterator();
		while (iter.hasNext()) {
			visitor = iter.next();
		}
		session.close();
		return visitor;
	}
	
	public static void createCaptcha(String machineID) throws ParseException, InvalidKeyException, NoSuchAlgorithmException, IOException{
		String captcha = SecurityManager.createRandomString();
		String storagePath = ImagePath.VISITORCAPTCHA;
		SecurityManager.createImage(captcha, machineID, storagePath);
		String captchaNoSpace = captcha.replaceAll("\\s+", "").toLowerCase();
		
		createSession();
		String hql = "update Visitor as v set v.captcha=:captcha, v.captchaStart=:captchaStart where machineID=:machineID";
		Query query = session.createQuery(hql);
		query.setString("captcha", captchaNoSpace);
		query.setString("captchaStart", df.format(new Date()));
		query.setString("machineID", machineID);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return;
//		Visitor visitor = new Visitor(machineID);
//		visitor.setCaptcha(captcha);
//		return visitor;
	}
	private static void deleteCaptcha(String machineID){
		createSession();
//		String hql = "Delete FROM Visitor Where machineID=:machineID";
		String hql = "update Visitor as v set v.captcha=:captcha, v.captchaStart=:captchaStart where machineID=:machineID";
		Query query = session.createQuery(hql);
		query.setString("machineID", machineID);
		query.setDate("captchaStart", defaultDate);
		query.setString("captcha", "");
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		String filepath = ImagePath.VISITORCAPTCHA +  machineID + ".png";
		File file = new File(filepath);
		if(file.isFile() && file.exists()){
			file.delete();
		}
	}
	public static int validateCaptcha(String machineID, String captchaInput, int source) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException{
		Visitor visitor = VisitorManager.queryVisitor(machineID);
		if(visitor == null)
			return StatusCode.USERID_NOT_EXIST;
		if(visitor.getFail() == 0 && source == CapValidationRequestSource.LOGIN){
			return StatusCode.CAPTCHA_VALIDATED;
		}
		if(InputValidation.validateCaptcha(captchaInput) != null){
			return StatusCode.CAPTCHA_NOT_CORRECT;
		}
		
		Date start = visitor.getCaptchaStart();
		Date current = new Date();
		long difference = current.getTime() - start.getTime();
		if(difference > 1000 * 60 * 5){
			createCaptcha(machineID);
			return StatusCode.CAPTCHA_EXPIRED;
		}
		if(captchaInput.toLowerCase().equals(visitor.getCaptcha()) != true){
			createCaptcha(machineID);
			return StatusCode.CAPTCHA_NOT_CORRECT;
		}	
		deleteCaptcha(machineID);
		return StatusCode.CAPTCHA_VALIDATED;	
	}
	
	public static void increaseFail(String machineID){
		Visitor visitor = queryVisitor(machineID);
		int fail = visitor.getFail();
		if(fail == 0){
			fail++;
			createSession();
			String hql = "update Visitor as v set v.fail=:fail where machineID=:machineID";
			Query query = session.createQuery(hql);
			query.setString("machineID", machineID);
			query.setInteger("fail", fail);
			query.executeUpdate(); 
			session.getTransaction().commit();
			session.close();
			return;
		}
	}
	
	public static String createOtp(String machineID, UserInformation userInfo) throws ParseException, InvalidKeyException, NoSuchAlgorithmException{
		String password = userInfo.getPassword();
		byte[] secret = password.getBytes();
		SimpleDateFormat dateformat = new SimpleDateFormat("yy-MM-dd HH:mm");
		Date date = new Date();
		String strDate = dateformat.format(date);
		date = dateformat.parse(strDate);
		long movingFactor = date.getTime();
		int codedigit = 8;
		int offset = (int)(Math.random()*100);
		boolean addChecksum = false;
		String otp = OneTimePasswordAlgorithm.generateOTP(secret, movingFactor, codedigit, addChecksum, offset);
		
		createSession();
		String hql = "update Visitor as v set v.otp=:otp, v.otpStart=:otpStart where machineID=:machineID";
		Query query = session.createQuery(hql);
		query.setString("otp", otp);
		query.setString("otpStart", df.format(new Date()));
		query.setString("machineID", machineID);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return otp;
//		System.out.println("OTP: " + otp);
//		sendEmail(user.get)
//		Visitor visitor = new Visitor(machineID);
//		return visitor;
//		return new Visitor(machineID, otp);
	}
	public static void deleteOtp(String machineID){
		createSession();
		String hql = "update Visitor as v set v.otp=:otp, v.otpStart=:otpStart where machineID=:machineID";
		Query query = session.createQuery(hql);
		query.setString("machineID", machineID);
		query.setDate("otpStart", defaultDate);
		query.setString("otp", "");
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
	public static int validateOtp(String machineID, String otpInput){
		Visitor visitor = queryVisitor(machineID);
		if(visitor == null)
			return StatusCode.USERID_NOT_EXIST;
		if(InputValidation.validateOTP(otpInput) != null){
			return StatusCode.OTP_NOT_CORRECT;
		}
		
		Date start = visitor.getOtpStart();
		Date current = new Date();
		long difference = current.getTime() - start.getTime();
		if(difference > 1000 * 60 * 5){
			deleteOtp(machineID);
			return StatusCode.OTP_EXPIRED;
		}
		if(otpInput.equals(visitor.getOtp()) != true){
			deleteOtp(machineID);
			return StatusCode.OTP_NOT_CORRECT;
		}	
		deleteOtp(machineID);
		return StatusCode.OTP_VALIDATED;	
	}
	
	public static void sendOTP(String otp, String recipient){
		String subject = "One Time Password from ASUBank";
		String content = "Dear user, \n This is your One Time Password: " + otp + ". Please validate it within one minute.";
		PublicMethod.sendEmail(recipient, subject, content, Roletype.NONMERCHANTUSER);
	}
	
	public static void sendNewAccountInfo(String userID, String password, String transPW, String recipient, int roletype){
		String subject = "One Time Password from ASUBank";
		String content = "Dear user, \nYou just create a new account in ASUBank."
					   + "\nThe userID is " + userID 
					   + "\nThe account password is " + password
					   + "\nThe transaction password is " + transPW;
		PublicMethod.sendEmail(recipient, subject, content, roletype);
	}
}
