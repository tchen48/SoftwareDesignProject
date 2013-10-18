package com.asubank.model.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class App{
public static String getWindowsMACAddress() { 
    String address = ""; 
    try { 
     ProcessBuilder pb = new ProcessBuilder("ipconfig", "/all"); 
     Process p = pb.start(); 
     BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())); 
     String line; 
     while ((line = br.readLine()) != null) { 
        if (line.indexOf("Physical Address") != -1) { 
         int index = line.indexOf(":"); 
         address = line.substring(index + 1); 
         break; 
        } 
     } 
     br.close(); 
     return address.trim(); 
    } catch (IOException e) { 
    } 
    return address; 
} 
public static String getLinuxMACAddress() { 
    String address = ""; 
    try { 
     ProcessBuilder pb = new ProcessBuilder("ifconfig", "-a"); 
     Process p = pb.start(); 
     BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())); 
     String line; 
     while ((line = br.readLine()) != null) { 
        System.out.println(line); 
        if (line.indexOf("Link encap:Ethernet    HWaddr") != -1) { 
         int index = line.indexOf("HWaddr"); 
         address = line.substring(index + 7); 
         break; 
        } 
     } 
     br.close(); 
     return address.trim(); 
    } catch (IOException e) { 
    } 
    return address; 
} 
public static String getMACAddress() { 
    String address = ""; 
    String os = System.getProperty("os.name"); 
    // System.out.println(os); 
    if (os != null && os.startsWith("Windows")) { 
     address = getWindowsMACAddress(); 
     address = address.replaceAll("-", ":"); 
    } else { 
     address = getLinuxMACAddress(); 
    } 
    return address; 
} 
public static void main(String[] args) { 
    System.out.println("取得网卡的 Mac 地址" + getMACAddress()); 
}
}