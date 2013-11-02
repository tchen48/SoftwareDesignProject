package com.asubank.model.user;

public class User {
	private String firstname;
	private String lastname;
	private String address;
	private String email;
	private String telephone;
	private int roletype;
	private String password;
	private String transactionpassword;
	private String shortname;
	private String strID;
	public String getTransactionpassword() {
		return transactionpassword;
	}
	public void setTransactionpassword(String transactionpassword) {
		this.transactionpassword = transactionpassword;
	}
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
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
}
