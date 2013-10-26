package com.asubank.model.transfer;

public class Transaction {
	
	    private int Id;
		private String strID;
		private long fromID;
		private long toID;
		private double amount;
		
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getStrID() {
			return strID;
		}
		public void setStrID(String strID) {
			this.strID = strID;
		}
		public long getFromID() {
			return fromID;
		}
		public void setFromID(long fromID) {
			this.fromID = fromID;
		}
		public long getToID() {
			return toID;
		}
		public void setToID(long toID) {
			this.toID = toID;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		
		
	}


