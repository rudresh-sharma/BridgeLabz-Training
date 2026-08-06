package com.javasms.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import com.javasms.model.Student;
import com.javasms.util.DBConnection;

public class StudentDAOImpl implements StudentDAO {
	
	
	private static final String INSERT_STUDENT =
	        "INSERT INTO student(first_name,last_name,email,course) VALUES(?,?,?,?)";
	
	
	@Override
	public void save(Student student) {

	    try (

	            Connection connection =
	                    DBConnection.getConnection();

	            PreparedStatement preparedStatement =
	                    connection.prepareStatement(INSERT_STUDENT)

	    ) {

	        preparedStatement.setString(1, student.getFirstName());
	        preparedStatement.setString(2, student.getLastName());
	        preparedStatement.setString(3, student.getEmail());
	        preparedStatement.setString(4, student.getCourse());

	        preparedStatement.executeUpdate();

	        System.out.println("\n\\n\nStudent Saved Successfully");

	    } catch (SQLException e) {

	        e.printStackTrace();

	    }

	}
	@Override
	public Student findById(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int studentId) {
		// TODO Auto-generated method stub
		
	}

 

}