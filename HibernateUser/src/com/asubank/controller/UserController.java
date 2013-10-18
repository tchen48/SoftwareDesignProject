package com.asubank.controller;

import com.asubank.model.user.LoginResult;
import com.asubank.model.user.UserManager;
import com.asubank.model.user.User;
import com.asubank.model.visitor.Visitor;
import com.asubank.model.visitor.VisitorManager;
import com.asubank.model.combinedcommand.UserVisitor;
import com.asubank.model.security.Security;
import com.asubank.model.security.SecurityManager;
import com.asubank.model.security.StatusCode;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

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
    public String login(@ModelAttribute("combinedcommand") UserVisitor combinedCommand, Model model) throws IOException, InvalidKeyException, NoSuchAlgorithmException, ParseException {	
		combinedCommand.setVisitor(VisitorManager.createVisitor());
//		String machineID = combinedCommand.getVisitor().getMachineID();
//		VisitorManager.createCaptcha(machineID);
//		String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
//	    model.addAttribute("encodedImage",encodedImage); 
	    model.addAttribute("visitor", combinedCommand.getVisitor());
        return "login";
    }
	
	@RequestMapping("/home")
	public String home(@RequestParam String action, @ModelAttribute("combinedcommand") UserVisitor combinedCommand, Model model) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException{
		if(action.equals("Submit")){
			String machineID = combinedCommand.getVisitor().getMachineID();
			int captchaCode = VisitorManager.validateCaptcha(machineID, combinedCommand.getVisitor().getCaptchaInput());
			if(captchaCode != StatusCode.CAPTCHA_VALIDATED){
				String captchaStatus = StatusCode.CAPTCHA_STATUS[captchaCode];
				model.addAttribute("captchaStatus", captchaStatus);
				String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
			    model.addAttribute("encodedImage",encodedImage); 
			    model.addAttribute("user", combinedCommand.getUser());
			    model.addAttribute("visitor", combinedCommand.getVisitor());
			    return "login";
			}
			LoginResult loginResult = UserManager.validate(combinedCommand.getUser().getStrID(), combinedCommand.getUser().getPassword());
			
			if(loginResult.getUser() == null){
				VisitorManager.increaseFail(machineID);
				VisitorManager.createCaptcha(machineID);
				String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
			    model.addAttribute("encodedImage",encodedImage); 
				String ErrorMsg = StatusCode.LOGIN_STATUS[loginResult.getStatusCode()];
				model.addAttribute("ErrorMsg", ErrorMsg);
				model.addAttribute("visitor", combinedCommand.getVisitor());
				return "login";
			}	
			Security security = SecurityManager.querySecurity(loginResult.getUser().getStrID());
			model.addAttribute("user",loginResult.getUser());
			model.addAttribute("security",security);
			VisitorManager.deleteVisitor(machineID);
			return "home";
		}
		else{
			String machineID = combinedCommand.getVisitor().getMachineID();
			VisitorManager.createCaptcha(machineID);
			String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
		    model.addAttribute("encodedImage",encodedImage); 
		    model.addAttribute("user", combinedCommand.getUser());
		    model.addAttribute("visitor", combinedCommand.getVisitor());
	        return "login";
		}
	}
	
	@RequestMapping("/userinfo")
    public String userInfo(Model model) {
		User user = new User();
        model.addAttribute("user", user);
        return "userinfo";
    }
	
	@RequestMapping("/forgetpwd")
	public String forgetPwd(@RequestParam("machineid") String machineID){
		if(machineID.equals("") == false)
			VisitorManager.deleteVisitor(machineID);
		return "forgetpwd";
	}
	
	@RequestMapping("/sendpwd")
	public String sendPwd(@ModelAttribute("user") User user, Model model){
//		Visitor
//		combinedCommand.setVisitor(VisitorManager.createVisitor());
		String strID = user.getStrID();
		String email = user.getEmail();
		user = UserManager.queryUser(strID);
		if(user == null){
			String ErrorMsg = StatusCode.LOGIN_STATUS[StatusCode.USERID_NOT_EXIST];
			model.addAttribute("ErrorMsg", ErrorMsg);
			model.addAttribute("machineid", "");
			return "forgetpwd";
		}
		
		
		
//		String machineID = combinedCommand.getVisitor().getMachineID();
//		VisitorManager.createCaptcha(machineID);
//		String encodedImage = imageToByteArray(machineID, VISITORCAPTCHA);
//	    model.addAttribute("encodedImage",encodedImage); 
//	    model.addAttribute("visitor", combinedCommand.getVisitor());
		
	}
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("user") User user, Model model) {
        String message = "";    	
    	message = UserManager.createUser(user.getFirstname(), user.getLastname(), user.getAddress(), user.getEmail(), user.getTelephone(), user.getRoletype(),
    			user.getPassword());
        SecurityManager.createSecurity(message);
    	model.addAttribute("message", message);
        return "result";
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
