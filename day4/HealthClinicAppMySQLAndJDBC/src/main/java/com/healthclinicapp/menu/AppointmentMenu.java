package com.healthclinicapp.menu;

import com.healthclinicapp.dao.AppointmentDAO;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Appointment;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class AppointmentMenu {

    private final AppointmentDAO dao = new AppointmentDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("APPOINTMENT MANAGEMENT");
            System.out.println(ColorUtil.CYAN +
                "  1. Schedule Appointment (Direct)\n" +
                "  2. Schedule via Stored Procedure\n" +
                "  3. View All Appointments (Paginated)\n" +
                "  4. Today's Appointments\n" +
                "  5. This Month's Appointments\n" +
                "  6. Appointments by Status\n" +
                "  7. Appointments by Patient\n" +
                "  8. Appointments by Doctor\n" +
                "  9. Appointments by Date Range (BETWEEN)\n" +
                "  10. View Appointment Details\n" +
                "  11. Update Appointment Status\n" +
                "  12. Cancel Appointment (Direct)\n" +
                "  13. Cancel via Stored Procedure\n" +
                "  14. Appointment Status Summary\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 14);
            switch (choice) {
                case 1  -> schedule(false);
                case 2  -> schedule(true);
                case 3  -> listAll();
                case 4  -> today();
                case 5  -> thisMonth();
                case 6  -> byStatus();
                case 7  -> byPatient();
                case 8  -> byDoctor();
                case 9  -> dateRange();
                case 10 -> viewOne();
                case 11 -> updateStatus();
                case 12 -> cancel(false);
                case 13 -> cancel(true);
                case 14 -> statusSummary();
                case 0  -> { return; }
            }
        }
    }

    private void schedule(boolean useProc) {
        PrintUtil.subHeader(useProc ? "Schedule Appointment (Stored Procedure)" : "Schedule Appointment");
        int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        int did = InputUtil.readInt("  Doctor ID: ",  1, Integer.MAX_VALUE);
        String date   = InputUtil.readDate  ("  Date (yyyy-MM-dd): ");
        String time   = InputUtil.readString("  Time (HH:MM): ");
        String reason = InputUtil.readString("  Reason: ");
        try {
            int id = useProc
                ? dao.scheduleViaProcedure(pid, did, date, time, reason)
                : insertDirect(pid, did, date, time, reason);
            if (id > 0) PrintUtil.success("Appointment scheduled! ID: " + id);
            else        PrintUtil.error("Scheduling failed.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private int insertDirect(int pid, int did, String date, String time, String reason) throws SQLException {
        Appointment a = new Appointment();
        a.setPatientId(pid); a.setDoctorId(did);
        a.setAppointmentDate(DateUtil.toSqlDate(date));
        a.setAppointmentTime(time + ":00");
        a.setReason(reason);
        return dao.insert(a);
    }

    private void listAll() {
        int page = 1; int size = 10;
        while (true) {
            try {
                List<Appointment> list = dao.findAll(page, size);
                int total = dao.countAll();
                PrintUtil.subHeader("Appointments — Page " + page + " (Total: " + total + ")");
                printTable(list);
                String nav = InputUtil.readString("  [N]ext [P]rev [0]Back: ").trim().toLowerCase();
                int pages = (int)Math.ceil((double)total/size);
                if      (nav.equals("n") && page < pages) page++;
                else if (nav.equals("p") && page > 1)     page--;
                else if (nav.equals("0"))                  break;
            } catch (SQLException e) { PrintUtil.error(e.getMessage()); break; }
        }
    }

    private void today() {
        try { PrintUtil.subHeader("Today's Appointments"); printTable(dao.findToday()); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void thisMonth() {
        try { PrintUtil.subHeader("This Month's Appointments"); printTable(dao.findThisMonth()); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void byStatus() {
        String status = InputUtil.readOption("  Status", new String[]{"Scheduled","Completed","Cancelled","No-Show"});
        try { printTable(dao.findByStatus(status)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void byPatient() {
        int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        try { printTable(dao.findByPatient(pid)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void byDoctor() {
        int did = InputUtil.readInt("  Doctor ID: ", 1, Integer.MAX_VALUE);
        try { printTable(dao.findByDoctor(did)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void dateRange() {
        String from = InputUtil.readDate("  From (yyyy-MM-dd): ");
        String to   = InputUtil.readDate("  To   (yyyy-MM-dd): ");
        try { printTable(dao.findByDateRange(from, to)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewOne() {
        int id = InputUtil.readInt("  Appointment ID: ", 1, Integer.MAX_VALUE);
        try {
            Appointment a = dao.findById(id);
            if (a==null) { PrintUtil.error("Not found."); return; }
            PrintUtil.subHeader("Appointment #" + id);
            PrintUtil.kv("Patient",  a.getPatientName());
            PrintUtil.kv("Doctor",   a.getDoctorName());
            PrintUtil.kv("Date",     String.valueOf(a.getAppointmentDate()));
            PrintUtil.kv("Time",     a.getAppointmentTime());
            PrintUtil.kv("Status",   a.getStatus());
            PrintUtil.kv("Reason",   a.getReason());
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void updateStatus() {
        int id = InputUtil.readInt("  Appointment ID: ", 1, Integer.MAX_VALUE);
        String status = InputUtil.readOption("  New Status", new String[]{"Scheduled","Completed","Cancelled","No-Show"});
        try {
            dao.updateStatus(id, status);
            PrintUtil.success("Status updated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void cancel(boolean useProc) {
        int id = InputUtil.readInt("  Appointment ID to cancel: ", 1, Integer.MAX_VALUE);
        if (!InputUtil.confirm("  Cancel appointment #" + id + "?")) return;
        try {
            if (useProc) dao.cancelViaProcedure(id);
            else         dao.updateStatus(id, "Cancelled");
            PrintUtil.success("Appointment cancelled.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void statusSummary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = dao.getStatusSummary(conn)) {
            PrintUtil.subHeader("Appointment Status Summary (GROUP BY)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void printTable(List<Appointment> list) {
        if (list.isEmpty()) { PrintUtil.info("No appointments found."); return; }
        String[] headers = {"ID","Date","Time","Patient","Doctor","Status","Reason"};
        String[][] rows = new String[list.size()][7];
        for (int i=0;i<list.size();i++) {
            Appointment a=list.get(i);
            rows[i]=new String[]{String.valueOf(a.getAppointmentId()),String.valueOf(a.getAppointmentDate()),a.getAppointmentTime(),a.getPatientName(),a.getDoctorName(),a.getStatus(),a.getReason()==null?"":a.getReason().substring(0,Math.min(30,a.getReason().length()))};
        }
        PrintUtil.table(headers,rows);
    }
}
