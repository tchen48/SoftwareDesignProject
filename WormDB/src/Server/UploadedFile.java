package Server;

import java.util.Date;

public class UploadedFile {
	private long fileId;
	private String fileName;
	private double fileSize;
	private String ownerId;
	private Date uploadedDate;
	private boolean shared;
	private String devicePath;

	public UploadedFile() {
	}

	public UploadedFile(long fileId, String fileName, double fileSize,
			String ownerId, String devicePath, Date uploadedDate, boolean shared) {
		this.fileId = fileId;
		this.fileName = new String(fileName);
		this.fileSize = fileSize;
		this.ownerId = new String(ownerId);
		this.devicePath = new String(devicePath);
		this.shared = shared;
		this.uploadedDate = uploadedDate;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public void setFileName(String fileName) {
		this.fileName = new String(fileName);
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public long getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public double getFileSize() {
		return fileSize;
	}

	public String getDevicePath() {
		return devicePath;
	}

	public void setDevicePath(String devicePath) {
		this.devicePath = devicePath;
	}
	
	public boolean getShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
}
