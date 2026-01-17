package com.dayfive.cropmonitor;

public class SensorData {
	
	private long timestamps;
	private double temperature;
	
	
	public SensorData(long timestamps, double temperature) {
		this.timestamps = timestamps;
		this.temperature = temperature;
	}


	public long getTimestamps() {
		return timestamps;
	}


	public void setTimestamps(long timestamps) {
		this.timestamps = timestamps;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	
	
	@Override
	public String toString() {
	    return timestamps + "-----" + temperature;
	}

	
}
