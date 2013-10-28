package com.asubank.controller;

import java.util.List;

import javax.servlet.http.HttpSession;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asubank.departmentAndcorporate.AccountManagement;
import com.asubank.departmentAndcorporate.Authorizations;
import com.asubank.departmentAndcorporate.DBManager;
import com.asubank.departmentAndcorporate.Employee;
import com.asubank.departmentAndcorporate.Transactions;

@Controller
public class AcctMgmtController {
	
	@RequestMapping("/CorporateHomePage")
    public String CorporateHomePage(HttpSession session) {  
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
        return "CorporateHomePage";
    }
	
	@RequestMapping("/DepartmentManager")
    public String DepartmentManager(HttpSession session) {    
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
        return "DepartmentManager";
    }
	
		
	@RequestMapping("/NoServicePage")
    public String NoServicePage(HttpSession session) {        
        return "NoServicePage";
    }
	
	@RequestMapping("/ContactUs")
    public String ContactUs(HttpSession session) {        
        return "ContactUs";
    }
	
	@RequestMapping("/HelpAndSupport")
    public String HelpAndSupport(HttpSession session) {        
        return "HelpAndSupport";
    }
	
	@RequestMapping("/Add_User")
    public ModelAndView Add_User(HttpSession session) {
		
		return new ModelAndView("Add_User", "command", new Employee());
	
    }
	
	@RequestMapping("/Delete_User")
    public ModelAndView Delete_User(HttpSession session) {  
		
		return new ModelAndView("Delete_User", "command", new Employee());
    }
	
	@RequestMapping("/Transfer_User")
    public ModelAndView Transfer_User(HttpSession session) {        
		return new ModelAndView("Transfer_User", "command", new Employee());
	}
        
	
    @RequestMapping(value = "/submit_add_user", method = RequestMethod.POST) 
    public String submit_add_user(@ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	  	 
    	 try{  
    	/*model.addAttribute("user_id", employee.getuser_id());
	      model.addAttribute("department", employee.getdepartment());
	      model.addAttribute("role", employee.getrole());
	      String id=employee.getuser_id();
	      String dept=employee.getdepartment();
	      System.out.println(id);
	      System.out.println(dept);*/      
    	  DBManager.addRow(employee);
    	 }
    	 catch(Exception e)
    	 {
    		 return "ErrorPage";
    	 }
		  return "Add_User";
    }
    
    @RequestMapping(value = "/submit_delete_user", method = RequestMethod.POST) 
    public String submit_delete_user(@ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	 try
    	 {
	      DBManager.deleteRow(employee);
	      
    	 }
    	 catch(Exception e)
    	 {
    		 return "ErrorPage";
    	 }
		  return "Delete_User";
    }
    
    @RequestMapping(value = "/submit_transfer_user", method = RequestMethod.POST) 
    public String submit_transfer_user(@ModelAttribute("Employee") Employee employee,ModelMap model,HttpSession session)    		 
    {
    	 
    	  try
    	  {
    	  String userid=(String) session.getAttribute("strID"); 
    	  String department=employee.getdepartment();
    	  String description="Transfer of Employee";
    	  String isAuthorized="No";
    	  String employee_to_transfer=employee.getuser_id();
    	  Authorizations auth=new Authorizations();
    	  auth.setdepartment(department);
    	  auth.setdescription(description);
    	  auth.setuser_id(userid);
    	  auth.setisAuthorized(isAuthorized);
    	  auth.setemployee_to_transfer(employee_to_transfer);
    	  
    	  System.out.println(auth.getuser_id()+"\n"+auth.getdepartment()+"\n"+auth.getdescription()+"\n"+auth.getisAuthorized()+"\n"+auth.getemployee_to_transfer());
    	  
    	  DBManager.updateRow(employee,auth);
    	  }
    	  catch(Exception e)
    	  {
    		  return "ErrorPage"; 
    	  }
		  return "Transfer_User";
    }
    
    @RequestMapping("/ViewTransactions")
	public String ViewTransactions(Model model, HttpSession session) 
    {     
    	 try
    	 {
    	   List<Transactions> list = AccountManagement.viewTransactions();
    	   model.addAttribute("list", list);    
    	 }
    	 catch(Exception e)
    	 {
    		 return "ErrorPage";
    	 }
    	 
           return "ViewTransactionsCorporatemanager";
    }
    
    @RequestMapping("/ViewTransactionsDepartment")
	public String ViewTransactionsDepartment(Model model,HttpSession session) 
    {     
    	try
    	{
    	   String userid=(String) session.getAttribute("strID");    	  
    	   List<Transactions> list = AccountManagement.viewTransactions(userid);
    	   model.addAttribute("list", list);  
    	}
    	catch(Exception e)
    	{
    		return "ErrorPage";
    	}
           return "ViewTransactions";
    }
    
   
    @RequestMapping("/ViewEmployees")
	public String ViewEmployees(Model model,HttpSession session ) 
    {     
    	try
    	{
    	   String userid=(String) session.getAttribute("strID");
    	   System.out.println("Department in ViewEmployees is : "+userid);
    	   List<Employee> list = AccountManagement.ViewEmployees( userid);
    	   model.addAttribute("list", list);    
           
    	 }
   	 	catch(Exception e)
   	 	{
   	 		return "ErrorPage";
   	 	}
    	return "ViewEmployees";
    }
    
    @RequestMapping("/ViewAuthorizations")
   	public ModelAndView ViewAuthorizations(Model model,HttpSession session) 
       {     
    	
       	try
       	{
       		
       	   String userid=(String) session.getAttribute("strID");    	  
       	   List<Authorizations> list = AccountManagement.viewAuthorizations(userid);
       	   model.addAttribute("list", list);  
       	}
       	catch(Exception e)
       	{
       		return new ModelAndView("ErrorPage");
       	}
       	
       	return new ModelAndView("ViewAuthorizations", "command", new Employee());
       	//return "ViewAuthorizations";
       }
       
    @RequestMapping(value = "/submit_authorization", method = RequestMethod.POST) 
    public String submit_authorizations(@ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	 try
    	 {
    	  Authorizations auth=new Authorizations();
    	  Employee emp=new Employee();
    	  emp.setdepartment(employee.getdepartment());
    	  emp.setrole("Employee");
    	  emp.setuser_id(employee.getuser_id());
    	  System.out.println(emp.getdepartment()+"\n"+emp.getuser_id()+"\n"+emp.getrole());
    	  auth.setisAuthorized("Yes");
    	  
	      DBManager.updateRow(emp,auth);
	      	      	      
    	 }
    	 catch(Exception e)
    	 {
    		 return "ErrorPage";
    	 }
		  return "DepartmentManager";
    }
    
}
