package com.encapsulation.hospitalmanagement;

public class OutPatient extends Patient implements MedicalRecord {

    private double consultationFee = 500;

    public OutPatient(String id, String name, int age) {
        super(id, name, age);
    }

    @Override
    public double calculateBill() {
        return consultationFee;
    }

    @Override
    public void addRecord(String record) {
        addToHistory(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History:");
        for (String r : getHistory()) {
            System.out.println("- " + r);
        }
    }
}
