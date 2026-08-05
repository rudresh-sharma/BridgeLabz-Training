package com.healthclinicapp.model;

import java.sql.Date;
import java.sql.Timestamp;

/** POJO for the {@code billing} table. */
public class Billing {

    private int    billId;
    private int    patientId;
    private Integer visitId;     // nullable
    private Date   billDate;
    private double totalAmount;
    private double paidAmount;
    private double discount;
    private double tax;
    private String status;       // Pending / Partial / Paid / Cancelled
    private String notes;
    private Timestamp createdAt;

    // ── Transient ─────────────────────────────────────────────────────────────
    private String patientName;

    public Billing() {}

    public Billing(int billId, int patientId, Integer visitId, Date billDate,
                   double totalAmount, double paidAmount, double discount,
                   double tax, String status, String notes, Timestamp createdAt) {
        this.billId      = billId;
        this.patientId   = patientId;
        this.visitId     = visitId;
        this.billDate    = billDate;
        this.totalAmount = totalAmount;
        this.paidAmount  = paidAmount;
        this.discount    = discount;
        this.tax         = tax;
        this.status      = status;
        this.notes       = notes;
        this.createdAt   = createdAt;
    }

    public int    getBillId()                   { return billId; }
    public void   setBillId(int id)             { this.billId = id; }
    public int    getPatientId()                { return patientId; }
    public void   setPatientId(int id)          { this.patientId = id; }
    public Integer getVisitId()                 { return visitId; }
    public void    setVisitId(Integer id)       { this.visitId = id; }
    public Date   getBillDate()                 { return billDate; }
    public void   setBillDate(Date d)           { this.billDate = d; }
    public double getTotalAmount()              { return totalAmount; }
    public void   setTotalAmount(double a)      { this.totalAmount = a; }
    public double getPaidAmount()               { return paidAmount; }
    public void   setPaidAmount(double a)       { this.paidAmount = a; }
    public double getDiscount()                 { return discount; }
    public void   setDiscount(double d)         { this.discount = d; }
    public double getTax()                      { return tax; }
    public void   setTax(double t)              { this.tax = t; }
    public String getStatus()                   { return status; }
    public void   setStatus(String s)           { this.status = s; }
    public String getNotes()                    { return notes; }
    public void   setNotes(String n)            { this.notes = n; }
    public Timestamp getCreatedAt()             { return createdAt; }
    public void      setCreatedAt(Timestamp t)  { this.createdAt = t; }
    public String getPatientName()              { return patientName; }
    public void   setPatientName(String n)      { this.patientName = n; }

    public double getOutstanding() { return totalAmount - paidAmount; }

    @Override
    public String toString() {
        return "Billing{id=" + billId + ", patientId=" + patientId +
               ", total=" + totalAmount + ", status='" + status + "'}";
    }
}
