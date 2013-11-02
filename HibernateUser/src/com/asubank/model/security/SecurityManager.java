package com.asubank.model.security;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.controller.InputValidation;
import com.asubank.model.publicmethod.PublicMethod;
import com.asubank.model.user.Roletype;
import com.asubank.model.user.User;
import com.asubank.model.user.UserManager;
//import com.asubank.model.security.SessionFactoryUtil;
//import com.asubank.model.security.OneTimePasswordAlgorithm;

public class SecurityManager {
	private static Date defaultDate;
	private static SimpleDateFormat df;
	private static Session session;
	private static final int THREE_FAILS = 3;
	
	static{
		String date0 = "1970-01-01 00:00:00";
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			defaultDate = df.parse(date0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	public static void createSecurity(String strID){
		createSession();
		Security security = new Security(strID);
		session.save(security);
		session.getTransaction().commit();
		session.close();
	}
	public static void createSecurity(Security security){
		createSession();
//		Security security = new Security(strID);
		session.save(security);
		session.getTransaction().commit();
		session.close();
	}
	public static Security createOtp(String strID) throws ParseException, InvalidKeyException, NoSuchAlgorithmException{
		User user = UserManager.queryUser(strID);
		String password = user.getPassword();
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
		String hql = "update Security as s set s.otp=:otp, s.otpStart=:otpStart where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("otp", otp);
		query.setString("otpStart", df.format(new Date()));
		query.setString("strID", strID);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
//		System.out.println("OTP: " + otp);
//		sendEmail(user.get)
		return new Security(strID, otp);
	}
	public static void deleteOtp(String strID){
		createSession();
		String hql = "update Security as s set s.otp=:otp, s.otpStart=:otpStart where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		query.setDate("otpStart", defaultDate);
		query.setString("otp", "");
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public static Security querySecurity(String strID){
		createSession();
		Query query = session.createQuery("from Security as s where s.strID=:strID");
		query.setString("strID", strID);
		List <Security>list = query.list();
		Security security = null;
		java.util.Iterator<Security> iter = list.iterator();
		while (iter.hasNext()) {
			security = iter.next();
		}
		session.close();
		return security;
	}
	
	public static int validateOtp(String strID, String otpInput){
		Security security = querySecurity(strID);
		if(security == null)
			return StatusCode.USERID_NOT_EXIST;
		if(InputValidation.validateOTP(otpInput) != null){
			return StatusCode.OTP_NOT_CORRECT;
		}
		
		Date start = security.getOtpStart();
		Date current = new Date();
		long difference = current.getTime() - start.getTime();
		if(difference > 1000 * 60 * 5){
			deleteOtp(strID);
			return StatusCode.OTP_EXPIRED;
		}
		if(otpInput.equals(security.getOtp()) != true){
			deleteOtp(strID);
			return StatusCode.OTP_NOT_CORRECT;
		}	
		deleteOtp(strID);
		return StatusCode.OTP_VALIDATED;	
	}
	public static int increaseFail(String strID){
		Security security = querySecurity(strID);
		int fail = security.getFail();
		fail++;
		if(fail == THREE_FAILS){
			blockUser(strID);
			fail--;
			return StatusCode.ACCT_BLOCKED;
		}
		else{			
			createSession();
			String hql = "update Security as s set s.fail=:fail where strID=:strID";
			Query query = session.createQuery(hql);
			query.setString("strID", strID);
			query.setInteger("fail", fail);
			query.executeUpdate(); 
			session.getTransaction().commit();
			session.close();
			return StatusCode.ACCT_NOT_BLOCKED;
		}		
	}
	public static void resetFail(String strID){
		createSession();
		String hql = "update Security as s set s.fail=:fail where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		query.setInteger("fail", 0);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return;
	}
	public static void blockUser(String strID){
		createSession();
		String hql = "update Security as s set s.block=:block, s.blockStart=:blockStart where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		query.setBoolean("block", true);
		query.setString("blockStart", df.format(new Date()));
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return;
	}
	public static void unblockUser(String strID){
		createSession();
		String hql = "update Security as s set s.block=:block, s.blockStart=:blockStart where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		query.setBoolean("block", false);
		query.setDate("blockStart", defaultDate);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return;
	}
	public static boolean checkBlock(String strID){
		Security security = querySecurity(strID);
		if(security != null){
			if(security.getBlock() == true){
				Date dateStart = security.getBlockStart();
				Date current = new Date();
				long difference = current.getTime() - dateStart.getTime();
//				System.out.println("difference = " + (difference - 1000 * 60 * 60 * 24));
//				System.out.println(String.valueOf(difference > 1000 * 60 * 60 * 24));
				if(difference > 1000 * 60 * 60 * 24){
					unblockUser(strID);
					return false;
				}
				else{
					return true;
				}
			}			
			return false;
		}
		return true;
	}
	
	
	
	public static Security createCaptcha(String strID) throws ParseException, InvalidKeyException, NoSuchAlgorithmException, IOException{
		String captcha = createRandomString();
		String storagePath = ImagePath.USERCAPTCHA;
		createImage(captcha, strID, storagePath);
		String capthaNoSpace = captcha.replaceAll("\\s+", "").toLowerCase();
		
		createSession();
		String hql = "update Security as s set s.captcha=:captcha, s.captchaStart=:captchaStart where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("captcha", capthaNoSpace);
		query.setString("captchaStart", df.format(new Date()));
		query.setString("strID", strID);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		Security security = new Security(strID);
//		security.setCaptcha(captcha);
		return security;
	}
	public static void deleteCaptcha(String strID){
		createSession();
		String hql = "update Security as s set s.captcha=:captcha, s.captchaStart=:captchaStart where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		query.setDate("captchaStart", defaultDate);
		query.setString("captcha", "");
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	public static int validateCaptcha(String strID, String captchaInput) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException{
		Security security = querySecurity(strID);
		if(security == null)
			return StatusCode.USERID_NOT_EXIST;
		
		Date start = security.getCaptchaStart();
		Date current = new Date();
		long difference = current.getTime() - start.getTime();
		if(difference > 1000 * 60 * 5){
//			deleteCaptcha(strID);
			createCaptcha(strID);
			return StatusCode.CAPTCHA_EXPIRED;
		}
		if(captchaInput.toLowerCase().equals(security.getCaptcha()) != true){
//			deleteCaptcha(strID);
			createCaptcha(strID);
			return StatusCode.CAPTCHA_NOT_CORRECT;
		}	
		deleteCaptcha(strID);
		return StatusCode.CAPTCHA_VALIDATED;	
	}
	
	public static String createRandomString(){
		char[] element = new char[62];
		for (int i = 0; i < 26; i++){
			element[i] = (char)('a' + i);
			element[i + 26] = (char)('A' + i);
		}
		for (int i = 0; i < 10; i++){
			element[i + 52] = (char)('0' + i);
		}				
		char[] randomChar = new char[6];
		for(int i = 0; i < 6; i++){
			randomChar[i] = element[(int)(Math.random() * 61 + 1)];
		}
		
		String[] spaces = new String[5];
		int count;
		for(int i = 0; i < 5; i++){			
			spaces[i] = "";
			count = (int)(Math.random() * 3 + 1);
			for(int j = 0; j < count; j++){
				spaces[i] = spaces[i].concat(" ");
			}
		}
		String captcha = "";
		for (int i = 0; i < 5; i++){
			captcha = captcha.concat(String.valueOf(randomChar[i]));
			captcha = captcha.concat(spaces[i]);
		}
		captcha = captcha.concat(String.valueOf(randomChar[5]));
		return captcha;
	}
	
	public static boolean createImage(String captcha, String strID, String storagePath) throws IOException{
		int capbg = 1 + (int)(Math.random() * 7 + 1);
		@SuppressWarnings("unused")
		String capbgnum = String.valueOf(capbg);
		String imageLocation = ImagePath.BACKGROUND + capbgnum + ".jpg";
		
		String storedFile = storagePath + strID + ".png";
	    BufferedImage image = ImageIO.read(new File(imageLocation));
	    Graphics g = image.getGraphics();
	    g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(20f));	    
	    g.drawString(captcha, 50, 40);
	    g.dispose();
	    File file = new File(storedFile);
	    ImageIO.write(image, "png", file);
	    return true;
	}
	
	public static void sendOTP(String otp, String recipient){
		//"shaosh0913@gmail.com";
		String subject = "One Time Password from ASUBank";
		String content = "Dear user, \n This is your One Time Password: " + otp + ". Please validate it within one minute.";
		PublicMethod.sendEmail(recipient, subject, content, Roletype.NONMERCHANTUSER);
	}
	
	public static void updatePassword(String strID, String newPW){
		createSession();
		String hql = "update User as u set u.password=:password where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("password", EncryptBase64.encodeString(newPW));
		query.setString("strID", strID);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		updateLastPWUpdate(strID);
	}
	
	private static void updateLastPWUpdate(String strID){
		createSession();
		String hql = "update Security as s set s.lastPasswordUpdate=:lastPasswordUpdate where strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("lastPasswordUpdate", df.format(new Date()));
		query.setString("strID", strID);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
	public static void sendTempPw(String tempPW, String recipient){
		String subject = "Temporary Password from ASUBank";
		String content = "Dear ASUBank Customer, \nThis is your Temporary Password:\n" + tempPW + "\nPlease access your account and change your password as soon as possible.\nYours,\nASUBank";
		PublicMethod.sendEmail(recipient, subject, content, Roletype.NONMERCHANTUSER);
	}
	
	public static String createRandomPW(){
		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7',
			     '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			     'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			     'y', 'z', 'A', 'B','C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			     'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			     'Z'};
		Random random = new Random();
		int randomLength = 3 + random.nextInt(2);
		int randomCount = 4 + random.nextInt(2);
		long mills = new Date().getTime();
		String[] passwords = new String[randomCount];
	    random = new Random(mills);
	    String finalpw = "";
	    for (int i = 0; i < randomCount; i++) {
	    	StringBuilder password = new StringBuilder("");
    		for (int m = 1; m <= randomLength; m++) {
    			password.append(chars[random.nextInt(62)]);
    		}
    		passwords[i] = password.toString();
    		finalpw = finalpw.concat(passwords[i]);
	    }
//	    System.out.println(finalpw);
	    return finalpw;
	}
}
