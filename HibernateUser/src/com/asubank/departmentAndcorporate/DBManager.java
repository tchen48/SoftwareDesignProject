package com.asubank.departmentAndcorporate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.user.User;

public class DBManager {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static void addRow(Employee obj)
	{
		boolean flag=false;
		createSession();		
		//session.save(obj);
		String userid=obj.getuser_id();			
		try
		{
			Query query_user = session.createQuery("from User");
			@SuppressWarnings("unchecked")
			List <User>list_user = query_user.list();
	        Iterator<User> iter = list_user.iterator();
	       
			while (iter.hasNext()) 
			{

	        	User user = iter.next();	        	
	        	if (user.getStrID().equals(userid))
	        	{
	        		
	        		session.save(obj);
	        		flag=true;
	        	}
	        }
			if(!flag)
			{
				throw new IllegalArgumentException();
			}			
			
		}
		finally
		{
		session.getTransaction().commit();
		session.close();
		}
		
	}
	public static void deleteRow(Employee obj)
	{
		createSession();
		//session.delete(obj);
		try{
		Query query = session.createQuery("delete Employee where user_id= :userid");
		String userid=obj.getuser_id();		
		query.setString("userid", userid);
		int result = query.executeUpdate();
		if (result!=1)
		{
			
			throw new IllegalArgumentException();
		}
		}
		finally
		{
		session.getTransaction().commit();
		session.close();
		}
	}
	
	public static void updateRow(Employee obj,Authorizations auth)
	{
		createSession();
		try
		{
		
			if(auth.getisAuthorized()=="No")
			{
				//System.out.println("AAAAA");
				session.flush();
				session.save(auth);				
			
			}
			else
			{
				//System.out.println("BBBBB");
				String userid=obj.getuser_id();	
				String department=obj.getdepartment();
			
				Query query_emp = session.createQuery("from Employee where user_id= :userid");
			
				query_emp.setString("userid", userid);				
											
				Query query = session.createQuery("update Employee set department = :department" +" where user_id= :userid");		
				
				query.setString("userid", userid);
				query.setString("department", department);	
				query.executeUpdate();
			
				Query query2 = session.createQuery("delete Authorizations where employee_to_transfer= :employee_to_transfer");				
				query2.setString("employee_to_transfer", userid);				
				query2.executeUpdate();
				
			}	
		}
		catch(Exception e)
   	 	{
			throw new IllegalArgumentException();
   	 	}
			finally
			{
				session.getTransaction().commit();
				session.close();
			}
		
		
	}
	
	public static String find_department(String userid)
	{
		String department=null;
		createSession();
		try
		{
		//Find department	
				Query query1 = session.createQuery("from Employee where user_id= :userid");			
				query1.setString("userid", userid);
				@SuppressWarnings("unchecked")
				List <Employee>list = query1.list();
				java.util.Iterator<Employee> iter = list.iterator();        
				Employee employee=iter.next();
				department=employee.getdepartment();
		}
		catch(Exception e)
   	 	{
			throw new IllegalArgumentException();
   	 	}
			finally
			{
				session.getTransaction().commit();
				session.close();
			}
		return department;
		
	}
	
	public static void deleteAuthorizations(Employee obj)
	{
		createSession();
		String userid=obj.getuser_id();
		try
		{
			Query query2 = session.createQuery("delete Authorizations where employee_to_transfer= :employee_to_transfer");				
			query2.setString("employee_to_transfer", userid);				
			query2.executeUpdate();
		}
		catch(Exception e)
   	 	{
			throw new IllegalArgumentException();
   	 	}
		finally
		{
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static void addlog(log obj)
	{
		try
		{
		createSession();		
		session.save(obj);
		//System.out.println("Inside DMManager");
		}
		catch(Exception e)
		{	
			
				throw new IllegalArgumentException();
		}	
		
		finally
		{
		session.getTransaction().commit();
		session.close();
		}
		
	}
	
	
}
