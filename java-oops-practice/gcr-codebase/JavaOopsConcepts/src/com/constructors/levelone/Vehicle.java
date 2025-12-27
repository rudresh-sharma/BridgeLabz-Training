package com.constructors.levelone;

public class Vehicle {
	
	private String ownerName;
	private String vehicleType;
	private static float registrationFee = 200;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String ownerName, String vehicleType) {
		this.ownerName = ownerName;
		this.vehicleType = vehicleType;
	}
	
	
	public void displayVehicleDetails() {
		System.out.println("Vehicle Details:");
		System.out.println("----------------");
		System.out.println("Owner Name = " + ownerName);
		System.out.println("Vehicle Type = " + vehicleType);
		System.out.println("Registratio fees = " + registrationFee);
		
	}
	
	
	public static void updateRegistrationFee() {
		registrationFee = 300;
	}
		
	
}
