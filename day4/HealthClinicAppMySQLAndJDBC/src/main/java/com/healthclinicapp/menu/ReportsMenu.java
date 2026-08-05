package com.healthclinicapp.menu;

import com.healthclinicapp.dao.ReportsDAO;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.util.*;

import java.sql.*;

/**
 * Reports & Analytics Menu — calls all ReportsDAO methods.
 * Every option demonstrates a specific advanced SQL concept.
 */
public class ReportsMenu {

    private final ReportsDAO dao = new ReportsDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("REPORTS & ANALYTICS");
            System.out.println(ColorUtil.CYAN +
                "  ── Window Functions ──\n" +
                "  1.  Doctor Rank by Department (RANK + PARTITION BY)\n" +
                "  2.  Patient Billing Dense Rank (DENSE_RANK)\n" +
                "  3.  Appointment Sequence (ROW_NUMBER)\n" +
                "  4.  Next/Prev Appointment (LEAD / LAG)\n" +
                "  5.  Running Revenue Total (SUM OVER + Moving AVG)\n" +
                "  ── CTEs & Recursive ──\n" +
                "  6.  Patients Above Avg Visits (Non-Recursive CTE)\n" +
                "  7.  Monthly Revenue Growth (Multi-CTE + LAG)\n" +
                "  8.  30-Day Calendar (Recursive CTE)\n" +
                "  ── Derived Tables & Subqueries ──\n" +
                "  9.  Top Departments (Derived Table)\n" +
                "  10. Patients with Paid Bills (EXISTS)\n" +
                "  11. Doctors with No Low Ratings (NOT EXISTS)\n" +
                "  12. Billing > ANY Payment (ANY operator)\n" +
                "  13. Doctors Salary > ALL Pediatrics (ALL operator)\n" +
                "  ── UNION / Temp Tables ──\n" +
                "  14. All Healthcare Providers (UNION ALL)\n" +
                "  15. Top Patients via Temp Table\n" +
                "  ── Function Showcases ──\n" +
                "  16. Patient String Functions Demo\n" +
                "  17. Patient Date Functions Demo\n" +
                "  18. Billing Numeric Functions Demo\n" +
                "  19. NULL Functions Demo\n" +
                "  20. CASE Expression Demo\n" +
                "  ── Stored Function/Procedure Calls ──\n" +
                "  21. Stored Functions Demo (GetPatientAge etc.)\n" +
                "  22. Top Doctors Report (Procedure)\n" +
                "  23. Monthly Revenue Report (Procedure)\n" +
                "  24. Upcoming Appointments (Procedure)\n" +
                "  25. Patient Outstanding (IN/OUT Procedure)\n" +
                "  ── Other ──\n" +
                "  26. Most Common Diseases\n" +
                "  27. Paginated Patients Demo\n" +
                "  28. Doctors with High Completion % (HAVING)\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 28);
            switch (choice) {
                case 1  -> run("Doctor Rank by Department",    c -> dao.getDoctorRankByDepartment(c));
                case 2  -> run("Patient Billing Dense Rank",   c -> dao.getPatientBillingDenseRank(c));
                case 3  -> run("Appointment Row Number",       c -> dao.getAppointmentRowNumber(c));
                case 4  -> run("Appointment LEAD / LAG",       c -> dao.getAppointmentLeadLag(c));
                case 5  -> run("Running Revenue Total",        c -> dao.getRunningRevenue(c));
                case 6  -> run("Patients Above Avg Visits",    c -> dao.getPatientsAboveAvgVisits(c));
                case 7  -> run("Monthly Revenue Growth",       c -> dao.getMonthlyRevenueGrowth(c));
                case 8  -> run("30-Day Calendar (Recursive)",  c -> dao.getDateSeriesRecursiveCTE(c));
                case 9  -> run("Top Departments",              c -> dao.getTopDepartments(c));
                case 10 -> run("Patients with Paid Bills",     c -> dao.getPatientsWithPaidBills(c));
                case 11 -> run("Doctors No Low Rating",        c -> dao.getDoctorsWithNoLowRating(c));
                case 12 -> run("Billing > ANY Payment",        c -> dao.getBillingGreaterThanAnyPayment(c));
                case 13 -> run("Salary > ALL Pediatrics",      c -> dao.getDoctorsWithSalaryAboveAllPediatrics(c));
                case 14 -> run("All Providers (UNION ALL)",    c -> dao.getAllProviders(c));
                case 15 -> run("Top Patients (Temp Table)",    c -> dao.getTopPatientsViaTemp(c));
                case 16 -> run("String Functions Demo",        c -> dao.getPatientStringFunctions(c));
                case 17 -> run("Date Functions Demo",          c -> dao.getPatientDateFunctions(c));
                case 18 -> run("Numeric Functions Demo",       c -> dao.getBillingNumericFunctions(c));
                case 19 -> run("NULL Functions Demo",          c -> dao.getNullFunctionDemo(c));
                case 20 -> run("CASE Expression Demo",         c -> dao.getCaseExpressionDemo(c));
                case 21 -> run("Stored Functions Demo",        c -> dao.getStoredFunctionDemo(c));
                case 22 -> { int lim = InputUtil.readInt("  Top N: ",1,50); run("Top Doctors Report", c -> dao.callTopDoctorsReport(c,lim)); }
                case 23 -> { int yr  = InputUtil.readInt("  Year: ",2000,2099); run("Monthly Revenue", c -> dao.callMonthlyRevenueReport(c,yr)); }
                case 24 -> { int days= InputUtil.readInt("  Days ahead: ",1,30); run("Upcoming Appointments", c -> dao.callUpcomingAppointments(c,days)); }
                case 25 -> {
                    int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
                    try (Connection conn = DatabaseConnection.getConnection()) {
                        PrintUtil.subHeader("GetPatientOutstanding (IN/OUT Procedure)");
                        dao.callGetPatientOutstanding(conn, pid);
                    } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
                }
                case 26 -> run("Most Common Diseases",         c -> dao.getMostCommonDiseases(c));
                case 27 -> {
                    int page = InputUtil.readInt("  Page: ",1,999);
                    int size = InputUtil.readInt("  Size: ",5,50);
                    run("Paginated Patients", c -> dao.getPaginatedPatients(c,page,size));
                }
                case 28 -> {
                    int pct = InputUtil.readInt("  Min completion %: ",0,100);
                    run("High Completion Doctors", c -> dao.getDoctorsWithHighCompletion(c,pct));
                }
                case 0 -> { return; }
            }
        }
    }

    @FunctionalInterface
    interface ReportQuery { ResultSet execute(Connection conn) throws SQLException; }

    private void run(String title, ReportQuery query) {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = query.execute(conn)) {
            PrintUtil.subHeader(title);
            PrintUtil.resultSet(rs);
        } catch (SQLException e) {
            PrintUtil.error(e.getMessage());
        }
    }
}
