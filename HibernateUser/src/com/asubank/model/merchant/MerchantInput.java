package com.asubank.model.merchant;

public class MerchantInput {
	private String customerid;

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	private String fromIDInput;
	private String toIDInput;
	private String amountInput;
	private String transactionpasswordInput;
	public String getTransactionpasswordInput() {
		return transactionpasswordInput;
	}
	public void setTransactionpasswordInput(String transactionpasswordInput) {
		this.transactionpasswordInput = transactionpasswordInput;
	}
	public String getFromIDInput() {
		return fromIDInput;
	}
	public void setFromIDInput(String fromIDInput) {
		this.fromIDInput = fromIDInput;
	}
	public String getToIDInput() {
		return toIDInput;
	}
	public void setToIDInput(String toIDInput) {
		this.toIDInput = toIDInput;
	}
	public String getAmountInput() {
		return amountInput;
	}
	public void setAmountInput(String amountInput) {
		this.amountInput = amountInput;
	}
	
}
