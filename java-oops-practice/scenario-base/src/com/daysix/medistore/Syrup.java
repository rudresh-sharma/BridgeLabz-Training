package com.daysix.medistore;
import java.time.LocalDate;

public class Syrup extends Medicine{


	private double bottleVolume;      // ml

	public Syrup(String name, double price, LocalDate expiryDate,int stock,  double bottleVolume) {
		super(name,  price, expiryDate, stock);
		this.bottleVolume = bottleVolume;
	}

	public double getBottleVolume() {
		return bottleVolume;
	}

	public void setBottleVolume(double bottleVolume) {
		this.bottleVolume = bottleVolume;
	}
	
	public void showDetails() {
		System.out.println("\nMedicine Detail:");
		System.out.println("Name: " + this.getName());
		System.out.println("Price: " + this.getPrice());
		System.out.println("Stock: " + this.getStock());
		System.out.println("Expiry Date: " + this.getExpiryDate());
		System.out.println("Bottle Volume: " + this.getBottleVolume() );
		
		System.out.println();
	}
	
	
	
}
