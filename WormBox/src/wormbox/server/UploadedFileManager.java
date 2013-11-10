package wormbox.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;

import wormbox.server.SessionFactoryUtil;

public class UploadedFileManager {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
	private static Session session;
	
	private static void createSession(){
		session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
	}
	
	public static void addFile(long fileId, String fileName, String fileSize, String ownerId,
			            Date uploadedDate, boolean shared, String devicePath, String cloudIp){
		System.out.println("here-1");
		UploadedFile file = new UploadedFile();
		System.out.println("here0");
//		file.setFileId(fileId);
		file.setFileName(fileName);
		file.setFileSize(fileSize);
		file.setOwnerId(ownerId);
		file.setUploadedDate(uploadedDate);
		file.setShared(shared);
		file.setDevicePath(devicePath);
		file.setCloudIp(cloudIp);
		System.out.println("here1");
		createSession();
		session.save(file);
		System.out.println("here2");
		session.getTransaction().commit();
		session.close();
		return;
	}
	
	
}
