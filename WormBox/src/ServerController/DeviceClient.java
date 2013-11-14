package ServerController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DeviceClient {
	public static final String IP_ADDR = "localhost";//Server address
	public static final int PORT = 12345;//Server end port number
	public static final int CLIENT_SERVER_PORT = 12346; // port number for client server and device 
	public static final int BUFFERSIZE = 8192;
	
    public static void main(String[] args) {  
    	uploadFile("C:/profile.jpg", "shihuan", "qingyun", "1.1.1.1");
//    	uploadFile("C:/A3_ShihuanShao.zip", "sshao1", "2.2.2.2");
//    	uploadFile("D:/Program Files/python.msi", "sshao1", "3.3.3.3");
//    	uploadFile("c:/FoodItemData.xml", "sshao1", "3.3.3.3");
//    	downloadFile("python.msi", "sshao1");
//    	downloadFile("profile.jpg", "sshao", 31.192609, 121.431577);
    }  
    
    private static boolean uploadFile(String path, String ownerId, String recipientId, String cloudIp){
    	boolean tag = true;
    	
    	System.out.println("Starting Client...");  
        System.out.println("When receiving \"upload successful\" from server, client will be terminated\n"); 
        while (true) {  
        	Socket socket = null;
        	try {
        		//Create a socket and connect to the specific port number in the address
	        	socket = new Socket(IP_ADDR, CLIENT_SERVER_PORT);  //IP_ADDR = selfVMIp
	              
	            //Read data from the server
	            DataInputStream input = new DataInputStream(socket.getInputStream());  
	            //Send data to server
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	            System.out.println("Uploading file to user VM... \t");  
	            
	            DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
	            File file = new File(path);
	            long length = file.length();
	            //Upload file to user VM
	            out.writeUTF(Command.UPLOAD + Command.DELIMITER + file.getName());
	            out.flush();
	            out.writeLong(length);
	            out.flush();
	            
	            boolean sendSuccess = sendFile(length, fis, out);
	            if(sendSuccess){
	            	String ret = input.readUTF();
		            fis.close();
		            //After upload file to vm, update the DB in server
		            if (Command.UPLOAD_SUCCESSFUL.equals(ret)) {  
		            	System.out.println(Command.UPLOAD_SUCCESSFUL);
		                System.out.println("Client will be closed");  
		                Thread.sleep(500);  
		                out.close();
			            input.close();
			            socket.close();
			            //Update DB in server
		            	socket = new Socket(IP_ADDR, PORT);//IP_ADDR = serverIp
		            	input = new DataInputStream(socket.getInputStream());  
		            	out = new DataOutputStream(socket.getOutputStream());  
		            	System.out.println("Updating DB... \t");
		            	out.writeUTF(Command.UPLOAD + Command.DELIMITER + file.getName() + Command.DELIMITER + ownerId + Command.DELIMITER + recipientId);
			            out.flush();
			            out.writeLong(length);
			            out.flush();
			            ret = input.readUTF();
			            if(Command.DB_UPDATE_SUCCESSFUL.equals(ret)){
			            	System.out.println(Command.DB_UPDATE_SUCCESSFUL);
			            }
			            else{
			            	tag = false;
			            	System.out.println(Command.DB_UPDATE_FAILED);			                
			            }
			            System.out.println("Client will be closed");  
		                Thread.sleep(500);  
		                out.close();
			            input.close();
		                break;  
		            }
		            else{
		            	tag = false;
		            	System.out.println(Command.UPLOAD_FAILED);
		                System.out.println("Client will be closed");  
		                Thread.sleep(500);  
		                out.close();
			            input.close();
		                break;  
		            }
	            }
	            else{
	            	tag = false;
	            	System.out.println(Command.UPLOAD_FAILED);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
		            fis.close();
	                break; 
	            }
        	} 
        	catch (Exception e) {
        		System.out.println("Client exception: " + e.getMessage()); 
        	} 
        	finally {
        		if (socket != null) {
        			try {
						socket.close();
						return tag;
					} 
        			catch (IOException e) {
						socket = null; 
						System.out.println("Client finally exception: " + e.getMessage()); 
						return false;
					}
        		}
        	}
        }
		return false;  
    }
    
    private static boolean downloadFile(String fileName, String ownerId, double selfLati, double selfLongi){
    	boolean tag = true;
    	System.out.println("Starting Client...");  
        System.out.println("When receiving \"download successful\" from server, client will be terminated\n"); 
        while (true) {  
        	Socket socket = null;
        	try {
        		//Create a socket and connect to the specific port number in the address
	        	socket = new Socket(IP_ADDR, PORT);  
	              
	            //Read data from the server
	        	DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	            //Send data to server
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	            System.out.println("Downloading file... \t");  
	            
	            out.writeUTF(Command.DOWNLOAD + Command.DELIMITER + fileName + Command.DELIMITER + ownerId);
	            out.flush();
	            String ret = input.readUTF();
	            if(ret.equals(Command.FILE_NOT_EXIST)){
	            	tag = false;
	            	System.out.println(Command.FILE_NOT_EXIST);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }
	            else{
	            	String sourceInfo = ret;
	            	String ip = selectSource(selfLati, selfLongi, sourceInfo);
	            	System.out.println(ip);
	            	//*****close the connection to server, open a connection to client server
	            	out.close();
	            	input.close();
	            	socket.close();
	            	socket = new Socket(IP_ADDR, CLIENT_SERVER_PORT);//IP_ADDR = ip
	            	input = new DataInputStream(socket.getInputStream());  
	            	out = new DataOutputStream(socket.getOutputStream());  
	            	//*****
	            	out.writeUTF(Command.DOWNLOAD + Command.DELIMITER + fileName);
		            out.flush();
	            
//		            int bufferSize = 8192;
//	            	byte[] buf = new byte[bufferSize];
//	            	int passedlen = 0;
//	            	long len = 0;
//	            	len = input.readLong();
//	            	String savePath = "f:/" + fileName; //Path needs to be changed
//	            	DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(savePath)));
//	            	System.out.println("Receive file length " + len);
//	                System.out.println("Start to receive file!" + "\n");
//	                long count = 0;
//	                while(count < len){
//	                	int read = 0;
//	                	if(input != null){
//	                		read = input.read(buf);
//	                	}
//	                	passedlen += read;
//	                	if(read == -1){
//	                		break;
//	                	}
//	                	if(passedlen * 100 / len > ((passedlen - read) * 100 / len))
//	                		System.out.println("File received " +  (passedlen * 100/ len) + "%");
//	                	fileOut.write(buf, 0, read);
//	                	count += (long)read;
//	                }
//	                fileOut.flush();
//	//                System.out.println("Downloading completed!");
//	                fileOut.close();
		            
		            ret = input.readUTF();
	
		            // If receive "upload successful" from server, disconnect
		            if (Command.DOWNLOAD_SUCCESSFUL.equals(ret)) {  
		            	System.out.println(Command.DOWNLOAD_SUCCESSFUL);
		                System.out.println("Client will be closed");  
		                Thread.sleep(500);  
		                out.close();
			            input.close();
		                break;  
		            }  
		            else{
		            	tag = false;
		            	System.out.println(Command.DOWNLOAD_FAILED);
		            	System.out.println("Client will be closed");  
		                Thread.sleep(500);
		            	out.close();
			            input.close();
			            break;
		            }
        		}
        	} 
        	catch (Exception e) {
        		System.out.println("Client exception: " + e.getMessage()); 
        	} 
        	finally {
        		if (socket != null) {
        			try {
						socket.close();
						return tag;
					} 
        			catch (IOException e) {
						socket = null; 
						System.out.println("Client finally exception: " + e.getMessage()); 
						return false;
					}
        		}
        	}
        }
		return false;  
    }
    
    private static boolean sendFile(long length, DataInputStream fis, DataOutputStream out) throws IOException{
    	int bufferSize = BUFFERSIZE;
        byte[] buf = new byte[bufferSize];
        long count = 0;
        while(count < length){
        	int read = 0;
        	if(fis != null){
        		read = fis.read(buf);
        	}
//        	if(read == -1){
//        		break;
//        	}
        	out.write(buf, 0 , read);
        	count += (long)read;
        }
        System.out.println("Send file length: " + count);
        return true;
    }
    
    private static boolean receiveFile(String fileName, DataInputStream input) throws IOException{
        int bufferSize = 8192;
    	byte[] buf = new byte[bufferSize];
    	int passedlen = 0;
    	long len = 0;
    	len = input.readLong();
    	String savePath = "f:/" + fileName; //Path needs to be changed
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
        fileOut.close();
        return true;
    }
    
    private static boolean login(String userId, String password){
    	boolean tag = true;
    	System.out.println("Starting Client...");  
        System.out.println("When receiving \"login successful\" from server, client will be terminated\n"); 
        while (true) {  
        	Socket socket = null;
        	try {
        		//Create a socket and connect to the specific port number in the address
	        	socket = new Socket(IP_ADDR, PORT);  
	              
	            //Read data from the server
	            DataInputStream input = new DataInputStream(socket.getInputStream());  
	            //Send data to server
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	            System.out.println("Validating credential... \t");  
	            
	            out.writeUTF(Command.LOGIN + Command.DELIMITER + userId + Command.DELIMITER + password);
	            out.flush();
	           
	            String ret = input.readUTF();

	            // If receive "login successful" from server, disconnect
	            if (Command.LOGIN_SUCCESSFUL.equals(ret)) {  
	            	System.out.println(Command.LOGIN_SUCCESSFUL);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }
	            else{
	            	tag = false;
	            	System.out.println(Command.LOGIN_FAIL);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }
	            
//	            out.close();
//	            input.close();
        	} 
        	catch (Exception e) {
        		System.out.println("Client exception: " + e.getMessage()); 
        	} 
        	finally {
        		if (socket != null) {
        			try {
						socket.close();
						return tag;
					} 
        			catch (IOException e) {
						socket = null; 
						System.out.println("Client finally exception: " + e.getMessage()); 
						return false;
					}
        		}
        	}
        }
		return false;  
    }
    
    private static boolean updateInfo(String userId, String deviceGPSLati, String deviceGPSLongi, String deviceIp){
    	boolean tag = true;
    	System.out.println("Starting Client...");  
        System.out.println("When receiving \"update userinfo successful\" from server, client will be terminated\n"); 
        while (true) {  
        	Socket socket = null;
        	try {
        		//Create a socket and connect to the specific port number in the address
	        	socket = new Socket(IP_ADDR, PORT);  
	              
	            //Read data from the server
	            DataInputStream input = new DataInputStream(socket.getInputStream());  
	            //Send data to server
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	            System.out.println("Updating userinfo... \t");  
	            
	            out.writeUTF(Command.UPDATE_USERINFO + Command.DELIMITER + userId + Command.DELIMITER + deviceGPSLati + Command.DELIMITER + deviceGPSLongi + Command.DELIMITER + deviceIp);
	            out.flush();
	           
	            String ret = input.readUTF();

	            // If receive "login successful" from server, disconnect
	            if (Command.UPDATE_USERINFO_SUCCESSFUL.equals(ret)) {  
	            	System.out.println(Command.UPDATE_USERINFO_SUCCESSFUL);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }  
	            else{
	            	tag = false;
	            	System.out.println(Command.UPDATE_USERINFO_FAIL);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }
//	            out.close();
//	            input.close();
        	} 
        	catch (Exception e) {
        		System.out.println("Client exception: " + e.getMessage()); 
        	} 
        	finally {
        		if (socket != null) {
        			try {
						socket.close();
						return tag;
					} 
        			catch (IOException e) {
						socket = null; 
						System.out.println("Client finally exception: " + e.getMessage()); 
						return false;
					}
        		}
        	}
        }
		return false;  
    }
    
    //Fetch owner device location, device ip and cloud ip
 /*   private static String getSourceInfo(String ownerId){
    	String ret = null;
    	System.out.println("Starting Client...");  
        System.out.println("When receiving \"get source info successful\" from server, client will be terminated\n"); 
        while (true) {  
        	Socket socket = null;
        	try {
        		//Create a socket and connect to the specific port number in the address
	        	socket = new Socket(IP_ADDR, PORT);  
	              
	            //Read data from the server
	            DataInputStream input = new DataInputStream(socket.getInputStream());  
	            //Send data to server
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	            System.out.println("Get file owner source info... \t");  
	            
	            out.writeUTF(Command.GET_SOURCE_INFO + Command.DELIMITER + ownerId);
	            out.flush();
	           
	            ret = input.readUTF();
	             
	            // If receive "get source info failed" from server, disconnect
	            if (Command.GET_SOURCE_INFO_FAILED.equals(ret)) {  
	            	System.out.println(Command.GET_SOURCE_INFO_FAILED);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }  
	            else{
	            	System.out.println(Command.GET_SOURCE_INFO_SUCCESSFUL);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }
        	} 
        	catch (Exception e) {
        		System.out.println("Client exception: " + e.getMessage()); 
        	} 
        	finally {
        		if (socket != null) {
        			try {
						socket.close();
						return ret;
					} 
        			catch (IOException e) {
						socket = null; 
						System.out.println("Client finally exception: " + e.getMessage()); 
						return ret;
					}
        		}
        	}
        }
		return ret;
    }
*/    
    private static String selectSource(double selfLati, double selfLongi, String sourceInfo){
    	System.out.println("sourceInfo: " + sourceInfo);
    	String[] parsedSourceInfo = parse(sourceInfo);
    	double sourceLati = Double.valueOf(parsedSourceInfo[0]);
    	double sourceLongi = Double.valueOf(parsedSourceInfo[1]);
    	String sourceCloudIp = parsedSourceInfo[2];
    	String sourceDeviceIp = parsedSourceInfo[3];
    	double distance = CalculateDistance.D_jw(selfLati, selfLongi, sourceLati, sourceLongi);
    	System.out.println("distance: " + distance);
    	if(distance > Command.CRITICAL_DISTANCE){
    		return sourceCloudIp;
    	}
    	else{
    		return sourceDeviceIp;
    	}
    }
    
    private static String[] parse(String command){
    	String[] dataArray = command.split("\\${5}"); 
    	return dataArray;
    }
}  