package com.daysix.medistore;
import java.time.LocalDate;

public class Injection extends Medicine{	
	private double dose;

	public Injection(String name, double price, LocalDate expiryDate, int stock,double dose) {
		super(name, price, expiryDate, stock);
		this.dose = dose;
	}

	public double getDose() {
		return dose;
	}

	public void setDose(double dose) {
		this.dose = dose;
	}
	
	
	public void showDetails() {
		System.out.println("\nMedicine Detail:");
		System.out.println("Name: " + this.getName());
		System.out.println("Price: " + this.getPrice());
		System.out.println("Stock: " + this.getStock());
		System.out.println("Expiry Date: " + this.getExpiryDate());
		System.out.println("Dose: : " + this.getDose() );

		System.out.println();
	}
}
