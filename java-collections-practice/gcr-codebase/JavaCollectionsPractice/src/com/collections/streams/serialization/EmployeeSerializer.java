package com.collections.streams.serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.List;

public class EmployeeSerializer {
	// Serializes list of employees to file
	public static void serialize(List<Employee> employees, String filePath) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {

			oos.writeObject(employees);
			System.out.println("Employees serialized successfully.");

		} catch (IOException e) {
			System.out.println("Serialization Error: " + e.getMessage());
		}
	}
}