package com.dbmsjdbc.jdbc.practicequestion.simplecrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchEmployee {
	static void searchEmp(int empId) {

	    String query = "SELECT * FROM employee WHERE id = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, empId);

	        ResultSet rs = pstmt.executeQuery();   // ✅ correct method

	        if (rs.next()) {   // move cursor to first row

	            System.out.println("Employee Found:");
	            System.out.println("----------------------------");
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Name: " + rs.getString("name"));
	            System.out.println("Email: " + rs.getString("email"));
	            System.out.println("Salary: " + rs.getDouble("salary"));
	            System.out.println("Department: " + rs.getString("department"));
	            System.out.println("----------------------------");

	        } else {
	            System.out.println("No employee found with ID: " + empId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
