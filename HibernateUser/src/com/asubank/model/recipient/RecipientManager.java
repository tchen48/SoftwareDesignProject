package com.asubank.model.recipient;

import java.util.List;
import java.util.Random;
import org.hibernate.Query;
import org.hibernate.Session;
import com.asubank.model.account.Account;
import com.asubank.model.security.Security;
import com.asubank.model.security.SessionFactoryUtil;
import com.asubank.model.user.User;

public class RecipientManager {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	public String verifyRecipient(String strID,long recipient_accountnumber ){
		int check;
		String hql="select count(strID) from Account as a where a.checkingID=:recipient_accountnumber";
		createSession();
		Query query = session.createQuery(hql);
		
		query.setDouble("recipient_accountnumber",recipient_accountnumber);
		String check1=query.uniqueResult().toString();
				
		//check = ((Number)query.uniqueResult()).intValue();		
		session.getTransaction().commit();
		session.close();
		return check1;
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
	
	
	
}
