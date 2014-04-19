package com.ProjMgmtSys.model.Field;
 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public class FieldManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static String createField(String fieldName, String dataType, int objId, int depId, int groId){
		Field field = new Field();
		field.setFieldName(fieldName);
		field.setDataType(dataType);
		field.setObjId(objId);
		field.setDepId(depId);
		field.setGroId(groId);
		
		createSession();
		session.save(field);
		session.getTransaction().commit();
		session.close();
		return  "" + field.getFieldId();
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object queryField(int fieldId){
		createSession();
		String hql = "from Field as field where field.fieldId=:fieldId";
		Query query = session.createQuery(hql);
		query.setInteger("fieldId", fieldId);
		List <Field>list = query.list();
		Field field = null;
		java.util.Iterator<Field> iter = list.iterator();
		while (iter.hasNext()) {
			field = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return field;	
	}
	
	public static void updateFieldName(int fieldId, String fieldName){
		createSession();
		String hql1 = "update Field as field set field.fieldName=:fieldName where fieldId=:fieldId";
		Query query1 = session.createQuery(hql1);
		query1.setInteger("fieldId", fieldId);
		query1.setString("fieldName", fieldName);
		query1.executeUpdate(); 
		session.getTransaction().commit();
		session.close();
	}
	
}