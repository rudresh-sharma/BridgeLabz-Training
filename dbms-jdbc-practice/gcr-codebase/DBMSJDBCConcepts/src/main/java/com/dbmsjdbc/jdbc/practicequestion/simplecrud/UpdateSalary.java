package com.dbmsjdbc.jdbc.practicequestion.simplecrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateSalary {

	static void updateEmpSalary(int empId) {
		
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = DatabaseConnection.getConnection()){
			
			String query = "UPDATE employee SET salary = ? WHERE id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(2, empId);
			
			System.out.println("Enter the Salary:");
			double salary = sc.nextDouble();
			
			pstmt.setDouble(1,salary);
			
			int rowsaffected = pstmt.executeUpdate();
			
			if(rowsaffected>0) {
				System.out.println("Salary Updated Successfully.");
			}
			else {
				System.out.println("Not Updated");
			}
			
			
		}
		catch( SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
		
	}
	
	
}
