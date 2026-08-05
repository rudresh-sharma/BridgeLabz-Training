package com.healthclinicapp.model;

import java.sql.Timestamp;

/** POJO for the {@code audit_log} table — populated by triggers. */
public class AuditLog {

    private int    logId;
    private String tableName;
    private String operation;   // INSERT / UPDATE / DELETE
    private Integer recordId;
    private String description;
    private Timestamp createdAt;

    public AuditLog() {}

    public AuditLog(int logId, String tableName, String operation,
                    Integer recordId, String description, Timestamp createdAt) {
        this.logId       = logId;
        this.tableName   = tableName;
        this.operation   = operation;
        this.recordId    = recordId;
        this.description = description;
        this.createdAt   = createdAt;
    }

    public int    getLogId()                    { return logId; }
    public void   setLogId(int id)              { this.logId = id; }
    public String getTableName()                { return tableName; }
    public void   setTableName(String t)        { this.tableName = t; }
    public String getOperation()                { return operation; }
    public void   setOperation(String o)        { this.operation = o; }
    public Integer getRecordId()                { return recordId; }
    public void    setRecordId(Integer id)      { this.recordId = id; }
    public String getDescription()              { return description; }
    public void   setDescription(String d)      { this.description = d; }
    public Timestamp getCreatedAt()             { return createdAt; }
    public void      setCreatedAt(Timestamp t)  { this.createdAt = t; }

    @Override
    public String toString() {
        return "AuditLog{id=" + logId + ", table='" + tableName +
               "', op='" + operation + "', recordId=" + recordId + "}";
    }
}
