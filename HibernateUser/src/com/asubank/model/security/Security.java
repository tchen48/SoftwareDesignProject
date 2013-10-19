package com.asubank.model.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Security {
	private static Date defaultDate;
	private String strID;
	private String otp;
	private String otpInput;
	private Date otpStart;
//	private Date otpEnd;
	private int fail;
	private Date blockStart;
//	private Date blockEnd;
	private boolean block;
	private String captcha;
	private String captchaInput;
	private Date captchaStart;
	private Date lastPasswordUpdate;
	private boolean loginStatus;
	private String transPwd;
//	private String transPwdConfirm;
	
	static{
		String date0 = "1970-01-01 00:00:00";
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			defaultDate = dateformat.parse(date0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Security(){}
	
	public Security(String strID, String otp){
		this.strID = new String(strID);
		this.otp = new String(otp);
		this.otpInput = "";
		this.otpStart = defaultDate;
//		this.otpEnd = null;
		this.fail = 0;
		this.blockStart = defaultDate;
//		this.blockEnd = null;
		this.block = false;
		this.captcha = "";
		this.captchaInput = "";
		this.captchaStart = defaultDate;
	}
	
//	public Security(String strID, String otp, String otpInput, Date otpStart, int fail, Date blockStart, boolean block){
//		this.strID = new String(strID); 
//		this.otp = new String(otp);
//		this.otpInput = new String(otpInput);
//		this.otpStart = otpStart;
////		this.otpEnd = otpEnd;
//		this.fail = fail;
//		this.blockStart = blockStart;
////		this.blockEnd = blockEnd;
//		this.block = block;
//	}
	public Security(String strID){
		this.strID =  new String(strID); 
		this.otp = "";
		this.otpInput = "";
		this.otpStart = defaultDate;
//		this.otpEnd = null;
		this.fail = 0;
		this.blockStart = defaultDate;
//		this.blockEnd = null;
		this.block = false;
		this.captcha = "";
		this.captchaInput = "";
		this.captchaStart = defaultDate;
		this.lastPasswordUpdate = new Date();
		this.loginStatus = false;
	}
	public void setStrID(String strID){
		this.strID = new String(strID);
	}
	public void setOtp(String otp){
		this.otp = new String(otp);
	}
	public void setOtpInput(String otpInput){
		this.otpInput = new String(otpInput);
	}
	public void setOtpStart(Date otpStart){
		this.otpStart = otpStart;
	}
//	public void setOtpEnd(Date otpEnd){
//		this.otpEnd = otpEnd;
//	}
	public void setFail(int fail){
		this.fail = fail;
	}
	public void setBlockStart(Date blockStart){
		this.blockStart = blockStart;
	}
//	public void setBlockEnd(Date blockEnd){
//		this.blockEnd = blockEnd;
//	}
	public void setBlock(boolean block){
		this.block = block;
	}
	public void setCaptcha(String captcha){
		this.captcha = new String(captcha);
	}
	public void setCaptchaInput(String captchaInput){
		this.captchaInput = new String(captchaInput);
	}
	public void setCaptchaStart(Date captchaStart){
		this.captchaStart = captchaStart;
	}
	public String getStrID(){
		return strID;
	}
	public String getOtp(){
		return otp;
	}
	public String getOtpInput(){
		return otpInput;
	}
	public Date getOtpStart(){
		return otpStart;
	}
//	public Date getOtpEnd(){
//		return otpEnd;
//	}
	public int getFail(){
		return fail;
	}
	public Date getBlockStart(){
		return blockStart;
	}
//	public Date getBlockEnd(){
//		return blockEnd;
//	}
	public boolean getBlock(){
		return block;
	}	
	public String getCaptcha(){
		return captcha;
	}
	public String getCaptchaInput(){
		return captchaInput;
	}
	public Date getCaptchaStart(){
		return captchaStart;
	}
	public Date getLastPasswordUpdate() {
		return lastPasswordUpdate;
	}

	public void setLastPasswordUpdate(Date lastPasswordUpdate) {
		this.lastPasswordUpdate = lastPasswordUpdate;
	}

	public boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getTransPwd() {
		return transPwd;
	}

	public void setTransPwd(String transPwd) {
		this.transPwd = transPwd;
	}

//	public String getTransPwdConfirm() {
//		return transPwdConfirm;
//	}
//
//	public void setTransPwdConfirm(String transPwdConfirm) {
//		this.transPwdConfirm = transPwdConfirm;
//	}
}
