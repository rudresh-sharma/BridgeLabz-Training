package com.daythree.swiftcart;

public class Product {
	private String name;
	private double price;
	private String category;
	private int quantity;
	private int productType;
	
	// Constructor 
 
	
	
	
	// Getter and Setter
	public String getName() {
		return name;
	}
	public Product(String name, double price, String category, int quantity, int productType) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
		this.productType = productType;
	}
	
	
	
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	// Method to calculate unit total price
	
	
	
}
