package com.dayseven.flashdealz;

public class Product {
	
	private String name;
	private double discount;
	public Product(String name, double discount) {
	
		this.name = name;
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public double getDiscount() {
		return discount;
	}
	
	@Override
	public String toString() {
		return name + " --> " + discount + "%" ;
	}
	
	
	

}