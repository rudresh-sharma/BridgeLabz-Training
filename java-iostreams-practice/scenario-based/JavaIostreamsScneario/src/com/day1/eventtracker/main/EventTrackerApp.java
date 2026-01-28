package com.day1.eventtracker.main;

import com.day1.eventtracker.model.AuditEvent;
import com.day1.eventtracker.scanner.AuditScanner;
import com.day1.eventtracker.service.UserActionService;
import com.day1.eventtracker.util.JsonLogger;

import java.util.List;

public class EventTrackerApp {

    public static void main(String[] args) {

        System.out.println("🛡️ EventTracker – Auto Audit System");
        System.out.println("=================================");

        List<AuditEvent> events =
                AuditScanner.scan(UserActionService.class);

        JsonLogger.writeLogs(events, "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\scenario-based\\JavaIostreamsScneario\\src\\com\\day1\\eventtracker\\audit_log.json");
    }
}
