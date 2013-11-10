package wormbox.server;

import java.io.Serializable;

public class Relation implements Serializable{
	private static final long serialVersionUID = -288002855915204255L; 
	private String recipientId;
	private long fileId;
	private long relationId;

	public Relation(){}
	
	public Relation(String recipientId, long fileId, long relationId){
		this.recipientId = recipientId;
		this.fileId = fileId;
		this.relationId = relationId;
	}	
		
	public long getRelationId() {
		return relationId;
	}

	public void setRelationId(long relationId) {
		this.relationId = relationId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
}
