package com.dbmsjdbc.hms.service;


import com.dbmsjdbc.hms.config.DBConnection;
import com.dbmsjdbc.hms.dao.AppointmentDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class AppointmentService {

    private Scanner sc = new Scanner(System.in);
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    // ================= BOOK APPOINTMENT =================
    public void bookAppointment() {

        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();

        System.out.print("Enter Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String dateInput = sc.nextLine();

        System.out.print("Enter Appointment Time (HH:MM): ");
        String timeInput = sc.nextLine();

        LocalDate localDate = LocalDate.parse(dateInput);
        LocalTime localTime = LocalTime.parse(timeInput);

        Date sqlDate = Date.valueOf(localDate);
        Time sqlTime = Time.valueOf(localTime);

        appointmentDAO.insertAppointment(
                patientId,
                doctorId,
                sqlDate,
                sqlTime
        );
    }

    // ================= CANCEL APPOINTMENT =================
    public void cancelAppointment() {

        System.out.print("Enter Appointment ID to Cancel: ");
        int appointmentId = sc.nextInt();

        appointmentDAO.cancelAppointment(appointmentId);
    }
    
    public void insertAppointment(int patientId,
            int doctorId,
            Date date,
            Time time) {

String sql = """
INSERT INTO appointments
(patient_id, doctor_id, appointment_date,
appointment_time, status)
VALUES (?, ?, ?, ?, 'SCHEDULED')
""";

try (Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement(sql)) {

ps.setInt(1, patientId);
ps.setInt(2, doctorId);
ps.setDate(3, date);
ps.setTime(4, time);

ps.executeUpdate();
System.out.println("Appointment Booked Successfully ✅");

} catch (Exception e) {
e.printStackTrace();
}
}

public void cancelAppointment(int appointmentId) {

String sql =
"UPDATE appointments SET status='CANCELLED' WHERE id=?";

try (Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement(sql)) {

ps.setInt(1, appointmentId);

int rows = ps.executeUpdate();

if (rows > 0)
System.out.println("Appointment Cancelled ✅");
else
System.out.println("Appointment Not Found ❌");

} catch (Exception e) {
e.printStackTrace();
}
}

}
