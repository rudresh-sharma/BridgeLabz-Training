package com.constructors.levelone;

public class ProductMain {
	public static  void main(String[] args) {
		
		// Creating Products object
		Product p1 = new Product("Chocolate", 200);
		Product p2 = new Product("Chips", 100);
		Product p3 = new Product("Rice", 300);
		Product p4 = new Product("Maggie", 200);
		
		
		// Printing product details
		p1.displayProductDetails();
		p2.displayProductDetails();
		p3.displayProductDetails();
		p4.displayProductDetails();
		
		
		// Printing total Number of products created
		Product.displayTotalProducts();
		
	}
}
