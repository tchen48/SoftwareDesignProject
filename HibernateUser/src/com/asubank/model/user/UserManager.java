package com.asubank.model.user;
import com.asubank.model.security.EncryptBase64;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.security.SecurityManager;
import com.asubank.model.user.LoginResult;
import com.asubank.model.security.StatusCode;

public class UserManager {
	private static Session session;
	private static Date defaultDate;
	private static SimpleDateFormat df;
	
	static{
		String date0 = "1970-01-01 00:00:00";
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			defaultDate = df.parse(date0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
			String password, String transactionPassword){
		createSession();
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);	
		user.setAddress(address);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setRoletype(roletype);
		//user.setTransactionpassword(transactionPassword);
		//encryption - Bhumik
		user.setTransactionpassword(EncryptBase64.encodeString(transactionPassword));
		
		user.setPassword(EncryptBase64.encodeString(password));
		//user.setPassword(password);
		
		String shortname = firstname.substring(0, 1).toLowerCase() + lastname.toLowerCase();
		session.close();
		int idnum = queryStrID(shortname) + 1;
		String strID = shortname + idnum;
		user.setShortname(shortname);
		user.setStrID(strID);
		createSession();
//		System.out.println("StrID = " + user.getStrID());
		session.save(user);
		session.getTransaction().commit();
		//queryPerson(session);
		session.close();
		return (user.getStrID());
//		return ("User " + user.getFirstname() + " " + user.getLastname() + " " + user.getUserID() + 
//				 	" " + user.getDeptID() + " " + user.getRoleID() + 
//				 	" " + user.getAccno() + " " + user.getAmount() + " " + user.getCardno() + 
//				 	" " + user.getPinno() + " " + user.getPassword() + " is created.");
	}
	
	public static List queryAllUsers(int roletype) {
		createSession();
		Query query = session.createQuery("from User as user where user.roletype=:roletype");  
		query.setInteger("roletype",roletype);
		List <User>list = query.list();		
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
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
	
	public static void updatePassword(String strID, String password){
		createSession();
		String hql1 = "update User as u set u.password=:password where strID=:strID";
		Query query1 = session.createQuery(hql1);
		query1.setString("strID", strID);
		query1.setString("password", EncryptBase64.encodeString(password)); // Encrypt - Bhumik
		query1.executeUpdate(); 
		
		String hql2 = "update Security as s set s.lastPasswordUpdate=:lastPasswordUpdate where strID=:strID";
		Query query2 = session.createQuery(hql2);
		query2.setString("strID", strID);
		query2.setString("lastPasswordUpdate", df.format(new Date()));
		query2.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public static void updateContact(ContactSet contactSet){
		String strID = contactSet.getStrID();
		String telephone = contactSet.getTelephone();
		String email = contactSet.getEmail();
		String address = contactSet.getAddress();	
		createSession();
		if(email != null){
			String emailNoSpace = email.replaceAll("\\s+", "");
			if(emailNoSpace.equals("") == false){
				String hql1 = "update User as u set u.email=:email where strID=:strID";
				Query query1 = session.createQuery(hql1);
				query1.setString("strID", strID);
				query1.setString("email", email);
				query1.executeUpdate();
			}
		}
		if(address != null){
			String addressNoSpace = address.replaceAll("\\s+", "");
			if(addressNoSpace.equals("") == false){		
				String hql2 = "update User as u set u.address=:address where strID=:strID";
				Query query2 = session.createQuery(hql2);
				query2.setString("strID", strID);
				query2.setString("address", address);
				query2.executeUpdate();
			}
		}
		if(telephone != null){
			String telephoneNoSpace = telephone.replaceAll("\\s+", "");
			if(telephoneNoSpace.equals("") == false){			
				String hql3 = "update User as u set u.telephone=:telephone where strID=:strID";
				Query query3 = session.createQuery(hql3);
				query3.setString("strID", strID);
				query3.setString("telephone", telephone);
				query3.executeUpdate(); 
			}
		}
		session.getTransaction().commit();
		session.close();
	}
	
	private static int queryStrID(String shortname){
		createSession();
		String hql = "select count(strID) from User as user where user.shortname=:shortname";
		Query query = session.createQuery(hql);  
		query.setString("shortname",shortname);
		int num = ((Number)query.uniqueResult()).intValue();
		session.getTransaction().commit();
		session.close();
		return num;		
	}
	
	public static int validatePassword(String strID, String password){
		createSession();
		String hql = "from User as user where user.strID=:strID"; //and user.password=:password";
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
		if(user == null){
			return StatusCode.USERID_NOT_EXIST;
		}
		String correctPassword = user.getPassword();
		if(EncryptBase64.encodeString(password).equals(correctPassword)){//Encrypt - Bhumik
			return StatusCode.LOGIN_SUCCESS;
		}
		else{
			return StatusCode.PASSWORD_NOT_CORRECT;
		}
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
		if(EncryptBase64.encodeString(password).equals(correctPassword) == true && isBlocked == false){//Encrypt - Bhumik
			SecurityManager.resetFail(strID);
			loginResult.setUser(user);
			loginResult.setStatusCode(StatusCode.LOGIN_SUCCESS);
			return loginResult;
		}
		else if(EncryptBase64.encodeString(password).equals(correctPassword) == true && isBlocked == true){// Encrypt - Bhumik
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