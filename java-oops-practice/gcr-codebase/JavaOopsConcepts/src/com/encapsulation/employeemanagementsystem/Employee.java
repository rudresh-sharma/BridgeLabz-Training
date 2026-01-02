package com.encapsulation.employeemanagementsystem;

public abstract class Employee {
	
	private String employeeId;
	private String name;
	private double baseSalary;
	public abstract double calculateSalary();
	public abstract double calculateSalary(float workingHours);

	
	public Employee(String name, String empId, double baseSalary) {
		this.name = name;
		this.employeeId =empId;
		this.baseSalary = baseSalary;
	}
	
	
	
	// Setter
	public void setEmployeeId(String eId) {
		this.employeeId = eId;
	}	
	public void setName(String name) {
		this.name = name;
	}	
	public void setSalary(double baseSal) {
		this.baseSalary = baseSal;
	}
	
	
	// Getter
	public String getEmployeeId() {
		return employeeId;
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return baseSalary;
	}
	
	
	
	public void displayDetails() {
	    System.out.println("Employee Id: " + employeeId);
	    System.out.println("Name: " + name);
	    System.out.println("Base Salary: " + baseSalary);
	}

	
	
	
	
	
	
}
