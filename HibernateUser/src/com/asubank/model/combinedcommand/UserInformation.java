package com.asubank.model.combinedcommand;

import java.util.Date;

import com.asubank.model.pii.Pii;
import com.asubank.model.security.Security;
import com.asubank.model.user.User;
import com.asubank.model.visitor.Visitor;

public class UserInformation {
	private String firstname;
	private String lastname;
	private String dobYear;
	private String dobDay;
	private String dobMonth;
//	private int dobYear;
//	private int dobDay;
//	private int dobMonth;
	private String ssn;
	private String address;
	private String email;
	private String telephone;
	private int roletype;
	private String password;
	private String pwdConfirm;
	private String transPwd;
	private String transPwdConfirm;
	private Visitor visitor;
	
	public Visitor getVisitor() {
		return visitor;
	}
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
//	public int getDobDay() {
//		return dobDay;
//	}
//	public void setDobDay(int dobDay) {
//		this.dobDay = dobDay;
//	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
//	public Date getDob() {
//		return dob;
//	}
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getRoletype() {
		return roletype;
	}
	public void setRoletype(int roletype) {
		this.roletype = roletype;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPwdConfirm() {
		return pwdConfirm;
	}
	public void setPwdConfirm(String pwdConfirm) {
		this.pwdConfirm = pwdConfirm;
	}
	public String getTransPwd() {
		return transPwd;
	}
	public void setTransPwd(String transPwd) {
		this.transPwd = transPwd;
	}
	public String getTransPwdConfirm() {
		return transPwdConfirm;
	}
	public void setTransPwdConfirm(String transPwdConfirm) {
		this.transPwdConfirm = transPwdConfirm;
	}
	public String getDobYear() {
		return dobYear;
	}
	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}
	public String getDobDay() {
		return dobDay;
	}
	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}
	public String getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}
	
//	public int getDobYear() {
//		return dobYear;
//	}
//	public void setDobYear(int dobYear) {
//		this.dobYear = dobYear;
//	}
//	public int getDobMonth() {
//		return dobMonth;
//	}
//	public void setDobMonth(int dobMonth) {
//		this.dobMonth = dobMonth;
//	}
	
	
	
//	private User user;
//	private Pii pii;
//	private Security security;
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public Pii getPii() {
//		return pii;
//	}
//	public void setPii(Pii pii) {
//		this.pii = pii;
//	}
//	public Security getSecurity() {
//		return security;
//	}
//	public void setSecurity(Security security) {
//		this.security = security;
//	}
}
