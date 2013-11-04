package com.asubank.departmentAndcorporate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class AccountManagement {
	
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	
	public static List<Transactions> viewTransactions()
	{
		
		createSession();
		Query query = session.createQuery("from Transactions");                 
        @SuppressWarnings("unchecked")
		List <Transactions>list = query.list();
        java.util.Iterator<Transactions> iter = list.iterator();
       
		while (iter.hasNext()) {

        	Transactions transaction = iter.next();
            
            //System.out.println("From_user:" +transaction.getfrom_user()+"\n"+
//            "Transaction_id :"+transaction.gettransaction_id()+"\n"+
//            "Transaction_type :"+transaction.gettransaction_type()+"\n"+
//            "Transaction_amount :"+transaction.gettransaction_amount()+"\n"+
//            "To_user :"+transaction.getto_user()+"\n"+
//            "Transaction_time :"+transaction.gettransaction_time()+"\n"+
//            "Status :"+transaction.getstatus()+"\n");    
    	   
           
        }

        session.getTransaction().commit();
        session.close();
       
        return list;
	}
	

	public static List<Transactions> viewTransactions(String userid)
	{
			
        createSession();
		
		//Find department	
		Query query1 = session.createQuery("from Employee where user_id= :userid");			
		query1.setString("userid", userid);
		@SuppressWarnings("unchecked")
		List <Employee>list = query1.list();
		java.util.Iterator<Employee> iter = list.iterator();        
		Employee employee=iter.next();
		String department=employee.getdepartment();
		
		Query query = session.createQuery("from Employee where department=:department");
		query.setParameter("department",department);
        @SuppressWarnings("unchecked")
		List <Employee>list1 = query.list();
       
        
        List<Transactions> list_dept_transactions =new ArrayList<Transactions>() ;
        Query query3 = session.createQuery("from Transactions");                 
        @SuppressWarnings("unchecked")
		List <Transactions>list3 = query3.list();
        java.util.Iterator<Transactions> iter3 = list3.iterator();
        while (iter3.hasNext()) 
        {
        	Transactions transaction = iter3.next();       
        	String user1=transaction.getfrom_user();
        	/*System.out.println("From_user:" +transaction.getfrom_user()+"\n"+
                    "Transaction_id :"+transaction.gettransaction_id()+"\n"+
                    "Transaction_type :"+transaction.gettransaction_type()+"\n"+
                    "Transaction_amount :"+transaction.gettransaction_amount()+"\n"+
                    "To_user :"+transaction.getto_user()+"\n"+
                    "Transaction_time :"+transaction.gettransaction_time()+"\n"+
                    "Status :"+transaction.getstatus()+"\n");  */
        	java.util.Iterator<Employee> iter1 = list1.iterator();
        	while(iter1.hasNext())
        	{
        		Employee employee_temp = iter1.next();
        		String user2=employee_temp.getuser_id();
        		if (user2.equals(user1))
        		{
        			//System.out.println("User id of employee:" +employee_temp.getuser_id()+"\n"+
                    //		"Department of employee:" +employee_temp.getdepartment()+"\n"+"Role of employee:"+employee_temp.getrole()
                   // +"\n");
        			list_dept_transactions.add(transaction);
        		}
        	}
        }      
		
        session.getTransaction().commit();  
        session.close();
        return list_dept_transactions;
	}
	
	
	
	
	
	public static List<Employee> ViewEmployees(String userid)
	{
		
		createSession();
		
		//Find department	
		Query query1 = session.createQuery("from Employee where user_id= :userid");			
		query1.setString("userid", userid);
		@SuppressWarnings("unchecked")
		List <Employee>list = query1.list();
		java.util.Iterator<Employee> iter = list.iterator();        
		Employee employee=iter.next();
		String department=employee.getdepartment();
		
		Query query = session.createQuery("from Employee where department=:department");
		query.setParameter("department",department);
        @SuppressWarnings("unchecked")
		List <Employee>list1 = query.list();
        java.util.Iterator<Employee> iter1 = list1.iterator();
        
		while (iter1.hasNext()) {

			Employee employee_temp = iter1.next();
            
           // System.out.println("User id of employee:" +employee_temp.getuser_id()+"\n"+
           // 		"Department of employee:" +employee_temp.getdepartment()+"\n"+"Role of employee:"+employee_temp.getrole()
          //  +"\n");       	   
           
        }

        session.getTransaction().commit();  
        session.close();
        return list1;
	}
	
	public static List<Authorizations> viewAuthorizations(String userid)
	{
			
        createSession();		
		
        //Find department	
      		Query query_dep = session.createQuery("from Employee where user_id= :userid");			
      		query_dep.setString("userid", userid);
      		@SuppressWarnings("unchecked")
      		List <Employee>list = query_dep.list();
      		java.util.Iterator<Employee> iter = list.iterator();        
      		Employee employee=iter.next();
      		String department=employee.getdepartment();
        
        
        
      		Query query = session.createQuery("from Authorizations where department=:department");
    		query.setParameter("department",department);
            @SuppressWarnings("unchecked")
    		List <Authorizations>list1 = query.list();
		
            java.util.Iterator<Authorizations> iter1 = list1.iterator();
        	while(iter1.hasNext())
        	{
        		Authorizations auth = iter1.next();
        		
        			//System.out.println(auth.getuser_id()+"\n"+auth.getdescription()+"\n"+auth.getdepartment());
        			
        		
        	}            
            
        session.getTransaction().commit();  
        session.close();
        return list1;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Employee> ViewEmployeesAll()
	{
		List <Employee>list=null;
		try
		{
		createSession();
		
		
		//Find department	
		Query query1 = session.createQuery("from Employee");			
		
		 list= query1.list();
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
        return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Employee> ViewAccounts()
	{
		List <Employee>list=null;
		try
		{
		createSession();
		
		
		//Find department	
		Query query1 = session.createQuery("from Account");			
		
		 list= query1.list();
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
        return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<log> ViewLogs()
	{
		List <log>list=null;
		try
		{
		createSession();
		
		
		//Find department	
		Query query1 = session.createQuery("from log");			
		
		 list= query1.list();
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
        return list;
	}
	
	
	
}
