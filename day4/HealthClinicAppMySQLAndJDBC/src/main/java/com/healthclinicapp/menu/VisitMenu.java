package com.healthclinicapp.menu;

import com.healthclinicapp.dao.*;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.*;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitMenu {

    private final VisitDAO         visitDao  = new VisitDAO();
    private final PrescriptionDAO  prescDao  = new PrescriptionDAO();
    private final LabDAO           labDao    = new LabDAO();
    private final MedicineDAO      medDao    = new MedicineDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("VISIT MANAGEMENT");
            System.out.println(ColorUtil.CYAN +
                "  1. Record New Visit\n" +
                "  2. View Visit Details\n" +
                "  3. Patient Visit History\n" +
                "  4. All Visits (Paginated)\n" +
                "  5. Update Visit\n" +
                "  6. Add Prescription to Visit\n" +
                "  7. View Prescription\n" +
                "  8. Add Lab Report to Visit\n" +
                "  9. View Lab Reports for Visit\n" +
                "  10. Overdue Follow-Ups\n" +
                "  11. Daily Visit Summary\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 11);
            switch (choice) {
                case 1  -> recordVisit();
                case 2  -> viewVisit();
                case 3  -> patientHistory();
                case 4  -> listAll();
                case 5  -> updateVisit();
                case 6  -> addPrescription();
                case 7  -> viewPrescription();
                case 8  -> addLabReport();
                case 9  -> viewLabReports();
                case 10 -> overdueFollowUps();
                case 11 -> dailySummary();
                case 0  -> { return; }
            }
        }
    }

    private void recordVisit() {
        PrintUtil.subHeader("Record New Visit");
        int apptId = InputUtil.readInt("  Appointment ID: ", 1, Integer.MAX_VALUE);
        Visit v = new Visit();
        v.setAppointmentId(apptId);
        v.setVisitDate    (DateUtil.toSqlDate(InputUtil.readDate("  Visit Date (yyyy-MM-dd): ")));
        v.setSymptoms     (InputUtil.readString("  Symptoms: "));
        v.setDiagnosis    (InputUtil.readString("  Diagnosis: "));
        v.setTreatment    (InputUtil.readString("  Treatment: "));
        v.setWeight       (InputUtil.readDouble("  Weight (kg): "));
        v.setBloodPressure(InputUtil.readString("  Blood Pressure (e.g. 120/80): "));
        v.setTemperature  (InputUtil.readDouble("  Temperature (°C): "));
        String fu = InputUtil.readDate("  Follow-Up Date (yyyy-MM-dd, Enter to skip): ");
        if (!fu.isBlank()) v.setFollowUpDate(DateUtil.toSqlDate(fu));
        v.setNotes(InputUtil.readString("  Notes: "));
        try {
            int id = visitDao.insert(v);
            PrintUtil.success("Visit recorded! ID: " + id);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewVisit() {
        int id = InputUtil.readInt("  Visit ID: ", 1, Integer.MAX_VALUE);
        try {
            Visit v = visitDao.findById(id);
            if (v==null) { PrintUtil.error("Visit not found."); return; }
            PrintUtil.subHeader("Visit #" + id);
            PrintUtil.kv("Patient",        v.getPatientName());
            PrintUtil.kv("Doctor",         v.getDoctorName());
            PrintUtil.kv("Date",           String.valueOf(v.getVisitDate()));
            PrintUtil.kv("Symptoms",       v.getSymptoms());
            PrintUtil.kv("Diagnosis",      v.getDiagnosis());
            PrintUtil.kv("Treatment",      v.getTreatment());
            PrintUtil.kv("BP",             v.getBloodPressure());
            PrintUtil.kv("Temp",           v.getTemperature() + " °C");
            PrintUtil.kv("Weight",         v.getWeight() + " kg");
            PrintUtil.kv("Follow-Up",      v.getFollowUpDate()!=null?String.valueOf(v.getFollowUpDate()):"None");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void patientHistory() {
        int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        try {
            List<Visit> list = visitDao.findByPatient(pid);
            PrintUtil.subHeader("Visit History for Patient #" + pid + " (" + list.size() + " visits)");
            if (list.isEmpty()) { PrintUtil.info("No visits found."); return; }
            String[] headers = {"Visit ID","Date","Diagnosis","Follow-Up"};
            String[][] rows = new String[list.size()][4];
            for (int i=0;i<list.size();i++) {
                Visit v = list.get(i);
                rows[i] = new String[]{String.valueOf(v.getVisitId()),String.valueOf(v.getVisitDate()),v.getDiagnosis(),v.getFollowUpDate()!=null?String.valueOf(v.getFollowUpDate()):"None"};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listAll() {
        int page=1; int size=10;
        while (true) {
            try {
                List<Visit> list = visitDao.findAll(page,size);
                PrintUtil.subHeader("All Visits — Page " + page);
                if (list.isEmpty()) { PrintUtil.info("No visits."); break; }
                String[] headers={"ID","Date","Patient","Doctor","Diagnosis"};
                String[][] rows=new String[list.size()][5];
                for (int i=0;i<list.size();i++) { Visit v=list.get(i); rows[i]=new String[]{String.valueOf(v.getVisitId()),String.valueOf(v.getVisitDate()),v.getPatientName(),v.getDoctorName(),v.getDiagnosis()}; }
                PrintUtil.table(headers,rows);
                String nav = InputUtil.readString("  [N]ext [P]rev [0]Back: ").trim().toLowerCase();
                if (nav.equals("n")) page++; else if (nav.equals("p") && page>1) page--; else break;
            } catch (SQLException e) { PrintUtil.error(e.getMessage()); break; }
        }
    }

    private void updateVisit() {
        int id = InputUtil.readInt("  Visit ID to update: ", 1, Integer.MAX_VALUE);
        try {
            Visit v = visitDao.findById(id);
            if (v==null) { PrintUtil.error("Not found."); return; }
            String diag  = InputUtil.readStringOptional("  Diagnosis [" + v.getDiagnosis() + "]: ");
            String treat = InputUtil.readStringOptional("  Treatment [" + v.getTreatment() + "]: ");
            String notes = InputUtil.readStringOptional("  Notes: ");
            if (!diag.isBlank())  v.setDiagnosis(diag);
            if (!treat.isBlank()) v.setTreatment(treat);
            if (!notes.isBlank()) v.setNotes(notes);
            visitDao.update(v);
            PrintUtil.success("Visit updated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void addPrescription() {
        int visitId = InputUtil.readInt("  Visit ID: ", 1, Integer.MAX_VALUE);
        Prescription p = new Prescription();
        p.setVisitId       (visitId);
        p.setPrescribedDate(DateUtil.toSqlDate(InputUtil.readDate("  Prescribed Date (yyyy-MM-dd): ")));
        p.setInstructions  (InputUtil.readString("  Instructions: "));
        try {
            int pid = prescDao.insertPrescription(p);
            PrintUtil.success("Prescription created! ID: " + pid);
            // Add items
            while (true) {
                if (!InputUtil.confirm("  Add medicine to prescription?")) break;
                PrescriptionItem item = new PrescriptionItem();
                item.setPrescriptionId(pid);
                item.setMedicineId   (InputUtil.readInt   ("    Medicine ID: ", 1, Integer.MAX_VALUE));
                item.setDosage       (InputUtil.readString ("    Dosage (e.g. 500mg): "));
                item.setFrequency    (InputUtil.readString ("    Frequency (e.g. Twice daily): "));
                item.setDurationDays (InputUtil.readInt   ("    Duration (days): ", 1, 365));
                item.setQuantity     (InputUtil.readInt   ("    Quantity: ", 1, 9999));
                prescDao.insertItem(item);
                PrintUtil.success("  Medicine added.");
            }
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewPrescription() {
        int visitId = InputUtil.readInt("  Visit ID: ", 1, Integer.MAX_VALUE);
        try {
            Prescription p = prescDao.findByVisit(visitId);
            if (p==null) { PrintUtil.info("No prescription for this visit."); return; }
            PrintUtil.subHeader("Prescription #" + p.getPrescriptionId());
            PrintUtil.kv("Date", String.valueOf(p.getPrescribedDate()));
            PrintUtil.kv("Instructions", p.getInstructions());
            List<PrescriptionItem> items = prescDao.getItemsByPrescription(p.getPrescriptionId());
            String[] headers={"Medicine","Dosage","Frequency","Duration","Qty"};
            String[][] rows=new String[items.size()][5];
            for (int i=0;i<items.size();i++) { PrescriptionItem it=items.get(i); rows[i]=new String[]{it.getMedicineName(),it.getDosage(),it.getFrequency(),it.getDurationDays()+" days",String.valueOf(it.getQuantity())}; }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void addLabReport() {
        int visitId = InputUtil.readInt("  Visit ID: ", 1, Integer.MAX_VALUE);
        LabReport r = new LabReport();
        r.setVisitId (visitId);
        r.setTestId  (InputUtil.readInt   ("  Lab Test ID: ", 1, Integer.MAX_VALUE));
        r.setTestDate(DateUtil.toSqlDate(InputUtil.readDate("  Test Date (yyyy-MM-dd): ")));
        r.setResult  (InputUtil.readString("  Result: "));
        String yn = InputUtil.readOption("  Is Normal?", new String[]{"Yes","No"});
        r.setIsNormal("Yes".equals(yn));
        r.setRemarks (InputUtil.readString("  Remarks: "));
        try {
            int id = labDao.insertReport(r);
            PrintUtil.success("Lab report added! ID: " + id);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewLabReports() {
        int visitId = InputUtil.readInt("  Visit ID: ", 1, Integer.MAX_VALUE);
        try {
            List<LabReport> list = labDao.findByVisit(visitId);
            if (list.isEmpty()) { PrintUtil.info("No lab reports for this visit."); return; }
            PrintUtil.subHeader("Lab Reports for Visit #" + visitId);
            String[] headers={"Report ID","Test","Date","Result","Normal?","Remarks"};
            String[][] rows=new String[list.size()][6];
            for (int i=0;i<list.size();i++) { LabReport r=list.get(i); rows[i]=new String[]{String.valueOf(r.getReportId()),r.getTestName(),String.valueOf(r.getTestDate()),r.getResult(),r.getIsNormal()!=null?(r.getIsNormal()?"Yes":"No"):"N/A",r.getRemarks()}; }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void overdueFollowUps() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = visitDao.getFollowUpsOverdue(conn)) {
            PrintUtil.subHeader("Overdue Follow-Ups");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void dailySummary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = visitDao.getDailySummary(conn)) {
            PrintUtil.subHeader("Daily Visit Summary (View: v_daily_visits)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }
}
