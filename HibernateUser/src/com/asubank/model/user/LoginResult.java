package com.asubank.model.user;

public class LoginResult {
	private User user;
	private int statusCode;
	
	public void setUser(User user){
		this.user = user;
	}
	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}
	public User getUser(){
		return user;
	}
	public int getStatusCode(){
		return statusCode;
	}
}
