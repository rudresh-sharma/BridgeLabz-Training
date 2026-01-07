package com.dayfive.bookbazaar;

public class Ebook extends Book {
	
	private String fileSize;
	private String format;
	private String licenseKey;
	
	
	
	public Ebook(String title, String author, double price, int stockCopies, String fileSize, String format,
			String licenseKey) {
		super(title, author, price, stockCopies);
		this.fileSize = fileSize;
		this.format = format;
		this.licenseKey = licenseKey;
	}
	
	
	
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	
	
	

}
