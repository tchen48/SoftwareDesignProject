package com.asubank.model.user;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.security.SecurityManager;
import com.asubank.model.user.LoginResult;
import com.asubank.model.security.StatusCode;

public class UserManager {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
//	
//	public static void main(String[] args) {
////		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
////		session.beginTransaction();
////		createPerson(session);
////		queryPerson(session);
//		queryStrID("New");
//	}
//
//	private static void queryPerson(Session session) {
//		Query query = session.createQuery("from User");                
//		List <User>list = query.list();
//		java.util.Iterator<User> iter = list.iterator();
//		while (iter.hasNext()) {
//			User user = iter.next();
//			System.out.println("User: \"" + user.getFirstname() +"\", " + user.getLastname() +"\", "+user.getUserID()+"\", "+user.getDeptID()+"\", "+
//					user.getRoleID() +"\", " + user.getAccno() +"\", "+user.getAmount()+"\", "+user.getPinno()+"\", "+
//					user.getPassword() +"\", " + user.getCardno() );
//		}
//		
//		session.getTransaction().commit();
//	}

//	public static void createPerson(Session session) {
//		User user = new User();
//		user.setUserID(123);
//		user.setFirstname("New");
//		user.setLastname("Obhama");	
//		user.setDeptID(9);
//		user.setRoleID(7);
//		user.setAccno("111111");
//		user.setAmount(100.01);
//		user.setCardno("1111222233334444");
//		user.setPinno(7910);
//		user.setPassword("shaosh0913");
//		session.save(user);
//	}
	public static String createUser(String firstname, String lastname, String address, String email, String telephone, int roletype,
			String password){
		createSession();
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);	
		user.setAddress(address);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setRoletype(roletype);
		user.setPassword(password);
		
		String shortname = firstname.substring(0, 1).toLowerCase() + lastname.toLowerCase();
		session.close();
		int idnum = queryStrID(shortname) + 1;
		String strID = shortname + idnum;
		user.setShortname(shortname);
		user.setStrID(strID);
		createSession();
		
		session.save(user);
		session.getTransaction().commit();
		//queryPerson(session);
//		System.out.println("================================");
		session.close();
		return (user.getStrID());
//		return ("User " + user.getFirstname() + " " + user.getLastname() + " " + user.getUserID() + 
//				 	" " + user.getDeptID() + " " + user.getRoleID() + 
//				 	" " + user.getAccno() + " " + user.getAmount() + " " + user.getCardno() + 
//				 	" " + user.getPinno() + " " + user.getPassword() + " is created.");
	}
	
//	public static List queryAllUsers() {
//		createSession();
//		Query query = session.createQuery("from User");                
//		List <User>list = query.list();
//		java.util.Iterator<User> iter = list.iterator();
//		while (iter.hasNext()) {
//			User user = iter.next();
//			System.out.println("User: \"" + user.getFirstname() +"\", " + user.getLastname() +"\", "+user.getUserID()+"\", "+user.getDeptID()+"\", "+
//					user.getRoleID() +"\", " + user.getAccno() +"\", "+user.getAmount()+"\", "+user.getPinno()+"\", "+
//					user.getPassword() +"\", " + user.getCardno() );
//		}
//		
//		session.getTransaction().commit();
//		session.close();
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public static User queryUser(String strID){
		createSession();
		String hql = "from User as user where user.strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
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
	
	private static int queryStrID(String shortname){
		createSession();
		String hql = "select count(userID) from User as user where user.shortname=:shortname";
		Query query = session.createQuery(hql);  
		query.setString("shortname",shortname);
		int num = ((Number)query.uniqueResult()).intValue();
		session.getTransaction().commit();
		session.close();
		return num;
		
	}
	@SuppressWarnings("unchecked")
	public static LoginResult validate(String strID, String password){
		LoginResult loginResult = new LoginResult();
		createSession();
		String hql = "from User as user where user.strID=:strID"; //and user.password=:password";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
//		query.setString("password", password);
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
		boolean isBlocked =  SecurityManager.checkBlock(strID);
		if(password.equals(correctPassword) == true && isBlocked == false){
			SecurityManager.resetFail(strID);
			loginResult.setUser(user);
			loginResult.setStatusCode(StatusCode.LOGIN_SUCCESS);
			return loginResult;
		}
		else if(password.equals(correctPassword) == true && isBlocked == true){
			loginResult.setUser(null);
			loginResult.setStatusCode(StatusCode.ACCOUNT_BLOCK);
			return loginResult;
		}
		else{
//			System.out.println(strID + " fails in UserMng");
			SecurityManager.increaseFail(strID);
			loginResult.setUser(null);
			loginResult.setStatusCode(StatusCode.PASSWORD_NOT_CORRECT);
			return loginResult;
		}	
	}
}