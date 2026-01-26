package com.csvdatahandling.question13;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeCsvReport {
    public static void main(String[] args) {
        // MySQL connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/employees"; // database name
        String username = "root";  // your MySQL username
        String password = "Rudresh@2005"; // replace with your MySQL root password

        String csvFile = "employee_report.csv";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement();
             FileWriter writer = new FileWriter(csvFile)) {

            // SQL query to get Employee ID, Name, Department, Current Salary
            String query = """
                SELECT e.emp_no AS EmployeeID,
                       CONCAT(e.first_name, ' ', e.last_name) AS Name,
                       d.dept_name AS Department,
                       s.salary AS Salary
                FROM employees e
                JOIN dept_emp de ON e.emp_no = de.emp_no
                JOIN departments d ON de.dept_no = d.dept_no
                JOIN salaries s ON e.emp_no = s.emp_no
                WHERE s.to_date = '9999-01-01'
                ORDER BY e.emp_no;
                """;

            ResultSet rs = stmt.executeQuery(query);

            // Write CSV header
            writer.append("Employee ID,Name,Department,Salary\n");

            // Write rows
            while (rs.next()) {
                writer.append(rs.getInt("EmployeeID") + ","
                        + rs.getString("Name") + ","
                        + rs.getString("Department") + ","
                        + rs.getInt("Salary") + "\n");
            }

            System.out.println("CSV report generated successfully: " + csvFile);

        } catch (IOException e) {
            System.err.println("CSV write error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
