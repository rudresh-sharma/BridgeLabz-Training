package com.dayone.hospitalpatientmanagementsystem;

public class InPatient extends Patient {

    private String roomNumber;
    private int dayAdmitted;
    private Bill bill;

    // Normal InPatient constructor
    public InPatient(int patientId, String name, int age,
                     String roomNumber, int dayAdmitted, Bill bill) {

        super(patientId, name, age);
        this.roomNumber = roomNumber;
        this.dayAdmitted = dayAdmitted;
        this.bill = bill;
    }

    // Emergency InPatient constructor
    public InPatient(int patientId, String name, int age, String medicalHistory,
                     String roomNumber, int dayAdmitted, Bill bill) {

        super(patientId, name, age, medicalHistory);
        this.roomNumber = roomNumber;
        this.dayAdmitted = dayAdmitted;
        this.bill = bill;
    }

    // Getters & Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getDayAdmitted() {
        return dayAdmitted;
    }

    public void setDayAdmitted(int dayAdmitted) {
        this.dayAdmitted = dayAdmitted;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    // Polymorphism
    @Override
    public void displayInfo() {
        System.out.println("----- In-Patient Details -----");
        getSummary();   // from Patient
        System.out.println("Room Number   : " + roomNumber);
        System.out.println("Days Admitted : " + dayAdmitted);
        System.out.println("Total Bill    : " + bill.calculatePayment());
    }
}
