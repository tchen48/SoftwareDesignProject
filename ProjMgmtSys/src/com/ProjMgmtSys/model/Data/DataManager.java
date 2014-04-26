package com.ProjMgmtSys.model.Data;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.xml.sax.SAXException;

import com.ProjMgmtSys.model.Object.ObjectManager;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;


public class DataManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createData(String Value, int fieldId, int objId, int rowId, int depId, int groId){
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
	
	public static String createData(JSONArray jArray, int objId, int depId, int groId) throws SAXException, JAXBException{
		List<Data> dataList = new ArrayList<Data>();
		int rowNO = ObjectManager.queryRowNO(objId);
		for(Object json : jArray){
			JSONObject jObj = (JSONObject)json;
			createSession();
			Data data = new Data();
			data.setDepId(depId);
			data.setFieldId(jObj.getInt("id"));
			data.setGroId(groId);
			data.setObjId(objId);
			data.setValue("" + jObj.get("val"));
			data.setRowId(rowNO);
			data.setDataId(objId + "_" + depId + "_" + groId + "_" + rowNO + "_" + jObj.getInt("id"));		
			session.save(data);
			session.getTransaction().commit();
			session.close();
//			dataList.add(data);
		}
		Iterator<Data> iter = dataList.iterator();
		
//		while(iter.hasNext()){
//			createSession();
//			Data data = iter.next();
//			session.save(data);
//			session.getTransaction().commit();
//			session.close();
//		}
		
		int projId = rowNO;
		ObjectManager.updateRowNO(objId, rowNO + 1);
		return "" + projId;
	}
	
	@SuppressWarnings("unchecked")
	public static Object queryData(int dataId){
		createSession();
		String hql = "from Data as data where data.dataId=:dataId";
		Query query = session.createQuery(hql);
		query.setInteger("dataId", dataId);
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

	public static Data queryData(int depId, int groId, int objId, int rowId, int fieldId){
		createSession();
		String hql = "from Data where depId=:depId, objId=:objId, groId=:groId, rowId=:rowId, fieldId=:fieldId, ";
		Query query = session.createQuery(hql);
		query.setInteger("depId", depId);
		query.setInteger("groId", groId);
		query.setInteger("objId", objId);
		query.setInteger("rowId", rowId);
		query.setInteger("fieldId", fieldId);
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
	
	public static void updateValue(int dataId, String value){
		createSession();
		String hql1 = "update Data as data set data.value=:value where dataId=:dataId";
		Query query1 = session.createQuery(hql1);
		query1.setInteger("dataId", dataId);
		query1.setString("value", value);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
}