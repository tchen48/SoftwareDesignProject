package com.asubank.model.pii;

import java.util.Date;

public class Pii {
	private int dobDay;
	private int dobYear;
	private int dobMonth;
	private String ssn;
	private String strID;
	public int getDobDay() {
		return dobDay;
	}
	public void setDobDay(int dobDay) {
		this.dobDay = dobDay;
	}	
	public String getSsn() {
		return ssn;
	}
	public int getDobYear() {
		return dobYear;
	}
	public void setDobYear(int dobYear) {
		this.dobYear = dobYear;
	}
	public int getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(int dobMonth) {
		this.dobMonth = dobMonth;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
}
