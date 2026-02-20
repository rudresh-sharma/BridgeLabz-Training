package com.dbmsjdbc.jdbc.practicequestion.simplecrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteEmployee {
	
	static void deleteEmp(int empId) {

	    String query = "DELETE FROM employee WHERE id = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, empId);

	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Employee data successfully deleted.");
	        } else {
	            System.out.println("No employee found with ID: " + empId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	
}
