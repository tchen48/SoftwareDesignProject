package com.asubank.model.sessionset;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.asubank.model.sessionset.SessionFactoryUtil;

public class SessionSetManager {
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
	
	public static SessionSet createSessionSet(String sessionID) throws NoSuchAlgorithmException{
		String sessionKey = makeSHA1Hash(sessionID);
		SessionSet sessionSet = new SessionSet();
		sessionSet.setSessionKey(sessionKey);
		sessionSet.setSessionID(sessionID);
		createSession();
		session.save(sessionSet);
		session.getTransaction().commit();
		session.close();
		return sessionSet;
	}
		
	public static boolean validateSessionSet(SessionSet sessionSet){
		String sessionID = sessionSet.getSessionID();
		String key = sessionSet.getSessionKey();
		SessionSet oriSessionSet = querySessionSet(sessionID);
		String oriSessionID = oriSessionSet.getSessionID();
		String oriKey = oriSessionSet.getSessionKey();
		
		if(sessionID.equals(oriSessionID) && key.equals(oriKey))
			return true;
		else
			return false;		
	}
	
	private static SessionSet querySessionSet(String sessionID){
		createSession();
		Query query = session.createQuery("from SessionSet as s where s.sessionID=:sessionID");
		query.setString("sessionID", sessionID);
		List <SessionSet>list = query.list();
		SessionSet sessionSet = null;
		java.util.Iterator<SessionSet> iter = list.iterator();
		while (iter.hasNext()) {
			sessionSet = iter.next();
		}
		session.close();
		return sessionSet;
	}
	
	private static String makeSHA1Hash(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = input.getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return hexStr;
    }
}
