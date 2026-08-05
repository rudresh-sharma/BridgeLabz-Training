package com.healthclinicapp.model;

import java.sql.Date;

/** POJO for the {@code insurance} table. */
public class Insurance {

    private int    insuranceId;
    private int    patientId;
    private String providerName;
    private String policyNumber;
    private double coverageAmount;
    private Date   validFrom;
    private Date   validTo;
    private boolean isActive;

    public Insurance() {}

    public Insurance(int insuranceId, int patientId, String providerName,
                     String policyNumber, double coverageAmount,
                     Date validFrom, Date validTo, boolean isActive) {
        this.insuranceId     = insuranceId;
        this.patientId       = patientId;
        this.providerName    = providerName;
        this.policyNumber    = policyNumber;
        this.coverageAmount  = coverageAmount;
        this.validFrom       = validFrom;
        this.validTo         = validTo;
        this.isActive        = isActive;
    }

    public int    getInsuranceId()              { return insuranceId; }
    public void   setInsuranceId(int id)        { this.insuranceId = id; }
    public int    getPatientId()                { return patientId; }
    public void   setPatientId(int id)          { this.patientId = id; }
    public String getProviderName()             { return providerName; }
    public void   setProviderName(String n)     { this.providerName = n; }
    public String getPolicyNumber()             { return policyNumber; }
    public void   setPolicyNumber(String p)     { this.policyNumber = p; }
    public double getCoverageAmount()           { return coverageAmount; }
    public void   setCoverageAmount(double a)   { this.coverageAmount = a; }
    public Date   getValidFrom()                { return validFrom; }
    public void   setValidFrom(Date d)          { this.validFrom = d; }
    public Date   getValidTo()                  { return validTo; }
    public void   setValidTo(Date d)            { this.validTo = d; }
    public boolean isActive()                   { return isActive; }
    public void    setActive(boolean a)         { this.isActive = a; }

    @Override
    public String toString() {
        return "Insurance{id=" + insuranceId + ", provider='" + providerName +
               "', policy='" + policyNumber + "'}";
    }
}
