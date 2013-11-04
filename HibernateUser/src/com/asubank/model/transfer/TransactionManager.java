package com.asubank.model.transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.departmentAndcorporate.DBManager;
import com.asubank.departmentAndcorporate.Transactions;
import com.asubank.departmentAndcorporate.log;
import com.asubank.model.account.Account;
import com.asubank.model.account.AccountManager;
import com.asubank.model.security.SessionFactoryUtil;

public class TransactionManager {
	Transaction transaction = new Transaction();
	private static Date defaultDate;
	private static SimpleDateFormat df;	
	private static Session session;
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

	public String transferMoney(String strID, long fromID, long toID, double amount)
	  {     
		Account account = AccountManager.queryAccount(strID);
		
		createSession();
		String hql="select strID from Recipient as a where a.recipient_accountnumber=:toID";
		Query query = session.createQuery(hql);
		query.setDouble("toID",toID);
		List check1=query.list();
		String message = null;
		
		if(check1.contains(strID)){
			String touser=AccountManager.queryAccountOwnerID(toID);
			Account toaccount=AccountManager.queryAccount(touser);
			double toBalance;
			if(toaccount.getCheckingID()==toID)
			{
				String sql="select checkingbalance from account as a where a.checkingid=:toID";
				Query query1 = session.createSQLQuery(sql);
				query1.setDouble("toID", toID);
				String toBalance1=query1.uniqueResult().toString();
				toBalance=Double.parseDouble(toBalance1);
			}
			else
			{
				{
					String sql="select savingbalance from account as a where a.savingid=:toID";
					Query query1 = session.createSQLQuery(sql);
					query1.setDouble("toID", toID);
					String toBalance1=query1.uniqueResult().toString();
					toBalance=Double.parseDouble(toBalance1);
				}
				
			}
		 
		if(fromID == account.getCheckingID()){
			if(account.getCheckingBalance()>=amount)
			{
			double money=account.getCheckingBalance()-amount;
			toBalance+=amount;
			
			String sql1="update account as a set a.checkingbalance=:money where a.checkingid=:fromID";
			Query query2 = session.createSQLQuery(sql1);
			query2.setDouble("fromID", fromID);
			query2.setDouble("money",money);
			query2.executeUpdate();
			
			if(toaccount.getCheckingID()==toID)
			{
			String sql2="update account as a set a.checkingbalance=:toBalance where a.checkingid=:toID";
			Query query3 = session.createSQLQuery(sql2);
			query3.setDouble("toID", toID);
			query3.setDouble("toBalance",toBalance);
			query3.executeUpdate();
			}
			else{
				String sql2="update account as a set a.savingbalance=:toBalance where a.savingid=:toID";
				Query query3 = session.createSQLQuery(sql2);
				query3.setDouble("toID", toID);
				query3.setDouble("toBalance",toBalance);
				query3.executeUpdate();
				
			}
			message="transfer successful";
			
			}
			else
			{
				message="Amount not sufficent in the account to tranasfer";
			}
		}
		else if(fromID == account.getSavingID()){
			if(account.getSavingBalance()>=amount)
			{
			double money=account.getSavingBalance()-amount;
			toBalance+=amount;
			
			String sql3="update account as a set a.savingbalance=:money where a.savingid=:fromID";
			Query query4 = session.createSQLQuery(sql3);
			query4.setDouble("fromID",fromID);
			query4.setDouble("money",money);
			query4.executeUpdate();
			
			if(toaccount.getCheckingID()==toID)
			{
			String sql4="update account as a set a.checkingbalance=:toBalance where a.checkingid=:toID";
			Query query5 = session.createSQLQuery(sql4);
			query5.setDouble("toID", toID);
			query5.setDouble("toBalance", toBalance);
			query5.executeUpdate();
			}
			else{
				String sql2="update account as a set a.savingbalance=:toBalance where a.savingid=:toID";
				Query query3 = session.createSQLQuery(sql2);
				query3.setDouble("toID", toID);
				query3.setDouble("toBalance",toBalance);
				query3.executeUpdate();
				
			}
			message="transfer successful";
			}
			
			else
			{
				message="Amount not sufficent in the account to tranasfer";
			}
		}
		session.getTransaction().commit();
		session.close();
		
		addTransaction(strID,fromID,toID,amount, "debit");
		
		
		String tostrID=AccountManager.queryAccountOwnerID(toID);
		addTransaction(tostrID,fromID,toID,amount, "credit");
		log lg = new log();
		String content = "Transaction done from " + fromID + " to " + toID + " of amount " + amount;
		lg.setcontent(content);
		lg.settime(new Date());
		DBManager.addlog(lg);
		}
		else if(AccountManager.queryAccountOwnerID(toID)!= null&&AccountManager.queryAccountOwnerID(toID).equals(AccountManager.queryAccountOwnerID(fromID))){
			createSession();
			if(account.getCheckingID()==fromID)
			{
				double newchecking=account.getCheckingBalance()-amount;
				double newsaving=account.getSavingBalance()+amount;
				String sql1="update account as a set a.checkingbalance=:newchecking where a.checkingid=:fromID";
				Query query2 = session.createSQLQuery(sql1);
				query2.setDouble("fromID", fromID);
				query2.setDouble("newchecking",newchecking);
				query2.executeUpdate();
				
				if(account.getSavingID()==toID)
				{
				
				String sql2="update account as a set a.savingbalance=:toBalance where a.savingid=:toID";
				Query query3 = session.createSQLQuery(sql2);
				query3.setDouble("toID", toID);
				query3.setDouble("toBalance",newsaving);
				query3.executeUpdate();
				message="transfer successful";
				}
				else
				{
					double newcredit=account.getCreditBalance()+amount;
					String sql2="update account as a set a.creditbalance=:toBalance where a.creditid=:toID";
					Query query3 = session.createSQLQuery(sql2);
					query3.setDouble("toID", toID);
					query3.setDouble("toBalance",newcredit);
					query3.executeUpdate();
					message="transfer successful";
				}
				
			}
			else
			{
				double newbalance=account.getCheckingBalance()+amount;
				double newsaving=account.getSavingBalance()-amount;
				String sql2="update account as a set a.savingbalance=:toBalance where a.savingid=:fromID";
				Query query3 = session.createSQLQuery(sql2);
				query3.setDouble("fromID", fromID);
				query3.setDouble("toBalance",newsaving);
				query3.executeUpdate();
				
				if(account.getCheckingID()==toID)
				{
				String sql1="update account as a set a.checkingbalance=:newchecking where a.checkingid=:toID";
				Query query2 = session.createSQLQuery(sql1);
				query2.setDouble("toID", toID);
				query2.setDouble("newchecking",newbalance);
				query2.executeUpdate();
				}
				else	
				{
					double newcredit=account.getCreditBalance()+amount;
					String sql3="update account as a set a.creditbalance=:toBalance where a.creditid=:toID";
					Query query4 = session.createSQLQuery(sql3);
					query4.setDouble("toID", toID);
					query4.setDouble("toBalance",newcredit);
					query4.executeUpdate();
					message="transfer successful";	
				}
				
				
				message="transfer successful";
			}	
			session.getTransaction().commit();
			session.close();
			addTransaction(strID,fromID,toID,amount, "debit");
			log lg = new log();
			String content = "Transaction done from " + fromID + " to " + toID + " of amount " + amount;
			lg.setcontent(content);
			lg.settime(new Date());
			DBManager.addlog(lg);
		}
		
		else{
			message = "Recepient doesn't exist!";
		}
	
		return message;
	  }
	
	
	public void addTransaction(String strID, long from_accountnumber, long to_accountnumber, double amount, String type)
	{
		createSession();
		Date d = new Date();
//		String strDate = df.format(d);		
		Transaction transaction=new Transaction();
		Transactions tr=new Transactions();
		
		tr.setfrom_user(strID);
		tr.setstatus("Complete");
		tr.setto_user(Long.toString(to_accountnumber));
		tr.settransaction_amount(Double.toString(amount));
		tr.settransaction_time(d);
		tr.settransaction_type(type);
		session.save(tr);
		
		transaction.setStrID(strID);
		transaction.setFromID(from_accountnumber);
		transaction.setToID(to_accountnumber);
		transaction.setAmount(amount);
		transaction.setTime(d);
		transaction.setType(type);
		session.save(transaction);
		
		session.getTransaction().commit();
		session.close();
	
	}
//	public static List<Transaction> getTransactionsById(String strID){
	public static List<Transaction> getTransactionsById(String strID,Long accountID){
		createSession();
		String hql = "from Transaction as t where t.fromID=:accountID or toID=:accountID";
		Query query = session.createQuery(hql);
		query.setLong("accountID", accountID);
//		query.setString("strID", strID);
//		query.setString("strID", strID);
		Transaction transaction=new Transaction();
		List <Transaction>list = query.list();
		List<Transaction> list1=new ArrayList<Transaction>();
		Iterator<Transaction> iter = list.iterator();
		while (iter.hasNext()) {
			transaction = iter.next();
			if(transaction.getStrID().equals(strID))
			{
				list1.add(transaction);
			}
		}
		session.getTransaction().commit();
		session.close();
		return list1;		
	}
	
	public static List<Transaction> getTransactionsById(String strID){
		//public static List<Transaction> getTransactionsById(Long accountID){
			createSession();
			String hql = "from Transaction as t where t.strID=:strID";
			Query query = session.createQuery(hql);
			//query.setLong("accountID", accountID);
			query.setString("strID", strID);
//			query.setString("strID", strID);
			List <Transaction>list = query.list();					
			session.getTransaction().commit();
			session.close();
			return list;		
		}

}
