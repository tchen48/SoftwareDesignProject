package com.ProjMgmtSys.controller;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  	 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import net.sf.json.*;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.ProjMgmtSys.model.Data.DataManager;
import com.ProjMgmtSys.model.Dept.Dept;
import com.ProjMgmtSys.model.Dept.DeptManager;
import com.ProjMgmtSys.model.Field.FieldManager;
import com.ProjMgmtSys.model.Gro.GroManager;
import com.ProjMgmtSys.model.User.*;
import com.opensymphony.xwork2.*;

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
			String groName = GroManager.queryGroById(user.getGroId(), user.getDepId()).getGroName();
			session.setAttribute("depName", depName);
			session.setAttribute("groName", groName);
			session.setAttribute("depId", user.getDepId());
			session.setAttribute("groId", user.getGroId());
			return "groMngHome";
		}	
		else{
			String depName = DeptManager.queryDeptById(user.getDepId()).getDepName();
			String groName = GroManager.queryGroById(user.getGroId(), user.getDepId()).getGroName();
			session.setAttribute("depName", depName);
			session.setAttribute("groName", groName);
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
	
	@RequestMapping("/newPass")  
	 public @ResponseBody  
	 String newPass(@RequestParam(value = "oldPass") String oldPass,
		 			@RequestParam(value = "newPass") String newPass,
		 			@RequestParam(value = "userId") String userId) throws Exception {  

		int result = UserManager.validatePassword(userId, oldPass);
		if(result == StatusCode.LOGIN_SUCCESS){
			UserManager.updatePassword(userId, newPass);
			return "A new Password is created!";  	  
		}
		else
			return "Incorrect old password";
//			throw new Exception("Error!") ;
	 } 
	 
	@RequestMapping("/getDept.html")  
	 public @ResponseBody  
	 String getDept(HttpServletRequest request, HttpServletResponse response)throws IOException  {  
		
		List<Dept> list = DeptManager.geyAllDepName();  
		JSONArray jsonArray = JSONArray.fromObject(list); 
		//response.setCharacterEncoding("UTF-8");  
		//response.getWriter().print(jsonArray);  
		System.out.println(jsonArray); 
		response.getWriter().print(jsonArray);  
		return null;
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
	
	@RequestMapping(value = "/getEmpList", method = RequestMethod.GET)
	public @ResponseBody
	String getEmpList(
		@RequestParam(value = "depId") String depId,
		@RequestParam(value = "groId") String groId,
		@RequestParam(value = "unassigned") String unassigned){
		try{
			JSONArray jsonA = UserManager.getEmpList(Integer.parseInt(depId), Integer.parseInt(groId), Integer.parseInt(unassigned));
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
	
	@RequestMapping(value = "/getDepGroList", method = RequestMethod.GET)
	public @ResponseBody
	String getDepGroList(
		@RequestParam(value = "depId") String depId){
		try{
			JSONArray jsonA = GroManager.getDeptGroList(Integer.parseInt(depId));
			return jsonA.toString();
		}
		catch (NumberFormatException e){
			return null;
		}
	}
	
	@RequestMapping(value = "/assignEmp")
	public @ResponseBody
	String assignEmp(
		@RequestParam(value = "userId") String userId,
		@RequestParam(value = "groId") String groId,
		@RequestParam(value = "isGroMng") String isGroMng){
		System.out.println(userId + " " + groId + " " + isGroMng);
		return UserManager.assignEmp(Integer.parseInt(userId), Integer.parseInt(groId), Boolean.parseBoolean(isGroMng));
	}
	
	
	@RequestMapping("/newProject")
    public String newProject(@ModelAttribute("user") User user, Model model){	
        return "newProject";
    }
	
	@RequestMapping("/project")
    public String project(@ModelAttribute("user") User user, Model model){	
        return "project";
    }
	
	@RequestMapping("/addField")
	public @ResponseBody
	String addField(
			@RequestParam(value = "depId") String depId,
			@RequestParam(value = "groId") String groId,
			@RequestParam(value = "objId") String objId,
			@RequestParam(value = "fieldName") String fieldName,
			@RequestParam(value = "fieldType") String fieldType){
		FieldManager.createField(fieldName, Integer.parseInt(fieldType), Integer.parseInt(objId), Integer.parseInt(depId), Integer.parseInt(groId));
		return "1";
	}
	
	@RequestMapping("/getCustomizedField")
	public @ResponseBody
	String getCustomizedField(
			@RequestParam(value = "depId") String depId,
			@RequestParam(value = "groId") String groId,
			@RequestParam(value = "objId") String objId){
		JSONArray jsonA = FieldManager.getCustomizedField(Integer.parseInt(depId), Integer.parseInt(groId), Integer.parseInt(objId));
		return jsonA.toString();
	}
	
	@RequestMapping("/groMngHome")
    public String groMngHome(@ModelAttribute("user") User user, Model model){	
        return "groMngHome";
    }
	
	@RequestMapping("/createProject")
	public @ResponseBody
	String createProject(
			@RequestParam(value = "jsonArray") String jsonArray,
			@RequestParam(value = "depId") String depId,
			@RequestParam(value = "groId") String groId,
			@RequestParam(value = "objId") String objId) throws NumberFormatException, SAXException, JAXBException{
		System.out.println(jsonArray);
		JSONArray jArray = (JSONArray) JSONSerializer.toJSON(jsonArray);
		return DataManager.createData(jArray, Integer.parseInt(objId), Integer.parseInt(depId), Integer.parseInt(groId));
	}
}
