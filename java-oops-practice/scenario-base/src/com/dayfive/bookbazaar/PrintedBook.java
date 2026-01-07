package com.dayfive.bookbazaar;

public class PrintedBook extends Book{
	
	private String pageCount;
	private double delivereyCharge;
	
	
	// Constructor
	public PrintedBook(String title, String author, double price, int stockCopies, String pageCount,
			double delivereyCharge) {
		super(title, author, price, stockCopies);
		this.pageCount = pageCount;
		this.delivereyCharge = delivereyCharge;
	}
	
	
	// Getter and Setters
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public double getDelivereyCharge() {
		return delivereyCharge;
	}
	public void setDelivereyCharge(double delivereyCharge) {
		this.delivereyCharge = delivereyCharge;
	}
	
	
	
	
}
