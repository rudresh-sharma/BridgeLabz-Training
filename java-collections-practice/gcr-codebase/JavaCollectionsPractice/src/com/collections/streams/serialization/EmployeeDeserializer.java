package com.collections.streams.serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;

public class EmployeeDeserializer {
	// Deserializes employees from file
	@SuppressWarnings("unchecked")
	public static List<Employee> deserialize(String filePath) {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {

			return (List<Employee>) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Deserialization Error: " + e.getMessage());
		}

		return null;
	}
}