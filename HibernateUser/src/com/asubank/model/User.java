package com.asubank.model;

public class User {
	private int userID;
	private int deptID;
	private String firstname;
	private String lastname;
	private int roleID;
	private String accno;
	private double amount;
	private String cardno;
	private int pinno;
	private String password;
	private String shortname;
	private String strID;
	
	public void setUserID(int userID){
		this.userID = userID;
	}
	
	public void setDeptID(int deptID){
		this.deptID = deptID;
	}
	
	public void setFirstname(String firstname){
		this.firstname = new String(firstname);
	}
	
	public void setLastname(String lastname){
		this.lastname = new String(lastname);
	}
	
	public void setRoleID(int roleID){
		this.roleID = roleID;
	}
	
	public void setAccno(String accno){
		this.accno = new String(accno);
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public void setCardno(String cardno){
		this.cardno = new String(cardno);
	}
	
	public void setPinno(int pinno){
		this.pinno = pinno;
	}
	
	public void setPassword(String password){
		this.password = new String(password);
	}
	
	public void setShortname(String shortname){
		this.shortname = shortname;
	}
	
	public void setStrID(String strID){
		this.strID = new String(strID);
	}
	
	public int getUserID(){
		return userID;
	}
	
	public int getDeptID(){
		return deptID;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	public String getLastname(){
		return lastname;
	}
	
	public int getRoleID(){
		return roleID;
	}
	
	public String getAccno(){
		return accno;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public String getCardno(){
		return cardno;
	}
	
	public int getPinno(){
		return pinno;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getShortname(){
		return shortname;
	}
	
	public String getStrID(){
		return strID;
	}
	
	public void view_trans(){};
	
}
