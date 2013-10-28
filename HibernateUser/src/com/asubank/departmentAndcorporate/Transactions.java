package com.asubank.departmentAndcorporate;

import java.util.Date;

public class Transactions {
	
	
	    private String from_user;     
	    private int transaction_id;     
	    private String transaction_type;   
	    private String transaction_amount; 
	    private String to_user; 
	    private Date transaction_time; 
	    private String status; 
	    
	    public Transactions() {}
	         
	    public Transactions(String from_user, int transaction_id, String transaction_type,String transaction_amount,String to_user,Date transaction_time,String status) 
	    {
	        this.from_user = from_user;
	        this.transaction_id = transaction_id;
	        this.transaction_type = transaction_type;
	        this.transaction_amount = transaction_amount;
	        this.to_user = to_user;
	        this.transaction_time = transaction_time;  
	        this.status=status;
	    }
	    
	 // Getter and Setter methods
		
		public String getfrom_user() {
			return from_user;
		}

		public void setfrom_user(String from_user) {
			this.from_user = from_user;
		}

		public int gettransaction_id() {
			return transaction_id;
		}

		public void settransaction_id(int transaction_id) {
			this.transaction_id= transaction_id;
		}
		public String gettransaction_type() {
			return transaction_type;
		}

		public void settransaction_type(String transaction_type) {
			this.transaction_type = transaction_type;
		}
	    
		public String gettransaction_amount() {
			return transaction_amount;
		}

		public void settransaction_amount(String transaction_amount) {
			this.transaction_amount = transaction_amount;
		}
	    
		public String getto_user() {
			return to_user;
		}

		public void setto_user(String to_user) {
			this.to_user = to_user;
		}
		
		public Date gettransaction_time()
		{
			return transaction_time;
		}
		
		public void settransaction_time(Date transaction_time)
		{
			this.transaction_time=transaction_time;
		}
		
		public String getstatus() {
			return status;
		}

		public void setstatus(String status) {
			this.status = status;
		}
		
		

}
