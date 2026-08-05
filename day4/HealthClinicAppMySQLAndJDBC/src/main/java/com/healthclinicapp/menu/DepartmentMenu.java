package com.healthclinicapp.menu;

import com.healthclinicapp.dao.DepartmentDAO;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Department;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class DepartmentMenu {

    private final DepartmentDAO dao = new DepartmentDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("DEPARTMENT MANAGEMENT");
            System.out.println(ColorUtil.CYAN +
                "  1. Add Department\n" +
                "  2. View All Departments\n" +
                "  3. View Department Details\n" +
                "  4. Update Department\n" +
                "  5. Set Head Doctor\n" +
                "  6. Delete Department\n" +
                "  7. Department Statistics\n" +
                "  8. Doctor Count per Department (GROUP BY)\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 8);
            switch (choice) {
                case 1 -> add();
                case 2 -> listAll();
                case 3 -> viewOne();
                case 4 -> update();
                case 5 -> setHead();
                case 6 -> delete();
                case 7 -> statistics();
                case 8 -> doctorCount();
                case 0 -> { return; }
            }
        }
    }

    private void add() {
        PrintUtil.subHeader("Add Department");
        Department d = new Department();
        d.setName       (InputUtil.readString("  Department Name: "));
        d.setDescription(InputUtil.readString("  Description: "));
        try {
            int id = dao.insert(d);
            PrintUtil.success("Department added! ID: " + id);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listAll() {
        try {
            List<Department> list = dao.findAll();
            PrintUtil.subHeader("All Departments (" + list.size() + ")");
            String[] headers = {"ID","Name","Description","Head Doctor ID"};
            String[][] rows = new String[list.size()][4];
            for (int i=0;i<list.size();i++) {
                Department d = list.get(i);
                rows[i]=new String[]{String.valueOf(d.getDepartmentId()),d.getName(),d.getDescription(),d.getHeadDoctorId()==null?"None":String.valueOf(d.getHeadDoctorId())};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewOne() {
        int id = InputUtil.readInt("  Department ID: ", 1, Integer.MAX_VALUE);
        try {
            Department d = dao.findById(id);
            if (d == null) { PrintUtil.error("Not found."); return; }
            PrintUtil.kv("ID",   String.valueOf(d.getDepartmentId()));
            PrintUtil.kv("Name", d.getName());
            PrintUtil.kv("Desc", d.getDescription());
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void update() {
        int id = InputUtil.readInt("  Department ID: ", 1, Integer.MAX_VALUE);
        try {
            Department d = dao.findById(id);
            if (d==null) { PrintUtil.error("Not found."); return; }
            String name = InputUtil.readStringOptional("  Name [" + d.getName() + "]: ");
            String desc = InputUtil.readStringOptional("  Description: ");
            if (!name.isBlank()) d.setName(name);
            if (!desc.isBlank()) d.setDescription(desc);
            dao.update(d);
            PrintUtil.success("Updated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void setHead() {
        int deptId   = InputUtil.readInt("  Department ID: ", 1, Integer.MAX_VALUE);
        int doctorId = InputUtil.readInt("  Doctor ID to set as head: ", 1, Integer.MAX_VALUE);
        try {
            dao.setHeadDoctor(deptId, doctorId);
            PrintUtil.success("Head doctor set.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void delete() {
        int id = InputUtil.readInt("  Department ID to delete: ", 1, Integer.MAX_VALUE);
        if (!InputUtil.confirm("  Delete department #" + id + "?")) return;
        try {
            dao.delete(id);
            PrintUtil.success("Deleted.");
        } catch (SQLException e) { PrintUtil.error("Cannot delete: " + e.getMessage()); }
    }

    private void statistics() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getStatistics(conn)) {
            PrintUtil.subHeader("Department Statistics");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void doctorCount() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getDoctorCountByDepartment(conn)) {
            PrintUtil.subHeader("Doctor & Room Count by Department (GROUP BY)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }
}
