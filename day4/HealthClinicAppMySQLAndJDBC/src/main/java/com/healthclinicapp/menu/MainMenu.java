package com.healthclinicapp.menu;

import com.healthclinicapp.util.ColorUtil;
import com.healthclinicapp.util.InputUtil;
import com.healthclinicapp.util.PrintUtil;

/**
 * Application entry point menu — routes to all sub-menus.
 */
public class MainMenu {

    private final PatientMenu     patientMenu     = new PatientMenu();
    private final DoctorMenu      doctorMenu      = new DoctorMenu();
    private final DepartmentMenu  departmentMenu  = new DepartmentMenu();
    private final AppointmentMenu appointmentMenu = new AppointmentMenu();
    private final VisitMenu       visitMenu       = new VisitMenu();
    private final BillingMenu     billingMenu     = new BillingMenu();
    private final MedicineMenu    medicineMenu    = new MedicineMenu();
    private final LabMenu         labMenu         = new LabMenu();
    private final RoomMenu        roomMenu        = new RoomMenu();
    private final ReportsMenu     reportsMenu     = new ReportsMenu();
    private final SQLPracticeMenu sqlMenu         = new SQLPracticeMenu();
    private final DatabaseMenu    dbMenu          = new DatabaseMenu();

    public void show() {
        while (true) {
            printHeader();
            int choice = InputUtil.readInt("  Enter choice: ", 0, 12);
            switch (choice) {
                case 1  -> patientMenu    .show();
                case 2  -> doctorMenu     .show();
                case 3  -> departmentMenu .show();
                case 4  -> appointmentMenu.show();
                case 5  -> visitMenu      .show();
                case 6  -> billingMenu    .show();
                case 7  -> medicineMenu   .show();
                case 8  -> labMenu        .show();
                case 9  -> roomMenu       .show();
                case 10 -> reportsMenu    .show();
                case 11 -> sqlMenu        .show();
                case 12 -> dbMenu         .show();
                case 0  -> {
                    PrintUtil.goodbye();
                    return;
                }
            }
        }
    }

    private void printHeader() {
        System.out.println(ColorUtil.BOLD_CYAN);
        System.out.println("  ╔══════════════════════════════════════════════════════════╗");
        System.out.println("  ║       🏥  HEALTH CLINIC MANAGEMENT SYSTEM                ║");
        System.out.println("  ║              Java + JDBC + MySQL Practice                ║");
        System.out.println("  ╚══════════════════════════════════════════════════════════╝");
        System.out.println(ColorUtil.RESET);
        System.out.println(ColorUtil.BOLD_WHITE + "  ┌─────────────────────────────────────────────────────────┐");
        System.out.println("  │  1.  Patient Management                                 │");
        System.out.println("  │  2.  Doctor Management                                  │");
        System.out.println("  │  3.  Department Management                              │");
        System.out.println("  │  4.  Appointment Management                             │");
        System.out.println("  │  5.  Visit Management                                   │");
        System.out.println("  │  6.  Billing & Payments                                 │");
        System.out.println("  │  7.  Medicine & Inventory                               │");
        System.out.println("  │  8.  Lab Tests & Reports                                │");
        System.out.println("  │  9.  Room & Admission Management                        │");
        System.out.println("  │  10. Reports & Analytics                                │");
        System.out.println("  │  11. SQL Practice Menu                                  │");
        System.out.println("  │  12. Database Management                                │");
        System.out.println("  │  0.  Exit                                               │");
        System.out.println("  └─────────────────────────────────────────────────────────┘");
        System.out.print(ColorUtil.RESET);
    }
}
