package com.day1.eventtracker.model;

import java.time.LocalDateTime;

public class AuditEvent {

    private String className;
    private String methodName;
    private String action;
    private LocalDateTime timestamp;

    public AuditEvent(String className, String methodName, String action) {
        this.className = className;
        this.methodName = methodName;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public String getClassName() { return className; }
    public String getMethodName() { return methodName; }
    public String getAction() { return action; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
