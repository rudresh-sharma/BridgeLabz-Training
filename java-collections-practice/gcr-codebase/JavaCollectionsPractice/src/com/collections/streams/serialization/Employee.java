package com.collections.streams.serialization;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String department;
	private double salary;

	// constructor
	public Employee(int id, String name, String department, double salary) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	// Displays employee details
	public void displayDetails() {
		System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary);
	}
}