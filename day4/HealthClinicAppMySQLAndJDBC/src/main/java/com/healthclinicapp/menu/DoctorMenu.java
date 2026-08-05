package com.healthclinicapp.menu;

import com.healthclinicapp.dao.DoctorDAO;
import com.healthclinicapp.dao.DepartmentDAO;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Doctor;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class DoctorMenu {

    private final DoctorDAO     dao     = new DoctorDAO();
    private final DepartmentDAO deptDao = new DepartmentDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("DOCTOR MANAGEMENT");
            System.out.println(ColorUtil.CYAN +
                "  1. Register New Doctor\n" +
                "  2. View All Doctors\n" +
                "  3. Search Doctor\n" +
                "  4. View Doctor Details\n" +
                "  5. Update Doctor\n" +
                "  6. Deactivate Doctor\n" +
                "  7. Doctors by Department\n" +
                "  8. Doctors with Min Appointments (HAVING)\n" +
                "  9. Avg Salary by Department (GROUP BY)\n" +
                "  10. Doctor Colleagues (Self Join)\n" +
                "  11. Doctors Above Average Salary (Subquery)\n" +
                "  12. All Doctors (UNION demo)\n" +
                "  13. Doctor Experience Ranking\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 13);
            switch (choice) {
                case 1  -> registerDoctor();
                case 2  -> listAll();
                case 3  -> search();
                case 4  -> viewDetails();
                case 5  -> updateDoctor();
                case 6  -> deactivate();
                case 7  -> byDepartment();
                case 8  -> minAppointments();
                case 9  -> avgSalary();
                case 10 -> colleagues();
                case 11 -> aboveAvgSalary();
                case 12 -> unionDemo();
                case 13 -> experienceRanking();
                case 0  -> { return; }
            }
        }
    }

    private void registerDoctor() {
        PrintUtil.subHeader("Register New Doctor");
        Doctor d = new Doctor();
        d.setFirstName     (InputUtil.readString("  First Name: "));
        d.setLastName      (InputUtil.readString("  Last Name: "));
        d.setSpecialization(InputUtil.readString("  Specialization: "));
        listDepts();
        d.setDepartmentId  (InputUtil.readInt("  Department ID: ", 1, 9999));
        d.setPhone         (InputUtil.readString("  Phone: "));
        d.setEmail         (InputUtil.readString("  Email: "));
        d.setSalary        (InputUtil.readDouble("  Monthly Salary: "));
        d.setJoinDate      (DateUtil.toSqlDate(InputUtil.readDate("  Join Date (yyyy-MM-dd): ")));
        try {
            int id = dao.insert(d);
            if (id > 0) PrintUtil.success("Doctor registered! ID: " + id);
            else        PrintUtil.error("Registration failed.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listAll() {
        try {
            List<Doctor> list = dao.findAll();
            PrintUtil.subHeader("All Active Doctors (" + list.size() + ")");
            String[] headers = {"ID","Name","Specialization","Department","Phone","Salary","Joined"};
            String[][] rows = new String[list.size()][7];
            for (int i=0;i<list.size();i++) {
                Doctor d = list.get(i);
                rows[i] = new String[]{
                    String.valueOf(d.getDoctorId()), "Dr. "+d.getFullName(),
                    d.getSpecialization(), d.getDepartmentName(),
                    d.getPhone(), String.format("%.0f",d.getSalary()),
                    String.valueOf(d.getJoinDate())
                };
            }
            PrintUtil.table(headers, rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void search() {
        String term = InputUtil.readString("  Search (name/specialization): ");
        try {
            List<Doctor> list = dao.search(term);
            if (list.isEmpty()) { PrintUtil.info("No doctors found."); return; }
            String[] headers = {"ID","Name","Specialization","Department","Phone"};
            String[][] rows = new String[list.size()][5];
            for (int i=0;i<list.size();i++) {
                Doctor d = list.get(i);
                rows[i] = new String[]{String.valueOf(d.getDoctorId()),"Dr. "+d.getFullName(),d.getSpecialization(),d.getDepartmentName(),d.getPhone()};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewDetails() {
        int id = InputUtil.readInt("  Doctor ID: ", 1, Integer.MAX_VALUE);
        try {
            Doctor d = dao.findById(id);
            if (d == null) { PrintUtil.error("Doctor not found."); return; }
            PrintUtil.subHeader("Doctor Details — ID " + id);
            PrintUtil.kv("Name",           "Dr. " + d.getFullName());
            PrintUtil.kv("Specialization", d.getSpecialization());
            PrintUtil.kv("Department",     d.getDepartmentName());
            PrintUtil.kv("Phone",          d.getPhone());
            PrintUtil.kv("Email",          d.getEmail());
            PrintUtil.kv("Salary",         "₹" + d.getSalary());
            PrintUtil.kv("Join Date",      String.valueOf(d.getJoinDate()));
            PrintUtil.kv("Status",         d.isActive()?"Active":"Inactive");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void updateDoctor() {
        int id = InputUtil.readInt("  Doctor ID to update: ", 1, Integer.MAX_VALUE);
        try {
            Doctor d = dao.findById(id);
            if (d == null) { PrintUtil.error("Doctor not found."); return; }
            String spec = InputUtil.readStringOptional("  Specialization [" + d.getSpecialization() + "]: ");
            String ph   = InputUtil.readStringOptional("  Phone ["          + d.getPhone() + "]: ");
            String em   = InputUtil.readStringOptional("  Email ["          + d.getEmail() + "]: ");
            if (!spec.isBlank()) d.setSpecialization(spec);
            if (!ph.isBlank())   d.setPhone(ph);
            if (!em.isBlank())   d.setEmail(em);
            dao.update(d);
            PrintUtil.success("Doctor updated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void deactivate() {
        int id = InputUtil.readInt("  Doctor ID: ", 1, Integer.MAX_VALUE);
        if (!InputUtil.confirm("  Deactivate Doctor #" + id + "?")) return;
        try {
            dao.deactivate(id);
            PrintUtil.success("Doctor deactivated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void byDepartment() {
        listDepts();
        int deptId = InputUtil.readInt("  Department ID: ", 1, 9999);
        try {
            List<Doctor> list = dao.findByDepartment(deptId);
            if (list.isEmpty()) { PrintUtil.info("No doctors in this department."); return; }
            String[] headers = {"ID","Name","Specialization","Phone"};
            String[][] rows = new String[list.size()][4];
            for (int i=0;i<list.size();i++) { Doctor d=list.get(i); rows[i]=new String[]{String.valueOf(d.getDoctorId()),"Dr. "+d.getFullName(),d.getSpecialization(),d.getPhone()}; }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void minAppointments() {
        int min = InputUtil.readInt("  Minimum completed appointments: ", 0, 9999);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getDoctorsWithMinAppointments(conn, min)) {
            PrintUtil.subHeader("Doctors with >= " + min + " completed appointments (GROUP BY + HAVING)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void avgSalary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getAvgSalaryByDepartment(conn)) {
            PrintUtil.subHeader("Average Salary by Department (GROUP BY + AVG)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void colleagues() {
        int id = InputUtil.readInt("  Doctor ID: ", 1, Integer.MAX_VALUE);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getColleagues(conn, id)) {
            PrintUtil.subHeader("Colleagues of Doctor #" + id + " (Self Join)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void aboveAvgSalary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getDoctorsAboveAvgSalary(conn)) {
            PrintUtil.subHeader("Doctors Above Average Salary (Subquery in WHERE)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void unionDemo() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getAllDoctorsUnion(conn)) {
            PrintUtil.subHeader("Active & Inactive Doctors (UNION ALL)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void experienceRanking() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getDoctorsByExperience(conn)) {
            PrintUtil.subHeader("Doctors Ranked by Experience (TIMESTAMPDIFF)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listDepts() {
        try {
            var depts = deptDao.findAll();
            System.out.println(ColorUtil.DIM + "  Available Departments:");
            depts.forEach(d -> System.out.println("    " + d.getDepartmentId() + ". " + d.getName()));
            System.out.print(ColorUtil.RESET);
        } catch (SQLException ignored) {}
    }
}
