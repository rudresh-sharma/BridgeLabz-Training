package com.dbmsjdbc.jdbc.practicequestion.simplecrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddEmployee {
	
	static void addEmployeeInDatabase(Scanner sc) {
		
		try(Connection conn = DatabaseConnection.getConnection()){
			
			String query = "INSERT INTO employee(name, email, salary, department) VALUES(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			String choice;
			while(true) {
				System.out.println("Enter Employee Details:");
				System.out.print("Enter Name: ");
				String name = sc.nextLine();
				pstmt.setString(1, name);
				
				System.out.print("Enter Email: ");
				String email = sc.nextLine();
				pstmt.setString(2, email);
				
				
				System.out.print("Enter Salary: ");
				double salary = sc.nextDouble();
				sc.nextLine();
				pstmt.setDouble(3, salary);
				
				System.out.println("Enter Department");
				String dept = null;

				while(true) {
				System.out.println("1. IT");
				System.out.println("2. HR");
				System.out.println("3. BU");
				System.out.println("4. Operations");
				
				System.out.println("Enter Department Number");
				int deptNo = sc.nextInt();
				sc.nextLine();
				
				switch(deptNo) {
				case 1:
					dept = "IT";
					break;
					
				case 2:
					dept = "HR";
					break;
					
				case 3:
					dept = "BU";
					break;
					
				case 4:
					dept = "Operations";
					break;
					
				default :
					System.out.println("Invalid Choice!");
				}
				
				if(deptNo == 1 || deptNo == 2 || deptNo == 3 || deptNo == 4 ) {
					break;
				}
				}
				
				pstmt.setString(4, dept);
				pstmt.addBatch();
				
				
				System.out.println("Do you want to add more(Y/N)");
				choice = sc.nextLine();
				
				if(choice.equalsIgnoreCase("N")) {
					break;
				}
				
			}
			
			int[] arr = pstmt.executeBatch();
			
			System.out.println("Employees Added Successfully");
			
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
	}

}
