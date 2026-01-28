package com.day1.eventtracker.scanner;

import com.day1.eventtracker.annotation.AuditTrail;
import com.day1.eventtracker.model.AuditEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AuditScanner {

    public static List<AuditEvent> scan(Class<?> clazz) {

        List<AuditEvent> events = new ArrayList<>();

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(AuditTrail.class)) {

                AuditTrail audit = method.getAnnotation(AuditTrail.class);

                AuditEvent event = new AuditEvent(
                        clazz.getSimpleName(),
                        method.getName(),
                        audit.action()
                );

                events.add(event);
            }
        }
        return events;
    }
}
