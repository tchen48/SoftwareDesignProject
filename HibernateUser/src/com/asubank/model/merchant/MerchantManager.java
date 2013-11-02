package com.asubank.model.merchant;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.account.Account;
import com.asubank.model.account.AccountManager;
import com.asubank.model.security.SessionFactoryUtil;
import com.asubank.model.transfer.Transaction;



public class MerchantManager {

	Merchant merchant = new Merchant();
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction(); 
	}

public String merchant_payment(String strID,long custID){		
		createSession();
		String message;
		Account account=AccountManager.queryAccount(strID);
		String hql="select customerid from Merchant as a where a.checkingID=:checkingID";
		Query query = session.createQuery(hql);
		query.setDouble("checkingID",account.getCheckingID());
		List check1=query.list();
				
		if(check1.contains(custID)){
			String verifyid=AccountManager.queryAccountOwnerID(custID);
			
			Account toaccount=AccountManager.queryAccount(verifyid);
			String cust_bal1;
			double cust_bal;
			if(toaccount.getCheckingID()==custID)
			{
				String sql="select checkingbalance from account as a where a.checkingid=:toID";
				Query query1 = session.createSQLQuery(sql);
				query1.setDouble("toID", custID);
				cust_bal1=query1.uniqueResult().toString();
				cust_bal=Double.parseDouble(cust_bal1);
			}
			else
			{
				{
					String sql="select savingbalance from account as a where a.savingid=:toID";
					Query query1 = session.createSQLQuery(sql);
					query1.setDouble("toID",custID);
					 cust_bal1=query1.uniqueResult().toString();
					cust_bal=Double.parseDouble(cust_bal1);
				}
				
			}
						
			long merchantID = account.getCheckingID();
			double merchant_balance = account.getCheckingBalance();
			
			String sql2="select pay_amount from Merchant as a where a.customerid=:custID";
			Query query2 = session.createSQLQuery(sql2);
			query2.setDouble("custID", custID);
			String amount1=query2.uniqueResult().toString();
			double amount=Double.parseDouble(amount1);
			
			merchant_balance+=amount;
			cust_bal-=amount;
			
			String sql3="update account as a set a.checkingbalance=:merchant_balance where a.checkingid=:merchantID";
			Query query3 = session.createSQLQuery(sql3);
			query3.setDouble("merchantID", merchantID);
			query3.setDouble("merchant_balance",merchant_balance);
			query3.executeUpdate();
			
			if(toaccount.getCheckingID()==custID)
			{
			String sql4="update account as a set a.checkingbalance=:cust_bal where a.checkingid=:custID";
			Query query4 = session.createSQLQuery(sql4);
			query4.setDouble("custID", custID);
			query4.setDouble("cust_bal",cust_bal);
			query4.executeUpdate();
			}
			else
			{
				String sql5="update account as a set a.savingbalance=:cust_bal where a.savingid=:custID";
				Query query5 = session.createSQLQuery(sql5);
				query5.setDouble("custID", custID);
				query5.setDouble("cust_bal",cust_bal);
				query5.executeUpdate();
			}
			
			session.getTransaction().commit();
			session.close();
			//addTransaction(strID,fromID,toID,amount);
			
			message="Make Payment Successful ! Please log out.";
			
		}
		else{
			message = "Customer does not exist!";
		}
		return message;
	}

	public static String createpayment(long checkingID,long customerid,double pay_amount)
	{
	createSession();
	Merchant merchant=new Merchant();
	merchant.setCheckingID(checkingID);
	merchant.setcustomerid(customerid);
	merchant.setPay_amount(pay_amount);
	
	
	session.save(merchant);
	session.getTransaction().commit();
	
	session.close();
	return "Payment Succesfull!!";
	}
	
	public static List queryPayments(long checkingID){
		createSession();
		String hql = "from Merchant as m where m.checkingID=:checkingID";
		Query query = session.createQuery(hql);
		query.setLong("checkingID",checkingID);
		
		List <Merchant>list = query.list();					
		session.getTransaction().commit();
		session.close();
		return list;
	}
}
