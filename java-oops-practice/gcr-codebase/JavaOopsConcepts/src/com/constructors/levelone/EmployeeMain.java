package com.constructors.levelone;

public class EmployeeMain {

	public static void main(String[] args) {
		Employee e1 = new Employee("1233", "CSE-AIML", 20000f);
		
		
		Manager m1 = new Manager("20033", "EC", 100000f);
		
		e1.displayDetails();		
		System.out.println("After salary Modification");
		e1.modifySalary(300000f);
		e1.displayDetails();
		
		
		
		m1.emploeeIDAndDepartment();
		
	}
			
}
