package com.asubank.model.security;

public class StatusCode {
	public static final int OTP_VALIDATED = 0;
	public static final int USERID_NOT_EXIST = 1;
	public static final int OTP_NOT_CORRECT = 2;
	public static final int OTP_EXPIRED = 3;
	public static final String[] STATUS = {"OTP Validated", "UserID not exist", "Incorrect OTP", "Expired OTP"};
	
	public static final int ACCT_NOT_BLOCKED = 0;
	public static final int ACCT_BLOCKED = 1;
	
	public static final int CAPTCHA_VALIDATED = 0;
	public static final int CAPTCHA_NOT_CORRECT = 2;
	public static final int CAPTCHA_EXPIRED = 3;
	public static final String[] CAPTCHA_STATUS = {"CAPTCHA Validated", "UserID not exist", "Incorrect CAPTCHA", "Expired CAPTCHA"};
	
	public static final int LOGIN_SUCCESS = 0;
	public static final int PASSWORD_NOT_CORRECT = 2;
	public static final int ACCOUNT_BLOCK = 3;
	public static final String[] LOGIN_STATUS = {"Login success", "UserID/VisitorID not exist", "Incorrect Password", "Blocked Account"};
}
