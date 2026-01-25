package com.dayten.avltree.hospitalqueuemanagementsystem;

import java.util.Scanner;

public class HospitalQueueDemo {

    public static void main(String[] args) {

        HospitalQueue queue = new HospitalQueue();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println("🏥 Welcome to Hospital Queue Management System");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️⃣ Register Patient");
            System.out.println("2️⃣ Discharge Patient");
            System.out.println("3️⃣ Display Patients by Arrival Time");
            System.out.println("0️⃣ Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter check-in time (yyyy-MM-dd HH:mm): ");
                    String time = sc.nextLine();
                    queue.registerPatient(name, time);
                    break;

                case 2:
                    System.out.print("Enter patient name to discharge: ");
                    String dischargeName = sc.nextLine();
                    System.out.print("Enter check-in time (yyyy-MM-dd HH:mm): ");
                    String dischargeTime = sc.nextLine();
                    queue.dischargePatient(dischargeName, dischargeTime);
                    break;

                case 3:
                    queue.showPatients();
                    break;

                case 0:
                    System.out.println("👋 Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
