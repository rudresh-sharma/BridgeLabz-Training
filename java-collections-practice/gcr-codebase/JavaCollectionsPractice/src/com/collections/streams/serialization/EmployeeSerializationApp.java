package com.collections.streams.serialization;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSerializationApp {
    public static void main(String[] args) {
        String filePath = "samplefiles/employees.dat";

        // Create employee list
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Amit", "IT", 50000));
        employees.add(new Employee(102, "Neha", "HR", 45000));
        employees.add(new Employee(103, "Rahul", "Finance", 60000));

        // Serialize
        EmployeeSerializer.serialize(employees, filePath);

        // Deserialize
        List<Employee> storedEmployees =
                EmployeeDeserializer.deserialize(filePath);

        System.out.println("\nEmployees retrieved from file:");

        if (storedEmployees != null) {
            for (Employee emp : storedEmployees) {
                emp.displayDetails();
            }
        }
    }
}