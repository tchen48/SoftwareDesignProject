package com.asubank.model.pii;

import org.hibernate.Session;

import com.asubank.model.pii.SessionFactoryUtil;
import com.asubank.model.security.Security;

public class PiiManager {
	private static Session session;
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	public static void createPii(Pii pii){
		createSession();
		session.save(pii);
		session.getTransaction().commit();
		session.close();
	}
	public static void createPartialPii(PartialPii partialPii){
		createSession();
		session.save(partialPii);
		session.getTransaction().commit();
		session.close();
	}
}
