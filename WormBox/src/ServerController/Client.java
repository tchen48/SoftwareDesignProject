package ServerController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	public static final String IP_ADDR = "localhost";//Server address
	public static final int PORT = 12345;//Server end port number
	
    public static void main(String[] args) {  
//    	uploadFile("C:/profile.jpg", "sshao", "1.1.1.1");
    	uploadFile("C:/A3_ShihuanShao.zip", "sshao1", "2.2.2.2");
//    	uploadFile("D:/Program Files/python.msi", "sshao1", "3.3.3.3");
//    	uploadFile("c:/FoodItemData.xml", "sshao1", "3.3.3.3");
//    	downloadFile("python.msi", "sshao1");
    }  
    
    private static boolean uploadFile(String path, String ownerId, String cloudIp){
    	boolean tag = true;
    	System.out.println("Starting Client...");  
        System.out.println("When receiving \"upload successful\" from server, client will be terminated\n"); 
        while (true) {  
        	Socket socket = null;
        	try {
        		//Create a socket and connect to the specific port number in the address
	        	socket = new Socket(IP_ADDR, PORT);  
	              
	            //Read data from the server
	            DataInputStream input = new DataInputStream(socket.getInputStream());  
	            //Send data to server
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	            System.out.println("Uploading file... \t");  
	            
	            DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
	            File file = new File(path);
	            long length = file.length();
	            out.writeUTF(Command.UPLOAD + Command.DELIMITER + path + Command.DELIMITER + ownerId + Command.DELIMITER + cloudIp);
	            out.flush();
	            if(input.readUTF().equals(Command.FILE_EXIST)){
	            	tag = false;
	            	System.out.println(Command.UPLOAD_FAILED);
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                out.close();
		            input.close();
	                break;  
	            }
	            else{
		            out.writeLong((long)file.length());
		            out.flush();
		            int bufferSize = 8192;
		            byte[] buf = new byte[bufferSize];
		            long count = 0;
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
		            String ret = input.readUTF();
	
	//	            out.flush();
		            fis.close();
		            // If receive "upload successful" from server, disconnect
		            if (Command.UPLOAD_SUCCESSFUL.equals(ret)) {  
		            	tag = true;
		            	System.out.println(Command.UPLOAD_SUCCESSFUL);
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
    
    private static boolean downloadFile(String fileName, String ownerId){
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
	            
	            int bufferSize = 8192;
            	byte[] buf = new byte[bufferSize];
            	int passedlen = 0;
            	long len = 0;
            	len = input.readLong();
            	String savePath = "f:/" + fileName;
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
//                System.out.println("Downloading completed!");
                fileOut.close();
	            
	            String ret = input.readUTF();

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
}  