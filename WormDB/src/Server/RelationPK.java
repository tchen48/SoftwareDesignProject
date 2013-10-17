package Server;

import java.io.Serializable;

public class RelationPK implements Serializable{
	private static final long serialVersionUID = -288002855915204255L; 
	private String recipientId;
	private long fileId;

	public RelationPK(){}
	
	public RelationPK(String recipientId, long fileId){
		this.recipientId = recipientId;
		this.fileId = fileId;
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
