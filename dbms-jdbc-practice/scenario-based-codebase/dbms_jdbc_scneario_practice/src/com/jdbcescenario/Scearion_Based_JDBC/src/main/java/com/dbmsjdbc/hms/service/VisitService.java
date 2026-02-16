package com.dbmsjdbc.hms.service;

import com.dbmsjdbc.hms.dao.VisitDAO;
import com.dbmsjdbc.hms.model.Visit;



import java.util.Scanner;

public class VisitService {

    private Scanner sc = new Scanner(System.in);
    private VisitDAO visitDAO = new VisitDAO();

    // ================= RECORD VISIT =================
    public void recordVisit() {

        System.out.print("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();

        System.out.print("Diagnosis: ");
        String diagnosis = sc.nextLine();

        System.out.print("Notes: ");
        String notes = sc.nextLine();

        visitDAO.recordVisit(appointmentId, diagnosis, notes);
    }

    // ================= VIEW MEDICAL HISTORY =================
    public void viewMedicalHistory() {

        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();

        visitDAO.viewMedicalHistory(patientId);
    }
}

