package com.healthclinicapp.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * POJO representing a row in the {@code visit} table.
 * A visit is created when a patient attends an appointment.
 */
public class Visit {

    private int    visitId;
    private int    appointmentId;
    private Date   visitDate;
    private String symptoms;
    private String diagnosis;
    private String treatment;
    private Date   followUpDate;
    private double weight;         // kg
    private String bloodPressure;  // e.g. "120/80"
    private double temperature;    // Celsius
    private String notes;
    private Timestamp createdAt;

    // ── Transient ─────────────────────────────────────────────────────────────
    private String patientName;
    private String doctorName;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Visit() {}

    public Visit(int visitId, int appointmentId, Date visitDate, String symptoms,
                 String diagnosis, String treatment, Date followUpDate,
                 double weight, String bloodPressure, double temperature,
                 String notes, Timestamp createdAt) {
        this.visitId       = visitId;
        this.appointmentId = appointmentId;
        this.visitDate     = visitDate;
        this.symptoms      = symptoms;
        this.diagnosis     = diagnosis;
        this.treatment     = treatment;
        this.followUpDate  = followUpDate;
        this.weight        = weight;
        this.bloodPressure = bloodPressure;
        this.temperature   = temperature;
        this.notes         = notes;
        this.createdAt     = createdAt;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public int    getVisitId()                   { return visitId; }
    public void   setVisitId(int id)             { this.visitId = id; }

    public int    getAppointmentId()             { return appointmentId; }
    public void   setAppointmentId(int id)       { this.appointmentId = id; }

    public Date   getVisitDate()                 { return visitDate; }
    public void   setVisitDate(Date d)           { this.visitDate = d; }

    public String getSymptoms()                  { return symptoms; }
    public void   setSymptoms(String s)          { this.symptoms = s; }

    public String getDiagnosis()                 { return diagnosis; }
    public void   setDiagnosis(String d)         { this.diagnosis = d; }

    public String getTreatment()                 { return treatment; }
    public void   setTreatment(String t)         { this.treatment = t; }

    public Date   getFollowUpDate()              { return followUpDate; }
    public void   setFollowUpDate(Date d)        { this.followUpDate = d; }

    public double getWeight()                    { return weight; }
    public void   setWeight(double w)            { this.weight = w; }

    public String getBloodPressure()             { return bloodPressure; }
    public void   setBloodPressure(String bp)    { this.bloodPressure = bp; }

    public double getTemperature()               { return temperature; }
    public void   setTemperature(double t)       { this.temperature = t; }

    public String getNotes()                     { return notes; }
    public void   setNotes(String n)             { this.notes = n; }

    public Timestamp getCreatedAt()              { return createdAt; }
    public void      setCreatedAt(Timestamp t)   { this.createdAt = t; }

    public String getPatientName()               { return patientName; }
    public void   setPatientName(String n)       { this.patientName = n; }

    public String getDoctorName()                { return doctorName; }
    public void   setDoctorName(String n)        { this.doctorName = n; }

    @Override
    public String toString() {
        return "Visit{id=" + visitId + ", appointmentId=" + appointmentId +
               ", date=" + visitDate + ", diagnosis='" + diagnosis + "'}";
    }
}
