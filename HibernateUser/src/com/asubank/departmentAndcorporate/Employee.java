package com.asubank.departmentAndcorporate;

public class Employee {
 
    private String user_id;     
    private String department;     
    private String role;     

    public Employee() {}
         
    public Employee(String user_id, String department, String role) 
    {
        this.user_id = user_id;
        this.department = department;
        this.role = role;
             
    }

    
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

	public String getrole() {
		return role;
	}

	public void setrole(String role) {
		this.role = role;
	}


       
 
}
