package com.healthclinicapp.menu;

import com.healthclinicapp.dao.LabDAO;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.LabTest;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class LabMenu {

    private final LabDAO dao = new LabDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("LAB TESTS & REPORTS");
            System.out.println(ColorUtil.CYAN +
                "  1. Add Lab Test\n" +
                "  2. View All Lab Tests\n" +
                "  3. View Lab Reports for Visit\n" +
                "  4. Abnormal Lab Results\n" +
                "  5. Lab Test Summary Report\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 5);
            switch (choice) {
                case 1 -> addTest();
                case 2 -> listTests();
                case 3 -> visitReports();
                case 4 -> abnormal();
                case 5 -> summary();
                case 0 -> { return; }
            }
        }
    }

    private void addTest() {
        PrintUtil.subHeader("Add Lab Test");
        LabTest t = new LabTest();
        t.setTestName   (InputUtil.readString("  Test Name: "));
        t.setDescription(InputUtil.readString("  Description: "));
        t.setNormalRange(InputUtil.readString("  Normal Range: "));
        t.setUnit       (InputUtil.readString("  Unit: "));
        t.setPrice      (InputUtil.readDouble("  Price: "));
        try {
            int id = dao.insertTest(t);
            PrintUtil.success("Lab test added! ID: " + id);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listTests() {
        try {
            List<LabTest> list = dao.findAllTests();
            PrintUtil.subHeader("All Lab Tests (" + list.size() + ")");
            String[] headers={"ID","Test Name","Normal Range","Unit","Price"};
            String[][] rows=new String[list.size()][5];
            for (int i=0;i<list.size();i++) {
                LabTest t=list.get(i);
                rows[i]=new String[]{String.valueOf(t.getTestId()),t.getTestName(),t.getNormalRange(),t.getUnit(),String.format("%.2f",t.getPrice())};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void visitReports() {
        int vid = InputUtil.readInt("  Visit ID: ", 1, Integer.MAX_VALUE);
        try {
            var list = dao.findByVisit(vid);
            if (list.isEmpty()) { PrintUtil.info("No reports for this visit."); return; }
            PrintUtil.subHeader("Lab Reports — Visit #" + vid);
            String[] headers={"ID","Test","Date","Result","Normal","Remarks"};
            String[][] rows=new String[list.size()][6];
            for (int i=0;i<list.size();i++) {
                var r=list.get(i);
                rows[i]=new String[]{String.valueOf(r.getReportId()),r.getTestName(),String.valueOf(r.getTestDate()),r.getResult(),r.getIsNormal()!=null?(r.getIsNormal()?"Yes":"NO"):"?",r.getRemarks()};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void abnormal() {
        try {
            var list = dao.findAbnormalReports();
            PrintUtil.subHeader("Abnormal Lab Results (" + list.size() + ")");
            if (list.isEmpty()) { PrintUtil.info("No abnormal results."); return; }
            String[] headers={"Report ID","Test","Visit","Result","Remarks"};
            String[][] rows=new String[list.size()][5];
            for (int i=0;i<list.size();i++) {
                var r=list.get(i);
                rows[i]=new String[]{String.valueOf(r.getReportId()),r.getTestName(),String.valueOf(r.getVisitId()),r.getResult(),r.getRemarks()};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void summary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getLabSummary(conn)) {
            PrintUtil.subHeader("Lab Test Summary (GROUP BY + COUNT)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }
}
