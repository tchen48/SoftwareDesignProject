package ServerController;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Date;

import wormbox.server.UploadedFileManager;

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
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String clientInputStr = input.readUTF();//Corresponds to the write method in client side, otherwise it will EOFException
                // Process data from client  
                System.out.println("Content sent by client:" + clientInputStr);  
//                String type = parse(clientInputStr);
                String[] parsedCommand = parse(clientInputStr);
                String type = parsedCommand[0];
                String s;
                if(type.equals(Command.DOWNLOAD)){
                	s = Command.DOWNLOAD;
                }
                else if(type.equals(Command.UPLOAD)){
//                	s = Command.UPLOAD;
//                }
//                else if(type.equals(Command.FILE)){
//                	s = new BufferedReader(new FileReader("C:\\testing.txt")).readLine();  
//                }
//                else{
//                	String data = getFileData(clientInputStr);
//                	String filename = getFileName(clientInputStr);
                	String data = parsedCommand[1];
                	String filename = getFileName(parsedCommand[2]);
                	File f = new File("d:/" + filename);	            
     	            FileWriter fw = new FileWriter(f);
     	            fw.write(data); 
     	            fw.flush();
     	            fw.close(); //Otherwise the file can't be opened properly
     	            
     	            long fileID = UploadedFileManager.addFile(filename, getFileSize(f), parsedCommand[3], new Date(),  
     	            		 parsedCommand[2], parsedCommand[4]);
     	            
                	s = Command.UPLOAD_SUCCESSFUL + "\nThe file ID is " + fileID;
                } 
                else{
                	s = "something";
                }
                
                // Respond to client
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
                System.out.println("Server: " + s);
//                System.out.print("请输�?\t");  
                // 发�?键盘输入的一�? 
//                String s = new BufferedReader(new FileReader("C:\\testing.txt")).readLine();  
                out.writeUTF(s);  
                out.close();  
                input.close();  
            } catch (Exception e) {  
                System.out.println("Server run exception: " + e.getMessage());  
            } finally {  
                if (socket != null) {  
                    try { 
//                    	System.out.println("socket close");
//                    	socket.
//                        socket.close();  
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
//        	if(dataArray[0].equals(Command.UPLOAD)){
//        		return Command.UPLOAD;
//        	}
//        	else if(command.equals(Command.DOWNLOAD)){
//        		return Command.DOWNLOAD;
//        	}
//        	else if(command.contains("/") || command.contains("\\")){
//        		return Command.PATH;
//        	}
//        	else{
//        		return Command.FILE;
//        	}        		
        }
        
        private String getFileData(String data){
        	String[] dataArray = data.split("\\${5}");
        	return dataArray[0];
        }
        
        private String getDevicePath(String data){
        	String[] dataArray = data.split("\\${5}");
        	return dataArray[1];
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
        
        private String getFileSize(File file){
    		DecimalFormat df = new DecimalFormat("#.00");
    		if(file.exists()){
    			double bytes = file.length();
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
    		}
    		else{
    			return "File not exist";
    		}		
    	}
        
        
    }  
}  