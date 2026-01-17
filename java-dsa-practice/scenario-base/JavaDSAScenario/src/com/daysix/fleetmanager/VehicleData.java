package com.daysix.fleetmanager;

public class VehicleData {
	private String vehicleId;
	double mileage;
	
	
	public VehicleData(String vehicleId, double mileage) {
		this.vehicleId = vehicleId;
		this.mileage = mileage;
	}
	
	
	
	@Override
	public String toString() {
		return vehicleId + "    | " + mileage;
	}



	public String getVehicleId() {
		return vehicleId;
	}



	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}



	public double getMileage() {
		return mileage;
	}



	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	
	
	
}
