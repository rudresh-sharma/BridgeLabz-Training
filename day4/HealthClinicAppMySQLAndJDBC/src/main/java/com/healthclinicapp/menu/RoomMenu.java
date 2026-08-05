package com.healthclinicapp.menu;

import com.healthclinicapp.dao.*;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.*;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class RoomMenu {

    private final RoomDAO      roomDao   = new RoomDAO();
    private final AdmissionDAO admitDao  = new AdmissionDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("ROOM & ADMISSION MANAGEMENT");
            System.out.println(ColorUtil.CYAN +
                "  ── Rooms ──\n" +
                "  1. Add Room\n" +
                "  2. View All Rooms\n" +
                "  3. Available Rooms\n" +
                "  4. Rooms by Type\n" +
                "  5. Update Room\n" +
                "  6. Mark Room Available/Occupied\n" +
                "  7. Room Occupancy Summary\n" +
                "  ── Admissions ──\n" +
                "  8. Admit Patient\n" +
                "  9. View Admission Details\n" +
                "  10. Active Admissions\n" +
                "  11. Patient Admission History\n" +
                "  12. Discharge Patient (Stored Procedure)\n" +
                "  13. Admission Statistics\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 13);
            switch (choice) {
                case 1  -> addRoom();
                case 2  -> listRooms();
                case 3  -> availableRooms();
                case 4  -> roomsByType();
                case 5  -> updateRoom();
                case 6  -> setAvailability();
                case 7  -> occupancySummary();
                case 8  -> admitPatient();
                case 9  -> viewAdmission();
                case 10 -> activeAdmissions();
                case 11 -> patientAdmissions();
                case 12 -> discharge();
                case 13 -> admissionStats();
                case 0  -> { return; }
            }
        }
    }

    private void addRoom() {
        PrintUtil.subHeader("Add Room");
        Room r = new Room();
        r.setRoomNumber (InputUtil.readString("  Room Number: "));
        r.setRoomType   (InputUtil.readOption("  Type", new String[]{"General","Private","ICU","Emergency"}));
        r.setDepartmentId(InputUtil.readInt("  Department ID (0 = none): ", 0, 9999));
        if (r.getDepartmentId()==0) r.setDepartmentId(null);
        r.setCapacity   (InputUtil.readInt   ("  Capacity: ", 1, 20));
        r.setDailyRate  (InputUtil.readDouble("  Daily Rate: "));
        r.setAvailable  (true);
        try { int id = roomDao.insert(r); PrintUtil.success("Room added! ID: " + id); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listRooms() {
        try {
            List<Room> list = roomDao.findAll();
            PrintUtil.subHeader("All Rooms (" + list.size() + ")");
            printRoomTable(list);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void availableRooms() {
        try { printRoomTable(roomDao.findAvailable()); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void roomsByType() {
        String type = InputUtil.readOption("  Type", new String[]{"General","Private","ICU","Emergency"});
        try { printRoomTable(roomDao.findByType(type)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void updateRoom() {
        int id = InputUtil.readInt("  Room ID: ", 1, Integer.MAX_VALUE);
        try {
            Room r = roomDao.findById(id);
            if (r==null) { PrintUtil.error("Not found."); return; }
            String num = InputUtil.readStringOptional("  Room Number ["+r.getRoomNumber()+"]: ");
            if (!num.isBlank()) r.setRoomNumber(num);
            roomDao.update(r);
            PrintUtil.success("Updated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void setAvailability() {
        int id = InputUtil.readInt("  Room ID: ", 1, Integer.MAX_VALUE);
        String av = InputUtil.readOption("  Set to", new String[]{"Available","Occupied"});
        try {
            roomDao.setAvailability(id, "Available".equals(av));
            PrintUtil.success("Room status updated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void occupancySummary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = roomDao.getOccupancySummary(conn)) {
            PrintUtil.subHeader("Room Occupancy Summary (GROUP BY + CASE)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void admitPatient() {
        PrintUtil.subHeader("Admit Patient");
        int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        int rid = InputUtil.readInt("  Room ID: ",    1, Integer.MAX_VALUE);
        int did = InputUtil.readInt("  Doctor ID: ",  1, Integer.MAX_VALUE);
        String reason = InputUtil.readString("  Reason for admission: ");
        Admission a = new Admission();
        a.setPatientId    (pid); a.setRoomId(rid); a.setDoctorId(did);
        a.setAdmissionDate(DateUtil.todaySqlDate());
        a.setReason       (reason);
        a.setStatus       ("Active");
        try {
            int id = admitDao.insert(a);
            PrintUtil.success("Patient admitted! Admission ID: " + id + " (Room marked occupied)");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewAdmission() {
        int id = InputUtil.readInt("  Admission ID: ", 1, Integer.MAX_VALUE);
        try {
            Admission a = admitDao.findById(id);
            if (a==null) { PrintUtil.error("Not found."); return; }
            PrintUtil.subHeader("Admission #" + id);
            PrintUtil.kv("Patient",    a.getPatientName());
            PrintUtil.kv("Room",       a.getRoomNumber());
            PrintUtil.kv("Doctor",     a.getDoctorName());
            PrintUtil.kv("Admitted",   String.valueOf(a.getAdmissionDate()));
            PrintUtil.kv("Discharged", a.getDischargeDate()!=null?String.valueOf(a.getDischargeDate()):"Still admitted");
            PrintUtil.kv("Status",     a.getStatus());
            PrintUtil.kv("Reason",     a.getReason());
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void activeAdmissions() {
        try {
            List<Admission> list = admitDao.findActive();
            PrintUtil.subHeader("Active Admissions (" + list.size() + ")");
            printAdmissionTable(list);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void patientAdmissions() {
        int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        try { printAdmissionTable(admitDao.findByPatient(pid)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void discharge() {
        int id = InputUtil.readInt("  Admission ID to discharge: ", 1, Integer.MAX_VALUE);
        if (!InputUtil.confirm("  Discharge patient from admission #" + id + "?")) return;
        try {
            admitDao.dischargeViaProcedure(id);
            PrintUtil.success("Patient discharged (trigger marked room as available).");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void admissionStats() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = admitDao.getAdmissionStats(conn)) {
            PrintUtil.subHeader("Admission Statistics (GROUP BY + AVG DATEDIFF)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void printRoomTable(List<Room> list) {
        if (list.isEmpty()) { PrintUtil.info("No rooms found."); return; }
        String[] headers={"ID","Number","Type","Department","Capacity","Available","Rate"};
        String[][] rows=new String[list.size()][7];
        for (int i=0;i<list.size();i++) {
            Room r=list.get(i);
            rows[i]=new String[]{String.valueOf(r.getRoomId()),r.getRoomNumber(),r.getRoomType(),r.getDepartmentName(),String.valueOf(r.getCapacity()),r.isAvailable()?"Yes":"No",String.format("%.0f",r.getDailyRate())};
        }
        PrintUtil.table(headers,rows);
    }

    private void printAdmissionTable(List<Admission> list) {
        if (list.isEmpty()) { PrintUtil.info("No admissions found."); return; }
        String[] headers={"ID","Patient","Room","Doctor","Admitted","Discharged","Status"};
        String[][] rows=new String[list.size()][7];
        for (int i=0;i<list.size();i++) {
            Admission a=list.get(i);
            rows[i]=new String[]{String.valueOf(a.getAdmissionId()),a.getPatientName(),a.getRoomNumber(),a.getDoctorName(),String.valueOf(a.getAdmissionDate()),a.getDischargeDate()!=null?String.valueOf(a.getDischargeDate()):"-",a.getStatus()};
        }
        PrintUtil.table(headers,rows);
    }
}
