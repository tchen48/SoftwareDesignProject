package com.ProjMgmtSys.model.User;

public class UserNameId {
	private int userId;
	private String userName;
	public UserNameId(){}
	public UserNameId(int userId, String userName){
		this.userId = userId;
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
