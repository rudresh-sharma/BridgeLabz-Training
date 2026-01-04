package com.encapsulation.vehiclerentalsystem;


public class Car extends Vehicle implements Insurable {

    private double insuranceAmount;
    private String policyNumber;   // encapsulated
    private double rentalCost;
    public Car(String number, double rate, String policy) {
        super(number, "Car", rate);
        this.policyNumber = policy;
    }
    
    
    @Override
	public  double calculateRentalCost(int days) {
    	return rentalCost = 2000*days;
    }
    @Override
    public double calculateInsurance() {
        insuranceAmount = getRentalRate() * 0.10;   // 10% for car
        return insuranceAmount;
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance = " + insuranceAmount;
    }

    // Encapsulation: no direct policy access
    public String getMaskedPolicy() {
        return "XXXX-" + policyNumber.substring(policyNumber.length()-4);
    }
}
