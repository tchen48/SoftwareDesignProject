package com.asubank.model.recipient;

public class Recipient {
	private int Id;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	private String recipient_nickname;
	private String recipient_lastname;
	private long recipient_accountnumber;
	private String strID;
	
	public String getRecipient_nickname() {
		return recipient_nickname;
	}
	public void setRecipient_nickname(String recipient_nickname) {
		this.recipient_nickname = recipient_nickname;
	}
	public String getRecipient_lastname() {
		return recipient_lastname;
	}
	public void setRecipient_lastname(String recipient_lastname) {
		this.recipient_lastname = recipient_lastname;
	}
	public long getRecipient_accountnumber() {
		return recipient_accountnumber;
	}
	public void setRecipient_accountnumber(long recipient_accountnumber) {
		this.recipient_accountnumber = recipient_accountnumber;
	}
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
	
}
	

