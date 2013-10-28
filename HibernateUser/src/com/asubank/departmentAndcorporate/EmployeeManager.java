package com.asubank.departmentAndcorporate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.security.Security;
import com.asubank.model.security.SessionFactoryUtil;

public class EmployeeManager {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	public static Employee queryEmployee(String strID){
		createSession();
		Query query = session.createQuery("from Employee as e where e.user_id=:strID");
		query.setString("strID", strID);
		List <Employee>list = query.list();
		Employee employee = null;
		java.util.Iterator<Employee> iter = list.iterator();
		while (iter.hasNext()) {
			employee = iter.next();
		}
		session.close();
		return employee;
	}
}	
