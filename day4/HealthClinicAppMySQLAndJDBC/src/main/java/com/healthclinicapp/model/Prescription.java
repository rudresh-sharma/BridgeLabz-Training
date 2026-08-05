package com.healthclinicapp.model;

import java.sql.Date;
import java.sql.Timestamp;

/** POJO for the {@code prescription} table. */
public class Prescription {

    private int    prescriptionId;
    private int    visitId;
    private Date   prescribedDate;
    private String instructions;
    private Timestamp createdAt;

    // ── Constructors ─────────────────────────────────────────────────────────
    public Prescription() {}

    public Prescription(int prescriptionId, int visitId, Date prescribedDate,
                        String instructions, Timestamp createdAt) {
        this.prescriptionId = prescriptionId;
        this.visitId        = visitId;
        this.prescribedDate = prescribedDate;
        this.instructions   = instructions;
        this.createdAt      = createdAt;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────
    public int    getPrescriptionId()                { return prescriptionId; }
    public void   setPrescriptionId(int id)          { this.prescriptionId = id; }
    public int    getVisitId()                       { return visitId; }
    public void   setVisitId(int id)                 { this.visitId = id; }
    public Date   getPrescribedDate()                { return prescribedDate; }
    public void   setPrescribedDate(Date d)          { this.prescribedDate = d; }
    public String getInstructions()                  { return instructions; }
    public void   setInstructions(String i)          { this.instructions = i; }
    public Timestamp getCreatedAt()                  { return createdAt; }
    public void      setCreatedAt(Timestamp t)       { this.createdAt = t; }

    @Override
    public String toString() {
        return "Prescription{id=" + prescriptionId + ", visitId=" + visitId +
               ", date=" + prescribedDate + "}";
    }
}
