package com.encapsulation.employeemanagementsystem;

public class FullTimeEmployee extends Employee implements Department{

	private String department ;
	
	public FullTimeEmployee(String name, String empId, double baseSalary, String dept) {
		super(name, empId, baseSalary);
		this.department = dept;
	}


	
	// Method to get Salary of employee
	public double calculateSalary() {		
		return getSalary();
	}
	
	// Method to assign department
	public void assignDepartment(String department) {
		this.department = department;
	}
	
	
	// Method to get Department details
	public void getDepartmentDetails() {
	    System.out.println("Department: " + department);
	}

	
	// Method to get employee Details
	public void displayDetails() {
		super.displayDetails();
		System.out.println("Employee Department: " + department );
	
	}



	@Override
	public double calculateSalary(float workingHours) {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
