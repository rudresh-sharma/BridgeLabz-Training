package com.collections.annotations.repeatable;

public class SoftwareModule {

    @BugReport(description = "NullPointerException on line 25")
    @BugReport(description = "Performance issue when input is large")
    public void processData() {
        System.out.println("Processing data...");
    }
}
