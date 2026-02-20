package com.dbmsjdbc.jdbc.practicequestion.librarymanagement;

import java.sql.*;

public class StudentService {

	public void addStudent(String name, String department) {

        try (Connection conn = DBConnection.getConnection()) {

            // Tell JDBC we want the generated keys
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO students(name, department) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, department);

            ps.executeUpdate();

            // Get the generated student_id
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int studentId = rs.getInt(1);
                System.out.println("Student Added Successfully ✅ | Student ID: " + studentId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
