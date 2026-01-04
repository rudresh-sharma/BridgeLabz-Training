package com.encapsulation.hospitalmanagement;

import java.util.ArrayList;

public class HospitalMain {
    public static void main(String[] args) {

        ArrayList<Patient> patients = new ArrayList<>();

        Patient p1 = new InPatient("P101", "Rudresh", 22, 5);
        Patient p2 = new OutPatient("P201", "Amit", 30);

        patients.add(p1);
        patients.add(p2);

        ((MedicalRecord)p1).addRecord("Fever");
        ((MedicalRecord)p1).addRecord("Blood Test");

        ((MedicalRecord)p2).addRecord("Cold");

        for (Patient p : patients) {
            System.out.println("-----------------------");
            p.getPatientDetails();
            System.out.println("Bill Amount: " + p.calculateBill());

            MedicalRecord m = (MedicalRecord) p;
            m.viewRecords();
        }
    }
}
