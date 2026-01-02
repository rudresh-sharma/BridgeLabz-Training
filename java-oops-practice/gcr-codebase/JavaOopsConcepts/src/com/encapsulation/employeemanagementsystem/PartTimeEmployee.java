package com.encapsulation.employeemanagementsystem;

public class PartTimeEmployee extends Employee implements Department{
	private String department ;

	public PartTimeEmployee(String name, String empId, double baseSalary, String dept) {
		super(name, empId, baseSalary);
		this.department = dept;
	}



	@Override
	public double calculateSalary(float workingHours) {
		return workingHours * 300;
	}


	public void assignDepartment(String department) {
		this.department = department;
	}
	
	public void getDepartmentDetails() {
	    System.out.println("Department: " + department);
	}

	public void displayDetails() {
	super.displayDetails();
	System.out.println("Employee Department: " + department );

	}



	@Override
	public double calculateSalary() {
		// TODO Auto-generated method stub
		return 0;
	}




	
	 









}
