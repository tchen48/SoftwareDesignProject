package com.asubank.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;






import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asubank.departmentAndcorporate.AccountManagement;
import com.asubank.departmentAndcorporate.Authorizations;
import com.asubank.departmentAndcorporate.DBManager;
import com.asubank.departmentAndcorporate.Employee;
import com.asubank.departmentAndcorporate.Transactions;
import com.asubank.departmentAndcorporate.log;

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
	
	@RequestMapping("/SystemAdmin")
    public String SystemAdmin(HttpSession session) { 
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
        return "SystemAdmin";
    }
	
	@RequestMapping("/EmployeeNotReady")
    public String EmployeeNotReady(HttpSession session) { 
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
        return "EmployeeNotReady";
    }
		
	@RequestMapping("/NoServicePage")
    public String NoServicePage(HttpSession session) {    
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
        return "NoServicePage";
    }
	
	@RequestMapping("/ContactUs")
    public String ContactUs(HttpSession session) {   
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
        return "ContactUs";
    }
	
	@RequestMapping("/HelpAndSupport")
    public String HelpAndSupport(HttpSession session) {    
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
        return "HelpAndSupport";
    }
	
	@RequestMapping("/Add_User")
    public String Add_User(HttpSession session, Model model) {
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
		model.addAttribute("command", new Employee());
		return "Add_User";
//		return new ModelAndView("Add_User", "command", new Employee());
	
    }
	
	@RequestMapping("/Delete_User")
    public String Delete_User(HttpSession session, Model model) {  
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
		model.addAttribute("command", new Employee());
		return "Delete_User";
//		return new ModelAndView("Delete_User", "command", new Employee());
    }
	
	@RequestMapping("/Transfer_User")
    public String Transfer_User(HttpSession session, Model model) {      
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
		model.addAttribute("command", new Employee());
		return "Transfer_User";
//		return new ModelAndView("Transfer_User", "command", new Employee());
	}
        
	
	@RequestMapping("/Add_User_Dept")
    public String Add_User_Dept(@ModelAttribute("Employee") Employee employee,ModelMap model,HttpSession session) {		
		String userid=(String) session.getAttribute("strID");		 
  	    String dept=DBManager.find_department(userid); 
  	    model.addAttribute("Dept", dept);  
  	    model.addAttribute("command", new Employee());
  	    return "Add_User_Dept";
//		return new ModelAndView("Add_User_Dept", "command", new Employee());
	
    }
	
	@RequestMapping("/Delete_User_Dept")
    public String Delete_User_Dept(HttpSession session, Model model) {  
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
		model.addAttribute("command", new Employee());
		return "Delete_User_Dept";
//		return new ModelAndView("Delete_User_Dept", "command", new Employee());
    }
	
	@RequestMapping("/Transfer_User_Dept")
    public String Transfer_User_Dept(HttpSession session, Model model) {        
		if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
		model.addAttribute("command", new Employee());
		return "Transfer_User_Dept";
//		return new ModelAndView("Transfer_User_Dept", "command", new Employee());
	}
		
    @RequestMapping(value = "/submit_add_user", method = RequestMethod.POST) 
    public String submit_add_user(@ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		} 	 
    	//Validate UserID
    	String strIDValidate = InputValidation.validateStrID(employee.getuser_id());
    	if(strIDValidate != null){
    		return "ErrorPage";
    	}
    	//Validate UserID
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
    
    @RequestMapping(value = "/submit_add_user_dept", method = RequestMethod.POST) 
    public String submit_add_user_dept(@ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}  	 
    	//Validate UserID
    	String strIDValidate = InputValidation.validateStrID(employee.getuser_id());
    	if(strIDValidate != null){
    		return "ErrorPage";
    	}
    	//Validate UserID
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
		  return "Add_User_Dept";
    }
    
    
    @RequestMapping(value = "/submit_delete_user", method = RequestMethod.POST) 
    public String submit_delete_user(@ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
    	//Validate UserID
    	String strIDValidate = InputValidation.validateStrID(employee.getuser_id());
    	if(strIDValidate != null){
    		return "ErrorPage";
    	}
    	//Validate UserID
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
    
    @RequestMapping(value = "/submit_delete_user_dept", method = RequestMethod.POST) 
    public String submit_delete_user_dept(@ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
    	//Validate UserID
    	String strIDValidate = InputValidation.validateStrID(employee.getuser_id());
    	if(strIDValidate != null){
    		return "ErrorPage";
    	}
    	//Validate UserID
    	 try
    	 {
	      DBManager.deleteRow(employee);
	      
    	 }
    	 catch(Exception e)
    	 {
    		 return "ErrorPage";
    	 }
		  return "Delete_User_Dept";
    }
    
    @RequestMapping(value = "/submit_transfer_user", method = RequestMethod.POST) 
    public String submit_transfer_user(@ModelAttribute("Employee") Employee employee,ModelMap model,HttpSession session)    		 
    {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
    	//Validate UserID
    	String strIDValidate = InputValidation.validateStrID(employee.getuser_id());
    	if(strIDValidate != null){
    		return "ErrorPage";
    	}
    	//Validate UserID
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
    	  
    	  //System.out.println(auth.getuser_id()+"\n"+auth.getdepartment()+"\n"+auth.getdescription()+"\n"+auth.getisAuthorized()+"\n"+auth.getemployee_to_transfer());
    	  
    	  DBManager.updateRow(employee,auth);
    	  
    	  String content="Employee Transfer Request from " + userid + "To " +department;
    	  log lg=new log();
    	  lg.settime(new Date());
    	  lg.setcontent(content);
    	  DBManager.addlog(lg);
    	  }
    	  catch(Exception e)
    	  {
    		  return "ErrorPage"; 
    	  }
		  return "Transfer_User";
    }
    
    @RequestMapping(value = "/submit_transfer_user_dept", method = RequestMethod.POST) 
    public String submit_transfer_user_dept(@ModelAttribute("Employee") Employee employee,ModelMap model,HttpSession session)    		 
    {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
    	//Validate UserID
    	String strIDValidate = InputValidation.validateStrID(employee.getuser_id());
    	if(strIDValidate != null){
    		return "ErrorPage";
    	}
    	//Validate UserID
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
    	  
    	  //System.out.println(auth.getuser_id()+"\n"+auth.getdepartment()+"\n"+auth.getdescription()+"\n"+auth.getisAuthorized()+"\n"+auth.getemployee_to_transfer());
    	  
    	  DBManager.updateRow(employee,auth);
    	  
    	  String content="Employee Transfer Request from " + userid + "To " +department;
    	  log lg=new log();
    	  lg.settime(new Date());
    	  lg.setcontent(content);
    	  DBManager.addlog(lg);
    	  }
    	  catch(Exception e)
    	  {
    		  return "ErrorPage"; 
    	  }
		  return "Transfer_User_Dept";
    }
    
    @RequestMapping("/ViewTransactions")
	public String ViewTransactions(Model model, HttpSession session) 
    {     
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
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
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
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
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
    	try
    	{
    	   String userid=(String) session.getAttribute("strID");
    	   //System.out.println("Department in ViewEmployees is : "+userid);
    	   List<Employee> list = AccountManagement.ViewEmployees( userid);
    	   model.addAttribute("list", list);    
           
    	 }
   	 	catch(Exception e)
   	 	{
   	 		return "ErrorPage";
   	 	}
    	return "ViewEmployees";
    }
    
    @RequestMapping("/ViewEmployeesAll")
   	public String ViewEmployeesAll(Model model,HttpSession session ) 
       {     
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
       	try
       	{
       	  
       	   List<Employee> list = AccountManagement.ViewEmployeesAll();
       	   model.addAttribute("list", list);    
              
       	 }
      	 	catch(Exception e)
      	 	{
      	 		return "ErrorPage";
      	 	}
       	return "ViewEmployeesAll";
       }
    
    @RequestMapping("/ViewAuthorizations")
   	public String ViewAuthorizations(Model model,HttpSession session) 
       {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}    	
       	try
       	{
       		
       	   String userid=(String) session.getAttribute("strID");    	  
       	   List<Authorizations> list = AccountManagement.viewAuthorizations(userid);
       	   model.addAttribute("list", list);  
       	}
       	catch(Exception e)
       	{
       		return "ErrorPage";
//       		return new ModelAndView("ErrorPage");
       	}
       	model.addAttribute("command", new Employee());
       	return "ViewAuthorizations";
//       	return new ModelAndView("ViewAuthorizations", "command", new Employee());
       	//return "ViewAuthorizations";
       }
       
    @RequestMapping(value = "/submit_authorization", method = RequestMethod.POST) 
    public String submit_authorizations(@RequestParam String action, @ModelAttribute("Employee") Employee employee,ModelMap model, HttpSession session)    		 
    {
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}    	
    	try
   	    {	
    	  Employee emp=new Employee();
      	  emp.setdepartment(employee.getdepartment());
      	  emp.setrole("Employee");
      	  emp.setuser_id(employee.getuser_id());
    	if(action.equals("Authorize"))
    	{
    	 
    	  Authorizations auth=new Authorizations();
    	  
    	  //System.out.println(emp.getdepartment()+"\n"+emp.getuser_id()+"\n"+emp.getrole());
    	  auth.setisAuthorized("Yes");
    	  
	      DBManager.updateRow(emp,auth);
	      	      	      
    	 }
    	
    	else
    	{
    		DBManager.deleteAuthorizations(emp);
    	}
   	    }
    	 catch(Exception e)
    	 {
    		 return "ErrorPage";
    	 }
		  return "DepartmentManager";
    }
    
    @RequestMapping("/ViewLogs")
   	public String ViewLogs(Model model,HttpSession session ) 
       {     
    	if((String)session.getAttribute("strID") == null){
			return "sessionTimeOut";
		}
       	try
       	{
       	  
       	   List<log> list = AccountManagement.ViewLogs();
       	   model.addAttribute("list", list);    
              
       	 }
      	 	catch(Exception e)
      	 	{
      	 		return "ErrorPage";
      	 	}
       	return "ViewLogs";
       }
}
