package ClientVM;

import java.util.Date;

public class FileList {
	private long fileId;
	private String fileName;
	private double fileSize;
	private boolean shared;
	private Date uploadDate;

	public FileList() {
	}

	public FileList(long fileId, String fileName, double fileSize,
			boolean shared, Date uploadDate) {
		this.fileId = fileId;
		this.fileName = new String(fileName);
		this.fileSize = fileSize;
		this.shared = shared;
		this.uploadDate = uploadDate;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
}
