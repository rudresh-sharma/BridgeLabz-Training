package com.healthclinicapp.model;

import java.sql.Date;

/** POJO for the {@code medical_history} table. */
public class MedicalHistory {

    private int    historyId;
    private int    patientId;
    private String conditionName;
    private Date   diagnosedDate;
    private String notes;

    public MedicalHistory() {}

    public MedicalHistory(int historyId, int patientId, String conditionName,
                          Date diagnosedDate, String notes) {
        this.historyId     = historyId;
        this.patientId     = patientId;
        this.conditionName = conditionName;
        this.diagnosedDate = diagnosedDate;
        this.notes         = notes;
    }

    public int    getHistoryId()                { return historyId; }
    public void   setHistoryId(int id)          { this.historyId = id; }
    public int    getPatientId()                { return patientId; }
    public void   setPatientId(int id)          { this.patientId = id; }
    public String getConditionName()            { return conditionName; }
    public void   setConditionName(String c)    { this.conditionName = c; }
    public Date   getDiagnosedDate()            { return diagnosedDate; }
    public void   setDiagnosedDate(Date d)      { this.diagnosedDate = d; }
    public String getNotes()                    { return notes; }
    public void   setNotes(String n)            { this.notes = n; }

    @Override
    public String toString() {
        return "MedicalHistory{id=" + historyId + ", patientId=" + patientId +
               ", condition='" + conditionName + "'}";
    }
}
