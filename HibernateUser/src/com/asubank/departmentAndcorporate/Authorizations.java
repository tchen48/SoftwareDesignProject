package com.asubank.departmentAndcorporate;

public class Authorizations {

	    private String user_id;     
	    private String department;     
	    private String description; 
	    private String isAuthorized;     
	    private String employee_to_transfer;

	    public Authorizations() {}
	    
	        
	 // Getter and Setter methods
		
		public String getuser_id() {
			return user_id;
		}

		public void setuser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getdepartment() {
			return department;
		}

		public void setdepartment(String department) {
			this.department = department;
		}

		public String getdescription() {
			return description;
		}

		public void setdescription(String description) {
			this.description = description;
		}
		
		public String getisAuthorized() {
			return isAuthorized;
		}

		public void setisAuthorized(String isAuthorized) {
			this.isAuthorized = isAuthorized;
		}
		
		public String getemployee_to_transfer() {
			return employee_to_transfer;
		}

		public void setemployee_to_transfer(String employee_to_transfer) {
			this.employee_to_transfer = employee_to_transfer;
		}
		
}
