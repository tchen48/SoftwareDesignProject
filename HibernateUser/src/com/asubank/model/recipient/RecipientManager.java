package com.asubank.model.recipient;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.account.Account;
import com.asubank.model.security.Security;
import com.asubank.model.security.SessionFactoryUtil;
import com.asubank.model.transfer.Transaction;
import com.asubank.model.user.User;

public class RecipientManager {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	public String verifyRecipient(String strID,long recipient_accountnumber ){
		String result = null;
		String hql0="select recipient_accountnumber from Recipient as r where r.strID=:strID";
		String hql="select count(strID) from Account as a where a.checkingID=:recipient_accountnumber";
		String hql1="select count(strID) from Account as a where a.savingID=:recipient_accountnumber";
		createSession();
		Query query0=session.createQuery(hql0);
		Query query = session.createQuery(hql);
		Query query1 = session.createQuery(hql1);
		
		query0.setString("strID", strID);
		query.setDouble("recipient_accountnumber",recipient_accountnumber);
		query1.setDouble("recipient_accountnumber",recipient_accountnumber);
		List list=query0.list();
		if(list.contains(recipient_accountnumber))
		{
			result="Recipient Already exists!!";
			
		}
		else{
		
		String check=query.uniqueResult().toString();
				int one=Integer.parseInt(check);
		String check1=query1.uniqueResult().toString();
				int two=Integer.parseInt(check1);
		if((one|two)==1)	
		{
			result="1";
		}
			else
			{
				result="0";
			}
		}
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public void addRecipient(String strID, long recipient_accountnumber, String recipient_lastname, String recipient_nickname)
	{
		createSession();
		Recipient recipient = new Recipient();
		
		recipient.setRecipient_accountnumber(recipient_accountnumber);
		recipient.setStrID(strID);
		recipient.setRecipient_lastname(recipient_lastname);
		recipient.setRecipient_nickname(recipient_nickname);
		
		session.save(recipient);
		session.getTransaction().commit();

		session.close();
	
	}
	
	public static List<Recipient> getRecipientsById(String strID){
		createSession();
		String hql = "from Recipient as r where r.strID=:strID";
		Query query = session.createQuery(hql);
		query.setString("strID", strID);
		List <Recipient>list = query.list();					
		session.getTransaction().commit();
		session.close();
		return list;		
	}
	
	
}
