package com.daytwo.cabbygo;

public class Driver {
	public String name;
	public String phoneNumber;
	private String licenseNumber;
	private double rating;
	private String pickUpLocation;
	private String dropLocation;
	private double distance;
	private double fare;
	
	
	
	public Driver(String pickL, String dropL, double distance) {
		this.dropLocation = dropL;
		this.pickUpLocation = pickL;
		this.distance = distance;
	}
	
	public String getPickUpLocation() {
		return pickUpLocation;
	}

	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}

	public String getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}



	public Driver(String name, String phoneNumber, String licenseNumber, double rating) {

		this.name = name;
		this.phoneNumber = phoneNumber;
		this.licenseNumber = licenseNumber;
		this.rating = rating;
	}
	
	
	// Getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	// Method to display driver details
	public void showDriverDetails() {
		System.out.println("Driver Details");
		System.out.println("----------------");
		System.out.println("Name" + name);
		System.out.println("Phone :" + phoneNumber);
		System.out.println("License Number:" + licenseNumber);
		System.out.println("Rating:" + rating);

	}
	
	
	//Method to calculate fare
	public double calculateFare(Vehicle v) {		
		double fare =  distance*v.getRidePerKm();		
		return fare;
	}





}
