package com.ProjMgmtSys.model.User;
 
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ProjMgmtSys.controller.DeptElementCode;
import com.ProjMgmtSys.model.Dept.Dept;
import com.ProjMgmtSys.model.Dept.DeptManager;
import com.ProjMgmtSys.model.Gro.Gro;
import com.ProjMgmtSys.model.Gro.GroManager;
import com.ProjMgmtSys.model.Object.SessionFactoryUtil;


public class UserManager {
	private static Session session;
	private static final int MAXFAIL = 3;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createUser(String userName, String password, int userType, int groId, int depId){
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserType(userType);
		user.setGroId(groId);
		user.setDepId(depId);

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
		
		Dept dept = DeptManager.queryDeptById(depId);
		System.out.println(dept.getDepName());
		
//		Gro gro = GroManager.queryGro(groId);
//		user.setGroName(gro.getGroName());
//		System.out.println(gro.getGroName());

//		System.out.println("test!!!!!");
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return  "" + user.getUserId();
	}
	
	
	@SuppressWarnings("unchecked")
	public static User queryUser(String userId){
		createSession();
		String hql = "from User as user where user.userId=:userId";
		Query query = session.createQuery(hql);
		query.setString("userId", userId);
		List <User>list = query.list();
		User user = null;
		java.util.Iterator<User> iter = list.iterator();
		while (iter.hasNext()) {
			user = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return user;		
	}
	
	public static void updatePassword(String userId, String password){
		createSession();
		String hql1 = "update User as u set u.password=:password where userId=:userId";
		Query query1 = session.createQuery(hql1);
		query1.setString("userId", userId);
		query1.setString("password", password);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
	
//	private static int queryStrID(String shortname){
//		createSession();
//		String hql = "select count(strID) from User as user where user.shortname=:shortname";
//		Query query = session.createQuery(hql);  
//		query.setString("shortname",shortname);
//		int num = ((Number)query.uniqueResult()).intValue();
//		session.getTransaction().commit();
//		session.close();
//		return num;		
//	}
	
	public static int validatePassword(String userId, String password){
		createSession();
		String hql = "from User as user where user.userId=:userId"; //and user.password=:password";
		Query query = session.createQuery(hql);
		query.setInteger("userId", Integer.parseInt(userId) );
		List <User>list = query.list();
		User user = null;
		java.util.Iterator<User> iter = list.iterator();
		while (iter.hasNext()) {
			user = iter.next();
		}		
		session.getTransaction().commit();
		session.close();
		if(user == null){
			return StatusCode.USERID_NOT_EXIST;
		}
		String correctPassword = user.getPassword();
		if(password.equals(correctPassword)){
			return StatusCode.LOGIN_SUCCESS;
		}
		else{
			return StatusCode.PASSWORD_NOT_CORRECT;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static LoginResult validate(int userId, String password){
		LoginResult loginResult = new LoginResult();
		createSession();
		String hql = "from User as user where user.userId=:userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		List <User>list = query.list();
		User user = null;
		java.util.Iterator<User> iter = list.iterator();
		while (iter.hasNext()) {
			user = iter.next();
		}		
		session.getTransaction().commit();
		session.close();
		if(user == null){
			loginResult.setUser(null);
			loginResult.setStatusCode(StatusCode.USERID_NOT_EXIST);
			return loginResult;
		}
		String correctPassword = user.getPassword();
		if(password.equals(correctPassword) == true && user.isBlock() == false){
			resetFail(userId);
			loginResult.setUser(user);
			loginResult.setStatusCode(StatusCode.LOGIN_SUCCESS);
			return loginResult;
		}
		else if(user.isBlock() == true){
			loginResult.setUser(null);
			loginResult.setStatusCode(StatusCode.ACCOUNT_BLOCK);
			return loginResult;
		}
		else{
			increaseFail(user);
			loginResult.setUser(null);
			loginResult.setStatusCode(StatusCode.PASSWORD_NOT_CORRECT);
			return loginResult;
		}	
	}
	
	public static int increaseFail(User user){
		int fail = user.getFail();
		int userId = user.getUserId();
		fail++;
		if(fail == MAXFAIL){
			blockUser(userId);
			return StatusCode.ACCT_BLOCKED;
		}
		else{			
			createSession();
			String hql = "update User as u set u.fail=:fail where userId=:userId";
			Query query = session.createQuery(hql);
			query.setInteger("userId", userId);
			query.setInteger("fail", fail);
			query.executeUpdate(); 
			session.getTransaction().commit();
			session.close();
			return StatusCode.ACCT_NOT_BLOCKED;
		}		
	}
	public static void resetFail(int userId){
		createSession();
		String hql = "update User as u set u.fail=:fail where userId=:userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		query.setInteger("fail", 0);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return;
	}
	public static void blockUser(int userId){
		createSession();
		String hql = "update User as u set u.block=:block where userId=:userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		query.setBoolean("block", true);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return;
	}
	public static String unblockUser(int userId){
		createSession();
		String hql = "update User as u set u.block=:block where u.userId=:userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		query.setBoolean("block", false);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return "1";
	}
	
	public static JSONArray getEmpList(int depId, int groId, int unassigned){
		JSONArray array = new JSONArray();
		createSession();
		String hql;
		if(unassigned == DeptElementCode.UNASSIGNED_EMP)
			hql = "from User as user where user.depId=:depId and user.userType=:userType and user.groId=:groId";
		else
			hql = "from User as user where user.depId=:depId and user.groId=:groId";
		Query query = session.createQuery(hql);
		query.setInteger("depId", depId);
		query.setInteger("groId", groId);
		if(unassigned == DeptElementCode.UNASSIGNED_EMP)
			query.setInteger("userType", UserType.EMPLOYEE);
		List <User>list = query.list();
		java.util.Iterator<User> iter = list.iterator();
		NameId nameid = new NameId();
		User user = null;
		while (iter.hasNext()) {
			user = iter.next();
			nameid.setId(user.getUserId());
			nameid.setName(user.getUserName());
			array.add(nameid);
		}					
		session.getTransaction().commit();
		session.close();
		System.out.println(array.toString());
		return array;
	}
	
	public static String assignEmp(int userId, int groId, Boolean isGroMng){
		createSession();
		String hql;
		if(isGroMng)
			hql = "update User as u set u.groId=:groId, u.userType=:userType where u.userId=:userId";
		else
			hql = "update User as u set u.groId=:groId where u.userId=:userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		query.setInteger("groId", groId);
		if(isGroMng)
			query.setInteger("userType", UserType.GROUPMANAGER);
		query.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return "1";
	 }
	
}