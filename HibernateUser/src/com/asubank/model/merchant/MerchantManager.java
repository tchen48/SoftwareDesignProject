package com.asubank.model.merchant;

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
		String hql="select count(customerid) from Merchant as a where a.customerid=:custID";
		Query query = session.createQuery(hql);
		query.setDouble("custID",custID);
		String check1=query.uniqueResult().toString();
		
		
		if(check1.equals("1")){
			Account account = AccountManager.queryAccount(strID);
			
			long merchantID = account.getCheckingID();
			double merchant_balance = account.getCheckingBalance();
			
			String sql1="select checkingbalance from account as a where a.checkingid=:custID";
			Query query1 = session.createSQLQuery(sql1);
			query1.setDouble("custID", custID);
			String cust_bal1=query1.uniqueResult().toString();
			double cust_bal=Double.parseDouble(cust_bal1);
			 
			
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
			
			String sql4="update account as a set a.checkingbalance=:cust_bal where a.checkingid=:custID";
			Query query4 = session.createSQLQuery(sql4);
			query4.setDouble("custID", custID);
			query4.setDouble("cust_bal",cust_bal);
			query4.executeUpdate();
			
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
	
}
