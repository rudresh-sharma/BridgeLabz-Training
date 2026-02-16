package com.dbmsjdbc.hms.dao;

import com.dbmsjdbc.hms.config.DBConnection;
import com.dbmsjdbc.hms.model.Visit;

import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.*;

public class VisitDAO {

    public void recordVisit(int appointmentId,
                            String diagnosis,
                            String notes) {

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            String insert =
                    "INSERT INTO visits(appointment_id,diagnosis,notes) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, appointmentId);
            ps.setString(2, diagnosis);
            ps.setString(3, notes);
            ps.executeUpdate();

            String update =
                    "UPDATE appointments SET status='COMPLETED' WHERE id=?";

            PreparedStatement ps2 =
                    conn.prepareStatement(update);
            ps2.setInt(1, appointmentId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Visit Recorded ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    public void viewMedicalHistory(int patientId) {

        String sql = """
            SELECT v.id AS visit_id,
                   a.appointment_date,
                   d.name AS doctor_name,
                   v.diagnosis,
                   v.notes
            FROM visits v
            JOIN appointments a ON v.appointment_id = a.id
            JOIN doctors d ON a.doctor_id = d.id
            WHERE a.patient_id = ?
            ORDER BY a.appointment_date DESC
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("--------------------------------------------------");
                System.out.println("Visit ID      : " + rs.getInt("visit_id"));
                System.out.println("Visit Date    : " + rs.getDate("appointment_date"));
                System.out.println("Doctor        : " + rs.getString("doctor_name"));
                System.out.println("Diagnosis     : " + rs.getString("diagnosis"));
                System.out.println("Notes         : " + rs.getString("notes"));
            }

            if (!found) {
                System.out.println("No Medical History Found ❌");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

