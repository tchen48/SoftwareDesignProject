package com.asubank.controller;

public class UserInfoErrorCode {
	public static final int NO_ERROR = 0;
	public static final int ACCOUNT_PASSWORD_NOT_CONFIRMED = 1;
	public static final int TRANSACTION_PASSWORD_NOT_CONFIRMED = 2;
	public static final int SAME_ACCOUNT_TRANSACTION_PASSWORD = 3;
	public static final int SIMPLE_ACCOUNT_PASSWORD = 4;
	public static final int SIMPLE_TRANSACTION_PASSWORD = 5;
	public static final int INVALID_DOB = 6;
	public static final String[] USERINFOERROR = {"No Error", 
												  "Account Password is not confirmed",
												  "Transaction Password is not confirmed",
												  "Account Password and Transaction Password should be different",
												  "Account Password is too simple",
												  "Transaction Password is too simple",
												  "DOB is invalid"};
	
//	public static final int ACCT_NOT_BLOCKED = 0;
//	public static final int ACCT_BLOCKED = 1;
//	
//	public static final int CAPTCHA_VALIDATED = 0;
//	public static final int CAPTCHA_NOT_CORRECT = 2;
//	public static final int CAPTCHA_EXPIRED = 3;
//	public static final String[] CAPTCHA_STATUS = {"CAPTCHA Validated", "UserID not exist", "Incorrect CAPTCHA", "Expired CAPTCHA"};
//	
//	public static final int LOGIN_SUCCESS = 0;
//	public static final int PASSWORD_NOT_CORRECT = 2;
//	public static final int ACCOUNT_BLOCK = 3;
//	public static final String[] LOGIN_STATUS = {"Login success", "UserID not exist", "Incorrect Password", "Blocked Account"};

}
