package com.dayone.hospitalpatientmanagementsystem;

public class OutPatient extends Patient{
	
	private String visitDate;
	private double consultationFee;
	private Bill bill;
	
	
	// Normal patient
	public OutPatient(int patientId, String name, int age, String visitDate, double consultationFee, Bill bill) {
		super(patientId, name, age);
		this.visitDate = visitDate;
		this.consultationFee = consultationFee;
		this.bill = bill;
	}
	
	
	// Emergency patient
	public OutPatient(int patientId, String name, int age,String medicalHistory, String visitDate, double consultationFee, Bill bill) {
		super(patientId, name, age, medicalHistory);
		this.visitDate = visitDate;
		this.consultationFee = consultationFee;
		this.bill = bill;
	}


	public String getVisitDate() {
		return visitDate;
	}


	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}


	public double getConsultationFee() {
		return consultationFee;
	}


	public void setConsultationFee(double consultationFee) {
		this.consultationFee = consultationFee;
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
	    System.out.println("----- Out-Patient Details -----");
	    getSummary();   // from Patient
	    System.out.println("Visit Date        : " + visitDate);
	    System.out.println("Consultation Fee : " + consultationFee);
	    System.out.println("Total Bill       : " + bill.calculatePayment());
	}

	
	
	
	
}
