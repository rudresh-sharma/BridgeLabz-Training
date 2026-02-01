package com.streamapi.foreach.iotsensorreading;

import java.util.ArrayList;
import java.util.List;

public class IotSensorReadingsApp {
    public static void main(String[] args) {
	List<SensorReading> readings = new ArrayList<>();

	// sample IoT sensor readings
	readings.add(new SensorReading("TEMP-001", 22.5, "10:05"));
	readings.add(new SensorReading("TEMP-002", 36.8, "10:06"));
	readings.add(new SensorReading("TEMP-003", 19.2, "10:07"));
	readings.add(new SensorReading("TEMP-004", 41.3, "10:08"));
	readings.add(new SensorReading("TEMP-005", 28.7, "10:09"));
	readings.add(new SensorReading("TEMP-006", 38.1, "10:10"));

	double threshold = 35.0;
	System.out.println("IoT Sensor Readings - Above Threshold (" + threshold + "°C)");
	System.out.println("----------------------------------------------------");

	readings.stream()
		.filter(r -> r.getTemperature() > threshold)
		.forEach(System.out::println);
	
	System.out.println("----------------------------------------------------");

	long highCount = readings.stream()
				 .filter(r -> r.getTemperature() > threshold)
				 .count();

	System.out.println("Number of readings above threshold: " + highCount);
    }
}