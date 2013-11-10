package wormbox.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import wormbox.server.SessionFactoryUtil;

public class UploadedFileManager {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static long addFile(String fileName, String fileSize, String ownerId,
			            Date uploadedDate, String devicePath, String cloudIp){
		UploadedFile file = new UploadedFile();
		file.setFileName(fileName);
		file.setFileSize(fileSize);
		file.setOwnerId(ownerId);
		file.setUploadedDate(uploadedDate);
//		file.setShared(shared);
		file.setDevicePath(devicePath);
		file.setCloudIp(cloudIp);
		createSession();
		session.save(file);
		session.getTransaction().commit();
		session.close();
		return file.getFileId();
	}
	
	private static UploadedFile queryFile(String fileName, String ownerId){
		createSession();
		String hql = "from uploadedfile as u where u.fileName=:fileName and u.ownerId=:ownerId";
		Query query = session.createQuery(hql);
		query.setString("fileName", fileName);
		query.setString("ownerId", ownerId);
		List <UploadedFile>list = query.list();
		UploadedFile file = null;
		java.util.Iterator<UploadedFile> iter = list.iterator();
		while (iter.hasNext()) {
			file = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return file;	
	}
	
	private static UploadedFile queryFile(long fileId){
		createSession();
		String hql = "from uploadedfile as u where u.fileId=:fileId";
		Query query = session.createQuery(hql);
		query.setLong("fileId", fileId);
		List <UploadedFile>list = query.list();
		UploadedFile file = null;
		java.util.Iterator<UploadedFile> iter = list.iterator();
		while (iter.hasNext()) {
			file = iter.next();
		}					
		session.getTransaction().commit();
		session.close();
		return file;	
	}
	
	public static UploadedFile fetchFile(String fileName, String ownerId){
		return queryFile(fileName, ownerId);
	}
	
	public static ArrayList<FilenameOwnerId> fetchFileList(List<Relation> list){
		ArrayList<FilenameOwnerId> files = new ArrayList<FilenameOwnerId>();
		Relation relation = null;
		java.util.Iterator<Relation> iter = list.iterator();
		long fileId;
		UploadedFile file;
		while (iter.hasNext()) {
			relation = iter.next();
			fileId = relation.getFileId();
			file = queryFile(fileId);
			FilenameOwnerId item = new FilenameOwnerId();
			item.setFileName(file.getFileName());
			item.setOwnerId(file.getOwnerId());
			files.add(item);
		}
		return files;
	}
}
