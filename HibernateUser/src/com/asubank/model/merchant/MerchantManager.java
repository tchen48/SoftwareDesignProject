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
		
			double amount = 0;
			String sql2="select pay_amount from Merchant as a where a.customerid=:custID";
			Query query2 = session.createSQLQuery(sql2);
			query2.setDouble("custID", custID);
			List<Double> amount1=query2.list();
			java.util.Iterator<Double> iter =amount1.iterator();
			while (iter.hasNext()) {
				amount += iter.next();
			}	
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
			
			Query query6 = session.createQuery("delete Merchant where customerid=:custID");	
			query6.setDouble("custID",custID);
			int result = query6.executeUpdate();
			if (result!=1)
			{
				
				throw new IllegalArgumentException();
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

	public static String createpayment(String checkingID,String customerid,String pay_amount)
	{
	createSession();
	
    String message=null;
	String hql = "select count(strID)from Account as a where a.checkingID=:accountID or a.savingID=:accountID ";
	String hql1 = "select count(strID)from Account as a where a.checkingID=:accountID or a.savingID=:accountID ";
	Query query = session.createQuery(hql);
	Query query1= session.createQuery(hql1);
	query.setLong("accountID", Long.valueOf(checkingID));
	query1.setLong("accountID",Long.valueOf(customerid));
	String check=query.uniqueResult().toString();
	String check1=query.uniqueResult().toString();
	Merchant merchant=new Merchant();
	if(check.equals("1")&&check1.equals("1"))
	{
	
	merchant.setCheckingID(Long.valueOf(checkingID));
    merchant.setcustomerid(Long.valueOf(customerid));
    merchant.setPay_amount(Double.valueOf(pay_amount));
    message="Payment Suceesfull";
	}
	else
	{
		message="Incorrect Account Number";
	}
	
	session.save(merchant);
	session.getTransaction().commit();
	session.close();
	return message;
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
