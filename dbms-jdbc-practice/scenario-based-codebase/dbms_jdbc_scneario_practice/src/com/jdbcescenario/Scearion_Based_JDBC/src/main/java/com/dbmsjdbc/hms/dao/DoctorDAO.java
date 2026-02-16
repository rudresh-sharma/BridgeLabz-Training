package com.dbmsjdbc.hms.dao;

import com.dbmsjdbc.hms.config.DBConnection;
import com.dbmsjdbc.hms.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DoctorDAO {

    Scanner sc = new Scanner(System.in);

    public void addDoctor() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Specialty ID: ");
            int sid = sc.nextInt();

            System.out.print("Contact: ");
            String contact = sc.next();

            System.out.print("Consultation Fee: ");
            double fee = sc.nextDouble();

            String sql = """
                INSERT INTO doctors(name,specialty_id,contact,consultation_fee)
                VALUES(?,?,?,?)
                """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, sid);
            ps.setString(3, contact);
            ps.setDouble(4, fee);

            ps.executeUpdate();
            System.out.println("Doctor Added ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDoctors() {
        try (Connection conn = DBConnection.getConnection()) {

            String sql = """
                SELECT d.id,d.name,s.name AS specialty,d.consultation_fee
                FROM doctors d
                JOIN specialties s ON d.specialty_id=s.id
                WHERE d.is_active=TRUE
                """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("specialty") + " | Fee: " +
                        rs.getDouble("consultation_fee"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deactivateDoctor() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Doctor ID: ");
            int id = sc.nextInt();

            String sql = "UPDATE doctors SET is_active=FALSE WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Doctor Deactivated ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

