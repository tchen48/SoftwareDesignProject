package com.asubank.controller;

import com.asubank.model.user.LoginResult;
import com.asubank.model.user.UserManager;
import com.asubank.model.user.User;
import com.asubank.model.visitor.CapValidationRequestSource;
import com.asubank.model.visitor.Visitor;
import com.asubank.model.visitor.VisitorManager;
import com.asubank.model.combinedcommand.UserInformation;
import com.asubank.model.combinedcommand.UserVisitor;
import com.asubank.model.pii.PartialPii;
import com.asubank.model.pii.Pii;
import com.asubank.model.pii.PiiManager;
import com.asubank.model.security.Security;
import com.asubank.model.security.SecurityManager;
import com.asubank.model.security.StatusCode;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import javax.imageio.ImageIO;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

@Controller("userController")
public class UserController {
	private static final String USERCAPTCHA = "usercaptcha";
	private static final String VISITORCAPTCHA = "visitorcaptcha";
	
	@RequestMapping("/login")
    public String login(@ModelAttribute("uservisitor") UserVisitor userVisitor, Model model) throws IOException, InvalidKeyException, NoSuchAlgorithmException, ParseException {	
		userVisitor.setVisitor(VisitorManager.createVisitor());
//		String machineID = combinedCommand.getVisitor().getMachineID();
//		VisitorManager.createCaptcha(machineID);
//		String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
//	    model.addAttribute("encodedImage",encodedImage); 
	    model.addAttribute("visitor", userVisitor.getVisitor());
        return "login";
    }
	
	@RequestMapping("/home")
	public String home(@RequestParam String action, @ModelAttribute("uservisitor") UserVisitor userVisitor, Model model) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException{
		if(action.equals("Submit")){
			String machineID = userVisitor.getVisitor().getMachineID();
			int captchaCode = VisitorManager.validateCaptcha(machineID, userVisitor.getVisitor().getCaptchaInput(), CapValidationRequestSource.LOGIN);
			if(captchaCode != StatusCode.CAPTCHA_VALIDATED){
				String captchaStatus = StatusCode.CAPTCHA_STATUS[captchaCode];
				model.addAttribute("captchaStatus", captchaStatus);
				String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
			    model.addAttribute("encodedImage",encodedImage); 
			    model.addAttribute("user", userVisitor.getUser());
			    model.addAttribute("visitor", userVisitor.getVisitor());
			    return "login";
			}
			LoginResult loginResult = UserManager.validate(userVisitor.getUser().getStrID(), userVisitor.getUser().getPassword());
			
			if(loginResult.getUser() == null){
				VisitorManager.increaseFail(machineID);
				VisitorManager.createCaptcha(machineID);
				String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
			    model.addAttribute("encodedImage",encodedImage); 
				String ErrorMsg = StatusCode.LOGIN_STATUS[loginResult.getStatusCode()];
				model.addAttribute("ErrorMsg", ErrorMsg);
				model.addAttribute("visitor", userVisitor.getVisitor());
				return "login";
			}	
			Security security = SecurityManager.querySecurity(loginResult.getUser().getStrID());
			model.addAttribute("user",loginResult.getUser());
			model.addAttribute("security",security);
			VisitorManager.deleteVisitor(machineID);
			return "home";
		}
		else{
			String machineID = userVisitor.getVisitor().getMachineID();
			VisitorManager.createCaptcha(machineID);
			String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
		    model.addAttribute("encodedImage",encodedImage); 
		    model.addAttribute("user", userVisitor.getUser());
		    model.addAttribute("visitor", userVisitor.getVisitor());
	        return "login";
		}
	}
	
	@RequestMapping("/applynewaccount")
    public String userInfo(@RequestParam("machineid") String machineID, Model model) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException {
//		User user = new User();
//        model.addAttribute("user", user);
		VisitorManager.deleteVisitor(machineID);
		
		Visitor visitor = VisitorManager.createVisitor();		
		machineID = visitor.getMachineID();
		VisitorManager.createCaptcha(machineID);
		String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
	    model.addAttribute("encodedImage",encodedImage); 
		model.addAttribute("visitor",visitor);		
		UserInformation userinformation = new UserInformation();
		model.addAttribute("userinformation", userinformation);
        return "applynewaccount";
    }
	
	@RequestMapping("/forgetpwd")
	public String forgetPwd(@RequestParam("machineid") String machineID){
		if(machineID.equals("") == false)
			VisitorManager.deleteVisitor(machineID);
		return "forgetpwd";
	}
	
//	@RequestMapping("/sendpwd")
//	public String sendPwd(@ModelAttribute("user") User user, Model model){
////		Visitor
////		combinedCommand.setVisitor(VisitorManager.createVisitor());
//		String strID = user.getStrID();
//		String email = user.getEmail();
//		user = UserManager.queryUser(strID);
//		if(user == null){
//			String ErrorMsg = StatusCode.LOGIN_STATUS[StatusCode.USERID_NOT_EXIST];
//			model.addAttribute("ErrorMsg", ErrorMsg);
//			model.addAttribute("machineid", "");
//			return "forgetpwd";
//		}
		
		
		
//		String machineID = combinedCommand.getVisitor().getMachineID();
//		VisitorManager.createCaptcha(machineID);
//		String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
//	    model.addAttribute("encodedImage",encodedImage); 
//	    model.addAttribute("visitor", combinedCommand.getVisitor());
		
//	}
	
	@RequestMapping("/createuser")
    public String createUser(@RequestParam String action, @ModelAttribute("userinformation") UserInformation userInformation, Model model) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException {
		if(action.equals("Submit")){
			String machineID = userInformation.getVisitor().getMachineID();
			int captchaCode = VisitorManager.validateCaptcha(machineID, userInformation.getVisitor().getCaptchaInput(), CapValidationRequestSource.REGISTRATION);
			if(captchaCode != StatusCode.CAPTCHA_VALIDATED){
				String captchaStatus = StatusCode.CAPTCHA_STATUS[captchaCode];
				model.addAttribute("captchaStatus", captchaStatus);
				String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
			    model.addAttribute("encodedImage",encodedImage); 
			    model.addAttribute("userInformation", userInformation);
			    model.addAttribute("visitor", userInformation.getVisitor());
			    return "applynewaccount";
			}
						
			int checkInfoCode = checkUserInfo(userInformation);
			if(checkInfoCode != UserInfoErrorCode.NO_ERROR){
				VisitorManager.createCaptcha(machineID);
				String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
			    model.addAttribute("encodedImage",encodedImage); 
			    model.addAttribute("userInfoError", UserInfoErrorCode.USERINFOERROR[checkInfoCode]);
				model.addAttribute("visitor", userInformation.getVisitor());
				return "applynewaccount";
			}
			
			
			User user = new User();
			
			Pii pii = new Pii();
			PartialPii partialPii = new PartialPii();
			
			user.setFirstname(userInformation.getFirstname());
			user.setLastname(userInformation.getLastname());
			user.setAddress(userInformation.getAddress());
			user.setEmail(userInformation.getEmail());
			user.setTelephone(userInformation.getTelephone());
			user.setRoletype(userInformation.getRoletype());
			user.setPassword(userInformation.getPassword());
			String message = "";    	
		    message = UserManager.createUser(user.getFirstname(), user.getLastname(), user.getAddress(), user.getEmail(), user.getTelephone(), user.getRoletype(),
		    			user.getPassword());
		    Security security = new Security(message);
//		    security.setStrID(message);
			security.setTransPwd(userInformation.getTransPwd());
			
	//		pii.setDob(userInformation.getDob());
			pii.setSsn(userInformation.getSsn());
			pii.setDobYear(userInformation.getDobYear());
			pii.setDobMonth(userInformation.getDobMonth());
	//		String dobString = pii.getDob().toString();
	//		String yearString = dobString.substring(dobString.length() - 4, dobString.length());
	//		int dobYear = Integer.parseInt(yearString);
			String ssnString = pii.getSsn();
			String ssnLastFour = ssnString.substring(ssnString.length() - 4, ssnString.length());
			partialPii.setDobYear(pii.getDobYear());
			partialPii.setSsnLastFour(ssnLastFour);
			
			
	//		User user = userinfo.getUser();
	//		Security security = userinfo.getSecurity();
	//		Pii pii = userinfo.getPii();
	//		String dobString = pii.getDob().toString();
	//		String yearString = dobString.substring(dobString.length() - 4, dobString.length());
	//		int dobYear = Integer.parseInt(yearString);
	//		String ssnString = pii.getSsn();
	//		String ssnLastFour = ssnString.substring(ssnString.length() - 4, ssnString.length());
			
	       
	    	
	    	pii.setStrID(message);
	    	partialPii.setStrID(message);
	        SecurityManager.createSecurity(security);
	        pii.setStrID(message);
	//        PartialPii partialPii = new PartialPii(dobYear, ssnLastFour, message);
	        PiiManager.createPii(pii);
	        PiiManager.createPartialPii(partialPii);
	    	model.addAttribute("message", message);
	    	VisitorManager.deleteVisitor(machineID);
	        return "result";
		}
		else{
			String machineID = userInformation.getVisitor().getMachineID();
			VisitorManager.createCaptcha(machineID);
			String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
		    model.addAttribute("encodedImage",encodedImage); 
		    model.addAttribute("visitor", userInformation.getVisitor());
	        return "applynewaccount";
		}
    }
	
	private int checkUserInfo(UserInformation userInfo){
		boolean uppercaseAcct = false;
		boolean lowercaseAcct =false;
		boolean numberAcct = false;
		boolean uppercaseTrans = false;
		boolean lowercaseTrans =false;
		boolean numberTrans = false;
		String pwd1 = userInfo.getPassword();
		String pwd2 = userInfo.getPwdConfirm();
		String transpwd1 = userInfo.getTransPwd();
		String transpwd2 = userInfo.getTransPwdConfirm();

		if(pwd1.equals(pwd2) != true){
//			return UserInfoErrorCode.USERINFOERROR[UserInfoErrorCode.ACCOUNT_PASSWORD_NOT_CONFIRMED];
			return UserInfoErrorCode.ACCOUNT_PASSWORD_NOT_CONFIRMED;
		}
		if(transpwd1.equals(transpwd2) != true){
//			return UserInfoErrorCode.USERINFOERROR[UserInfoErrorCode.TRANSACTION_PASSWORD_NOT_CONFIRMED];
			return UserInfoErrorCode.TRANSACTION_PASSWORD_NOT_CONFIRMED;
		}
		if(pwd1.equals(transpwd1)){
//			return UserInfoErrorCode.USERINFOERROR[UserInfoErrorCode.SAME_ACCOUNT_TRANSACTION_PASSWORD];
			return UserInfoErrorCode.SAME_ACCOUNT_TRANSACTION_PASSWORD;
		}
		char c;
		for(int i = 0; i < pwd1.length(); i++){
			c = pwd1.charAt(i);
			if (c >= 'a' && c <= 'z'){
				lowercaseAcct = true;
			}
			else if (c >= 'A' && c <= 'Z'){
				uppercaseAcct = true;
			}
			else if (c >= '0' && c <= '9'){
				numberAcct = true;
			}			
		}
		if(lowercaseAcct == false || uppercaseAcct == false || numberAcct == false){
//			return UserInfoErrorCode.USERINFOERROR[UserInfoErrorCode.SIMPLE_ACCOUNT_PASSWORD];
			return UserInfoErrorCode.SIMPLE_ACCOUNT_PASSWORD;
		}
		for(int i = 0; i < transpwd1.length(); i++){
			c = transpwd1.charAt(i);
			if (c >= 'a' && c <= 'z'){
				lowercaseTrans = true;
			}
			else if (c >= 'A' && c <= 'Z'){
				uppercaseTrans = true;
			}
			else if (c >= '0' && c <= '9'){
				numberTrans = true;
			}			
		}
		if(lowercaseTrans == false || uppercaseTrans == false || numberTrans == false){
//			return UserInfoErrorCode.USERINFOERROR[UserInfoErrorCode.SIMPLE_TRANSACTION_PASSWORD];
			return UserInfoErrorCode.SIMPLE_TRANSACTION_PASSWORD;
		}
//		return UserInfoErrorCode.USERINFOERROR[UserInfoErrorCode.NO_ERROR];	
		return UserInfoErrorCode.NO_ERROR;
	}
	
//	@RequestMapping("/userlist")
//    public ModelAndView userList(Model model) {
//		List<User> list = UserManager.queryAllUsers();
//        model.addAttribute("message", list);
//        return new ModelAndView("userlist", "list", list);
//    }
	
	@RequestMapping("/createotp")
	public String createOTP(@ModelAttribute("user") User user, Model model) throws InvalidKeyException, NoSuchAlgorithmException, ParseException{
		Security security = SecurityManager.createOtp(user.getStrID());
		model.addAttribute("user",user);
		model.addAttribute("security", security);
		return "home";		
	}
	
	@RequestMapping("/validateotp")
	public String validateOTP(@ModelAttribute("security") Security security, Model model){
		int statusCode = SecurityManager.validateOtp(security.getStrID(), security.getOtpInput());
		String status = StatusCode.STATUS[statusCode];
		model.addAttribute("status", status);
		User user = UserManager.queryUser(security.getStrID());
		model.addAttribute("user",user);
		return "home";
	}
	
	@RequestMapping("/createcaptcha")
	public String createCaptcha(@ModelAttribute("user") User user, Model model) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException{
		Security security = SecurityManager.createCaptcha(user.getStrID());
		String encodedImage = imageToByteArray(user.getStrID(), USERCAPTCHA);
	    model.addAttribute("encodedImage",encodedImage);
		model.addAttribute("user",user);
		model.addAttribute("security", security);
		return "home";		
	}
	
	@RequestMapping("/validatecaptcha")
	public String validateCaptcha(@ModelAttribute("security") Security security, Model model) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException{
		int statusCode = SecurityManager.validateCaptcha(security.getStrID(), security.getCaptchaInput());
		String statusCap = StatusCode.CAPTCHA_STATUS[statusCode];
		
		if(statusCode != 0){
			String encodedImage = imageToByteArray(security.getStrID(), USERCAPTCHA);
		    model.addAttribute("encodedImage",encodedImage);
		}
		
		model.addAttribute("statusCap", statusCap);
		User user = UserManager.queryUser(security.getStrID());
		model.addAttribute("user",user);
		return "home";
	}
	
	//From: http://mrbool.com/how-to-convert-image-to-byte-array-and-byte-array-to-image-in-java/25136#
	private static String imageToByteArray(String strID, String type) throws IOException{
		BufferedImage image = ImageIO.read(new File("C:\\Users\\shaosh\\Documents\\workspace-sts-3.4.0.M1\\HibernateUser\\WebContent\\images\\capbg\\"+ type + "\\" + strID + ".png"));
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(image, "png", baos);
//	    byte[] res=baos.toByteArray();
	    String encodedImage = Base64.encode(baos.toByteArray());
	    return encodedImage;
	}
	
	
	private static void main(String[] args){
		 
		InetAddress ip;
		try {
	 
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
	 
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	 
			byte[] mac = network.getHardwareAddress();
	 
			System.out.print("Current MAC address : ");
	 
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			System.out.println(sb.toString());
	 
		} catch (UnknownHostException e) {
	 
			e.printStackTrace();
	 
		} catch (SocketException e){
	 
			e.printStackTrace();
	 
		}
	 
	   }
	
}
