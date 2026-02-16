package com.dbmsjdbc.hms;



import com.dbmsjdbc.hms.dao.*;
import com.dbmsjdbc.hms.model.*;
import com.dbmsjdbc.hms.service.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;




public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO();
    private static AppointmentService appointmentService = new AppointmentService();
    private static VisitService visitService = new VisitService();
    private static BillingDAO billingDAO = new BillingDAO();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Visit & Medical Records");
            System.out.println("5. Billing & Payments");
            System.out.println("6. Exit");
            System.out.print("Choose Option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> appointmentMenu();
                case 4 -> visitMenu();
                case 5 -> billingMenu();
                case 6 -> {
                    System.out.println("Exiting System...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Option!");
            }
        }
    }

    // ================= PATIENT MENU =================
    private static void patientMenu() {
        while (true) {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> patientDAO.registerPatient();
                case 2 -> patientDAO.searchPatient();
                case 3 -> patientDAO.updatePatient();
                case 4 -> { return; }
                default -> System.out.println("Invalid Option!");
            }
        }
    }

    // ================= DOCTOR MENU =================
    private static void doctorMenu() {
        while (true) {
            System.out.println("\n--- Doctor Management ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Deactivate Doctor");
            System.out.println("4. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> doctorDAO.addDoctor();
                case 2 -> doctorDAO.viewDoctors();
                case 3 -> doctorDAO.deactivateDoctor();
                case 4 -> { return; }
                default -> System.out.println("Invalid Option!");
            }
        }
    }

    // ================= APPOINTMENT MENU =================
    private static void appointmentMenu() {
        while (true) {
            System.out.println("\n--- Appointment Management ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> appointmentService.bookAppointment();
                case 2 -> appointmentService.cancelAppointment();
                case 3 -> { return; }
                default -> System.out.println("Invalid Option!");
            }
        }
    }

    // ================= VISIT MENU =================
    private static void visitMenu() {
        while (true) {
            System.out.println("\n--- Visit & Medical Records ---");
            System.out.println("1. Record Visit");
            System.out.println("2. View Medical History");
            System.out.println("3. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> visitService.recordVisit();
                case 2 -> visitService.viewMedicalHistory();
                case 3 -> { return; }
                default -> System.out.println("Invalid Option!");
            }
        }
    }

    // ================= BILLING MENU =================
    private static void billingMenu() {
        while (true) {
            System.out.println("\n--- Billing & Payments ---");
            System.out.println("1. Generate Bill");
            System.out.println("2. Record Payment");
            System.out.println("3. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> billingDAO.generateBill();
                case 2 -> billingDAO.recordPayment();
                case 3 -> { return; }
                default -> System.out.println("Invalid Option!");
            }
        }
    }
}
