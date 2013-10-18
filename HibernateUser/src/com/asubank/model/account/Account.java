package com.asubank.model.account;

public class Account{
	private String strID;
	private String checkingID;
	private double checkingBalance;
	private String savingID;
	private double savingBalance;
	private String creditID;
	private double creditBalance;
	private double maxOverdraft;
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
	public String getCheckingID() {
		return checkingID;
	}
	public void setCheckingID(String checkingID) {
		this.checkingID = checkingID;
	}
	public double getCheckingBalance() {
		return checkingBalance;
	}
	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}
	public String getSavingID() {
		return savingID;
	}
	public void setSavingID(String savingID) {
		this.savingID = savingID;
	}
	public double getSavingBalance() {
		return savingBalance;
	}
	public void setSavingBalance(double savingBalance) {
		this.savingBalance = savingBalance;
	}
	public String getCreditID() {
		return creditID;
	}
	public void setCreditID(String creditID) {
		this.creditID = creditID;
	}
	public double getCreditBalance() {
		return creditBalance;
	}
	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}
	public double getMaxOverdraft() {
		return maxOverdraft;
	}
	public void setMaxOverdraft(double maxOverdraft) {
		this.maxOverdraft = maxOverdraft;
	}	
	
}

//public class Account {
//	String accountNo;
//	double balance;
//	
//	public void setAccountNo(String accountNo){
//		this.accountNo = new String(accountNo);
//	}
//	public void setBalance(double balance){
//		this.balance = balance;
//	}
//	public String getAccountNo(){
//		return accountNo;
//	}
//	public double getBalance(){
//		return balance;
//	}
//	public double deposit(double amount){
//		balance += amount;
//		return balance;
//	}
//	public double withdraw(double amount){
//		if(balance >= amount){
//			balance -= amount;
//			return balance;
//		}
//		else{
//			return -1;
//		}
//	}
//}
