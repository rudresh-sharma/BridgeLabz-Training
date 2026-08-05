package com.healthclinicapp.menu;

import com.healthclinicapp.dao.*;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.*;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class BillingMenu {

    private final BillingDAO  billingDao = new BillingDAO();
    private final PaymentDAO  paymentDao = new PaymentDAO();
    private final InsuranceDAO insDao    = new InsuranceDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("BILLING & PAYMENTS");
            System.out.println(ColorUtil.CYAN +
                "  1. Generate Bill (Direct)\n" +
                "  2. Generate Bill (Stored Procedure)\n" +
                "  3. View Bill Details\n" +
                "  4. Patient Bills\n" +
                "  5. Bills by Status\n" +
                "  6. All Bills (Paginated)\n" +
                "  7. Apply Payment (Transaction Demo)\n" +
                "  8. Apply Payment (Stored Procedure)\n" +
                "  9. View Payments for Bill\n" +
                "  10. Cancel Bill\n" +
                "  11. Revenue Summary\n" +
                "  12. Monthly Revenue\n" +
                "  13. Outstanding Balances\n" +
                "  14. Payment Method Summary\n" +
                "  ── Insurance ──\n" +
                "  15. Add Insurance Policy\n" +
                "  16. Patient Insurance Policies\n" +
                "  17. Expiring Insurance Soon\n" +
                "  18. Insurance Summary View\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 18);
            switch (choice) {
                case 1  -> generateBill(false);
                case 2  -> generateBill(true);
                case 3  -> viewBill();
                case 4  -> patientBills();
                case 5  -> byStatus();
                case 6  -> listAll();
                case 7  -> applyPayment();
                case 8  -> applyPaymentProc();
                case 9  -> viewPayments();
                case 10 -> cancelBill();
                case 11 -> revenueSummary();
                case 12 -> monthlyRevenue();
                case 13 -> outstanding();
                case 14 -> paymentMethod();
                case 15 -> addInsurance();
                case 16 -> patientInsurance();
                case 17 -> expiringInsurance();
                case 18 -> insuranceSummary();
                case 0  -> { return; }
            }
        }
    }

    private void generateBill(boolean useProc) {
        PrintUtil.subHeader("Generate Bill" + (useProc?" (Procedure)":""));
        int pid      = InputUtil.readInt   ("  Patient ID: ", 1, Integer.MAX_VALUE);
        int vid      = InputUtil.readInt   ("  Visit ID (0 = none): ", 0, Integer.MAX_VALUE);
        double amt   = InputUtil.readDouble("  Total Amount: ");
        double disc  = InputUtil.readDouble("  Discount: ");
        double tax   = InputUtil.readDouble("  Tax: ");
        try {
            int id = useProc
                ? billingDao.generateViaProcedure(pid, vid, amt, disc, tax)
                : insertBillDirect(pid, vid, amt, disc, tax);
            PrintUtil.success("Bill created! ID: " + id);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private int insertBillDirect(int pid, int vid, double amt, double disc, double tax) throws SQLException {
        Billing b = new Billing();
        b.setPatientId  (pid);
        b.setVisitId    (vid > 0 ? vid : null);
        b.setBillDate   (DateUtil.todaySqlDate());
        b.setTotalAmount(amt - disc + tax);
        b.setDiscount   (disc);
        b.setTax        (tax);
        b.setStatus     ("Pending");
        return billingDao.insert(b);
    }

    private void viewBill() {
        int id = InputUtil.readInt("  Bill ID: ", 1, Integer.MAX_VALUE);
        try {
            Billing b = billingDao.findById(id);
            if (b==null) { PrintUtil.error("Bill not found."); return; }
            PrintUtil.subHeader("Bill #" + id);
            PrintUtil.kv("Patient",   b.getPatientName());
            PrintUtil.kv("Date",      String.valueOf(b.getBillDate()));
            PrintUtil.kv("Total",     "₹" + b.getTotalAmount());
            PrintUtil.kv("Paid",      "₹" + b.getPaidAmount());
            PrintUtil.kv("Outstanding","₹" + b.getOutstanding());
            PrintUtil.kv("Status",    b.getStatus());
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void patientBills() {
        int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        try {
            List<Billing> list = billingDao.findByPatient(pid);
            printTable(list);
            double outstanding = billingDao.getOutstandingBalance(pid);
            System.out.println(ColorUtil.BOLD_YELLOW + "  Outstanding (via stored function): ₹" + outstanding + ColorUtil.RESET);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void byStatus() {
        String status = InputUtil.readOption("  Status", new String[]{"Pending","Partial","Paid","Cancelled"});
        try { printTable(billingDao.findByStatus(status)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listAll() {
        int page=1;
        while (true) {
            try {
                List<Billing> list = billingDao.findAll(page,10);
                PrintUtil.subHeader("All Bills — Page " + page);
                printTable(list);
                String nav = InputUtil.readString("  [N]ext [P]rev [0]Back: ").trim().toLowerCase();
                if (nav.equals("n")) page++; else if (nav.equals("p") && page>1) page--; else break;
            } catch (SQLException e) { PrintUtil.error(e.getMessage()); break; }
        }
    }

    private void applyPayment() {
        int billId = InputUtil.readInt   ("  Bill ID: ", 1, Integer.MAX_VALUE);
        double amt = InputUtil.readDouble("  Amount: ");
        String method = InputUtil.readOption("  Method", new String[]{"Cash","Card","Online","Insurance"});
        String ref    = InputUtil.readString ("  Reference# (Enter to skip): ");
        try {
            billingDao.applyPayment(billId, amt, method, ref.isBlank()?null:ref);
            PrintUtil.success("Payment applied (Transaction + Savepoint demo).");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void applyPaymentProc() {
        int billId = InputUtil.readInt   ("  Bill ID: ", 1, Integer.MAX_VALUE);
        double amt = InputUtil.readDouble("  Amount: ");
        String method = InputUtil.readOption("  Method", new String[]{"Cash","Card","Online","Insurance"});
        String ref    = InputUtil.readString ("  Reference#: ");
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL PayBill(?,?,?,?)}")) {
            cs.setInt(1,billId); cs.setDouble(2,amt); cs.setString(3,method); cs.setString(4,ref);
            cs.execute();
            PrintUtil.success("Payment applied via stored procedure.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void viewPayments() {
        int billId = InputUtil.readInt("  Bill ID: ", 1, Integer.MAX_VALUE);
        try {
            List<Payment> list = paymentDao.findByBill(billId);
            if (list.isEmpty()) { PrintUtil.info("No payments for this bill."); return; }
            String[] headers={"ID","Amount","Date","Method","Reference"};
            String[][] rows=new String[list.size()][5];
            for (int i=0;i<list.size();i++) { Payment p=list.get(i); rows[i]=new String[]{String.valueOf(p.getPaymentId()),String.format("%.2f",p.getAmount()),String.valueOf(p.getPaymentDate()),p.getPaymentMethod(),p.getReferenceNumber()}; }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void cancelBill() {
        int id = InputUtil.readInt("  Bill ID: ", 1, Integer.MAX_VALUE);
        if (!InputUtil.confirm("  Cancel bill #" + id + "?")) return;
        try { billingDao.cancel(id); PrintUtil.success("Cancelled."); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void revenueSummary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = billingDao.getRevenueSummary(conn)) {
            PrintUtil.subHeader("Revenue Summary (SUM/AVG/MAX/MIN)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void monthlyRevenue() {
        int year = InputUtil.readInt("  Year (e.g. 2024): ", 2000, 2099);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = billingDao.getMonthlyRevenue(conn, year)) {
            PrintUtil.subHeader("Monthly Revenue — " + year);
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void outstanding() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = billingDao.getOutstandingByPatient(conn)) {
            PrintUtil.subHeader("Outstanding Balances by Patient");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void paymentMethod() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = paymentDao.getPaymentMethodSummary(conn)) {
            PrintUtil.subHeader("Payment Method Summary");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void addInsurance() {
        PrintUtil.subHeader("Add Insurance Policy");
        Insurance ins = new Insurance();
        ins.setPatientId     (InputUtil.readInt   ("  Patient ID: ", 1, Integer.MAX_VALUE));
        ins.setProviderName  (InputUtil.readString ("  Provider Name: "));
        ins.setPolicyNumber  (InputUtil.readString ("  Policy Number: "));
        ins.setCoverageAmount(InputUtil.readDouble ("  Coverage Amount: "));
        ins.setValidFrom     (DateUtil.toSqlDate(InputUtil.readDate("  Valid From (yyyy-MM-dd): ")));
        ins.setValidTo       (DateUtil.toSqlDate(InputUtil.readDate("  Valid To   (yyyy-MM-dd): ")));
        try { int id = insDao.insert(ins); PrintUtil.success("Insurance added! ID: " + id); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void patientInsurance() {
        int pid = InputUtil.readInt("  Patient ID: ", 1, Integer.MAX_VALUE);
        try {
            List<Insurance> list = insDao.findByPatient(pid);
            if (list.isEmpty()) { PrintUtil.info("No insurance policies."); return; }
            String[] headers={"ID","Provider","Policy#","Coverage","From","To","Active"};
            String[][] rows=new String[list.size()][7];
            for (int i=0;i<list.size();i++) { Insurance ins=list.get(i); rows[i]=new String[]{String.valueOf(ins.getInsuranceId()),ins.getProviderName(),ins.getPolicyNumber(),String.format("%.0f",ins.getCoverageAmount()),String.valueOf(ins.getValidFrom()),String.valueOf(ins.getValidTo()),ins.isActive()?"Yes":"No"}; }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void expiringInsurance() {
        int days = InputUtil.readInt("  Within next N days: ", 1, 365);
        try {
            List<Insurance> list = insDao.findExpiringSoon(days);
            PrintUtil.subHeader("Insurance Expiring within " + days + " days");
            if (list.isEmpty()) { PrintUtil.info("None expiring soon."); return; }
            String[] headers={"Provider","Policy#","Valid To","Patient ID"};
            String[][] rows=new String[list.size()][4];
            for (int i=0;i<list.size();i++) { Insurance ins=list.get(i); rows[i]=new String[]{ins.getProviderName(),ins.getPolicyNumber(),String.valueOf(ins.getValidTo()),String.valueOf(ins.getPatientId())}; }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void insuranceSummary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = insDao.getSummaryView(conn)) {
            PrintUtil.subHeader("Insurance Summary (v_insurance_summary)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void printTable(List<Billing> list) {
        if (list.isEmpty()) { PrintUtil.info("No bills found."); return; }
        String[] headers={"ID","Patient","Date","Total","Paid","Outstanding","Status"};
        String[][] rows=new String[list.size()][7];
        for (int i=0;i<list.size();i++) {
            Billing b=list.get(i);
            rows[i]=new String[]{String.valueOf(b.getBillId()),b.getPatientName(),String.valueOf(b.getBillDate()),String.format("%.2f",b.getTotalAmount()),String.format("%.2f",b.getPaidAmount()),String.format("%.2f",b.getOutstanding()),b.getStatus()};
        }
        PrintUtil.table(headers,rows);
    }
}
