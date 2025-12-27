package com.constructors.levelone;

public class Product {
	
	// Definite instance variable
	private String productName;
	private float price;
	static int totalProducts=0;
	
	
	// Constructor configuration
	public Product() {
		totalProducts++;
	}
	public Product(String productName, float price) {
		this();
		this.productName= productName;
		this.price = price;
	}
	
	
	// Method for Display product details
	public void displayProductDetails() {
		System.out.println("Product Details");
		System.out.println("------------------");
		System.out.println("Name :" + productName);
		System.out.println("Price : " + price);
		
	}

	
	// Method for printing total product created
	public static void displayTotalProducts() {
		System.out.println("Total Number of Products = " +totalProducts);

	}
}
