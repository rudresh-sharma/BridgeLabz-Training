package com.dayfive.bookbazaar;

public class Book {
	private String title;
	private String author;
	private double price;
	private int stockCopies;
	
	
	
	public Book(String title, String author, double price, int stockCopies) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.stockCopies = stockCopies;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getStockCopies() {
		return stockCopies;
	}



	public void setStockCopies(int stockCopies) {
		this.stockCopies = stockCopies;
	}
	
	public void decreaseStock() {
		this.stockCopies -= 1;
	}
	
	
	public void printBookDetails(Book b) {
		System.out.println("Title : " + b.getTitle());
		System.out.println("Price : " + b.getPrice());
		System.out.println("Author :" + b.getAuthor());	
		
		if(b instanceof Ebook) {
			Ebook ob = (Ebook) b;
			System.out.println("Filesize: " + ob.getFileSize());
			System.out.println("Format: " + ob.getFormat());
			System.out.println("LicenseKey: " + ob.getLicenseKey());
		}
		else {
			PrintedBook ob = (PrintedBook)b;
			System.out.println("Page Count: " + ob.getPageCount());
			System.out.println("Delivery charge: " + ob.getDelivereyCharge());
		}
		
		System.out.println();
	}
	
	
	
}
