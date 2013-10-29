package com.asubank.departmentAndcorporate;

import java.util.Date;

public class log {
	private int id;     
    private String content;    
    private Date time;  

    public log() {}
         
    public log(int id, String content,Date time) 
    {
        this.id = id;
        this.content = content;
        this.time=time;
             
    }

    
 // Getter and Setter methods
	
	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}
	
	public Date gettime() {
		return time;
	}

	public void settime(Date time) {
		this.time = time;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	
}
