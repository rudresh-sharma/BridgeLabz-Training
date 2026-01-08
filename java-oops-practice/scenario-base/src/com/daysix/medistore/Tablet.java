package com.daysix.medistore;

import java.time.LocalDate;

public class Tablet extends Medicine{
	private int tabletsPerStrip;
	private String strength;   // e.g., 500mg, 250mg
	
	
	
	

	public Tablet(String name, double price, LocalDate expiryDate,int stock, int tabletPerStrip, String strength) {
		super(name, price, expiryDate, stock );
		this.tabletsPerStrip = tabletPerStrip;
		this.strength = strength;
	}
	
	
	
	
	public void showDetails() {
		System.out.println("\nMedicine Detail:");
		System.out.println("Name: " + this.getName());
		System.out.println("Price: " + this.getPrice());
		System.out.println("Stock: " + this.getStock());
		System.out.println("Expiry Date: " + this.getExpiryDate());
		System.out.println("Tablets Per Strip" + this.getTabletsPerStrip() );
		System.out.println("strength: " + this.getStrength());
		
		System.out.println();
	
	}
	
	
	public int getTabletsPerStrip() {
		return tabletsPerStrip;
	}
	public void setTabletsPerStrip(int tabletsPerStrip) {
		this.tabletsPerStrip = tabletsPerStrip;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	
	
	
	
}
