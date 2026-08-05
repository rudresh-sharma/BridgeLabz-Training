package com.healthclinicapp.menu;

import com.healthclinicapp.dao.*;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.*;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

public class MedicineMenu {

    private final MedicineDAO  medDao = new MedicineDAO();
    private final InventoryDAO invDao = new InventoryDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("MEDICINE & INVENTORY");
            System.out.println(ColorUtil.CYAN +
                "  1. Add Medicine\n" +
                "  2. View All Medicines\n" +
                "  3. Search Medicine\n" +
                "  4. Medicines by Category\n" +
                "  5. Update Medicine\n" +
                "  6. Deactivate Medicine\n" +
                "  7. Low Stock Alert (View)\n" +
                "  8. Full Inventory View\n" +
                "  9. Check Medicine Availability (Function)\n" +
                "  10. Most Prescribed Medicines\n" +
                "  11. Medicine Categories Summary\n" +
                "  ── Inventory / Stock ──\n" +
                "  12. Record Stock Purchase\n" +
                "  13. View Stock by Medicine\n" +
                "  14. Medicines Expiring Soon\n" +
                "  15. Purchase Summary\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 15);
            switch (choice) {
                case 1  -> addMedicine();
                case 2  -> listAll();
                case 3  -> search();
                case 4  -> byCategory();
                case 5  -> update();
                case 6  -> deactivate();
                case 7  -> lowStock();
                case 8  -> inventoryView();
                case 9  -> checkAvailability();
                case 10 -> mostPrescribed();
                case 11 -> categories();
                case 12 -> recordStock();
                case 13 -> stockByMedicine();
                case 14 -> expiringSoon();
                case 15 -> purchaseSummary();
                case 0  -> { return; }
            }
        }
    }

    private void addMedicine() {
        PrintUtil.subHeader("Add Medicine");
        Medicine m = new Medicine();
        m.setName         (InputUtil.readString("  Name: "));
        m.setGenericName  (InputUtil.readString("  Generic Name: "));
        m.setCategory     (InputUtil.readString("  Category: "));
        m.setUnit         (InputUtil.readString("  Unit (Tablet/Capsule/Bottle/Vial/Tube): "));
        m.setUnitPrice    (InputUtil.readDouble("  Unit Price: "));
        m.setStockQuantity(InputUtil.readInt   ("  Opening Stock: ", 0, 99999));
        m.setMinStockLevel(InputUtil.readInt   ("  Min Stock Level: ", 1, 99999));
        m.setDescription  (InputUtil.readString("  Description: "));
        try {
            int id = medDao.insert(m);
            PrintUtil.success("Medicine added! ID: " + id);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void listAll() {
        try {
            List<Medicine> list = medDao.findAll();
            PrintUtil.subHeader("All Medicines (" + list.size() + ")");
            printTable(list);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void search() {
        String term = InputUtil.readString("  Search: ");
        try { printTable(medDao.search(term)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void byCategory() {
        String cat = InputUtil.readString("  Category: ");
        try { printTable(medDao.findByCategory(cat)); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void update() {
        int id = InputUtil.readInt("  Medicine ID: ", 1, Integer.MAX_VALUE);
        try {
            Medicine m = medDao.findById(id);
            if (m==null) { PrintUtil.error("Not found."); return; }
            String name = InputUtil.readStringOptional("  Name ["+m.getName()+"]: ");
            String cat  = InputUtil.readStringOptional("  Category ["+m.getCategory()+"]: ");
            if (!name.isBlank()) m.setName(name);
            if (!cat.isBlank())  m.setCategory(cat);
            medDao.update(m);
            PrintUtil.success("Updated.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void deactivate() {
        int id = InputUtil.readInt("  Medicine ID: ", 1, Integer.MAX_VALUE);
        try { medDao.deactivate(id); PrintUtil.success("Deactivated."); }
        catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void lowStock() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = medDao.getLowStock(conn)) {
            PrintUtil.subHeader("Low Stock Alert (v_low_stock)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void inventoryView() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = medDao.getInventoryView(conn)) {
            PrintUtil.subHeader("Medicine Inventory (v_medicine_inventory)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void checkAvailability() {
        int mid = InputUtil.readInt("  Medicine ID: ", 1, Integer.MAX_VALUE);
        int qty = InputUtil.readInt("  Required Quantity: ", 1, 9999);
        try {
            boolean avail = medDao.isAvailable(mid, qty);
            if (avail) PrintUtil.success("Medicine is available (IsMedicineAvailable() = TRUE).");
            else       PrintUtil.error  ("Not enough stock! (IsMedicineAvailable() = FALSE)");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void mostPrescribed() {
        int limit = InputUtil.readInt("  Top N: ", 1, 50);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = medDao.getMostPrescribed(conn, limit)) {
            PrintUtil.subHeader("Most Prescribed Medicines (GROUP BY + COUNT)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void categories() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = medDao.getCategories(conn)) {
            PrintUtil.subHeader("Medicine Categories (DISTINCT + COUNT)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void recordStock() {
        PrintUtil.subHeader("Record Stock Purchase");
        Inventory inv = new Inventory();
        inv.setMedicineId  (InputUtil.readInt   ("  Medicine ID: ", 1, Integer.MAX_VALUE));
        inv.setSupplierId  (InputUtil.readInt   ("  Supplier ID (0 = none): ", 0, Integer.MAX_VALUE));
        if (inv.getSupplierId()==0) inv.setSupplierId(null);
        inv.setQuantity    (InputUtil.readInt   ("  Quantity: ", 1, 99999));
        inv.setPurchaseDate(DateUtil.toSqlDate(InputUtil.readDate("  Purchase Date (yyyy-MM-dd): ")));
        inv.setUnitCost    (InputUtil.readDouble("  Unit Cost: "));
        inv.setExpiryDate  (DateUtil.toSqlDate(InputUtil.readDate("  Expiry Date (yyyy-MM-dd, Enter to skip): ")));
        inv.setBatchNumber (InputUtil.readString("  Batch Number: "));
        try {
            int id = invDao.insert(inv);
            PrintUtil.success("Inventory record added! ID: " + id + " (trigger updated medicine stock)");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void stockByMedicine() {
        int mid = InputUtil.readInt("  Medicine ID: ", 1, Integer.MAX_VALUE);
        try {
            List<Inventory> list = invDao.findByMedicine(mid);
            PrintUtil.subHeader("Stock Records for Medicine #" + mid + " (" + list.size() + " batches)");
            if (list.isEmpty()) { PrintUtil.info("No records."); return; }
            String[] headers={"ID","Batch","Qty","Purchase Date","Unit Cost","Expiry","Supplier"};
            String[][] rows=new String[list.size()][7];
            for (int i=0;i<list.size();i++) { Inventory inv=list.get(i); rows[i]=new String[]{String.valueOf(inv.getInventoryId()),inv.getBatchNumber(),String.valueOf(inv.getQuantity()),String.valueOf(inv.getPurchaseDate()),String.format("%.2f",inv.getUnitCost()),String.valueOf(inv.getExpiryDate()),inv.getSupplierName()}; }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void expiringSoon() {
        int days = InputUtil.readInt("  Expiring within N days: ", 1, 365);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = invDao.getExpiringSoon(conn, days)) {
            PrintUtil.subHeader("Inventory Expiring within " + days + " days (DATE_ADD / BETWEEN)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void purchaseSummary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = invDao.getPurchaseSummary(conn)) {
            PrintUtil.subHeader("Inventory Purchase Summary");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void printTable(List<Medicine> list) {
        if (list.isEmpty()) { PrintUtil.info("No medicines found."); return; }
        String[] headers={"ID","Name","Category","Unit","Price","Stock","Min Stock","Status"};
        String[][] rows=new String[list.size()][8];
        for (int i=0;i<list.size();i++) {
            Medicine m=list.get(i);
            String stockFlag = m.getStockQuantity()<=m.getMinStockLevel() ? ColorUtil.RED+"LOW"+ColorUtil.RESET : "OK";
            rows[i]=new String[]{String.valueOf(m.getMedicineId()),m.getName(),m.getCategory(),m.getUnit(),String.format("%.2f",m.getUnitPrice()),String.valueOf(m.getStockQuantity()),String.valueOf(m.getMinStockLevel()),stockFlag};
        }
        PrintUtil.table(headers,rows);
    }
}
