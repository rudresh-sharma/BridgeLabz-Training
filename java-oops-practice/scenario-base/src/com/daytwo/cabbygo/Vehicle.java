package com.daytwo.cabbygo;

public class Vehicle implements IRideService{

	private String vehicleNumber;
	private int capacity;
	private String type;
	private double ridePerKm;
	
	public Vehicle() {
		
	}
	
	
	public Vehicle(String vehicleNumber, int capacity,double ridePerKm, String type) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.capacity = capacity;
		this.ridePerKm = ridePerKm;
		this.type = type;
	}

	
	// Getter and Setter
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	public double getRidePerKm() {
		return ridePerKm;
	}

	public void setRidePerKm(double ridePerKm) {
		this.ridePerKm = ridePerKm;
	}

	// Method to bookRide and endRide
	
	public void bookRide() {
		System.out.println("Your Ride is booked, relax");
	}
	
	
	public void endRide(Driver d, Vehicle v) {
		System.out.println("Your total fare is:" + d.calculateFare(v));
		System.out.println("Thankyou  for taking a ride with Cabbygo");
	}
	
	// Method to show vehicle details
	public void showVehicleDetails() {
		System.out.println("Vehicle Details");
		System.out.println("-----------------");
		System.out.println("Vehicle No: " + vehicleNumber);
		System.out.println("Vehicle Capacity:  " + capacity);
		System.out.println("Vehicle Type:  " + type);
		System.out.println("Charge per km  " + ridePerKm);
	}
	
	
	
	
	
}
