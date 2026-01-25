/*
 * Hospital Triage System
Simulate a hospital triage system using a PriorityQueue where patients with higher severity are treated first.
Example:
Patients: [("John", 3), ("Alice", 5), ("Bob", 2)] → Order: Alice, John, Bob.


 */


package com.collections.queueinterface;
import java.util.PriorityQueue;
import java.util.Scanner;

// Patient class
class Patient {
    String name;
    int severity;

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }
}

public class HospitalTriageSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // PriorityQueue: higher severity treated first
        PriorityQueue<Patient> pq = new PriorityQueue<>(
                (p1, p2) -> p2.severity - p1.severity
        );

        System.out.print("Enter number of patients: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter patient name: ");
            String name = sc.nextLine();

            System.out.print("Enter severity level: ");
            int severity = sc.nextInt();
            sc.nextLine(); // consume newline

            pq.add(new Patient(name, severity));
        }

        System.out.println("\nTreatment Order:");
        while (!pq.isEmpty()) {
            Patient p = pq.poll();
            System.out.println(p.name + " (Severity: " + p.severity + ")");
        }

        sc.close();
    }
}
