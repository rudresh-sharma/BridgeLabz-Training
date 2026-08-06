package com.healthclinicapp.menu;

import com.healthclinicapp.dao.PatientDAO;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Patient;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class PatientMenu {

    private final PatientDAO dao = new PatientDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("PATIENT MANAGEMENT");
            System.out.println(ColorUtil.BOLD_WHITE +
                "  1. Register New Patient\n" +
                "  2. View All Patients (Paginated)\n" +
                "  3. Search Patient (Name / Phone / Email)\n" +
                "  4. View Patient Details\n" +
                "  5. Update Patient\n" +
                "  6. Deactivate Patient\n" +
                "  7. Register Patient with Emergency Contact\n" +
                "  8. Patients with No Appointments\n" +
                "  9. Filter by Blood Group\n" +
                "  10. Filter by Age Range\n" +
                "  11. Filter by City\n" +
                "  12. Gender Statistics\n" +
                "  13. Age Distribution\n" +
                "  14. Top Debtors\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 14);
            switch (choice) {
                case 1  -> registerPatient();
                case 2  -> listAll();
                case 3  -> search();
                case 4  -> viewDetails();
                case 5  -> updatePatient();
                case 6  -> deactivate();
                case 7  -> registerWithContact();
                case 8  -> noAppointments();
                case 9  -> filterBloodGroup();
                case 10 -> filterAge();
                case 11 -> filterCity();
                case 12 -> genderStats();
                case 13 -> ageDistribution();
                case 14 -> topDebtors();
                case 0  -> { return; }
            }
        }
    }

    private void registerPatient() {
        System.out.println(ColorUtil.BOLD_YELLOW + "\n  ── Register New Patient ──" + ColorUtil.RESET);
        Patient p = new Patient();
        p.setFirstName  (InputUtil.readString("  First Name: "));
        p.setLastName   (InputUtil.readString("  Last Name: "));
        p.setDateOfBirth(DateUtil.toSqlDate(InputUtil.readDate("  Date of Birth (yyyy-MM-dd): ")));
        p.setGender     (InputUtil.readOption("  Gender", new String[]{"Male", "Female", "Other"}));
        p.setBloodGroup (InputUtil.readString("  Blood Group (A+/B-/O+/AB+ etc, or press Enter to skip): "));
        p.setPhone      (InputUtil.readString("  Phone (10 digits): "));
        p.setEmail      (InputUtil.readString("  Email (or Enter to skip): "));
        p.setAddress    (InputUtil.readString("  Address: "));
        p.setCity       (InputUtil.readString("  City: "));
        try {
            int id = dao.insert(p);
            if (id > 0) PrintUtil.success("Patient registered! ID: " + id);
            else        PrintUtil.error("Failed to register patient.");
        } catch (SQLException e) {
            PrintUtil.error("DB Error: " + e.getMessage());
        }
    }

    private void listAll() {
        int page = 1; int size = 10;
        while (true) {
            try {
                List<Patient> patients = dao.findAll(page, size);
                int total = dao.countAll();
                int totalPages = (int) Math.ceil((double) total / size);
                PrintUtil.subHeader("Patients — Page " + page + " of " + totalPages + " (Total: " + total + ")");
                String[] headers = {"ID","Name","Gender","Blood","Phone","City","Status"};
                String[][] rows = new String[patients.size()][7];
                for (int i=0;i<patients.size();i++) {
                    Patient p = patients.get(i);
                    rows[i] = new String[]{
                        String.valueOf(p.getPatientId()), p.getFullName(), p.getGender(),
                        p.getBloodGroup(), p.getPhone(), p.getCity(),
                        p.isActive()?"Active":"Inactive"
                    };
                }
                PrintUtil.table(headers, rows);
                System.out.println("  [N]ext  [P]rev  [0]Back");
                String nav = InputUtil.readString("  Navigation: ").trim().toLowerCase();
                if      (nav.equals("n") && page < totalPages) page++;
                else if (nav.equals("p") && page > 1)          page--;
                else if (nav.equals("0"))                       break;
            } catch (SQLException e) { PrintUtil.error(e.getMessage()); break; }
        }
    }

    private void search() {
        String term = InputUtil.readString("  Search (name/phone/email): ");
        try {
            List<Patient> list = dao.search(term);
            if (list.isEmpty()) { PrintUtil.info("No patients found."); return; }
            String[] headers = {"ID","Name","Phone","Email","City"};
            String[][] rows = new String[list.size()][5];
            for (int i=0;i<list.size();i++) {
                Patient p = list.get(i);
                rows[i] = new String[]{String.valueOf(p.getPatientId()),p.getFullName(),p.getPhone(),p.getEmail(),p.getCity()};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewDetails() {
        int id = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        try {
            Patient p = dao.findById(id);
            if (p == null) { PrintUtil.error("Patient not found."); return; }
            PrintUtil.subHeader("Patient Details — ID " + id);
            PrintUtil.kv("ID",           String.valueOf(p.getPatientId()));
            PrintUtil.kv("Name",         p.getFullName());
            PrintUtil.kv("DOB",          String.valueOf(p.getDateOfBirth()));
            PrintUtil.kv("Gender",       p.getGender());
            PrintUtil.kv("Blood Group",  p.getBloodGroup());
            PrintUtil.kv("Phone",        p.getPhone());
            PrintUtil.kv("Email",        p.getEmail());
            PrintUtil.kv("Address",      p.getAddress());
            PrintUtil.kv("City",         p.getCity());
            PrintUtil.kv("Status",       p.isActive()?"Active":"Inactive");
            PrintUtil.kv("Registered",   String.valueOf(p.getRegisteredAt()));
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void updatePatient() {
        int id = InputUtil.readInt("  Patient ID to update: ", 1, Integer.MAX_VALUE);
        try {
            Patient p = dao.findById(id);
            if (p == null) { PrintUtil.error("Patient not found."); return; }
            System.out.println("  (Press Enter to keep existing value)");
            String fn = InputUtil.readStringOptional("  First Name [" + p.getFirstName() + "]: ");
            String ln = InputUtil.readStringOptional("  Last Name ["  + p.getLastName()  + "]: ");
            String ph = InputUtil.readStringOptional("  Phone ["      + p.getPhone()     + "]: ");
            String em = InputUtil.readStringOptional("  Email ["      + p.getEmail()     + "]: ");
            String ad = InputUtil.readStringOptional("  Address ["    + p.getAddress()   + "]: ");
            String ci = InputUtil.readStringOptional("  City ["       + p.getCity()      + "]: ");
            if (!fn.isBlank()) p.setFirstName(fn);
            if (!ln.isBlank()) p.setLastName (ln);
            if (!ph.isBlank()) p.setPhone    (ph);
            if (!em.isBlank()) p.setEmail    (em);
            if (!ad.isBlank()) p.setAddress  (ad);
            if (!ci.isBlank()) p.setCity     (ci);
            boolean ok = dao.update(p);
            if (ok) PrintUtil.success("Patient updated.");
            else    PrintUtil.error("Update failed.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void deactivate() {
        int id = InputUtil.readInt("  Patient ID to deactivate: ", 1, Integer.MAX_VALUE);
        if (!InputUtil.confirm("  Deactivate patient #" + id + "?")) return;
        try {
            boolean ok = dao.deactivate(id);
            if (ok) PrintUtil.success("Patient deactivated."); else PrintUtil.error("Failed.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void registerWithContact() {
        System.out.println(ColorUtil.BOLD_YELLOW + "\n  ── Register Patient with Emergency Contact (Savepoint Demo) ──" + ColorUtil.RESET);
        Patient p = new Patient();
        p.setFirstName  (InputUtil.readString("  First Name: "));
        p.setLastName   (InputUtil.readString("  Last Name: "));
        p.setDateOfBirth(DateUtil.toSqlDate(InputUtil.readDate("  DOB (yyyy-MM-dd): ")));
        p.setGender     (InputUtil.readOption("  Gender", new String[]{"Male","Female","Other"}));
        p.setPhone      (InputUtil.readString("  Phone: "));
        String cName = InputUtil.readString("  Emergency Contact Name (Enter to skip): ");
        String cRel  = cName.isBlank() ? "" : InputUtil.readString("  Relationship: ");
        String cPh   = cName.isBlank() ? "" : InputUtil.readString("  Contact Phone: ");
        try {
            int id = dao.insertWithEmergencyContact(p, cName.isBlank()?null:cName, cRel, cPh);
            PrintUtil.success("Patient registered! ID: " + id);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void noAppointments() {
        try {
            List<Patient> list = dao.findPatientsWithNoAppointments();
            PrintUtil.subHeader("Patients with NO Appointments (NOT EXISTS demo)");
            if (list.isEmpty()) { PrintUtil.info("All patients have appointments."); return; }
            String[] headers = {"ID","Name","Phone","Registered"};
            String[][] rows = new String[list.size()][4];
            for (int i=0;i<list.size();i++) {
                Patient p = list.get(i);
                rows[i] = new String[]{String.valueOf(p.getPatientId()),p.getFullName(),p.getPhone(),String.valueOf(p.getRegisteredAt())};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void filterBloodGroup() {
        String bg = InputUtil.readString("  Blood Group (e.g. A+): ");
        try {
            List<Patient> list = dao.findByBloodGroups(List.of(bg));
            showSimpleList(list);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void filterAge() {
        int min = InputUtil.readInt("  Min Age: ", 0, 150);
        int max = InputUtil.readInt("  Max Age: ", min, 150);
        try { showSimpleList(dao.findByAgeRange(min,max)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void filterCity() {
        String city = InputUtil.readString("  City name: ");
        try { showSimpleList(dao.findByCity(city)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void genderStats() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getGenderStats(conn)) {
            PrintUtil.subHeader("Patient Gender Statistics");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void ageDistribution() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getAgeDistribution(conn)) {
            PrintUtil.subHeader("Patient Age Distribution");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void topDebtors() {
        int limit = InputUtil.readInt("  Top N patients: ", 1, 50);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getTopDebtors(conn, limit)) {
            PrintUtil.subHeader("Top Debtors by Outstanding Balance");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void showSimpleList(List<Patient> list) {
        if (list.isEmpty()) { PrintUtil.info("No patients found."); return; }
        String[] headers = {"ID","Name","Gender","Blood","Phone","City"};
        String[][] rows = new String[list.size()][6];
        for (int i=0;i<list.size();i++) {
            Patient p = list.get(i);
            rows[i] = new String[]{String.valueOf(p.getPatientId()),p.getFullName(),p.getGender(),p.getBloodGroup(),p.getPhone(),p.getCity()};
        }
        PrintUtil.table(headers,rows);
    }
}
