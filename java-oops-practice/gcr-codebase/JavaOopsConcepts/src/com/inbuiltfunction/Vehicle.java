package com.inbuiltfunction;

public class Vehicle {
	   // Static variable shared by all vehicles
	   private static double registrationFee = 100.0; // Default registration fee
	   // Instance variables
	   private final String registrationNumber; // Final variable to ensure the registration number cannot be changed
	   private String ownerName;
	   private String vehicleType;
	   // Constructor to initialize ownerName, vehicleType, and registrationNumber
	   public Vehicle(String ownerName, String vehicleType, String registrationNumber) {
	       this.ownerName = ownerName;
	       this.vehicleType = vehicleType;
	       this.registrationNumber = registrationNumber;
	   }
	   // Static method to update the registration fee
	   public static void updateRegistrationFee(double newFee) {
	       if (newFee >= 0.0) {
	           registrationFee = newFee;
	       } else {
	           System.out.println("Invalid registration fee. Please enter a non-negative value.");
	       }
	   }
	   // Method to display vehicle registration details
	   public void displayRegistrationDetails() {
	       // Using 'instanceof' to verify the object's type before displaying details
	       if (this instanceof Vehicle) {
	           System.out.println("Owner Name: " + ownerName);
	           System.out.println("Vehicle Type: " + vehicleType);
	           System.out.println("Registration Number: " + registrationNumber);
	           System.out.println("Registration Fee: $" + registrationFee);
	       } else {
	           System.out.println("The object is not an instance of the Vehicle class.");
	       }
	   }
	   // Getters for instance variables (optional, but recommended for encapsulation)
	   public String getOwnerName() {
	       return ownerName;
	   }
	   public String getVehicleType() {
	       return vehicleType;
	   }
	   public String getRegistrationNumber() {
	       return registrationNumber;
	   }
}
