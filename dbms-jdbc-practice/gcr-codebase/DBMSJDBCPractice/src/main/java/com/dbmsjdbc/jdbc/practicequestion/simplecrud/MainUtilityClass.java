package com.dbmsjdbc.jdbc.practicequestion.simplecrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MainUtilityClass {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
		
		do {
			showMenu();
			
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			
			
			switch(choice) {
			case 1:
				AddEmployee.addEmployeeInDatabase(sc);
				break;
				
			
			case 2:
				ViewAllEmployee.showEmployees();
				break;
				
			case 3:
				System.out.println("Enter Employee Id:");
				int id = sc.nextInt();
				sc.nextLine();
				UpdateSalary.updateEmpSalary(id);
				break;
				
			case 4:
				System.out.println("Enter Employee Id to whome you want to delete.");
				int empId = sc.nextInt();
				sc.nextLine();
				DeleteEmployee.deleteEmp(empId);
				break;
			
				
			case 5:
				System.out.println("Enter Employee Id to whome you want to search.");
				int empId1 = sc.nextInt();
				sc.nextLine();
				SearchEmployee.searchEmp(empId1);
				break;
				
			
			case 6: 
				System.out.println("Thank You !!");
				break;
				
			default :
				System.out.println("Invalid Choice!");
				
				
				
			}
			
			
			
		}while(choice != 6);
		
		
	}
	
	
	
	
	




	public static void showMenu() {
		System.out.println("1. Add Employee");
		System.out.println("2. View All Employees");
		System.out.println("3. Update Employee Salary");
		System.out.println("4. Delete Employee");
		System.out.println("5. Search Employee");
		System.out.println("6. Exit");
	}
}
