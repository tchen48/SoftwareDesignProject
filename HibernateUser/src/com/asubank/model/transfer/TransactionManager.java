package com.asubank.model.transfer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.account.Account;
import com.asubank.model.account.AccountManager;
import com.asubank.model.security.SessionFactoryUtil;

public class TransactionManager {
	Transaction transaction = new Transaction();
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}

	public String transferMoney(String strID, long fromID, long toID, double amount)
	  {     
		Account account = AccountManager.queryAccount(strID);
		
		createSession();
		
		String hql="select count(strID) from Recipient as a where a.recipient_accountnumber=:toID";
		Query query = session.createQuery(hql);
		query.setDouble("toID",toID);
		String check1=query.uniqueResult().toString();
		String message;
		
		if(check1.equals("1")){
		String sql="select checkingbalance from account as a where a.checkingid=:toID";
		Query query1 = session.createSQLQuery(sql);
		query1.setDouble("toID", toID);
		String toBalance1=query1.uniqueResult().toString();
		double toBalance=Double.parseDouble(toBalance1);
		 
		if(fromID == account.getCheckingID()){
			double money=account.getCheckingBalance()-amount;
			toBalance+=amount;
			
			String sql1="update account as a set a.checkingbalance=:money where a.checkingid=:fromID";
			Query query2 = session.createSQLQuery(sql1);
			query2.setDouble("fromID", fromID);
			query2.setDouble("money",money);
			query2.executeUpdate();
			
			String sql2="update account as a set a.checkingbalance=:toBalance where a.checkingid=:toID";
			Query query3 = session.createSQLQuery(sql2);
			query3.setDouble("toID", toID);
			query3.setDouble("toBalance",toBalance);
			query3.executeUpdate();
		}
		else if(fromID == account.getSavingID()){
			double money=account.getSavingBalance()-amount;
			toBalance+=amount;
			
			String sql3="update account as a set a.savingbalance=:money where a.savingid=:fromID";
			Query query4 = session.createSQLQuery(sql3);
			query4.setDouble("fromID",fromID);
			query4.setDouble("money",money);
			query4.executeUpdate();
			
			String sql4="update account as a set a.checkingbalance=:toBalance where a.checkingid=:toID";
			Query query5 = session.createSQLQuery(sql4);
			query5.setDouble("toID", toID);
			query5.setDouble("toBalance", toBalance);
			query5.executeUpdate();
		}
		session.getTransaction().commit();
		session.close();
		addTransaction(strID,fromID,toID,amount);
		
		message="transfer successful";
		
		
		}
		else{
			message = "Recepient doesn't exist!";
		}
		
		
		return message;
	  }
	
	
	public void addTransaction(String strID, long from_accountnumber, long to_accountnumber, double amount)
	{
		createSession();
		Transaction transaction=new Transaction();
		transaction.setStrID(strID);
		transaction.setFromID(from_accountnumber);
		transaction.setToID(to_accountnumber);
		transaction.setAmount(amount);
		session.save(transaction);
		session.getTransaction().commit();
		session.close();
	
	}
	public static List<Transaction> getTransactionsById(String strID){
		createSession();
		String hql = "from Transaction as t where t.strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		query.setString("strID", strID);
		List <Transaction>list = query.list();					
		session.getTransaction().commit();
		session.close();
		return list;		
	}

}
