package com.dayten.avltree.hospitalqueuemanagementsystem;

import java.util.List;

public class HospitalQueue {

    private AVLTree tree = new AVLTree();

    // Scenario 1: Patient Registration
    public void registerPatient(String name, String checkInTime) {
        Patient patient = new Patient(name, checkInTime);
        tree.insert(patient);
        System.out.println("✅ Patient registered: " + patient);
    }

    // Scenario 2: Discharge/Delete Record
    public void dischargePatient(String name, String checkInTime) {
        Patient patient = new Patient(name, checkInTime);
        tree.remove(patient);
        System.out.println("✅ Patient discharged (if existed): " + patient);
    }

    // Scenario 3: Display by Arrival Time
    public void showPatients() {
        List<Patient> patients = tree.getPatientsInOrder();
        if (patients.isEmpty()) {
            System.out.println("No patients in queue.");
            return;
        }
        System.out.println("🩺 Patients in Arrival Order:");
        for (Patient p : patients) {
            System.out.println("- " + p);
        }
    }
}
