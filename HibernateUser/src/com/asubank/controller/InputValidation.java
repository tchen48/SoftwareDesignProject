package com.asubank.controller;

import com.asubank.model.combinedcommand.UserInformation;

public class InputValidation {
	public static String validateUserInformation(UserInformation userInformation){
		if(RegExPattern.validateName(userInformation.getFirstname()) == false){
			return InputErrorCode.FIRSTNAME_ERROR;
		}
		if(RegExPattern.validateName(userInformation.getLastname()) == false){
			return InputErrorCode.LASTNAME_ERROR;
		}
		if((RegExPattern.validateDobYear(userInformation.getDobYear()) == false) ||
		   (RegExPattern.validateDobDay(userInformation.getDobDay()) == false) ||
		   (RegExPattern.validateDobMonth(userInformation.getDobMonth()) == false)){
			return InputErrorCode.DOB_ERROR;
		}
		if(RegExPattern.validateSsn(userInformation.getSsn()) == false){
			return InputErrorCode.SSN_ERROR;
		}
		if(RegExPattern.validateAddress(userInformation.getAddress()) == false){
			return InputErrorCode.ADDRESS_ERROR;
		}
		if(RegExPattern.validateEmail(userInformation.getEmail()) == false){
			return InputErrorCode.EMAIL_ERROR;
		}
		if(RegExPattern.validateTelephone(userInformation.getTelephone()) == false){
			return InputErrorCode.TELEPHONE_ERROR;
		}
		if(RegExPattern.validatePassword(userInformation.getPassword()) == false){
			return InputErrorCode.PASSWORD_ERROR;
		}
		if(RegExPattern.validatePassword(userInformation.getPwdConfirm()) == false){
			return InputErrorCode.PASSWORD_ERROR;
		}
		if(RegExPattern.validatePassword(userInformation.getTransPwd()) == false){
			return InputErrorCode.PASSWORD_ERROR;
		}
		if(RegExPattern.validatePassword(userInformation.getTransPwdConfirm()) == false){
			return InputErrorCode.PASSWORD_ERROR;
		}
		return null;
	}
	
	public static String validateLogin(String strID, String password){
		if(RegExPattern.validateStrID(strID) == false){
			return InputErrorCode.STRID_ERROR;
		}
		if(RegExPattern.validatePassword(password) == false){
			return InputErrorCode.PASSWORD_ERROR;
		}
		return null;
	}
	
	public static String validateOTP(String otp){
		if(RegExPattern.validateOTP(otp) == false){
			return InputErrorCode.OTP_ERROR;
		}
		return null;
	}
	
	public static String validateCaptcha(String captcha){
		if(RegExPattern.validateCaptcha (captcha) == false){
			return InputErrorCode.CAPTCHA_ERROR;
		}
		return null;
	}
	
	public static String validateStrID(String strID){
		if(RegExPattern.validateStrID(strID) == false){
			return InputErrorCode.STRID_ERROR;
		}
		return null;
	}
}
