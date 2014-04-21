package com.ProjMgmtSys.controller;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ProjMgmtSys.model.Dept.Dept;
import com.ProjMgmtSys.model.Dept.DeptManager;
import com.ProjMgmtSys.model.Gro.GroManager;
import com.ProjMgmtSys.model.User.*;

@Controller("userController")
public class UserController {
	@RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model){	
        return "login";
    }
	
	@RequestMapping("/home")
	public String home(@ModelAttribute("user") User user, Model model, HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, ParseException, IOException{
		LoginResult loginResult = UserManager.validate(user.getUserId(), user.getPassword());
		if(loginResult.getUser() == null){
			String ErrorMsg = StatusCode.LOGIN_STATUS[loginResult.getStatusCode()];
			model.addAttribute("ErrorMsg", ErrorMsg);
			return "login";
		}	
		user = loginResult.getUser();
		int usertype = user.getUserType();
		session.setMaxInactiveInterval(1200);
		session.setAttribute("userName", user.getUserName());
		session.setAttribute("userId", user.getUserId());
		model.addAttribute("userName", user.getUserName());
		if(usertype == UserType.SUPERADMIN){
			return "adminHome";
		}
		else if(usertype == UserType.DEPTMANAGER){
			String depName = DeptManager.queryDeptById(user.getDepId()).getDepName();
			session.setAttribute("depName", depName);
			session.setAttribute("depId", user.getDepId());
			return "deptMngHome";
		}
		else if(usertype == UserType.GROUPMANAGER){
			String depName = DeptManager.queryDeptById(user.getDepId()).getDepName();
			session.setAttribute("depName", depName);
			session.setAttribute("depId", user.getDepId());
			session.setAttribute("groId", user.getGroId());
			return "groMngHome";
		}
		else{
			String depName = DeptManager.queryDeptById(user.getDepId()).getDepName();
			session.setAttribute("depName", depName);
			session.setAttribute("depId", user.getDepId());
			session.setAttribute("groId", user.getGroId());
			return "empHome";
		}
//			
//			if(roletype != Roletype.INTERNALUSER){
//				Account account = AccountManager.queryAccount(strID);
//				String checkingID = String.valueOf(account.getCheckingID());
//				String savingID = String.valueOf(account.getSavingID());
//				String creditID = String.valueOf(account.getCreditID());			
//				model.addAttribute("checkingLastFour", checkingID.substring(checkingID.length() - 4, checkingID.length()));
//				model.addAttribute("savingLastFour", savingID.substring(savingID.length() - 4, savingID.length()));
//				model.addAttribute("creditLastFour", creditID.substring(creditID.length() - 4, creditID.length()));
//				model.addAttribute("checkingBalance", account.getCheckingBalance());
//				model.addAttribute("savingBalance", account.getSavingBalance());
//				model.addAttribute("creditBalance", account.getCreditBalance());
//				VisitorManager.deleteVisitor(machineID);
//				session.setMaxInactiveInterval(1200);
//				return "account";
//			}
//			else{
//				session.setMaxInactiveInterval(1200);
//				Employee employee = EmployeeManager.queryEmployee(strID);
//				if(employee == null){
//					Account account = AccountManager.queryAccount(strID);
//					String checkingID = String.valueOf(account.getCheckingID());
//					String savingID = String.valueOf(account.getSavingID());
//					String creditID = String.valueOf(account.getCreditID());			
//					model.addAttribute("checkingLastFour", checkingID.substring(checkingID.length() - 4, checkingID.length()));
//					model.addAttribute("savingLastFour", savingID.substring(savingID.length() - 4, savingID.length()));
//					model.addAttribute("creditLastFour", creditID.substring(creditID.length() - 4, creditID.length()));
//					model.addAttribute("checkingBalance", account.getCheckingBalance());
//					model.addAttribute("savingBalance", account.getSavingBalance());
//					model.addAttribute("creditBalance", account.getCreditBalance());
//					model.addAttribute("employee", "Employee");
//					session.setAttribute("employeepage", "EmployeeNotReady.html");
//					return "account";
//				}
//				if(user.getStrID().equals("admin1")){
//					session.setAttribute("employeepage", "SystemAdmin.html");
//					VisitorManager.deleteVisitor(machineID);
//					return "SystemAdmin";
//				}
//				else if(((String)employee.getdepartment()).equalsIgnoreCase("Corporate Management") && ((String)employee.getrole()).equalsIgnoreCase("Manager")){
//					session.setAttribute("employeepage", "CorporateHomePage.html");
//					VisitorManager.deleteVisitor(machineID);
//					return "CorporateHomePage";
//				}
//				else if(((String)employee.getrole()).equalsIgnoreCase("Manager")){
//					session.setAttribute("employeepage", "DepartmentManager.html");
//					VisitorManager.deleteVisitor(machineID);
//					return "DepartmentManager";
//				}
//				else{
//					session.setAttribute("employeepage", "RegularEmployee.html");
//					VisitorManager.deleteVisitor(machineID);
//					return "RegularEmployee";
//				}
//					
//			}
	}
	
	
	@RequestMapping("/newDept")  
	public @ResponseBody  
	String newDept(@RequestParam(value = "depName") String depName) {  
		String depId = DeptManager.createDept(depName);
		return depId;  	  
	} 
	
	@RequestMapping("/modifyDept")  
	public @ResponseBody  
	String modifyDept(
		@RequestParam(value = "oldName") String oldName,
		@RequestParam(value = "newName") String newName) {  
		return  DeptManager.updateDepName(oldName, newName);
	} 
	
	@RequestMapping("/unblockUser")  
	public @ResponseBody  
	String unblock(
		@RequestParam(value = "userId") String userId) { 
		int intId;
		try{
			intId = Integer.parseInt(userId);
			return  UserManager.unblockUser(intId);
		}
		catch (NumberFormatException e){
			return "UserID must be Integer";
		}		
	} 
	
	
	@RequestMapping("/newEmp")  
	 public @ResponseBody  
	 String newEmp( @RequestParam(value = "empName") String empName,
			 		@RequestParam(value = "depList") int depList,
			 		@RequestParam(value = "isManager") boolean isManager) {  
		System.out.println("empName " + empName); 
		System.out.println("depList " + depList); 
		System.out.println("isManager " + isManager);
		String empId = UserManager.createUser(empName, empName, isManager?1:3, 0, depList);
		return empId;  	  
	 } 
	
	@RequestMapping("/newGroup")  
	public @ResponseBody  
	String newGroup(@RequestParam(value = "groName") String groName, 
					@RequestParam(value = "depId") String depId) {  
		int intId;
		try{
			intId = Integer.parseInt(depId);
			return GroManager.createGro(groName, intId);
		}
		catch (NumberFormatException e){
			return "GroupID must be Integer";
		}		
	} 
	
	@RequestMapping("/modifyGroup")  
	public @ResponseBody  
	String modifyGroup(
		@RequestParam(value = "oldName") String oldName,
		@RequestParam(value = "newName") String newName,
		@RequestParam(value = "depId") String depId) {  
		int intId;
		try{
			intId = Integer.parseInt(depId);
			return  GroManager.updateGroName(oldName, newName, intId);
		}
		catch (NumberFormatException e){
			return "GroupID must be Integer";
		}
	} 
	
	@RequestMapping(value = "/getDepEmpList", method = RequestMethod.GET)
	public @ResponseBody
	String getDepEmpList(
		@RequestParam(value = "depId") String depId,
		@RequestParam(value = "allemp") String allEmp){
		try{
			JSONArray jsonA = UserManager.getDeptEmpList(Integer.parseInt(depId), Integer.parseInt(allEmp));
			return jsonA.toString();
		}
////				result = GroManager.getDeptGroList(Integer.parseInt(depId));
////				System.out.println(result);
//				return null;
//		}
		catch (NumberFormatException e){
			return null;
		}
	}
	
	
	@RequestMapping("/newProject")
    public String newProject(@ModelAttribute("user") User user, Model model){	
        return "newProject";
    }
	
	@RequestMapping("/project")
    public String project(@ModelAttribute("user") User user, Model model){	
        return "project";
    }
}
