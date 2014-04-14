package com.ProjMgmtSys.model.Object;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public class ObjectManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createObject(String objName, String depId, String groId, String rowNO){
		Object object = new Object();
		object.setObjName(objName);
		object.setDepId(depId);
		object.setGroId(groId);
		object.setRowNO(rowNO);
		
		createSession();
		session.save(object);
		session.getTransaction().commit();
		session.close();
		return  "" + object.getObjId();
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object queryObject(String objId){
		createSession();
		String hql = "from Object as object where object.objId=:objId";
		Query query = session.createQuery(hql);
		query.setString("objId", objId);
		List <Object>list = query.list();
		Object object = null;
		java.util.Iterator<Object> iter = list.iterator();
		while (iter.hasNext()) {
			object = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return object;	
	}
	
	public static void updateObjName(String objId, String objName){
		createSession();
		String hql1 = "update Object as object set object.objName=:objName where objId=:objId";
		Query query1 = session.createQuery(hql1);
		query1.setString("objId", objId);
		query1.setString("objName", objName);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
}