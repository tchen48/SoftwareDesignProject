package com.asubank.model.combinedcommand;

import com.asubank.model.user.*;
import com.asubank.model.visitor.*;


public class UserVisitor {
	private User user;
	private Visitor visitor;
	public UserVisitor(){}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Visitor getVisitor() {
		return visitor;
	}
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}	
}
