package com.asubank.model.account;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.security.Security;
import com.asubank.model.security.SessionFactoryUtil;
import com.asubank.model.user.User;
import com.asubank.model.user.UserManager;

public class AccountManager {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	public static void createAccount(String strID){
		Account account = new Account(strID);
		User user=UserManager.queryUser(strID);
		account.setFirstname(user.getFirstname());
		createAccountID(account);		
		createSession();		
		session.save(account);
		session.getTransaction().commit();
		session.close();
	}
	
	private static void createAccountID(Account account){
		Random random = new Random();
		boolean flag = false;	
		int num;
		long checkingID, savingID, creditID;
		while(flag == false){
			checkingID = Math.round(Math.pow(10, 11) + random.nextDouble() * 3 * Math.pow(10, 11));
			String hql = "select count(strID) from Account as a where a.checkingID=:checkingID";
			createSession();
			Query query = session.createQuery(hql);  
			query.setLong("checkingID",checkingID);
			num = ((Number)query.uniqueResult()).intValue();
			session.getTransaction().commit();
			session.close();
			if(num == 0){
				flag = true;
				account.setCheckingID(checkingID);
			}
		}
				
		flag = false;		
		while(flag == false){
			savingID = Math.round(Math.pow(10, 11) * 4 + random.nextDouble() * 3 * Math.pow(10, 11));
			String hql = "select count(strID) from Account as a where a.savingID=:savingID";
			createSession();
			Query query = session.createQuery(hql);  
			query.setLong("savingID",savingID);
			num = ((Number)query.uniqueResult()).intValue();
			session.getTransaction().commit();
			session.close();
			if(num == 0){
				flag = true;
				account.setSavingID(savingID);
			}
		}
		
		flag = false;		
		while(flag == false){
			creditID = Math.round(Math.pow(10, 11) * 7 + random.nextDouble() * 3 * Math.pow(10, 11));
			String hql = "select count(strID) from Account as a where a.creditID=:creditID";
			createSession();
			Query query = session.createQuery(hql);  
			query.setLong("creditID",creditID);
			num = ((Number)query.uniqueResult()).intValue();
			session.getTransaction().commit();
			session.close();
			if(num == 0){
				flag = true;
				account.setCreditID(creditID);
			}
		}
		return;
	} 
	
	public static Account queryAccount(String strID){
		createSession();
		String hql = "from Account as a where a.strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		List <Account>list = query.list();
		Account account = null;
		java.util.Iterator<Account> iter = list.iterator();
		while (iter.hasNext()) {
			account = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return account;		
	}
	
	public static String queryAccountOwnerID(long accountID){
		createSession();
		String hql = "from Account as a where a.checkingID=:accountID or a.savingID=:accountID or a.creditID=:accountID";
		Query query = session.createQuery(hql);
		query.setLong("accountID", accountID);
		List <Account>list = query.list();
		Account account = null;
		java.util.Iterator<Account> iter = list.iterator();
		while (iter.hasNext()) {
			account = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		if(account != null)
			return account.getStrID();	
		else
			return null;
	}
	
}
