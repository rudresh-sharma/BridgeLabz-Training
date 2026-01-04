package com.inbuiltfunction;

public class ProductMain {
	  public static void main(String[] args) {
	       // Update the discount for all products
	       Product.updateDiscount(10.0); // 10% discount
	       // Create new product instances
	       Product product1 = new Product("P001", "Laptop", 1200.00, 5);
	       Product product2 = new Product("P002", "Smartphone", 800.00, 10);
	       // Display product details
	       product1.displayProductDetails();
	       System.out.println();
	       product2.displayProductDetails();
	   }

}
