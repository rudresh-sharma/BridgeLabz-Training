package com.healthclinicapp.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * POJO representing a row in the {@code appointment} table.
 */
public class Appointment {

    private int    appointmentId;
    private int    patientId;
    private int    doctorId;
    private Date   appointmentDate;
    private String appointmentTime;  // stored as TIME string HH:MM:SS
    private String status;           // Scheduled / Completed / Cancelled / No-Show
    private String reason;
    private String notes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // ── Transient fields populated by JOIN queries ────────────────────────────
    private String patientName;
    private String doctorName;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Appointment() {}

    public Appointment(int appointmentId, int patientId, int doctorId,
                       Date appointmentDate, String appointmentTime, String status,
                       String reason, String notes,
                       Timestamp createdAt, Timestamp updatedAt) {
        this.appointmentId   = appointmentId;
        this.patientId       = patientId;
        this.doctorId        = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status          = status;
        this.reason          = reason;
        this.notes           = notes;
        this.createdAt       = createdAt;
        this.updatedAt       = updatedAt;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public int    getAppointmentId()                 { return appointmentId; }
    public void   setAppointmentId(int id)           { this.appointmentId = id; }

    public int    getPatientId()                     { return patientId; }
    public void   setPatientId(int id)               { this.patientId = id; }

    public int    getDoctorId()                      { return doctorId; }
    public void   setDoctorId(int id)                { this.doctorId = id; }

    public Date   getAppointmentDate()               { return appointmentDate; }
    public void   setAppointmentDate(Date d)         { this.appointmentDate = d; }

    public String getAppointmentTime()               { return appointmentTime; }
    public void   setAppointmentTime(String t)       { this.appointmentTime = t; }

    public String getStatus()                        { return status; }
    public void   setStatus(String s)                { this.status = s; }

    public String getReason()                        { return reason; }
    public void   setReason(String r)                { this.reason = r; }

    public String getNotes()                         { return notes; }
    public void   setNotes(String n)                 { this.notes = n; }

    public Timestamp getCreatedAt()                  { return createdAt; }
    public void      setCreatedAt(Timestamp t)       { this.createdAt = t; }

    public Timestamp getUpdatedAt()                  { return updatedAt; }
    public void      setUpdatedAt(Timestamp t)       { this.updatedAt = t; }

    public String getPatientName()                   { return patientName; }
    public void   setPatientName(String n)           { this.patientName = n; }

    public String getDoctorName()                    { return doctorName; }
    public void   setDoctorName(String n)            { this.doctorName = n; }

    @Override
    public String toString() {
        return "Appointment{id=" + appointmentId + ", patientId=" + patientId +
               ", doctorId=" + doctorId + ", date=" + appointmentDate +
               ", status='" + status + "'}";
    }
}
