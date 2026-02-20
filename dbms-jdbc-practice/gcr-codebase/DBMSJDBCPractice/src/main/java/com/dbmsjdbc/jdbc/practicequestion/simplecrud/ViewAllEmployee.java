package com.dbmsjdbc.jdbc.practicequestion.simplecrud;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAllEmployee {
	
	static void showEmployees() {
		String sql = "SELECT * FROM employee";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement(sql);
		     ResultSet rs = pstmt.executeQuery()) {

		    System.out.println("---------------------------------------------------------------------------------------------");
		    System.out.printf("%-5s %-20s %-25s %-12s %-15s%n",
		            "ID", "Name", "Email", "Salary", "Department");
		    System.out.println("---------------------------------------------------------------------------------------------");

		    while (rs.next()) {
		        System.out.printf("%-5d %-20s %-25s %-12.2f %-15s%n",
		                rs.getInt("id"),
		                rs.getString("name"),
		                rs.getString("email"),
		                rs.getDouble("salary"),
		                rs.getString("department"));
		    }

		    System.out.println("---------------------------------------------------------------------------------------------");

		} catch (SQLException e) {
		    e.printStackTrace();
		}

	}
}
