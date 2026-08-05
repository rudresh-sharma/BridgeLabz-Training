package com.healthclinicapp.model;

import java.sql.Date;
import java.sql.Timestamp;

/** POJO for the {@code lab_report} table. */
public class LabReport {

    private int     reportId;
    private int     visitId;
    private int     testId;
    private Date    testDate;
    private String  result;
    private Boolean isNormal;
    private String  remarks;
    private Timestamp createdAt;

    // ── Transient ─────────────────────────────────────────────────────────────
    private String testName;

    public LabReport() {}

    public LabReport(int reportId, int visitId, int testId, Date testDate,
                     String result, Boolean isNormal, String remarks, Timestamp createdAt) {
        this.reportId  = reportId;
        this.visitId   = visitId;
        this.testId    = testId;
        this.testDate  = testDate;
        this.result    = result;
        this.isNormal  = isNormal;
        this.remarks   = remarks;
        this.createdAt = createdAt;
    }

    public int     getReportId()               { return reportId; }
    public void    setReportId(int id)         { this.reportId = id; }
    public int     getVisitId()                { return visitId; }
    public void    setVisitId(int id)          { this.visitId = id; }
    public int     getTestId()                 { return testId; }
    public void    setTestId(int id)           { this.testId = id; }
    public Date    getTestDate()               { return testDate; }
    public void    setTestDate(Date d)         { this.testDate = d; }
    public String  getResult()                 { return result; }
    public void    setResult(String r)         { this.result = r; }
    public Boolean getIsNormal()               { return isNormal; }
    public void    setIsNormal(Boolean n)      { this.isNormal = n; }
    public String  getRemarks()                { return remarks; }
    public void    setRemarks(String r)        { this.remarks = r; }
    public Timestamp getCreatedAt()            { return createdAt; }
    public void      setCreatedAt(Timestamp t) { this.createdAt = t; }
    public String  getTestName()               { return testName; }
    public void    setTestName(String n)       { this.testName = n; }

    @Override
    public String toString() {
        return "LabReport{id=" + reportId + ", testId=" + testId + ", result='" + result + "'}";
    }
}
