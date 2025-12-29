package com.inbuiltfunction;

public class EmployeeMain {
	  public static void main(String[] args) {
	       // Set the company name (static variable)
		  Employee .setCompanyName("Tech Solutions Inc.");
	       // Create new employee instances
	       Employee emp1 = new Employee ("Thamarai", 101, "Software Engineer");
	       Employee  emp2 = new Employee ("Rohan", 102, "Project Manager");
	       // Display total number of employees
	       Employee .displayTotalEmployees();
	       // Display employee details
	       emp1.displayEmployeeDetails();
	       emp2.displayEmployeeDetails();
	   }

}
