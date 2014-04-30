package com.ProjMgmtSys.model.Object;
 
import java.util.List;

import net.sf.json.JSONArray;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ProjMgmtSys.model.Gro.Gro;
import com.ProjMgmtSys.model.User.NameId;


public class ObjectManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createObject(String objName, int depId, int groId, int rowNO){
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
	public static Object queryObject(int objId){
		createSession();
		String hql = "from Object as object where object.objId=:objId";
		Query query = session.createQuery(hql);
		query.setInteger("objId", objId);
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
	
	public static int queryRowNO(int objId){
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
		String hql = "from Object where objId=:objId";
		Query query = session.createQuery(hql); 
		query.setInteger("objId", objId);
		List <Object>list = query.list();
		Object object = null;
		java.util.Iterator<Object> iter = list.iterator();
		while (iter.hasNext()) {
			object = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return object.getRowNO();	
	}
	
	public static void updateObjName(int objId, String objName){
		createSession();
		String hql1 = "update Object set objName=:objName where objId=:objId";
		Query query1 = session.createQuery(hql1);
		query1.setInteger("objId", objId);
		query1.setString("objName", objName);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
	public static void updateRowNO(int objId, int rowNO){
		createSession();
		String hql1 = "update Object set rowNO=:rowNO where objId=:objId";
		Query query1 = session.createQuery(hql1);
		query1.setInteger("objId", objId);
		query1.setInteger("rowNO", rowNO);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
}