package com.encapsulation.hospitalmanagement;

public class InPatient extends Patient implements MedicalRecord {

    private int daysAdmitted;
    private double dailyCharge = 2000;

    public InPatient(String id, String name, int age, int days) {
        super(id, name, age);
        this.daysAdmitted = days;
    }

    @Override
    public double calculateBill() {
        return daysAdmitted * dailyCharge;
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
