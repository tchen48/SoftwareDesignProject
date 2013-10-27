package com.asubank.model.transfer;

public class TransactionInput {
	private String fromIDInput;
	private String toIDInput;
	private String amountInput;
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
