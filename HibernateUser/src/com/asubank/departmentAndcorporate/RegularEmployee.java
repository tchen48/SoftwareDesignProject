package com.asubank.departmentAndcorporate;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.account.Account;
import com.asubank.model.pii.PartialPii;
import com.asubank.model.security.SessionFactoryUtil;
import com.asubank.model.transfer.Transaction;


public class RegularEmployee {

	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static PartialPii getPIIById(String strID){
		createSession();
		String hql = "from PartialPii as p where p.strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		//query.setString("strID", strID);
		PartialPii partial = (PartialPii) query.uniqueResult();					
		session.getTransaction().commit();
		session.close();
		return partial;		
	}
	public static List<Transaction> getTransactionsById(String strID){
		createSession();
		String hql = "from Transaction as t where t.strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		//query.setString("strID", strID);
		List <Transaction>list = query.list();					
		session.getTransaction().commit();
		session.close();
		return list;		
	}

	public static List<Account> getAccounts() {
		createSession();
		String hql = "from Account";
		Query query = session.createQuery(hql);
		
		List<Account> list = query.list();
						
		session.getTransaction().commit();
		session.close();
		return list;		
	}
}
