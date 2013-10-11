package com.asubank.model;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class TestUser {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
//	
	public static void main(String[] args) {
//		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		createPerson(session);
//		queryPerson(session);
		queryStrID("New");
	}

	private static void queryPerson(Session session) {
		Query query = session.createQuery("from User");                
		List <User>list = query.list();
		java.util.Iterator<User> iter = list.iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			System.out.println("User: \"" + user.getFirstname() +"\", " + user.getLastname() +"\", "+user.getUserID()+"\", "+user.getDeptID()+"\", "+
					user.getRoleID() +"\", " + user.getAccno() +"\", "+user.getAmount()+"\", "+user.getPinno()+"\", "+
					user.getPassword() +"\", " + user.getCardno() );
		}
		
		session.getTransaction().commit();
	}

	public static void createPerson(Session session) {
		User user = new User();
		user.setUserID(123);
		user.setFirstname("New");
		user.setLastname("Obhama");	
		user.setDeptID(9);
		user.setRoleID(7);
		user.setAccno("111111");
		user.setAmount(100.01);
		user.setCardno("1111222233334444");
		user.setPinno(7910);
		user.setPassword("shaosh0913");
		session.save(user);
	}
	public static String createUser(int userID, String firstname, String lastname, int deptID, int roleID, String accno, double amount,
			String cardno, int pinno, String password){
		createSession();
		User user = new User();
		user.setUserID(userID);
		user.setFirstname(firstname);
		user.setLastname(lastname);	
		user.setDeptID(deptID);
		user.setRoleID(roleID);
		user.setAccno(accno);
		user.setAmount(amount);
		user.setCardno(cardno);
		user.setPinno(pinno);
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
		System.out.println("================================");
		session.close();
		return (user.getStrID());
//		return ("User " + user.getFirstname() + " " + user.getLastname() + " " + user.getUserID() + 
//				 	" " + user.getDeptID() + " " + user.getRoleID() + 
//				 	" " + user.getAccno() + " " + user.getAmount() + " " + user.getCardno() + 
//				 	" " + user.getPinno() + " " + user.getPassword() + " is created.");
	}
	
	public static List queryUser() {
		createSession();
		Query query = session.createQuery("from User");                
		List <User>list = query.list();
		java.util.Iterator<User> iter = list.iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			System.out.println("User: \"" + user.getFirstname() +"\", " + user.getLastname() +"\", "+user.getUserID()+"\", "+user.getDeptID()+"\", "+
					user.getRoleID() +"\", " + user.getAccno() +"\", "+user.getAmount()+"\", "+user.getPinno()+"\", "+
					user.getPassword() +"\", " + user.getCardno() );
		}
		
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	private static int queryStrID(String shortname){
		createSession();
		String hql = "select count(userID) from User as user where user.shortname=:shortname";
		Query query = session.createQuery(hql);  
		query.setString("shortname",shortname);
		int num = ((Number)query.uniqueResult()).intValue();
		session.getTransaction().commit();
		System.out.println("" + num);
		session.close();
		return num;
		
	}
	public static User validate(String strID, String password){
		createSession();
		String hql = "from User as user where user.strID=:strID and user.password=:password";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		query.setString("password", password);
//		int num = ((Number)query.uniqueResult()).intValue();		
		List <User>list = query.list();
		User user = null;
//		if(num > 0){
		java.util.Iterator<User> iter = list.iterator();
		while (iter.hasNext()) {
			user = iter.next();
		}			
//		}
		
		session.getTransaction().commit();
		session.close();
		return user;		
	}
}