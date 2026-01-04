package com.dayone.hospitalpatientmanagementsystem;

import java.util.*;

public class Hospital {

    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add In-Patient");
            System.out.println("3. Add Out-Patient");
            System.out.println("4. Show All Doctors");
            System.out.println("5. Show All Patients");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    // Add Doctor
                    System.out.print("Enter Doctor ID: ");
                    int did = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor Name: ");
                    String dname = sc.nextLine();

                    System.out.print("Enter Specialization: ");
                    String spec = sc.nextLine();

                    doctors.add(new Doctor(did, dname, spec));
                    System.out.println("Doctor added successfully!");
                    break;

                case 2:
                    // Add InPatient
                    System.out.print("Enter Patient ID: ");
                    int ipid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String ipname = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int ipage = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Medical History: ");
                    String mh = sc.nextLine();

                    System.out.print("Enter Room Number: ");
                    String room = sc.nextLine();

                    System.out.print("Enter Days Admitted: ");
                    int days = sc.nextInt();

                    System.out.print("Enter Base Amount: ");
                    double base1 = sc.nextDouble();

                    System.out.print("Enter Tax %: ");
                    double tax1 = sc.nextDouble();

                    System.out.print("Enter Discount: ");
                    double disc1 = sc.nextDouble();

                    Bill bill1 = new Bill(base1, tax1, disc1);

                    patients.add(new InPatient(ipid, ipname, ipage, mh, room, days, bill1));
                    System.out.println("In-Patient added successfully!");
                    break;

                case 3:
                    // Add OutPatient
                    System.out.print("Enter Patient ID: ");
                    int opid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String opname = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int opage = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Visit Date: ");
                    String vdate = sc.nextLine();

                    System.out.print("Enter Consultation Fee: ");
                    double fee = sc.nextDouble();

                    System.out.print("Enter Tax %: ");
                    double tax2 = sc.nextDouble();

                    System.out.print("Enter Discount: ");
                    double disc2 = sc.nextDouble();

                    Bill bill2 = new Bill(fee, tax2, disc2);

                    patients.add(new OutPatient(opid, opname, opage, vdate, fee, bill2));
                    System.out.println("Out-Patient added successfully!");
                    break;

                case 4:
                    // Show Doctors
                    for (Doctor d : doctors) {
                        d.displayInfo();
                    }
                    break;

                case 5:
                    // Show Patients
                    for (Patient p : patients) {
                        p.displayInfo();   // Polymorphism
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using Hospital System!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
