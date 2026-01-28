package com.day1.eventtracker.util;

import com.day1.eventtracker.model.AuditEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.List;

public class JsonLogger {

    public static void writeLogs(List<AuditEvent> events, String fileName) {

        try {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.registerModule(new JavaTimeModule());

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(fileName), events);

            System.out.println("✅ Audit log generated: " + fileName);

        } catch (Exception e) {
            System.out.println("❌ Error writing audit log: " + e.getMessage());
        }
    }
}
