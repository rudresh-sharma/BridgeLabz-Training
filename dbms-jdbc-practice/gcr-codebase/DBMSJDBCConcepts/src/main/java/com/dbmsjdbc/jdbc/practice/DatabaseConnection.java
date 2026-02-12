package com.dbmsjdbc.jdbc.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
// Database credentials
private static final String URL = "jdbc:mysql://localhost:3306/student_db";
private static final String USER = "root";
private static final String PASSWORD = "Rudresh@2005";

public static Connection getConnection() {
Connection connection = null;
try {

// Load MySQL JDBC Driver (optional in modern JDBC)
Class.forName("com.mysql.cj.jdbc.Driver");

// Establish connection
connection = DriverManager.getConnection(URL, USER, PASSWORD);
System.out.println("Database connected successfully!");

} catch (ClassNotFoundException e) {
System.err.println("MySQL JDBC Driver not found!");
e.printStackTrace();
} catch (SQLException e) {
System.err.println("Connection failed!");
e.printStackTrace();
}
return connection;
}

public static void main(String[] args) {
Connection conn = getConnection();
if (conn != null) {
try {
conn.close();
System.out.println("Connection closed.");
} catch (SQLException e) {
e.printStackTrace();
}
}
}
}