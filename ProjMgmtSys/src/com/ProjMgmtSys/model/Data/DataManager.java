package com.ProjMgmtSys.model.Data;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public class DataManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createData(String Value, String fieldId, String objId, String rowId, String depId, String groId){
		Data data = new Data();
		data.setValue(Value);
		data.setFieldId(fieldId);
		data.setObjId(objId);
		data.setRowId(rowId);
		data.setDepId(depId);
		data.setGroId(groId);
		
		createSession();
		session.save(data);
		session.getTransaction().commit();
		session.close();
		return  "" + data.getDataId();
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object queryData(String dataId){
		createSession();
		String hql = "from Data as data where data.dataId=:dataId";
		Query query = session.createQuery(hql);
		query.setString("dataId", dataId);
		List <Data>list = query.list();
		Data data = null;
		java.util.Iterator<Data> iter = list.iterator();
		while (iter.hasNext()) {
			data = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return data;	
	}
	
	public static void updateValue(String dataId, String value){
		createSession();
		String hql1 = "update Data as data set data.value=:value where dataId=:dataId";
		Query query1 = session.createQuery(hql1);
		query1.setString("dataId", dataId);
		query1.setString("value", value);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
}