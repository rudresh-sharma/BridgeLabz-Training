package com.streamapi.foreach.iotsensorreading;

public class SensorReading {
    private String sensorId;
    private double temperature; // in Celsius
    private String timestamp;

    public SensorReading(String sensorId, double temperature, String timestamp) {
	this.sensorId = sensorId;
	this.temperature = temperature;
	this.timestamp = timestamp;
    }

    public double getTemperature() {
	return temperature;
    }

    @Override
    public String toString() {
	return "Sensor " + sensorId + " | Temp: " + temperature + "°C | Time: " + timestamp;
    }
}