package com.dayfive.bookbazaar;

import java.util.ArrayList;
import java.util.List;
public class Order implements IDiscountable {
	private String userName;
	private List<Book> books;
	
	// Constructors
	public Order(String userName) {
		super();
		this.userName = userName;
		this.books = new ArrayList<>();
	}
	
	
	
	// Getter and Setters
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
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
	}
	
	
	public void PrintBill() {
		System.out.println("Book Bazaar Invoice");
		double totalBill = 0;
		for(int i=0; i<books.size(); i++) {
			totalBill += applyDiscount(books.get(i));
		}
		
		System.out.println("Total bill with discount = " + totalBill);
		System.out.println("Thankyou for shop1ping " + this.userName);
		
	}
	
	
	 public double applyDiscount(Book b) {
		 double discount = 0;
		 
		 if(b instanceof Ebook) {
			 discount = b.getPrice()*5/100;
			 return b.getPrice()-discount;
		 }
		 else {
			 PrintedBook ob = (PrintedBook)b;

			 discount = b.getPrice()*10/100;
			 return b.getPrice()+ ob.getDelivereyCharge() - discount;
		 }
		 
		 
	 }
}
