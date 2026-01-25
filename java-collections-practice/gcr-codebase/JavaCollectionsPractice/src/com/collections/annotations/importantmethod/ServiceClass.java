package com.collections.annotations.importantmethod;

public class ServiceClass {

    @ImportantMethod   // uses default level = "HIGH"
    public void processPayment() {
        System.out.println("Processing payment...");
    }

    @ImportantMethod(level = "MEDIUM")
    public void generateReport() {
        System.out.println("Generating report...");
    }

    public void normalMethod() {
        System.out.println("This is a normal method.");
    }
}
