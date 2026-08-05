package com.healthclinicapp.model;

import java.sql.Date;

/** POJO for the {@code admission} table. */
public class Admission {

    private int    admissionId;
    private int    patientId;
    private int    roomId;
    private int    doctorId;
    private Date   admissionDate;
    private Date   dischargeDate;
    private String reason;
    private String status;   // Active / Discharged / Transferred

    // ── Transient ─────────────────────────────────────────────────────────────
    private String patientName;
    private String roomNumber;
    private String doctorName;

    public Admission() {}

    public Admission(int admissionId, int patientId, int roomId, int doctorId,
                     Date admissionDate, Date dischargeDate, String reason, String status) {
        this.admissionId   = admissionId;
        this.patientId     = patientId;
        this.roomId        = roomId;
        this.doctorId      = doctorId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.reason        = reason;
        this.status        = status;
    }

    public int    getAdmissionId()                 { return admissionId; }
    public void   setAdmissionId(int id)           { this.admissionId = id; }
    public int    getPatientId()                   { return patientId; }
    public void   setPatientId(int id)             { this.patientId = id; }
    public int    getRoomId()                      { return roomId; }
    public void   setRoomId(int id)                { this.roomId = id; }
    public int    getDoctorId()                    { return doctorId; }
    public void   setDoctorId(int id)              { this.doctorId = id; }
    public Date   getAdmissionDate()               { return admissionDate; }
    public void   setAdmissionDate(Date d)         { this.admissionDate = d; }
    public Date   getDischargeDate()               { return dischargeDate; }
    public void   setDischargeDate(Date d)         { this.dischargeDate = d; }
    public String getReason()                      { return reason; }
    public void   setReason(String r)              { this.reason = r; }
    public String getStatus()                      { return status; }
    public void   setStatus(String s)              { this.status = s; }
    public String getPatientName()                 { return patientName; }
    public void   setPatientName(String n)         { this.patientName = n; }
    public String getRoomNumber()                  { return roomNumber; }
    public void   setRoomNumber(String n)          { this.roomNumber = n; }
    public String getDoctorName()                  { return doctorName; }
    public void   setDoctorName(String n)          { this.doctorName = n; }

    @Override
    public String toString() {
        return "Admission{id=" + admissionId + ", patientId=" + patientId +
               ", roomId=" + roomId + ", status='" + status + "'}";
    }
}
