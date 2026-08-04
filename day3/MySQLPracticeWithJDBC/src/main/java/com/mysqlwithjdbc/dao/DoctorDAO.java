package com.mysqlwithjdbc.dao;

import com.mysqlwithjdbc.util.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysqlwithjdbc.database.DatabaseInitializer;
import com.mysqlwithjdbc.model.Doctor;

public class DoctorDAO {
	
	
	
	   public void addDoctor(Doctor doctor) {

		   String sql = "INSERT INTO doctor (name, specialty, experience, consultation_fee) "
		           + "VALUES (?, ?, ?, ?)";
	        try (
	                Connection connection = DBConnection.getConnection();
	                PreparedStatement preparedStatement =
	                        connection.prepareStatement(sql)
	        ) {

	            preparedStatement.setString(1, doctor.getName());
	            preparedStatement.setString(2, doctor.getSpecialty());
	            preparedStatement.setInt(3, doctor.getExperience());
	            preparedStatement.setDouble(4, doctor.getConsultationFee());

	            int rows = preparedStatement.executeUpdate();

	            if (rows > 0)
	                System.out.println("Doctor Added Successfully.");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    }
	   	
	   
	   public void addDoctorUsingProcedure(Doctor doctor) {

		    String sql = "{CALL AddDoctor(?, ?, ?, ?)}";

		    try (
		            Connection connection = DBConnection.getConnection()
		    ) {

		        if (!procedureExists(connection, "AddDoctor")) {

		            System.out.println("Procedure not found.");
		            System.out.println("Creating Procedure...");

		           

		            DatabaseInitializer.createAddDoctorProcedure();

		            System.out.println("Procedure Created Successfully.");
		        }

		        try (CallableStatement callableStatement =
		                     connection.prepareCall(sql)) {

		            callableStatement.setString(1, doctor.getName());
		            callableStatement.setString(2, doctor.getSpecialty());
		            callableStatement.setInt(3, doctor.getExperience());
		            callableStatement.setDouble(4, doctor.getConsultationFee());

		            callableStatement.executeUpdate();

		            System.out.println("Doctor Added Successfully.");

		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	   
	   private static boolean procedureExists(Connection connection, String procedureName)
		        throws SQLException {

		    String sql = "SELECT COUNT(*) "
		               + "FROM information_schema.ROUTINES "
		               + "WHERE ROUTINE_SCHEMA = DATABASE() "
		               + "AND ROUTINE_NAME = ?";

		    try (PreparedStatement ps = connection.prepareStatement(sql)) {

		        ps.setString(1, procedureName);

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            return rs.getInt(1) > 0;
		        }
		    }

		    return false;
		}
	   
	   public List<Doctor> getAllDoctors() {

		    List<Doctor> doctors = new ArrayList<>();

		    String sql = "SELECT doctor_id, name, specialty, experience, consultation_fee "
		               + "FROM doctor "
		               + "ORDER BY doctor_id";

		    try (
		            Connection connection = DBConnection.getConnection();
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);
		            ResultSet resultSet = preparedStatement.executeQuery()
		    ) {

		        while (resultSet.next()) {

		            Doctor doctor = new Doctor();

		            doctor.setDoctorId(resultSet.getInt("doctor_id"));
		            doctor.setName(resultSet.getString("name"));
		            doctor.setSpecialty(resultSet.getString("specialty"));
		            doctor.setExperience(resultSet.getInt("experience"));
		            doctor.setConsultationFee(resultSet.getDouble("consultation_fee"));

		            doctors.add(doctor);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return doctors;
		}
	
	
	
	
	   public Doctor getDoctorById(int doctorId) {

		    Doctor doctor = null;

		    String sql = "SELECT * FROM doctor WHERE doctor_id = ?";

		    try (
		            Connection connection = DBConnection.getConnection();
		            PreparedStatement preparedStatement = connection.prepareStatement(sql)
		    ) {

		        preparedStatement.setInt(1, doctorId);

		        ResultSet resultSet = preparedStatement.executeQuery();

		        if (resultSet.next()) {

		            doctor = new Doctor();

		            doctor.setDoctorId(resultSet.getInt("doctor_id"));
		            doctor.setName(resultSet.getString("name"));
		            doctor.setSpecialty(resultSet.getString("specialty"));
		            doctor.setExperience(resultSet.getInt("experience"));
		            doctor.setConsultationFee(resultSet.getDouble("consultation_fee"));

		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return doctor;
		}
	
	
	
	   public static void updateDoctor(Doctor doctor) {

		    String sql = "UPDATE doctor "
		               + "SET name = ?, "
		               + "specialty = ?, "
		               + "experience = ?, "
		               + "consultation_fee = ? "
		               + "WHERE doctor_id = ?";

		    try (
		            Connection connection = DBConnection.getConnection();
		            PreparedStatement preparedStatement =
		                    connection.prepareStatement(sql)
		    ) {

		        preparedStatement.setString(1, doctor.getName());
		        preparedStatement.setString(2, doctor.getSpecialty());
		        preparedStatement.setInt(3, doctor.getExperience());
		        preparedStatement.setDouble(4, doctor.getConsultationFee());
		        preparedStatement.setInt(5, doctor.getDoctorId());

		        int rows = preparedStatement.executeUpdate();

		        if (rows > 0) {
		            System.out.println("Doctor Updated Successfully.");
		        } else {
		            System.out.println("Doctor Not Found.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	
	
	
	
	   public static void deleteDoctor(int doctorId) {

		    String sql = "DELETE FROM doctor WHERE doctor_id = ?";

		    try (
		            Connection connection = DBConnection.getConnection();
		            PreparedStatement preparedStatement =
		                    connection.prepareStatement(sql)
		    ) {

		        preparedStatement.setInt(1, doctorId);

		        int rows = preparedStatement.executeUpdate();

		        if (rows > 0) {
		            System.out.println("Doctor Deleted Successfully.");
		        } else {
		            System.out.println("Doctor Not Found.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//    public void addDoctor(Doctor doctor) {
//
//        String sql = "INSERT INTO doctor(name, specialty, experience, consultation_fee) " +
//                     "VALUES ('" + doctor.getName() + "', '" +
//                     doctor.getSpecialty() + "', " +
//                     doctor.getExperience() + ", " +
//                     doctor.getConsultationFee() + ")";
//
//        try (
//                Connection connection = DBConnection.getConnection();
//                Statement statement = connection.createStatement()
//        ) {
//
//            int rowsAffected = statement.executeUpdate(sql);
//
//            if (rowsAffected > 0) {
//                System.out.println("Doctor added successfully.");
//            } else {
//                System.out.println("Doctor could not be added.");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}