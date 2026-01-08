package com.daysix.medistore;
import java.time.LocalDate;
import java.util.ArrayList;

public class Medicine implements ISellable {
	private String name;
	private double price;
	private LocalDate expiryDate;
	private int stock;
	
	
	
	
	public Medicine(String name, double price, LocalDate expiryDate, int stock) {
		super();
		this.name = name;
		this.price = price;
		this.expiryDate = expiryDate;
		this.stock = stock;
	}
	
	


	public Medicine() {
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public LocalDate getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public double sell(int quantity, Medicine m) {
			
		double priceAfterDiscount;		
			
		   if(stock<quantity) {
				return 0;
			}
			else {
				priceAfterDiscount = applyDiscount(m);
			}
			
			return priceAfterDiscount;
			
		
	}
	@Override
	public boolean checkExpiry() {
		
		LocalDate today = LocalDate.now();

	    if (expiryDate.isBefore(today)) {
	    	return true;
	    } else {
	    	return false;
	    } 
		
	}
	
	
	public double applyDiscount(Medicine m) {
		 double priceAfterDiscount;
		 double discount = 0;
		 if(m instanceof Tablet) {
			 discount = m.getPrice()*5/100; 
		 }
		 else if(m instanceof Syrup) {
			 discount = m.getPrice()*10/100; 
		 }
		 else if(m instanceof Injection) {
			 discount = m.getPrice()*30/100;
		}
		 
		 priceAfterDiscount = m.getPrice() - discount;
		 
		 return priceAfterDiscount;
	}
	
	
	
	
	
	
	
	
	
}
