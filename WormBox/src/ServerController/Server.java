package ServerController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Date;

import wormbox.server.UploadedFile;
import wormbox.server.UploadedFileManager;
import wormbox.server.UserInfo;
import wormbox.server.UserInfoManager;

public class Server {
	public static final int PORT = 12345;//Listening port number   
	
    public static void main(String[] args) {  
        System.out.println("Starting server...\n");  
        Server server = new Server();  
        server.init();  
    }  
  
    public void init() {  
        try {  
            ServerSocket serverSocket = new ServerSocket(PORT);  
            while (true) {  
                //Client and server are connected.
                Socket client = serverSocket.accept();  
                // Process this connection
                new HandlerThread(client);  
            }  
        } catch (Exception e) {  
            System.out.println("Server exception: " + e.getMessage());  
        }  
    }  
  
    private class HandlerThread implements Runnable {  
        private Socket socket;  
        public HandlerThread(Socket client) {  
            socket = client;  
            new Thread(this).start();  
        }  
  
        public void run() {  
            try {     
                // Read data from client
            	DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            	DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            	String clientInputStr = input.readUTF();//Corresponds to the write method in client side, otherwise it will EOFException
                // Process data from client  
                System.out.println("Content sent by client:" + clientInputStr);  
                String[] parsedCommand = parse(clientInputStr);
                String type = parsedCommand[0];
                String s=null;
                System.out.println(type);
                if(type.equals(Command.LOGIN)){
                	boolean b = UserInfoManager.validateUser(parsedCommand[1], parsedCommand[2]);
                	if(b==true){
                		out.writeUTF(Command.LOGIN_SUCCESSFUL);
                		out.flush();
                		s = Command.LOGIN_SUCCESSFUL;
                	}
                	else {
                		out.writeUTF(Command.LOGIN_FAIL);
                		out.flush();
                		s = Command.LOGIN_FAIL;
                	} 
                }                
                else if(type.equals(Command.UPDATE_USERINFO)){
                	boolean b = UserInfoManager.updateUserInfo(parsedCommand[1], parsedCommand[4], Double.valueOf(parsedCommand[2]), Double.valueOf(parsedCommand[3]));
                	if(b){
                		out.writeUTF(Command.UPDATE_USERINFO_SUCCESSFUL);
                		out.flush();
                		s = Command.UPDATE_USERINFO_SUCCESSFUL;
                	}
                	else{
                		out.writeUTF(Command.UPDATE_USERINFO_FAIL);
                		out.flush();
                		s = Command.UPDATE_USERINFO_FAIL;
                	}                
                }
//                else if(type.equals(Command.GET_SOURCE_INFO)){
//                	UserInfo user = UserInfoManager.queryUser(parsedCommand[1]);
//                	if(user != null){
//	                	double lati = user.getDeviceGPSLati();
//	                	double longi = user.getDeviceGPSLongi();
//	                	String cloudIp = user.getCloudIp();
//	                	String deviceIp = user.getDeviceIp();
//	                	out.writeUTF("" + lati + Command.DELIMITER + longi + Command.DELIMITER + cloudIp + Command.DELIMITER + deviceIp);
//	    	            out.flush();
//	    	            s = Command.GET_SOURCE_INFO_SUCCESSFUL;
//                	}
//                	else{
//                		out.writeUTF(Command.GET_SOURCE_INFO_FAILED);
//                		out.flush();
//                		s = Command.GET_SOURCE_INFO_FAILED;
//                	}
//                }
                else if(type.equals(Command.DOWNLOAD)){
                	String fileName = parsedCommand[1];
                	String ownerId = parsedCommand[2];
                	UploadedFile uploadedFile = UploadedFileManager.fetchFile(fileName, ownerId);
                	if(uploadedFile != null){
                	/*
                		String path = "D:/" + fileName;
                		File file = new File(path); 
                		long length = file.length();
                		out.writeLong(length);
                		out.flush();
                		int bufferSize = 8192;
                		byte[] buf = new byte[bufferSize];
        	            long count = 0;
        	            DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        	            while(count < length){
        	            	int read = 0;
        	            	if(fis != null){
        	            		read = fis.read(buf);
        	            	}
        	            	if(read == -1){
        	            		break;
        	            	}
        	            	out.write(buf, 0 , read);
        	            	count += (long)read;
        	            }
        	            System.out.println("Send file length: " + count);
        	            fis.close();
        	            s = Command.DOWNLOAD_SUCCESSFUL;*/
                		UserInfo user = UserInfoManager.queryUser(ownerId);
	                	double lati = user.getDeviceGPSLati();
	                	double longi = user.getDeviceGPSLongi();
	                	String cloudIp = user.getCloudIp();
	                	String deviceIp = user.getDeviceIp();
//	                	out.writeUTF("" + lati + Command.DELIMITER + longi + Command.DELIMITER + cloudIp + Command.DELIMITER + deviceIp);
                		s = "" + lati + Command.DELIMITER + longi + Command.DELIMITER + cloudIp + Command.DELIMITER + deviceIp;
                	}
                	else{
                		out.writeLong(0);
                		out.flush();
                		s = Command.FILE_NOT_EXIST;
                	}
                }
                else if(type.equals(Command.UPLOAD)){
                	String fileName = getFileName(parsedCommand[1]);                	
                	if(UploadedFileManager.fetchFile(fileName, parsedCommand[2]) != null){
                		s = Command.FILE_EXIST;
                	}    
                	else{
                		/* This code should be in client server
                		int bufferSize = 8192;
                    	byte[] buf = new byte[bufferSize];
                    	int passedlen = 0;
                    	long len = 0;
                    	len = input.readLong();
	                	String savePath = "d:/" + fileName;
	                	DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(savePath)));
	                	System.out.println("Receive file length " + len);
	                    System.out.println("Start to receive file!" + "\n");
	                    long count = 0;
	                    while(count < len){
	                    	int read = 0;
	                    	if(input != null){
	                    		read = input.read(buf);
	                    	}
	                    	passedlen += read;
	                    	if(read == -1){
	                    		break;
	                    	}
	                    	if(passedlen * 100 / len > ((passedlen - read) * 100 / len))
	                    		System.out.println("File received " +  (passedlen * 100/ len) + "%");
	                    	fileOut.write(buf, 0, read);
	                    	count += (long)read;
	                    }
	                	fileOut.flush();
	                    System.out.println("Uploading completed!");
	                    fileOut.close();*/
                		long len = 0;
                		len = input.readLong();
//	     	            long fileID = UploadedFileManager.addFile(fileName, getFileSize(new File(savePath)), parsedCommand[2], new Date(), 
                		long fileID = UploadedFileManager.addFile(fileName, getFileSize(len), parsedCommand[2], new Date(), 
	     	            		 parsedCommand[1], parsedCommand[3]);	     	            
	                	s = Command.UPLOAD_ALLOWED + "\nThe file ID is " + fileID;
                	}
                } 
                else{
                	s = "something";
                }
                
                // Respond to client
                System.out.println("Server: " + s);
                out.writeUTF(s);  
                out.close();  
                input.close();  
            } catch (Exception e) {  
                System.out.println("Server run exception: " + e.getMessage());  
            } finally {  
                if (socket != null) {  
                    try { 
                    } 
                    catch (Exception e) {  
                        socket = null;  
                        System.out.println("Server finally exception: " + e.getMessage());  
                    }  
                }  
            } 
        } 
        
        private String[] parse(String command){
        	String[] dataArray = command.split("\\${5}"); 
        	return dataArray;
        }
        
        private String getFileName(String data){
        	int length = data.length();
        	for(int i = length - 1; i > -1; i--){        		
        		if(data.charAt(i) == '/' || data.charAt(i) == '\\'){
        			return data.substring(i + 1, length);
        		}
        	}
        	return null;
        }
        
//        private String getFileSize(File file){
        private String getFileSize(long length){
    		DecimalFormat df = new DecimalFormat("#.00");
//    		if(file.exists()){
//			double bytes = file.length();
    		double bytes = length;
			double kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);
			double gigabytes = (megabytes / 1024);
			if(gigabytes >= 1){
				return (df.format(gigabytes) + " " + "GB");
			}
			else if(megabytes >= 1){
				return (df.format(megabytes) + " " + "MB");
			}
			else if(kilobytes >= 1){
				return (df.format(kilobytes) + " " + "KB");
			}
			else{
				return (df.format(bytes) + " " + "Bytes");
			}
//    		}
//    		else{
//    			return "File not exist";
//    		}		
    	}
        
        
    }  
}  