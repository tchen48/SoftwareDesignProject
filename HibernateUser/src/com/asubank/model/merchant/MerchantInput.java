package com.asubank.model.merchant;

public class MerchantInput {
	private String customerid;

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	private long fromIDInput;
	private long toIDInput;
	private double amountInput;
	private String transactionpasswordInput;
	public String getTransactionpasswordInput() {
		return transactionpasswordInput;
	}
	public void setTransactionpasswordInput(String transactionpasswordInput) {
		this.transactionpasswordInput = transactionpasswordInput;
	}
	public long getFromIDInput() {
		return fromIDInput;
	}
	public void setFromIDInput(long fromIDInput) {
		this.fromIDInput = fromIDInput;
	}
	public long getToIDInput() {
		return toIDInput;
	}
	public void setToIDInput(long toIDInput) {
		this.toIDInput = toIDInput;
	}
	public double getAmountInput() {
		return amountInput;
	}
	public void setAmountInput(double amountInput) {
		this.amountInput = amountInput;
	}
	
}
