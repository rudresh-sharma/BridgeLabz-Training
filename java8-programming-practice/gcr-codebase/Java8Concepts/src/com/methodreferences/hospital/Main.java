package com.methodreferences.hospital;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("P1001", "John Doe"));
        patients.add(new Patient("P1002", "Jane Smith"));
        patients.add(new Patient("P1003", "Alice Johnson"));
        patients.add(new Patient("P1004", "Bob Williams"));

        PatientService service = new PatientService();
        System.out.println("Patient IDs:");
        service.printPatientIds(patients);
    }
}
