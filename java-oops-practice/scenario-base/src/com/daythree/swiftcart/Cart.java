package com.daythree.swiftcart;

import java.util.ArrayList;
public class Cart  implements ICheckout, PerishableProduct, NonPerishableProduct{
	
	 ArrayList<Product> products = new ArrayList<>();
	 private double totalPrice;
	 static int user;
	
	
	
	
	
	public Cart() {
		user++;
	}






	// Method to calculate total Price
	public  void calculateTotalPrice(Cart c1) {
		
		for(Product p : c1.products) {
			c1.totalPrice += p.getPrice() * p.getQuantity();
		}
		
		
	}






	@Override
	 public void generateBill(Cart c1) {
		 c1.totalPrice = 0;             // reset
		 c1.calculateTotalPrice(c1); 
		System.out.println("Swift Card Invoice");;
		int i=0;
		
		for(Product p : products) {
			System.out.println((i+1) + ".  Name: " + p.getName() + " || Unit Price: " + p.getPrice() + " || Quantity: " + p.getQuantity() + " || " + "UnitTotalPrice :"  + (p.getPrice() * p.getQuantity()) );                       
		}
		
		double discount = c1.applyDiscount(c1);
		double finalPrice = c1.totalPrice - discount;

		System.out.println("\nTotal Price: " + finalPrice);

	}






	@Override
	public double applyDiscount(Cart c1) {
	    double discount = 0;

	    for(Product p : c1.products) {
	        if(p.getProductType() == 1) { // perishable
	            discount += p.getQuantity() * c1.giveDiscountOnP(p.getPrice());
	        }
	        else {
	            discount += p.getQuantity() * c1.giveDiscountOnNPP(p.getPrice());
	        }
	    }
	    return discount;
	}






	@Override
	public  double giveDiscountOnNPP(double price) {
		double disPrice = price*5/100;
		return disPrice;
	}






	@Override
	public double giveDiscountOnP(double price) {
		double disPrice = price*5/100;
		return disPrice;		
	}





}
