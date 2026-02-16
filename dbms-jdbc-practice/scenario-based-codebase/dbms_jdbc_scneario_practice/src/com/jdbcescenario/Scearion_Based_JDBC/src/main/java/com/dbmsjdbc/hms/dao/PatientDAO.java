package com.dbmsjdbc.hms.dao;

import com.dbmsjdbc.hms.config.DBConnection;
import com.dbmsjdbc.hms.model.Patient;

import java.sql.*;


import java.util.Scanner;


public class PatientDAO {

    Scanner sc = new Scanner(System.in);

    public void registerPatient() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("DOB (YYYY-MM-DD): ");
            String dob = sc.nextLine();

            System.out.print("Phone: ");
            String phone = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Address: ");
            String address = sc.nextLine();

            System.out.print("Blood Group: ");
            String blood = sc.nextLine();

            String sql = """
                INSERT INTO patients(name,dob,phone,email,address,blood_group)
                VALUES(?,?,?,?,?,?)
                """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDate(2, Date.valueOf(dob));
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, address);
            ps.setString(6, blood);

            ps.executeUpdate();
            System.out.println("Patient Registered ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchPatient() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Enter ID/Phone/Name: ");
            String input = sc.nextLine();

            String sql = """
                SELECT * FROM patients
                WHERE id=? OR phone=? OR name LIKE ?
                """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, input);
            ps.setString(2, input);
            ps.setString(3, "%" + input + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("phone") + " | " +
                        rs.getString("blood_group"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePatient() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Patient ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("New Address: ");
            String address = sc.nextLine();

            System.out.print("New Phone: ");
            String phone = sc.nextLine();

            String sql = "UPDATE patients SET address=?, phone=? WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, address);
            ps.setString(2, phone);
            ps.setInt(3, id);

            ps.executeUpdate();
            System.out.println("Patient Updated ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
