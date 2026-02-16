package com.dbmsjdbc.hms.dao;

import com.dbmsjdbc.hms.config.DBConnection;
import com.dbmsjdbc.hms.model.Appointment;

import java.sql.*;

;

public class AppointmentDAO {

    public void insertAppointment(int patientId, int doctorId,
                                  Date date, Time time) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = """
                INSERT INTO appointments
                (patient_id,doctor_id,appointment_date,
                 appointment_time,status)
                VALUES(?,?,?,?, 'SCHEDULED')
                """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setDate(3, date);
            ps.setTime(4, time);

            ps.executeUpdate();
            System.out.println("Appointment Booked ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelAppointment(int id) {
        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "UPDATE appointments SET status='CANCELLED' WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Appointment Cancelled ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
