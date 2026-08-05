package com.healthclinicapp.model;

import java.sql.Timestamp;

/** POJO for the {@code activity_log} table — populated by triggers and application code. */
public class ActivityLog {

    private int    activityId;
    private String activityType;
    private String description;
    private Timestamp createdAt;

    public ActivityLog() {}

    public ActivityLog(int activityId, String activityType,
                       String description, Timestamp createdAt) {
        this.activityId   = activityId;
        this.activityType = activityType;
        this.description  = description;
        this.createdAt    = createdAt;
    }

    public int    getActivityId()               { return activityId; }
    public void   setActivityId(int id)         { this.activityId = id; }
    public String getActivityType()             { return activityType; }
    public void   setActivityType(String t)     { this.activityType = t; }
    public String getDescription()              { return description; }
    public void   setDescription(String d)      { this.description = d; }
    public Timestamp getCreatedAt()             { return createdAt; }
    public void      setCreatedAt(Timestamp t)  { this.createdAt = t; }

    @Override
    public String toString() {
        return "ActivityLog{id=" + activityId + ", type='" + activityType + "'}";
    }
}
