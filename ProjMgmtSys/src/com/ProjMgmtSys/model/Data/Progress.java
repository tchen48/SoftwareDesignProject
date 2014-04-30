package com.ProjMgmtSys.model.Data;

public class Progress {
	private int progId;
	private int projId;
	private String userName;
	private String endDate;
	private String startDate;
	private String progress;
	public Progress(){}
	
	public int getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public int getProjId() {
		return projId;
	}
	public void setProjId(int projId) {
		this.projId = projId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
}

