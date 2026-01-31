package com.methodreferences.hospital;
import java.util.List;

public class PatientService {

    // Method to print all patient IDs using a method reference
    public void printPatientIds(List<Patient> patients) {
        // Method reference to System.out.println
        patients.stream()
                .map(Patient::getId)  // Extract patient ID
                .forEach(System.out::println); // Print each ID
    }
}
