package com.healthclinicapp.model;

import java.sql.Timestamp;

/** POJO for the {@code feedback} table. */
public class Feedback {

    private int    feedbackId;
    private int    patientId;
    private int    doctorId;
    private int    rating;        // 1 – 5
    private String comments;
    private Timestamp createdAt;

    // ── Transient ─────────────────────────────────────────────────────────────
    private String patientName;
    private String doctorName;

    public Feedback() {}

    public Feedback(int feedbackId, int patientId, int doctorId,
                    int rating, String comments, Timestamp createdAt) {
        this.feedbackId = feedbackId;
        this.patientId  = patientId;
        this.doctorId   = doctorId;
        this.rating     = rating;
        this.comments   = comments;
        this.createdAt  = createdAt;
    }

    public int    getFeedbackId()               { return feedbackId; }
    public void   setFeedbackId(int id)         { this.feedbackId = id; }
    public int    getPatientId()                { return patientId; }
    public void   setPatientId(int id)          { this.patientId = id; }
    public int    getDoctorId()                 { return doctorId; }
    public void   setDoctorId(int id)           { this.doctorId = id; }
    public int    getRating()                   { return rating; }
    public void   setRating(int r)              { this.rating = r; }
    public String getComments()                 { return comments; }
    public void   setComments(String c)         { this.comments = c; }
    public Timestamp getCreatedAt()             { return createdAt; }
    public void      setCreatedAt(Timestamp t)  { this.createdAt = t; }
    public String getPatientName()              { return patientName; }
    public void   setPatientName(String n)      { this.patientName = n; }
    public String getDoctorName()               { return doctorName; }
    public void   setDoctorName(String n)       { this.doctorName = n; }

    @Override
    public String toString() {
        return "Feedback{id=" + feedbackId + ", patientId=" + patientId +
               ", doctorId=" + doctorId + ", rating=" + rating + "}";
    }
}
