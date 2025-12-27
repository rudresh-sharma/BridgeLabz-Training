package com.constructors.levelone;

public class Employee {
	
	// Instance variabl
	public String employeeID;
	protected String department;
	private float salary;
	
	
	// Constructor configuration
	public Employee() {
		
	}
	public Employee(String employeeID, String department, float salary){
		super();
		this.employeeID = employeeID;
		this.department = department;
		this.salary = salary;
	}
	
	public void displayDetails() {
		System.out.println("Employee details:");
		System.out.println("------------------");
		System.out.println("Employee ID = " + employeeID);
		System.out.println("department = " +  department);
		System.out.println("salary  = " + salary);
	}
	
	public void modifySalary(float newSalary) {
		this.salary = newSalary;
	}
	
	
	
}
