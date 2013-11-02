package com.asubank.model.merchant;

public class Merchant {

	private int Id;
	private long checkingID;
	public long getCheckingID() {
		return checkingID;
	}

	public void setCheckingID(long checkingID) {
		this.checkingID = checkingID;
	}

	private long customerid;
	private double pay_amount;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	public long getcustomerid() {
		return customerid;
	}

	public void setcustomerid(long customerid) {
		this.customerid = customerid;
	}

	public double getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(double pay_amount) {
		this.pay_amount = pay_amount;
	}

}
