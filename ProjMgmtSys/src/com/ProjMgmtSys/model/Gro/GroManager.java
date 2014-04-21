package com.ProjMgmtSys.model.Gro;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ProjMgmtSys.model.Dept.Dept;


public class GroManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createGro(String groName, int depId){
		Gro gro = new Gro();
		gro.setGroName(groName);
		gro.setDepId(depId);
		
		int repeat = checkRepeatGro(groName, depId);
		if(repeat == -1)
			return "";
		
		createSession();
		session.save(gro);
		session.getTransaction().commit();
		session.close();
		return  "" + gro.getGroId();
	}
	
	public static int checkRepeatGro(String groName, int depId){
		createSession();
		String hql = "from Gro as gro where gro.groName=:groName and gro.depId=:depId";
		Query query = session.createQuery(hql);
		query.setString("groName", groName);
		query.setInteger("depId", depId);
		List <Dept>list = query.list();
		int result = 0;
		if(list.size() > 0)
			result = -1;
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Gro queryGro(int groId){
		createSession();
		String hql = "from Gro as gro where gro.groId=:groId";
		Query query = session.createQuery(hql);
		query.setInteger("groId", groId);
		List <Gro>list = query.list();
		Gro gro = null;
		java.util.Iterator<Gro> iter = list.iterator();
		while (iter.hasNext()) {
			gro = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return gro;	
	}
	
	public static Gro queryGroByName(String groName, int depId){
		createSession();
		String hql = "from Gro as gro where gro.groName=:groName and gro.depId=:depId";
		Query query = session.createQuery(hql);
		query.setString("groName", groName);
		query.setInteger("depId", depId);
		List <Gro>list = query.list();
		Gro gro = null;
		java.util.Iterator<Gro> iter = list.iterator();
		while (iter.hasNext()) {
			gro = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return gro;		
	}
	
	public static String updateGroName(String oldName, String newName, int depId){
		int repeat = checkRepeatGro(newName, depId);
		if(repeat == -1)
			return "0";
		
		Gro gro = queryGroByName(oldName, depId);
		if(gro == null)
			return "0";
		
		createSession();
		String hql1 = "update Gro as gro set gro.groName=:newName where gro.groName=:oldName and gro.depId=:depId";
		Query query1 = session.createQuery(hql1);
		query1.setInteger("depId", depId);
		query1.setString("newName", newName);
		query1.setString("oldName", oldName);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
		return "1";
	}
	
}