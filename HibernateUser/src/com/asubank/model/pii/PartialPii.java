package com.asubank.model.pii;

public class PartialPii {
	private int dobYear;
	private String ssnLastFour;
	private String strID;
	public int getDobYear() {
		return dobYear;
	}
	public void setDobYear(int dobYear) {
		this.dobYear = dobYear;
	}
	public String getSsnLastFour() {
		return ssnLastFour;
	}
	public void setSsnLastFour(String ssnLastFour) {
		this.ssnLastFour = ssnLastFour;
	}
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}		
}
