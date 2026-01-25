package com.collections.annotations.repeatable;

import java.lang.reflect.Method;

public class RepeatableAnnotationDemo {

    public static void main(String[] args) throws Exception {

        Class<SoftwareModule> clazz = SoftwareModule.class;

        Method method = clazz.getDeclaredMethod("processData");

        // Get all BugReport annotations (repeatable)
        BugReport[] reports = method.getAnnotationsByType(BugReport.class);

        System.out.println("Bug Reports for method: " + method.getName());

        for (BugReport report : reports) {
            System.out.println("- " + report.description());
        }
    }
}
