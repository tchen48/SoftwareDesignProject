package wormbox.server;

public class UserInfo {
	private String userId;
	private String deviceIp;
	private double deviceGPSLati;
	private double deviceGPSLongi;	
	private String password;
	
	public UserInfo(){}
	
	public UserInfo(String userId, String password){
		this.userId = new String(userId);
		this.password = new String(password);
		this.deviceIp = "";
		this.deviceGPSLati = 0;
		this.deviceGPSLongi = 0;
	}
	
	public UserInfo(String userId, String deviceIp, double deviceGPSLati, double deviceGPSLongi, String password){
		this.userId = new String(userId);
		this.password = new String(password);
		this.deviceIp = new String(deviceIp);
		this.deviceGPSLati = deviceGPSLati;
		this.deviceGPSLongi = deviceGPSLongi;
	}
	
	public void setUserId(String userId){
		this.userId = new String(userId);
	}
	
	public void setPassword(String password){
		this.password = new String(password);
	}
	
	public void setDeviceIp(String deviceIp){
		this.deviceIp = new String(deviceIp);
	}
	
	public void setDeviceGPSLati(double deviceGPSLati){
		this.deviceGPSLati = deviceGPSLati;
	}
	
	public void setDeviceGPSLongi(double devicGPSLongi){
		this.deviceGPSLongi = deviceGPSLongi;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getDeviceIp(){
		return deviceIp;
	}
	
	public double getDeviceGPSLati(){
		return deviceGPSLati;
	}
	
	public double getDeviceGPSLongi(){
		return deviceGPSLongi;
	}
}
