package com.constructors.levelone;

public class Manager extends Employee{


	
	public Manager(String employeeID, String department, float salary) {
		super(employeeID,department,salary);
	}
	
	
	
	public void emploeeIDAndDepartment() {
		System.out.println("Manager Details: ");
		System.out.println("Employee ID = "+ employeeID);
		System.out.println("Department = " + department);

	
	}
	
	
	
	
	
}
