package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Prescription;
import com.healthclinicapp.model.PrescriptionItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for prescription and prescription_item tables. */
public class PrescriptionDAO {

    public int insertPrescription(Prescription p) throws SQLException {
        String sql = "INSERT INTO prescription(visit_id,prescribed_date,instructions) VALUES(?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1, p.getVisitId());
            ps.setDate  (2, p.getPrescribedDate());
            ps.setString(3, p.getInstructions());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    public int insertItem(PrescriptionItem item) throws SQLException {
        String sql = """
            INSERT INTO prescription_item(prescription_id,medicine_id,dosage,frequency,duration_days,quantity)
            VALUES(?,?,?,?,?,?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1, item.getPrescriptionId());
            ps.setInt   (2, item.getMedicineId());
            ps.setString(3, item.getDosage());
            ps.setString(4, item.getFrequency());
            ps.setInt   (5, item.getDurationDays());
            ps.setInt   (6, item.getQuantity());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    /** Batch insert prescription items. */
    public int[] batchInsertItems(List<PrescriptionItem> items) throws SQLException {
        String sql = "INSERT INTO prescription_item(prescription_id,medicine_id,dosage,frequency,duration_days,quantity) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (PrescriptionItem i : items) {
                ps.setInt(1,i.getPrescriptionId()); ps.setInt(2,i.getMedicineId());
                ps.setString(3,i.getDosage()); ps.setString(4,i.getFrequency());
                ps.setInt(5,i.getDurationDays()); ps.setInt(6,i.getQuantity());
                ps.addBatch();
            }
            return ps.executeBatch();
        }
    }

    public Prescription findById(int id) throws SQLException {
        String sql = "SELECT * FROM prescription WHERE prescription_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapPrescription(rs) : null;
            }
        }
    }

    public Prescription findByVisit(int visitId) throws SQLException {
        String sql = "SELECT * FROM prescription WHERE visit_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,visitId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapPrescription(rs) : null;
            }
        }
    }

    public List<PrescriptionItem> getItemsByPrescription(int prescriptionId) throws SQLException {
        String sql = """
            SELECT pi.*, m.name AS medicine_name
            FROM prescription_item pi
            JOIN medicine m ON pi.medicine_id = m.medicine_id
            WHERE pi.prescription_id = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prescriptionId);
            try (ResultSet rs = ps.executeQuery()) {
                List<PrescriptionItem> list = new ArrayList<>();
                while (rs.next()) {
                    PrescriptionItem item = new PrescriptionItem();
                    item.setItemId        (rs.getInt   ("item_id"));
                    item.setPrescriptionId(rs.getInt   ("prescription_id"));
                    item.setMedicineId    (rs.getInt   ("medicine_id"));
                    item.setDosage        (rs.getString("dosage"));
                    item.setFrequency     (rs.getString("frequency"));
                    item.setDurationDays  (rs.getInt   ("duration_days"));
                    item.setQuantity      (rs.getInt   ("quantity"));
                    item.setMedicineName  (rs.getString("medicine_name"));
                    list.add(item);
                }
                return list;
            }
        }
    }

    /** Full prescription details using the view. */
    public ResultSet getPrescriptionDetailsView(Connection conn, int patientId) throws SQLException {
        String sql = """
            SELECT pd.*
            FROM v_prescription_details pd
            JOIN appointment a ON pd.patient_name = CONCAT(
                (SELECT first_name FROM patient WHERE patient_id=?), ' ',
                (SELECT last_name  FROM patient WHERE patient_id=?))
            """;
        // Simpler direct query:
        String sql2 = """
            SELECT pr.prescription_id, pr.prescribed_date,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name,
                   m.name AS medicine, pi.dosage, pi.frequency, pi.duration_days, pi.quantity
            FROM prescription pr
            JOIN visit       v   ON pr.visit_id        = v.visit_id
            JOIN appointment a   ON v.appointment_id   = a.appointment_id
            JOIN patient     p   ON a.patient_id       = p.patient_id
            JOIN doctor      d   ON a.doctor_id        = d.doctor_id
            JOIN prescription_item pi ON pr.prescription_id = pi.prescription_id
            JOIN medicine    m   ON pi.medicine_id     = m.medicine_id
            WHERE p.patient_id = ?
            ORDER BY pr.prescribed_date DESC
            """;
        PreparedStatement ps = conn.prepareStatement(sql2);
        ps.setInt(1, patientId);
        return ps.executeQuery();
    }

    private Prescription mapPrescription(ResultSet rs) throws SQLException {
        Prescription p = new Prescription();
        p.setPrescriptionId(rs.getInt      ("prescription_id"));
        p.setVisitId       (rs.getInt      ("visit_id"));
        p.setPrescribedDate(rs.getDate     ("prescribed_date"));
        p.setInstructions  (rs.getString   ("instructions"));
        p.setCreatedAt     (rs.getTimestamp("created_at"));
        return p;
    }
}
