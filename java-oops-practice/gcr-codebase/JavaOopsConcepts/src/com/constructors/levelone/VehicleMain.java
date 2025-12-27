package com.constructors.levelone;

public class VehicleMain {
	public static void main(String[] args) {
		
		// Creating vehicles objects
		Vehicle v1 = new Vehicle("Rudresh Sharma", "Bike");
		Vehicle v2 = new Vehicle("Rudra Raj", "Bike");
		Vehicle v3 = new Vehicle("Soumya Sharif", "Scooty");
		Vehicle v4 = new Vehicle("Sachin Sisodiyaa", "Bike");
		
		
		// Calling vehicle details 
		v1.displayVehicleDetails();
		v2.displayVehicleDetails();
		v3.displayVehicleDetails();
		v4.displayVehicleDetails();
		
		Vehicle.updateRegistrationFee();
		
		
		v1.displayVehicleDetails();
	}
}
