package wormbox.server;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class RelationManager {
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static void addRelation(String recipientId, long fileId){
		Relation relation = new Relation();
		relation.setFileId(fileId);
		relation.setRecipientId(recipientId);
		createSession();
		session.save(relation);
		session.getTransaction().commit();
		session.close();
		return;
	}
	
//	private static String[] queryFileIdList(String recipientId){
	public static List queryFileIdList(String recipientId){
		createSession();
		String hql = "from relation as r where r.recipientId=:recipientId";
		Query query = session.createQuery(hql);
		query.setString("recipientId", recipientId);
		List <Relation>list = query.list();
//		Relation relation = null;
//		java.util.Iterator<Relation> iter = list.iterator();
//		while (iter.hasNext()) {
//			relation = iter.next();
//		}					
		session.getTransaction().commit();
		session.close();
		return list;
//		return file;	
	}
}

