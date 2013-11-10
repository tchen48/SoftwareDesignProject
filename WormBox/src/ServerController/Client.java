package ServerController;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static final String IP_ADDR = "localhost";//Server address
	public static final int PORT = 12345;//Server end port number
	
    public static void main(String[] args) {  
//        System.out.println("客户端启�?..");  
//        System.out.println("当接收到服务器端字符�?\"OK\" 的时�? 客户端将终止\n"); 
//        while (true) {  
//        	Socket socket = null;
//        	try {
//        		//创建�?��流套接字并将其连接到指定主机上的指定端口�?//	        	socket = new Socket(IP_ADDR, PORT);  
//	              
//	            //读取服务器端数据  
//	            DataInputStream input = new DataInputStream(socket.getInputStream());  
//	            //向服务器端发送数�? 
//	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
//	            System.out.print("请输�? \t");  
//	            String str = new BufferedReader(new InputStreamReader(System.in)).readLine();  
////	            String str = new BufferedReader(new FileReader("C:\\upload.txt")).readLine();  
//	            out.writeUTF(str);  
//	              
//	            String ret = input.readUTF();   
//	            System.out.println("服务器端返回过来的是: " + ret); 
//	            File f = new File("d:/test.txt");	            
//	            FileWriter fw = new FileWriter(f);
//	            fw.write(ret); 
//	            fw.flush();
//	            
//	            // 如接收到 "OK" 则断�?���? 
//	            if ("OK".equals(ret)) {  
//	                System.out.println("客户端将关闭连接");  
//	                Thread.sleep(500);  
//	                break;  
//	            }  
//	            
//	            out.close();
//	            input.close();
//        	} catch (Exception e) {
//        		System.out.println("客户端异�?" + e.getMessage()); 
//        	} finally {
//        		if (socket != null) {
//        			try {
//						socket.close();
//					} catch (IOException e) {
//						socket = null; 
//						System.out.println("客户�?finally 异常:" + e.getMessage()); 
//					}
//        		}
//        	}
//        }  
    	uploadFile("C:/upload.txt");
    }  
    
    private static boolean uploadFile(String path){
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
	            System.out.print("Uploading file... \t");  
	            String str = Command.UPLOAD;
	            out.writeUTF(str);  
	              
	            String ret = input.readUTF();   
	            System.out.println("\nReturned by server: " + ret); 
	            if (ret.equals(Command.UPLOAD)){
	            	socket = new Socket(IP_ADDR, PORT);  
		            input = new DataInputStream(socket.getInputStream());  
		            out = new DataOutputStream(socket.getOutputStream());  
	            	
	            	FileReader fr = new FileReader(path);
		            str = new BufferedReader(fr).readLine();
		            System.out.println(str);
		            out.writeUTF(str + "$$$$$" + path);  
		            ret = input.readUTF();   
	            }
	            else{
	            	return false;
	            }
	            
	            // If receive "upload successful" from server, disconnect
	            if (Command.UPLOAD_SUCCESSFUL.equals(ret)) {  
	                System.out.println("Client will be closed");  
	                Thread.sleep(500);  
	                break;  
	            }  
	            
	            out.close();
	            input.close();
        	} 
        	catch (Exception e) {
        		System.out.println("Client exception: " + e.getMessage()); 
        	} 
        	finally {
        		if (socket != null) {
        			try {
						socket.close();
						return true;
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
    
//    private static String downloadFile(String fileName){
//    	
//    }
    
}  