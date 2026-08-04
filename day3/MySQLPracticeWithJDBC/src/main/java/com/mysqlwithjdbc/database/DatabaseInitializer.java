package com.mysqlwithjdbc.database;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysqlwithjdbc.util.DBConnection;

public class DatabaseInitializer {

    public static void createDoctorTable() {

    	String sql = "CREATE TABLE IF NOT EXISTS doctor ("
    	           + "doctor_id INT PRIMARY KEY AUTO_INCREMENT, "
    	           + "name VARCHAR(100) NOT NULL, "
    	           + "specialty VARCHAR(100) NOT NULL, "
    	           + "experience INT NOT NULL, "
    	           + "consultation_fee DECIMAL(10,2) NOT NULL"
    	           + ");";

        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {

            statement.execute(sql);

            System.out.println("Doctor table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
    public static void createAddDoctorProcedure() {

        String dropProcedure =
                "DROP PROCEDURE IF EXISTS AddDoctor";

        String createProcedure =
                "CREATE PROCEDURE AddDoctor(" +
                "IN p_name VARCHAR(100), " +
                "IN p_specialty VARCHAR(100), " +
                "IN p_experience INT, " +
                "IN p_consultation_fee DECIMAL(10,2)" +
                ") " +
                "BEGIN " +
                "INSERT INTO doctor(name, specialty, experience, consultation_fee) " +
                "VALUES(p_name, p_specialty, p_experience, p_consultation_fee); " +
                "END";

        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {

            statement.execute(dropProcedure);

            statement.execute(createProcedure);

            System.out.println("AddDoctor Procedure Created Successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}