package com.healthclinicapp.database;

import com.healthclinicapp.util.ColorUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Fully automatic database bootstrapper for the Health Clinic system.
 *
 * Call {@link #initialize()} once on application startup.  It will:
 *  1. Connect to MySQL server (no DB selected) and CREATE DATABASE if missing
 *  2. Switch to health_clinic_db and CREATE all 25 tables
 *  3. CREATE all indexes, views, triggers, stored functions, stored procedures, and events
 *  4. INSERT sample data (only if the tables are empty)
 *
 * Every SQL statement is executed via {@code Statement.execute()} — no manual MySQL needed.
 */
public class DatabaseInitializer {

    private DatabaseInitializer() { /* static utility */ }

    // ── Public entry point ────────────────────────────────────────────────────

    /** Run once at startup. Creates / upgrades the entire schema. */
    public static void initialize() {
        createDatabase();
        createTables();
        createIndexes();
        createViews();
        createFunctions();
        createTriggers();
        createProcedures();
        createEvents();
        insertSampleData();
    }

    // ── 1. Create database ────────────────────────────────────────────────────

    private static void createDatabase() {
        print("Creating database if not exists...");
        try (Connection base = DatabaseConnection.getBaseConnection();
             Statement  stmt = base.createStatement()) {

            stmt.execute("CREATE DATABASE IF NOT EXISTS " + DatabaseConnection.DB_NAME +
                         " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
            ok("Database '" + DatabaseConnection.DB_NAME + "' ready.");
        } catch (SQLException e) {
            err("createDatabase: " + e.getMessage());
        }
    }

    // ── 2. Create tables ──────────────────────────────────────────────────────

    private static void createTables() {
        print("Creating tables...");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {

            // ── department ────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS department (
                    department_id   INT PRIMARY KEY AUTO_INCREMENT,
                    name            VARCHAR(100)    NOT NULL UNIQUE,
                    description     TEXT,
                    head_doctor_id  INT,
                    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                ) ENGINE=InnoDB
                """);

            // ── doctor ────────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS doctor (
                    doctor_id       INT PRIMARY KEY AUTO_INCREMENT,
                    first_name      VARCHAR(50)  NOT NULL,
                    last_name       VARCHAR(50)  NOT NULL,
                    specialization  VARCHAR(100) NOT NULL,
                    department_id   INT          NOT NULL,
                    phone           VARCHAR(15)  UNIQUE,
                    email           VARCHAR(100) UNIQUE,
                    salary          DECIMAL(10,2) CHECK (salary > 0),
                    join_date       DATE         NOT NULL,
                    is_active       BOOLEAN      DEFAULT TRUE,
                    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
                    updated_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (department_id) REFERENCES department(department_id)
                        ON DELETE RESTRICT ON UPDATE CASCADE
                ) ENGINE=InnoDB
                """);

            // ── Add head_doctor FK after doctor table exists ──────────────────
            // Note: ADD CONSTRAINT IF NOT EXISTS is only MySQL 8.0.31+.
            // We use a separate try-catch to silently skip if FK already exists.
            try {
                stmt.execute("""
                    ALTER TABLE department
                        ADD CONSTRAINT fk_dept_head_doctor
                        FOREIGN KEY (head_doctor_id) REFERENCES doctor(doctor_id)
                        ON DELETE SET NULL
                    """);
            } catch (SQLException fkEx) {
                // 1826 = ER_FK_DUP_NAME, 1061 = ER_DUP_KEY_NAME — FK already exists, safe to ignore
                if (fkEx.getErrorCode() != 1826 && fkEx.getErrorCode() != 1061) {
                    err("addHeadDoctorFK: " + fkEx.getMessage());
                }
            }

            // ── patient ───────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS patient (
                    patient_id      INT PRIMARY KEY AUTO_INCREMENT,
                    first_name      VARCHAR(50)  NOT NULL,
                    last_name       VARCHAR(50)  NOT NULL,
                    date_of_birth   DATE         NOT NULL,
                    gender          ENUM('Male','Female','Other') NOT NULL,
                    blood_group     VARCHAR(5),
                    phone           VARCHAR(15)  UNIQUE NOT NULL,
                    email           VARCHAR(100) UNIQUE,
                    address         TEXT,
                    city            VARCHAR(50),
                    is_active       BOOLEAN      DEFAULT TRUE,
                    registered_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
                    updated_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                ) ENGINE=InnoDB
                """);

            // ── emergency_contact (1-1 with patient) ──────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS emergency_contact (
                    contact_id      INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id      INT          NOT NULL UNIQUE,
                    name            VARCHAR(100) NOT NULL,
                    relationship    VARCHAR(50),
                    phone           VARCHAR(15)  NOT NULL,
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE
                ) ENGINE=InnoDB
                """);

            // ── medical_history ───────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS medical_history (
                    history_id      INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id      INT          NOT NULL,
                    condition_name  VARCHAR(200) NOT NULL,
                    diagnosed_date  DATE,
                    notes           TEXT,
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE
                ) ENGINE=InnoDB
                """);

            // ── room ──────────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS room (
                    room_id         INT PRIMARY KEY AUTO_INCREMENT,
                    room_number     VARCHAR(10)  NOT NULL UNIQUE,
                    room_type       ENUM('General','Private','ICU','Emergency') NOT NULL,
                    department_id   INT,
                    capacity        INT          DEFAULT 1 CHECK (capacity > 0),
                    is_available    BOOLEAN      DEFAULT TRUE,
                    daily_rate      DECIMAL(8,2) CHECK (daily_rate >= 0),
                    FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE SET NULL
                ) ENGINE=InnoDB
                """);

            // ── appointment ───────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS appointment (
                    appointment_id   INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id       INT NOT NULL,
                    doctor_id        INT NOT NULL,
                    appointment_date DATE NOT NULL,
                    appointment_time TIME NOT NULL,
                    status           ENUM('Scheduled','Completed','Cancelled','No-Show') DEFAULT 'Scheduled',
                    reason           TEXT,
                    notes            TEXT,
                    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE,
                    FOREIGN KEY (doctor_id)  REFERENCES doctor(doctor_id)  ON DELETE RESTRICT
                ) ENGINE=InnoDB
                """);

            // ── visit ─────────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS visit (
                    visit_id          INT PRIMARY KEY AUTO_INCREMENT,
                    appointment_id    INT NOT NULL UNIQUE,
                    visit_date        DATE NOT NULL,
                    symptoms          TEXT,
                    diagnosis         TEXT,
                    treatment         TEXT,
                    follow_up_date    DATE,
                    weight            DECIMAL(5,2),
                    blood_pressure    VARCHAR(20),
                    temperature       DECIMAL(4,1),
                    notes             TEXT,
                    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id)
                ) ENGINE=InnoDB
                """);

            // ── prescription ──────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS prescription (
                    prescription_id  INT PRIMARY KEY AUTO_INCREMENT,
                    visit_id         INT  NOT NULL,
                    prescribed_date  DATE NOT NULL,
                    instructions     TEXT,
                    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (visit_id) REFERENCES visit(visit_id) ON DELETE CASCADE
                ) ENGINE=InnoDB
                """);

            // ── medicine ──────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS medicine (
                    medicine_id     INT PRIMARY KEY AUTO_INCREMENT,
                    name            VARCHAR(200) NOT NULL UNIQUE,
                    generic_name    VARCHAR(200),
                    category        VARCHAR(100),
                    unit            VARCHAR(20)  NOT NULL,
                    unit_price      DECIMAL(8,2) CHECK (unit_price >= 0),
                    stock_quantity  INT DEFAULT 0 CHECK (stock_quantity >= 0),
                    min_stock_level INT DEFAULT 10,
                    description     TEXT,
                    is_active       BOOLEAN DEFAULT TRUE
                ) ENGINE=InnoDB
                """);

            // ── prescription_item (M-M bridge: prescription ↔ medicine) ──────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS prescription_item (
                    item_id           INT PRIMARY KEY AUTO_INCREMENT,
                    prescription_id   INT NOT NULL,
                    medicine_id       INT NOT NULL,
                    dosage            VARCHAR(100),
                    frequency         VARCHAR(100),
                    duration_days     INT CHECK (duration_days > 0),
                    quantity          INT CHECK (quantity > 0),
                    UNIQUE KEY uk_presc_med (prescription_id, medicine_id),
                    FOREIGN KEY (prescription_id) REFERENCES prescription(prescription_id) ON DELETE CASCADE,
                    FOREIGN KEY (medicine_id)     REFERENCES medicine(medicine_id)     ON DELETE RESTRICT
                ) ENGINE=InnoDB
                """);

            // ── lab_test ──────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS lab_test (
                    test_id      INT PRIMARY KEY AUTO_INCREMENT,
                    test_name    VARCHAR(200) NOT NULL UNIQUE,
                    description  TEXT,
                    normal_range VARCHAR(100),
                    unit         VARCHAR(50),
                    price        DECIMAL(8,2) CHECK (price >= 0)
                ) ENGINE=InnoDB
                """);

            // ── lab_report ────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS lab_report (
                    report_id    INT PRIMARY KEY AUTO_INCREMENT,
                    visit_id     INT  NOT NULL,
                    test_id      INT  NOT NULL,
                    test_date    DATE NOT NULL,
                    result       VARCHAR(200),
                    is_normal    BOOLEAN,
                    remarks      TEXT,
                    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (visit_id) REFERENCES visit(visit_id)     ON DELETE CASCADE,
                    FOREIGN KEY (test_id)  REFERENCES lab_test(test_id)   ON DELETE RESTRICT
                ) ENGINE=InnoDB
                """);

            // ── billing ───────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS billing (
                    bill_id       INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id    INT NOT NULL,
                    visit_id      INT,
                    bill_date     DATE NOT NULL,
                    total_amount  DECIMAL(10,2) CHECK (total_amount >= 0),
                    paid_amount   DECIMAL(10,2) DEFAULT 0 CHECK (paid_amount >= 0),
                    discount      DECIMAL(8,2)  DEFAULT 0,
                    tax           DECIMAL(8,2)  DEFAULT 0,
                    status        ENUM('Pending','Partial','Paid','Cancelled') DEFAULT 'Pending',
                    notes         TEXT,
                    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
                    FOREIGN KEY (visit_id)   REFERENCES visit(visit_id)   ON DELETE SET NULL
                ) ENGINE=InnoDB
                """);

            // ── payment ───────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS payment (
                    payment_id       INT PRIMARY KEY AUTO_INCREMENT,
                    bill_id          INT NOT NULL,
                    amount           DECIMAL(10,2) NOT NULL CHECK (amount > 0),
                    payment_date     DATE NOT NULL,
                    payment_method   ENUM('Cash','Card','Online','Insurance') NOT NULL,
                    reference_number VARCHAR(100),
                    FOREIGN KEY (bill_id) REFERENCES billing(bill_id) ON DELETE CASCADE
                ) ENGINE=InnoDB
                """);

            // ── insurance ─────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS insurance (
                    insurance_id    INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id      INT          NOT NULL,
                    provider_name   VARCHAR(200) NOT NULL,
                    policy_number   VARCHAR(100) NOT NULL UNIQUE,
                    coverage_amount DECIMAL(10,2),
                    valid_from      DATE,
                    valid_to        DATE,
                    is_active       BOOLEAN DEFAULT TRUE,
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE
                ) ENGINE=InnoDB
                """);

            // ── staff ─────────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS staff (
                    staff_id      INT PRIMARY KEY AUTO_INCREMENT,
                    first_name    VARCHAR(50)  NOT NULL,
                    last_name     VARCHAR(50)  NOT NULL,
                    role          VARCHAR(100) NOT NULL,
                    department_id INT,
                    phone         VARCHAR(15),
                    email         VARCHAR(100) UNIQUE,
                    salary        DECIMAL(10,2) CHECK (salary > 0),
                    join_date     DATE,
                    is_active     BOOLEAN DEFAULT TRUE,
                    FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE SET NULL
                ) ENGINE=InnoDB
                """);

            // ── admission ─────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS admission (
                    admission_id   INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id     INT NOT NULL,
                    room_id        INT NOT NULL,
                    doctor_id      INT NOT NULL,
                    admission_date DATE NOT NULL,
                    discharge_date DATE,
                    reason         TEXT,
                    status         ENUM('Active','Discharged','Transferred') DEFAULT 'Active',
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
                    FOREIGN KEY (room_id)    REFERENCES room(room_id),
                    FOREIGN KEY (doctor_id)  REFERENCES doctor(doctor_id)
                ) ENGINE=InnoDB
                """);

            // ── supplier ──────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS supplier (
                    supplier_id    INT PRIMARY KEY AUTO_INCREMENT,
                    name           VARCHAR(200) NOT NULL,
                    contact_person VARCHAR(100),
                    phone          VARCHAR(15),
                    email          VARCHAR(100),
                    address        TEXT,
                    is_active      BOOLEAN DEFAULT TRUE
                ) ENGINE=InnoDB
                """);

            // ── inventory ─────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS inventory (
                    inventory_id  INT PRIMARY KEY AUTO_INCREMENT,
                    medicine_id   INT NOT NULL,
                    supplier_id   INT,
                    quantity      INT NOT NULL CHECK (quantity > 0),
                    purchase_date DATE NOT NULL,
                    unit_cost     DECIMAL(8,2) CHECK (unit_cost >= 0),
                    expiry_date   DATE,
                    batch_number  VARCHAR(100),
                    FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id),
                    FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON DELETE SET NULL
                ) ENGINE=InnoDB
                """);

            // ── disease ───────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS disease (
                    disease_id   INT PRIMARY KEY AUTO_INCREMENT,
                    name         VARCHAR(200) NOT NULL UNIQUE,
                    icd_code     VARCHAR(20),
                    description  TEXT,
                    category     VARCHAR(100)
                ) ENGINE=InnoDB
                """);

            // ── patient_disease (M-M bridge: patient ↔ disease) ───────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS patient_disease (
                    pd_id         INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id    INT NOT NULL,
                    disease_id    INT NOT NULL,
                    diagnosed_date DATE,
                    notes         TEXT,
                    UNIQUE KEY uk_patient_disease (patient_id, disease_id),
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id)  ON DELETE CASCADE,
                    FOREIGN KEY (disease_id) REFERENCES disease(disease_id)  ON DELETE RESTRICT
                ) ENGINE=InnoDB
                """);

            // ── audit_log ─────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS audit_log (
                    log_id      INT PRIMARY KEY AUTO_INCREMENT,
                    table_name  VARCHAR(100) NOT NULL,
                    operation   VARCHAR(20)  NOT NULL,
                    record_id   INT,
                    description TEXT,
                    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                ) ENGINE=InnoDB
                """);

            // ── activity_log ──────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS activity_log (
                    activity_id   INT PRIMARY KEY AUTO_INCREMENT,
                    activity_type VARCHAR(100),
                    description   TEXT,
                    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                ) ENGINE=InnoDB
                """);

            // ── feedback ──────────────────────────────────────────────────────
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS feedback (
                    feedback_id  INT PRIMARY KEY AUTO_INCREMENT,
                    patient_id   INT NOT NULL,
                    doctor_id    INT NOT NULL,
                    rating       INT CHECK (rating BETWEEN 1 AND 5),
                    comments     TEXT,
                    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
                    FOREIGN KEY (doctor_id)  REFERENCES doctor(doctor_id)
                ) ENGINE=InnoDB
                """);

            ok("All 25 tables created.");
        } catch (SQLException e) {
            err("createTables: " + e.getMessage());
        }
    }

    // ── 3. Create indexes ─────────────────────────────────────────────────────

    private static void createIndexes() {
        print("Creating indexes...");
        String[][] indexes = {
            {"idx_patient_phone",      "CREATE INDEX IF NOT EXISTS idx_patient_phone ON patient(phone)"},
            {"idx_patient_name",       "CREATE INDEX IF NOT EXISTS idx_patient_name ON patient(last_name, first_name)"},
            {"idx_patient_city",       "CREATE INDEX IF NOT EXISTS idx_patient_city ON patient(city)"},
            {"idx_doctor_spec",        "CREATE INDEX IF NOT EXISTS idx_doctor_spec ON doctor(specialization)"},
            {"idx_doctor_dept",        "CREATE INDEX IF NOT EXISTS idx_doctor_dept ON doctor(department_id)"},
            {"idx_appt_date",          "CREATE INDEX IF NOT EXISTS idx_appt_date ON appointment(appointment_date)"},
            {"idx_appt_status",        "CREATE INDEX IF NOT EXISTS idx_appt_status ON appointment(status)"},
            {"idx_appt_patient",       "CREATE INDEX IF NOT EXISTS idx_appt_patient ON appointment(patient_id)"},
            {"idx_appt_doctor",        "CREATE INDEX IF NOT EXISTS idx_appt_doctor ON appointment(doctor_id)"},
            {"idx_visit_date",         "CREATE INDEX IF NOT EXISTS idx_visit_date ON visit(visit_date)"},
            {"idx_billing_status",     "CREATE INDEX IF NOT EXISTS idx_billing_status ON billing(status)"},
            {"idx_billing_patient",    "CREATE INDEX IF NOT EXISTS idx_billing_patient ON billing(patient_id)"},
            {"idx_billing_date",       "CREATE INDEX IF NOT EXISTS idx_billing_date ON billing(bill_date)"},
            {"idx_payment_bill",       "CREATE INDEX IF NOT EXISTS idx_payment_bill ON payment(bill_id)"},
            {"idx_medicine_name",      "CREATE INDEX IF NOT EXISTS idx_medicine_name ON medicine(name)"},
            {"idx_medicine_cat",       "CREATE INDEX IF NOT EXISTS idx_medicine_cat ON medicine(category)"},
            {"idx_inventory_med",      "CREATE INDEX IF NOT EXISTS idx_inventory_med ON inventory(medicine_id)"},
            {"idx_inventory_expiry",   "CREATE INDEX IF NOT EXISTS idx_inventory_expiry ON inventory(expiry_date)"},
            {"idx_audit_table",        "CREATE INDEX IF NOT EXISTS idx_audit_table ON audit_log(table_name)"},
            {"idx_audit_created",      "CREATE INDEX IF NOT EXISTS idx_audit_created ON audit_log(created_at)"},
            {"idx_lab_report_visit",   "CREATE INDEX IF NOT EXISTS idx_lab_report_visit ON lab_report(visit_id)"},
            {"idx_admission_patient",  "CREATE INDEX IF NOT EXISTS idx_admission_patient ON admission(patient_id)"},
            {"idx_presc_item_med",     "CREATE INDEX IF NOT EXISTS idx_presc_item_med ON prescription_item(medicine_id)"},
        };

        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {
            for (String[] idx : indexes) {
                try { stmt.execute(idx[1]); }
                catch (SQLException e) { /* index may already exist */ }
            }
            ok(indexes.length + " indexes created.");
        } catch (SQLException e) {
            err("createIndexes: " + e.getMessage());
        }
    }

    // ── 4. Create views ───────────────────────────────────────────────────────

    private static void createViews() {
        print("Creating views...");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {

            // v_patient_summary
            stmt.execute("DROP VIEW IF EXISTS v_patient_summary");
            stmt.execute("""
                CREATE VIEW v_patient_summary AS
                SELECT
                    p.patient_id,
                    CONCAT(p.first_name, ' ', p.last_name) AS full_name,
                    p.gender,
                    TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) AS age,
                    p.blood_group,
                    p.phone,
                    p.email,
                    p.city,
                    COUNT(DISTINCT a.appointment_id) AS total_appointments,
                    COUNT(DISTINCT b.bill_id)         AS total_bills,
                    COALESCE(SUM(b.total_amount), 0)  AS total_billed,
                    COALESCE(SUM(b.paid_amount),  0)  AS total_paid,
                    IF(p.is_active, 'Active', 'Inactive') AS patient_status
                FROM patient p
                LEFT JOIN appointment a ON p.patient_id = a.patient_id
                LEFT JOIN billing     b ON p.patient_id = b.patient_id
                GROUP BY p.patient_id, p.first_name, p.last_name,
                         p.gender, p.date_of_birth, p.blood_group,
                         p.phone, p.email, p.city, p.is_active
                """);

            // v_doctor_summary
            stmt.execute("DROP VIEW IF EXISTS v_doctor_summary");
            stmt.execute("""
                CREATE VIEW v_doctor_summary AS
                SELECT
                    d.doctor_id,
                    CONCAT('Dr. ', d.first_name, ' ', d.last_name) AS doctor_name,
                    d.specialization,
                    dep.name                AS department,
                    d.phone,
                    d.email,
                    TIMESTAMPDIFF(YEAR, d.join_date, CURDATE()) AS years_experience,
                    COUNT(DISTINCT a.appointment_id)            AS total_appointments,
                    SUM(a.status = 'Completed')                 AS completed,
                    SUM(a.status = 'Cancelled')                 AS cancelled,
                    ROUND(AVG(f.rating), 1)                     AS avg_rating,
                    IF(d.is_active,'Active','Inactive')         AS status
                FROM doctor d
                LEFT JOIN department dep ON d.department_id = dep.department_id
                LEFT JOIN appointment a  ON d.doctor_id     = a.doctor_id
                LEFT JOIN feedback    f  ON d.doctor_id     = f.doctor_id
                GROUP BY d.doctor_id, d.first_name, d.last_name, d.specialization,
                         dep.name, d.phone, d.email, d.join_date, d.is_active
                """);

            // v_appointment_details
            stmt.execute("DROP VIEW IF EXISTS v_appointment_details");
            stmt.execute("""
                CREATE VIEW v_appointment_details AS
                SELECT
                    a.appointment_id,
                    a.appointment_date,
                    a.appointment_time,
                    CONCAT(p.first_name, ' ', p.last_name)          AS patient_name,
                    CONCAT('Dr. ', d.first_name, ' ', d.last_name)  AS doctor_name,
                    d.specialization,
                    dep.name AS department,
                    a.status,
                    a.reason
                FROM appointment a
                JOIN patient    p   ON a.patient_id   = p.patient_id
                JOIN doctor     d   ON a.doctor_id    = d.doctor_id
                JOIN department dep ON d.department_id = dep.department_id
                """);

            // v_billing_details
            stmt.execute("DROP VIEW IF EXISTS v_billing_details");
            stmt.execute("""
                CREATE VIEW v_billing_details AS
                SELECT
                    b.bill_id,
                    b.bill_date,
                    CONCAT(p.first_name, ' ', p.last_name) AS patient_name,
                    b.total_amount,
                    b.paid_amount,
                    (b.total_amount - b.paid_amount)        AS outstanding,
                    b.discount,
                    b.tax,
                    b.status
                FROM billing b
                JOIN patient p ON b.patient_id = p.patient_id
                """);

            // v_pending_bills
            stmt.execute("DROP VIEW IF EXISTS v_pending_bills");
            stmt.execute("""
                CREATE VIEW v_pending_bills AS
                SELECT * FROM v_billing_details WHERE status IN ('Pending','Partial')
                """);

            // v_paid_bills
            stmt.execute("DROP VIEW IF EXISTS v_paid_bills");
            stmt.execute("""
                CREATE VIEW v_paid_bills AS
                SELECT * FROM v_billing_details WHERE status = 'Paid'
                """);

            // v_revenue_report
            stmt.execute("DROP VIEW IF EXISTS v_revenue_report");
            stmt.execute("""
                CREATE VIEW v_revenue_report AS
                SELECT
                    YEAR(b.bill_date)  AS yr,
                    MONTH(b.bill_date) AS mo,
                    MONTHNAME(b.bill_date) AS month_name,
                    COUNT(*)           AS total_bills,
                    SUM(b.total_amount) AS gross_revenue,
                    SUM(b.paid_amount)  AS collected,
                    SUM(b.total_amount - b.paid_amount) AS outstanding
                FROM billing b
                WHERE b.status != 'Cancelled'
                GROUP BY YEAR(b.bill_date), MONTH(b.bill_date), MONTHNAME(b.bill_date)
                ORDER BY yr DESC, mo DESC
                """);

            // v_daily_visits
            stmt.execute("DROP VIEW IF EXISTS v_daily_visits");
            stmt.execute("""
                CREATE VIEW v_daily_visits AS
                SELECT
                    v.visit_date,
                    COUNT(*)  AS total_visits,
                    COUNT(DISTINCT a.doctor_id) AS doctors_consulted
                FROM visit v
                JOIN appointment a ON v.appointment_id = a.appointment_id
                GROUP BY v.visit_date
                ORDER BY v.visit_date DESC
                """);

            // v_department_statistics
            stmt.execute("DROP VIEW IF EXISTS v_department_statistics");
            stmt.execute("""
                CREATE VIEW v_department_statistics AS
                SELECT
                    dep.department_id,
                    dep.name                           AS department,
                    COUNT(DISTINCT d.doctor_id)        AS num_doctors,
                    COUNT(DISTINCT a.appointment_id)   AS total_appointments,
                    COUNT(DISTINCT r.room_id)          AS num_rooms,
                    SUM(r.is_available = FALSE)        AS rooms_occupied
                FROM department dep
                LEFT JOIN doctor      d   ON dep.department_id = d.department_id
                LEFT JOIN appointment a   ON d.doctor_id       = a.doctor_id
                LEFT JOIN room        r   ON dep.department_id = r.department_id
                GROUP BY dep.department_id, dep.name
                """);

            // v_medicine_inventory
            stmt.execute("DROP VIEW IF EXISTS v_medicine_inventory");
            stmt.execute("""
                CREATE VIEW v_medicine_inventory AS
                SELECT
                    m.medicine_id,
                    m.name,
                    m.category,
                    m.unit,
                    m.unit_price,
                    m.stock_quantity,
                    m.min_stock_level,
                    IF(m.stock_quantity <= m.min_stock_level, 'LOW', 'OK') AS stock_status
                FROM medicine m WHERE m.is_active = TRUE
                """);

            // v_low_stock
            stmt.execute("DROP VIEW IF EXISTS v_low_stock");
            stmt.execute("""
                CREATE VIEW v_low_stock AS
                SELECT * FROM v_medicine_inventory WHERE stock_status = 'LOW'
                """);

            // v_doctor_performance
            stmt.execute("DROP VIEW IF EXISTS v_doctor_performance");
            stmt.execute("""
                CREATE VIEW v_doctor_performance AS
                SELECT
                    d.doctor_id,
                    CONCAT('Dr. ', d.first_name, ' ', d.last_name) AS doctor_name,
                    d.specialization,
                    COUNT(DISTINCT a.appointment_id)  AS total_appointments,
                    SUM(a.status = 'Completed')        AS completed,
                    SUM(a.status = 'Cancelled')        AS cancelled,
                    SUM(a.status = 'No-Show')          AS no_shows,
                    ROUND(AVG(f.rating), 2)            AS avg_rating,
                    COUNT(DISTINCT f.feedback_id)      AS total_feedback
                FROM doctor d
                LEFT JOIN appointment a ON d.doctor_id = a.doctor_id
                LEFT JOIN feedback    f ON d.doctor_id = f.doctor_id
                GROUP BY d.doctor_id, d.first_name, d.last_name, d.specialization
                ORDER BY completed DESC
                """);

            // v_prescription_details
            stmt.execute("DROP VIEW IF EXISTS v_prescription_details");
            stmt.execute("""
                CREATE VIEW v_prescription_details AS
                SELECT
                    pr.prescription_id,
                    pr.prescribed_date,
                    CONCAT(p.first_name,' ',p.last_name)         AS patient_name,
                    CONCAT('Dr. ',d.first_name,' ',d.last_name)  AS doctor_name,
                    m.name                                        AS medicine,
                    pi.dosage,
                    pi.frequency,
                    pi.duration_days,
                    pi.quantity
                FROM prescription pr
                JOIN visit       v   ON pr.visit_id        = v.visit_id
                JOIN appointment a   ON v.appointment_id   = a.appointment_id
                JOIN patient     p   ON a.patient_id       = p.patient_id
                JOIN doctor      d   ON a.doctor_id        = d.doctor_id
                JOIN prescription_item pi ON pr.prescription_id = pi.prescription_id
                JOIN medicine    m   ON pi.medicine_id     = m.medicine_id
                """);

            // v_insurance_summary
            stmt.execute("DROP VIEW IF EXISTS v_insurance_summary");
            stmt.execute("""
                CREATE VIEW v_insurance_summary AS
                SELECT
                    i.insurance_id,
                    CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                    i.provider_name,
                    i.policy_number,
                    i.coverage_amount,
                    i.valid_from,
                    i.valid_to,
                    DATEDIFF(i.valid_to, CURDATE())       AS days_remaining,
                    IF(i.is_active AND i.valid_to >= CURDATE(),'Active','Expired') AS ins_status
                FROM insurance i
                JOIN patient p ON i.patient_id = p.patient_id
                """);

            // v_patient_history (complex multi-join)
            stmt.execute("DROP VIEW IF EXISTS v_patient_history");
            stmt.execute("""
                CREATE VIEW v_patient_history AS
                SELECT
                    p.patient_id,
                    CONCAT(p.first_name,' ',p.last_name)        AS patient_name,
                    a.appointment_date,
                    CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name,
                    a.status                                     AS appt_status,
                    v.diagnosis,
                    v.treatment,
                    COALESCE(b.total_amount,0)                  AS bill_amount,
                    b.status                                     AS bill_status
                FROM patient p
                LEFT JOIN appointment a  ON p.patient_id = a.patient_id
                LEFT JOIN doctor      d  ON a.doctor_id  = d.doctor_id
                LEFT JOIN visit       v  ON a.appointment_id = v.appointment_id
                LEFT JOIN billing     b  ON p.patient_id = b.patient_id AND b.visit_id = v.visit_id
                ORDER BY p.patient_id, a.appointment_date DESC
                """);

            ok("15 views created.");
        } catch (SQLException e) {
            err("createViews: " + e.getMessage());
        }
    }

    // ── 5. Create stored functions ────────────────────────────────────────────

    private static void createFunctions() {
        print("Creating stored functions...");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {

            stmt.execute("DROP FUNCTION IF EXISTS GetPatientAge");
            stmt.execute("""
                CREATE FUNCTION GetPatientAge(p_patient_id INT)
                RETURNS INT DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_dob DATE;
                    SELECT date_of_birth INTO v_dob FROM patient WHERE patient_id = p_patient_id;
                    RETURN IFNULL(TIMESTAMPDIFF(YEAR, v_dob, CURDATE()), 0);
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS GetDoctorExperience");
            stmt.execute("""
                CREATE FUNCTION GetDoctorExperience(p_doctor_id INT)
                RETURNS INT DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_join DATE;
                    SELECT join_date INTO v_join FROM doctor WHERE doctor_id = p_doctor_id;
                    RETURN IFNULL(TIMESTAMPDIFF(YEAR, v_join, CURDATE()), 0);
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS GetOutstandingBalance");
            stmt.execute("""
                CREATE FUNCTION GetOutstandingBalance(p_patient_id INT)
                RETURNS DECIMAL(10,2) DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_bal DECIMAL(10,2);
                    SELECT COALESCE(SUM(total_amount - paid_amount), 0)
                    INTO   v_bal
                    FROM   billing
                    WHERE  patient_id = p_patient_id AND status != 'Cancelled';
                    RETURN v_bal;
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS IsMedicineAvailable");
            stmt.execute("""
                CREATE FUNCTION IsMedicineAvailable(p_medicine_id INT, p_qty INT)
                RETURNS BOOLEAN DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_stock INT;
                    SELECT stock_quantity INTO v_stock FROM medicine WHERE medicine_id = p_medicine_id;
                    RETURN IFNULL(v_stock, 0) >= p_qty;
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS GetAppointmentCount");
            stmt.execute("""
                CREATE FUNCTION GetAppointmentCount(p_patient_id INT)
                RETURNS INT DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_cnt INT;
                    SELECT COUNT(*) INTO v_cnt FROM appointment WHERE patient_id = p_patient_id;
                    RETURN IFNULL(v_cnt, 0);
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS GetRevenueByMonth");
            stmt.execute("""
                CREATE FUNCTION GetRevenueByMonth(p_year INT, p_month INT)
                RETURNS DECIMAL(12,2) DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_rev DECIMAL(12,2);
                    SELECT COALESCE(SUM(paid_amount), 0) INTO v_rev
                    FROM   billing
                    WHERE  YEAR(bill_date) = p_year AND MONTH(bill_date) = p_month
                    AND    status != 'Cancelled';
                    RETURN v_rev;
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS GetTotalPaid");
            stmt.execute("""
                CREATE FUNCTION GetTotalPaid(p_patient_id INT)
                RETURNS DECIMAL(10,2) DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_paid DECIMAL(10,2);
                    SELECT COALESCE(SUM(paid_amount), 0) INTO v_paid
                    FROM   billing WHERE patient_id = p_patient_id;
                    RETURN v_paid;
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS GetPatientVisitCount");
            stmt.execute("""
                CREATE FUNCTION GetPatientVisitCount(p_patient_id INT)
                RETURNS INT DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_cnt INT;
                    SELECT COUNT(v.visit_id) INTO v_cnt
                    FROM   visit v
                    JOIN   appointment a ON v.appointment_id = a.appointment_id
                    WHERE  a.patient_id = p_patient_id;
                    RETURN IFNULL(v_cnt, 0);
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS CalculateBMI");
            stmt.execute("""
                CREATE FUNCTION CalculateBMI(p_weight_kg DECIMAL(5,2), p_height_m DECIMAL(4,2))
                RETURNS DECIMAL(5,2) DETERMINISTIC
                BEGIN
                    IF p_height_m = 0 OR p_height_m IS NULL THEN RETURN 0; END IF;
                    RETURN ROUND(p_weight_kg / (p_height_m * p_height_m), 2);
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS CalculateDiscount");
            stmt.execute("""
                CREATE FUNCTION CalculateDiscount(p_amount DECIMAL(10,2), p_pct DECIMAL(5,2))
                RETURNS DECIMAL(10,2) DETERMINISTIC
                BEGIN
                    RETURN ROUND(p_amount * (1 - p_pct / 100), 2);
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS CalculateTax");
            stmt.execute("""
                CREATE FUNCTION CalculateTax(p_amount DECIMAL(10,2), p_tax_pct DECIMAL(5,2))
                RETURNS DECIMAL(10,2) DETERMINISTIC
                BEGIN
                    RETURN ROUND(p_amount * p_tax_pct / 100, 2);
                END
                """);

            stmt.execute("DROP FUNCTION IF EXISTS GetRoomOccupancy");
            stmt.execute("""
                CREATE FUNCTION GetRoomOccupancy(p_dept_id INT)
                RETURNS INT DETERMINISTIC READS SQL DATA
                BEGIN
                    DECLARE v_cnt INT;
                    SELECT COUNT(*) INTO v_cnt FROM room
                    WHERE department_id = p_dept_id AND is_available = FALSE;
                    RETURN IFNULL(v_cnt, 0);
                END
                """);

            ok("12 stored functions created.");
        } catch (SQLException e) {
            err("createFunctions: " + e.getMessage());
        }
    }

    // ── 6. Create triggers ────────────────────────────────────────────────────

    private static void createTriggers() {
        print("Creating triggers...");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {

            // 1. Patient inserted → audit_log
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_patient_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_patient_insert
                AFTER INSERT ON patient FOR EACH ROW
                BEGIN
                    INSERT INTO audit_log(table_name, operation, record_id, description)
                    VALUES('patient', 'INSERT', NEW.patient_id,
                           CONCAT('New patient: ', NEW.first_name,' ',NEW.last_name,
                                  ' | Phone: ', NEW.phone));
                END
                """);

            // 2. Before patient delete → activity_log
            stmt.execute("DROP TRIGGER IF EXISTS trg_before_patient_delete");
            stmt.execute("""
                CREATE TRIGGER trg_before_patient_delete
                BEFORE DELETE ON patient FOR EACH ROW
                BEGIN
                    INSERT INTO activity_log(activity_type, description)
                    VALUES('PATIENT_DELETE',
                           CONCAT('Patient deleted: ', OLD.first_name,' ',OLD.last_name,
                                  ' | ID: ', OLD.patient_id));
                END
                """);

            // 3. After appointment update → audit_log when status changes
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_appointment_update");
            stmt.execute("""
                CREATE TRIGGER trg_after_appointment_update
                AFTER UPDATE ON appointment FOR EACH ROW
                BEGIN
                    IF NEW.status != OLD.status THEN
                        INSERT INTO audit_log(table_name, operation, record_id, description)
                        VALUES('appointment', 'UPDATE', NEW.appointment_id,
                               CONCAT('Status changed: ', OLD.status,' → ', NEW.status));
                    END IF;
                END
                """);

            // 4. Before doctor delete → block if has scheduled appointments
            stmt.execute("DROP TRIGGER IF EXISTS trg_before_doctor_delete");
            stmt.execute("""
                CREATE TRIGGER trg_before_doctor_delete
                BEFORE DELETE ON doctor FOR EACH ROW
                BEGIN
                    DECLARE cnt INT;
                    SELECT COUNT(*) INTO cnt FROM appointment
                    WHERE doctor_id = OLD.doctor_id AND status = 'Scheduled';
                    IF cnt > 0 THEN
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT = 'Cannot delete: doctor has scheduled appointments';
                    END IF;
                END
                """);

            // 5. After payment insert → update billing paid_amount & status
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_payment_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_payment_insert
                AFTER INSERT ON payment FOR EACH ROW
                BEGIN
                    UPDATE billing
                    SET paid_amount = paid_amount + NEW.amount
                    WHERE bill_id = NEW.bill_id;

                    UPDATE billing
                    SET status = CASE
                        WHEN paid_amount >= total_amount THEN 'Paid'
                        WHEN paid_amount > 0             THEN 'Partial'
                        ELSE 'Pending'
                    END
                    WHERE bill_id = NEW.bill_id;
                END
                """);

            // 6. After prescription_item insert → reduce medicine stock
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_presc_item_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_presc_item_insert
                AFTER INSERT ON prescription_item FOR EACH ROW
                BEGIN
                    UPDATE medicine
                    SET stock_quantity = stock_quantity - NEW.quantity
                    WHERE medicine_id = NEW.medicine_id AND stock_quantity >= NEW.quantity;

                    INSERT INTO activity_log(activity_type, description)
                    VALUES('STOCK_REDUCE',
                           CONCAT('Medicine ID ', NEW.medicine_id, ' reduced by ', NEW.quantity));
                END
                """);

            // 7. After admission update → if discharged, mark room available
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_admission_update");
            stmt.execute("""
                CREATE TRIGGER trg_after_admission_update
                AFTER UPDATE ON admission FOR EACH ROW
                BEGIN
                    IF NEW.status = 'Discharged' AND OLD.status != 'Discharged' THEN
                        UPDATE room SET is_available = TRUE WHERE room_id = OLD.room_id;
                        INSERT INTO audit_log(table_name, operation, record_id, description)
                        VALUES('admission','UPDATE', NEW.admission_id,
                               CONCAT('Patient ', NEW.patient_id,' discharged from room ',OLD.room_id));
                    END IF;
                END
                """);

            // 8. After inventory insert → increase medicine stock
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_inventory_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_inventory_insert
                AFTER INSERT ON inventory FOR EACH ROW
                BEGIN
                    UPDATE medicine
                    SET stock_quantity = stock_quantity + NEW.quantity
                    WHERE medicine_id = NEW.medicine_id;
                    INSERT INTO audit_log(table_name, operation, record_id, description)
                    VALUES('inventory','INSERT', NEW.inventory_id,
                           CONCAT('Stock added: medicine_id=', NEW.medicine_id,
                                  ', qty=', NEW.quantity));
                END
                """);

            // 9. After prescription insert → audit
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_prescription_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_prescription_insert
                AFTER INSERT ON prescription FOR EACH ROW
                BEGIN
                    INSERT INTO audit_log(table_name, operation, record_id, description)
                    VALUES('prescription','INSERT', NEW.prescription_id,
                           CONCAT('Prescription created for visit ', NEW.visit_id));
                END
                """);

            // 10. After visit insert → set appointment to Completed
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_visit_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_visit_insert
                AFTER INSERT ON visit FOR EACH ROW
                BEGIN
                    UPDATE appointment SET status = 'Completed'
                    WHERE appointment_id = NEW.appointment_id;
                END
                """);

            // 11. After doctor update → audit
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_doctor_update");
            stmt.execute("""
                CREATE TRIGGER trg_after_doctor_update
                AFTER UPDATE ON doctor FOR EACH ROW
                BEGIN
                    INSERT INTO audit_log(table_name, operation, record_id, description)
                    VALUES('doctor','UPDATE', NEW.doctor_id,
                           CONCAT('Doctor updated: ', NEW.first_name,' ', NEW.last_name));
                END
                """);

            // 12. After billing update → activity_log
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_billing_update");
            stmt.execute("""
                CREATE TRIGGER trg_after_billing_update
                AFTER UPDATE ON billing FOR EACH ROW
                BEGIN
                    INSERT INTO activity_log(activity_type, description)
                    VALUES('BILLING_UPDATE',
                           CONCAT('Bill #',NEW.bill_id,' status → ', NEW.status));
                END
                """);

            // 13. After lab report insert → audit
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_lab_report_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_lab_report_insert
                AFTER INSERT ON lab_report FOR EACH ROW
                BEGIN
                    INSERT INTO audit_log(table_name, operation, record_id, description)
                    VALUES('lab_report','INSERT', NEW.report_id,
                           CONCAT('Lab result for visit ', NEW.visit_id,
                                  ': ', IFNULL(NEW.result,'N/A')));
                END
                """);

            // 14. After feedback insert → activity_log
            stmt.execute("DROP TRIGGER IF EXISTS trg_after_feedback_insert");
            stmt.execute("""
                CREATE TRIGGER trg_after_feedback_insert
                AFTER INSERT ON feedback FOR EACH ROW
                BEGIN
                    INSERT INTO activity_log(activity_type, description)
                    VALUES('FEEDBACK',
                           CONCAT('Patient ', NEW.patient_id,
                                  ' rated Doctor ', NEW.doctor_id,
                                  ': ', NEW.rating, '/5'));
                END
                """);

            // 15. Before billing insert → block negative amounts
            stmt.execute("DROP TRIGGER IF EXISTS trg_before_billing_insert");
            stmt.execute("""
                CREATE TRIGGER trg_before_billing_insert
                BEFORE INSERT ON billing FOR EACH ROW
                BEGIN
                    IF NEW.total_amount < 0 THEN
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT = 'Billing amount cannot be negative';
                    END IF;
                END
                """);

            ok("15 triggers created.");
        } catch (SQLException e) {
            err("createTriggers: " + e.getMessage());
        }
    }

    // ── 7. Create stored procedures ───────────────────────────────────────────

    private static void createProcedures() {
        print("Creating stored procedures...");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {

            // ── RegisterPatient ─────────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS RegisterPatient");
            stmt.execute("""
                CREATE PROCEDURE RegisterPatient(
                    IN  p_first VARCHAR(50), IN  p_last VARCHAR(50),
                    IN  p_dob DATE, IN p_gender VARCHAR(10),
                    IN  p_phone VARCHAR(15), IN p_email VARCHAR(100),
                    IN  p_address TEXT, IN p_city VARCHAR(50),
                    OUT p_id INT)
                BEGIN
                    DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; SET p_id = -1; END;
                    START TRANSACTION;
                    INSERT INTO patient(first_name,last_name,date_of_birth,gender,phone,email,address,city)
                    VALUES(p_first,p_last,p_dob,p_gender,p_phone,p_email,p_address,p_city);
                    SET p_id = LAST_INSERT_ID();
                    COMMIT;
                END
                """);

            // ── RegisterDoctor ──────────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS RegisterDoctor");
            stmt.execute("""
                CREATE PROCEDURE RegisterDoctor(
                    IN  p_first VARCHAR(50), IN  p_last VARCHAR(50),
                    IN  p_spec VARCHAR(100), IN  p_dept_id INT,
                    IN  p_phone VARCHAR(15), IN  p_email VARCHAR(100),
                    IN  p_salary DECIMAL(10,2), IN p_join_date DATE,
                    OUT p_id INT)
                BEGIN
                    DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; SET p_id = -1; END;
                    START TRANSACTION;
                    INSERT INTO doctor(first_name,last_name,specialization,department_id,
                                       phone,email,salary,join_date)
                    VALUES(p_first,p_last,p_spec,p_dept_id,p_phone,p_email,p_salary,p_join_date);
                    SET p_id = LAST_INSERT_ID();
                    COMMIT;
                END
                """);

            // ── ScheduleAppointment ─────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS ScheduleAppointment");
            stmt.execute("""
                CREATE PROCEDURE ScheduleAppointment(
                    IN  p_patient_id INT, IN p_doctor_id INT,
                    IN  p_date DATE,      IN p_time TIME,
                    IN  p_reason TEXT,    OUT p_id INT)
                BEGIN
                    DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; SET p_id = -1; END;
                    START TRANSACTION;
                    INSERT INTO appointment(patient_id,doctor_id,appointment_date,appointment_time,reason)
                    VALUES(p_patient_id,p_doctor_id,p_date,p_time,p_reason);
                    SET p_id = LAST_INSERT_ID();
                    COMMIT;
                END
                """);

            // ── CancelAppointment ───────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS CancelAppointment");
            stmt.execute("""
                CREATE PROCEDURE CancelAppointment(IN p_appt_id INT)
                BEGIN
                    UPDATE appointment SET status = 'Cancelled'
                    WHERE appointment_id = p_appt_id AND status = 'Scheduled';
                END
                """);

            // ── GenerateBill ────────────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS GenerateBill");
            stmt.execute("""
                CREATE PROCEDURE GenerateBill(
                    IN  p_patient_id INT, IN p_visit_id INT,
                    IN  p_amount DECIMAL(10,2), IN p_discount DECIMAL(8,2),
                    IN  p_tax DECIMAL(8,2), OUT p_bill_id INT)
                BEGIN
                    DECLARE v_final DECIMAL(10,2);
                    DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; SET p_bill_id=-1; END;
                    SET v_final = (p_amount - p_discount) + p_tax;
                    START TRANSACTION;
                    INSERT INTO billing(patient_id,visit_id,bill_date,total_amount,discount,tax)
                    VALUES(p_patient_id, IF(p_visit_id=0,NULL,p_visit_id),
                           CURDATE(), v_final, p_discount, p_tax);
                    SET p_bill_id = LAST_INSERT_ID();
                    COMMIT;
                END
                """);

            // ── PayBill ─────────────────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS PayBill");
            stmt.execute("""
                CREATE PROCEDURE PayBill(
                    IN p_bill_id INT, IN p_amount DECIMAL(10,2),
                    IN p_method VARCHAR(20), IN p_ref VARCHAR(100))
                BEGIN
                    DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; END;
                    START TRANSACTION;
                    INSERT INTO payment(bill_id,amount,payment_date,payment_method,reference_number)
                    VALUES(p_bill_id,p_amount,CURDATE(),p_method,p_ref);
                    COMMIT;
                END
                """);

            // ── DischargePatient ────────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS DischargePatient");
            stmt.execute("""
                CREATE PROCEDURE DischargePatient(IN p_admission_id INT)
                BEGIN
                    DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; END;
                    START TRANSACTION;
                    UPDATE admission SET status='Discharged', discharge_date=CURDATE()
                    WHERE admission_id = p_admission_id AND status='Active';
                    COMMIT;
                END
                """);

            // ── DoctorSummaryReport ─────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS DoctorSummaryReport");
            stmt.execute("""
                CREATE PROCEDURE DoctorSummaryReport(IN p_doctor_id INT)
                BEGIN
                    SELECT * FROM v_doctor_summary WHERE doctor_id = p_doctor_id;

                    SELECT a.appointment_date, a.status,
                           CONCAT(p.first_name,' ',p.last_name) AS patient
                    FROM appointment a
                    JOIN patient p ON a.patient_id = p.patient_id
                    WHERE a.doctor_id = p_doctor_id
                    ORDER BY a.appointment_date DESC
                    LIMIT 20;
                END
                """);

            // ── MonthlyRevenueReport ────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS MonthlyRevenueReport");
            stmt.execute("""
                CREATE PROCEDURE MonthlyRevenueReport(IN p_year INT)
                BEGIN
                    SELECT mo AS month_num, month_name, total_bills,
                           gross_revenue, collected, outstanding
                    FROM v_revenue_report WHERE yr = p_year
                    ORDER BY month_num;
                END
                """);

            // ── PatientHistoryReport ────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS PatientHistoryReport");
            stmt.execute("""
                CREATE PROCEDURE PatientHistoryReport(IN p_patient_id INT)
                BEGIN
                    SELECT * FROM v_patient_history WHERE patient_id = p_patient_id;
                    SELECT mh.condition_name, mh.diagnosed_date, mh.notes
                    FROM medical_history mh WHERE mh.patient_id = p_patient_id;
                END
                """);

            // ── LowInventoryReport ──────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS LowInventoryReport");
            stmt.execute("""
                CREATE PROCEDURE LowInventoryReport()
                BEGIN
                    SELECT * FROM v_low_stock ORDER BY stock_quantity ASC;
                END
                """);

            // ── GetPatientOutstanding (IN/OUT demo) ─────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS GetPatientOutstanding");
            stmt.execute("""
                CREATE PROCEDURE GetPatientOutstanding(
                    IN  p_patient_id INT,
                    OUT p_balance    DECIMAL(10,2),
                    OUT p_bill_count INT)
                BEGIN
                    SELECT COALESCE(SUM(total_amount - paid_amount),0), COUNT(*)
                    INTO   p_balance, p_bill_count
                    FROM   billing
                    WHERE  patient_id = p_patient_id AND status != 'Cancelled';
                END
                """);

            // ── TopDoctorsReport ────────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS TopDoctorsReport");
            stmt.execute("""
                CREATE PROCEDURE TopDoctorsReport(IN p_limit INT)
                BEGIN
                    SELECT doctor_id, doctor_name, specialization,
                           completed, avg_rating
                    FROM v_doctor_performance
                    ORDER BY completed DESC, avg_rating DESC
                    LIMIT p_limit;
                END
                """);

            // ── InactivePatientsReport ──────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS InactivePatientsReport");
            stmt.execute("""
                CREATE PROCEDURE InactivePatientsReport(IN p_days INT)
                BEGIN
                    SELECT p.patient_id, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                           p.phone, MAX(a.appointment_date) AS last_appointment,
                           DATEDIFF(CURDATE(), MAX(a.appointment_date)) AS days_since
                    FROM patient p
                    LEFT JOIN appointment a ON p.patient_id = a.patient_id
                    GROUP BY p.patient_id, p.first_name, p.last_name, p.phone
                    HAVING last_appointment IS NULL
                        OR DATEDIFF(CURDATE(), last_appointment) > p_days
                    ORDER BY days_since DESC;
                END
                """);

            // ── UpcomingAppointments ────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS UpcomingAppointments");
            stmt.execute("""
                CREATE PROCEDURE UpcomingAppointments(IN p_days INT)
                BEGIN
                    SELECT a.appointment_id, a.appointment_date, a.appointment_time,
                           CONCAT(p.first_name,' ',p.last_name) AS patient,
                           CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor,
                           a.reason
                    FROM appointment a
                    JOIN patient p ON a.patient_id = p.patient_id
                    JOIN doctor  d ON a.doctor_id  = d.doctor_id
                    WHERE a.status = 'Scheduled'
                      AND a.appointment_date BETWEEN CURDATE()
                          AND DATE_ADD(CURDATE(), INTERVAL p_days DAY)
                    ORDER BY a.appointment_date, a.appointment_time;
                END
                """);

            // ── ProcessPrescriptionCursor (cursor demo) ─────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS ProcessPrescriptionCursor");
            stmt.execute("""
                CREATE PROCEDURE ProcessPrescriptionCursor(IN p_prescription_id INT)
                BEGIN
                    DECLARE v_done     INT DEFAULT 0;
                    DECLARE v_med_id   INT;
                    DECLARE v_qty      INT;
                    DECLARE v_name     VARCHAR(200);
                    DECLARE v_stock    INT;

                    DECLARE cur CURSOR FOR
                        SELECT pi.medicine_id, pi.quantity, m.name, m.stock_quantity
                        FROM   prescription_item pi
                        JOIN   medicine m ON pi.medicine_id = m.medicine_id
                        WHERE  pi.prescription_id = p_prescription_id;

                    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = 1;

                    OPEN cur;
                    fetch_loop: LOOP
                        FETCH cur INTO v_med_id, v_qty, v_name, v_stock;
                        IF v_done = 1 THEN LEAVE fetch_loop; END IF;

                        IF v_stock < v_qty THEN
                            INSERT INTO activity_log(activity_type, description)
                            VALUES('STOCK_WARNING',
                                   CONCAT('LOW STOCK: ', v_name,
                                          ' needs ', v_qty, ' but only ', v_stock,' available'));
                        END IF;
                    END LOOP;
                    CLOSE cur;
                END
                """);

            // ── BulkUpdateAppointmentStatus (loop demo) ────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS BulkUpdateNoShow");
            stmt.execute("""
                CREATE PROCEDURE BulkUpdateNoShow(IN p_before_date DATE)
                BEGIN
                    DECLARE v_done INT DEFAULT 0;
                    DECLARE v_id   INT;
                    DECLARE cur CURSOR FOR
                        SELECT appointment_id FROM appointment
                        WHERE  status = 'Scheduled' AND appointment_date < p_before_date;
                    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = 1;

                    OPEN cur;
                    upd_loop: LOOP
                        FETCH cur INTO v_id;
                        IF v_done = 1 THEN LEAVE upd_loop; END IF;
                        UPDATE appointment SET status = 'No-Show' WHERE appointment_id = v_id;
                    END LOOP;
                    CLOSE cur;
                END
                """);

            // ── DepartmentStatistics ────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS DepartmentStatistics");
            stmt.execute("""
                CREATE PROCEDURE DepartmentStatistics()
                BEGIN
                    SELECT * FROM v_department_statistics ORDER BY num_doctors DESC;
                END
                """);

            // ── AuditLogReport ──────────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS AuditLogReport");
            stmt.execute("""
                CREATE PROCEDURE AuditLogReport(IN p_table VARCHAR(100), IN p_limit INT)
                BEGIN
                    SELECT * FROM audit_log
                    WHERE (p_table = '' OR table_name = p_table)
                    ORDER BY created_at DESC
                    LIMIT p_limit;
                END
                """);

            // ── YearlyRevenueReport ─────────────────────────────────────────
            stmt.execute("DROP PROCEDURE IF EXISTS YearlyRevenueReport");
            stmt.execute("""
                CREATE PROCEDURE YearlyRevenueReport()
                BEGIN
                    SELECT
                        YEAR(bill_date) AS year,
                        COUNT(*)         AS total_bills,
                        SUM(total_amount) AS gross_revenue,
                        SUM(paid_amount)  AS collected,
                        SUM(total_amount-paid_amount) AS outstanding
                    FROM billing
                    WHERE status != 'Cancelled'
                    GROUP BY YEAR(bill_date)
                    ORDER BY year DESC;
                END
                """);

            ok("20 stored procedures created.");
        } catch (SQLException e) {
            err("createProcedures: " + e.getMessage());
        }
    }

    // ── 8. Create events ──────────────────────────────────────────────────────

    private static void createEvents() {
        print("Creating events (requires event_scheduler = ON in MySQL)...");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {

            // Enable the event scheduler (requires SUPER or SYSTEM_VARIABLES_ADMIN)
            try { stmt.execute("SET GLOBAL event_scheduler = ON"); }
            catch (SQLException ex) { warn("Cannot enable event_scheduler: " + ex.getMessage()); }

            // evt_delete_old_logs — delete audit logs older than 1 year (daily)
            stmt.execute("DROP EVENT IF EXISTS evt_delete_old_logs");
            stmt.execute("""
                CREATE EVENT evt_delete_old_logs
                ON SCHEDULE EVERY 1 DAY
                STARTS CURRENT_TIMESTAMP
                DO BEGIN
                    DELETE FROM audit_log    WHERE created_at < DATE_SUB(NOW(), INTERVAL 1 YEAR);
                    DELETE FROM activity_log WHERE created_at < DATE_SUB(NOW(), INTERVAL 1 YEAR);
                END
                """);

            // evt_clean_expired_appointments — mark no-show for past scheduled (daily)
            stmt.execute("DROP EVENT IF EXISTS evt_clean_expired_appointments");
            stmt.execute("""
                CREATE EVENT evt_clean_expired_appointments
                ON SCHEDULE EVERY 1 DAY
                STARTS CURRENT_TIMESTAMP
                DO
                    UPDATE appointment
                    SET    status = 'No-Show'
                    WHERE  status = 'Scheduled'
                    AND    appointment_date < CURDATE() - INTERVAL 1 DAY
                """);

            // evt_monthly_revenue_archive — log monthly totals (monthly)
            stmt.execute("DROP EVENT IF EXISTS evt_monthly_revenue_archive");
            stmt.execute("""
                CREATE EVENT evt_monthly_revenue_archive
                ON SCHEDULE EVERY 1 MONTH
                STARTS CURRENT_TIMESTAMP
                DO BEGIN
                    INSERT INTO activity_log(activity_type, description)
                    SELECT 'MONTHLY_ARCHIVE',
                           CONCAT('Month ',MONTH(NOW()),'-',YEAR(NOW()),
                                  ' revenue: ', SUM(paid_amount))
                    FROM billing
                    WHERE MONTH(bill_date) = MONTH(NOW() - INTERVAL 1 MONTH)
                      AND YEAR (bill_date) = YEAR (NOW() - INTERVAL 1 MONTH)
                      AND status != 'Cancelled';
                END
                """);

            // evt_nightly_stats — refresh nightly summary (daily at midnight)
            stmt.execute("DROP EVENT IF EXISTS evt_nightly_stats");
            stmt.execute("""
                CREATE EVENT evt_nightly_stats
                ON SCHEDULE EVERY 1 DAY
                STARTS (CURDATE() + INTERVAL 1 DAY + INTERVAL 0 HOUR)
                DO BEGIN
                    INSERT INTO activity_log(activity_type, description)
                    VALUES('NIGHTLY_STATS',
                           CONCAT('Daily stats: ',
                                  (SELECT COUNT(*) FROM appointment WHERE appointment_date = CURDATE()),
                                  ' appointments today.'));
                END
                """);

            // evt_deactivate_old_insurance — mark expired insurance (daily)
            stmt.execute("DROP EVENT IF EXISTS evt_deactivate_old_insurance");
            stmt.execute("""
                CREATE EVENT evt_deactivate_old_insurance
                ON SCHEDULE EVERY 1 DAY
                STARTS CURRENT_TIMESTAMP
                DO
                    UPDATE insurance SET is_active = FALSE
                    WHERE valid_to < CURDATE() AND is_active = TRUE
                """);

            ok("5 events created.");
        } catch (SQLException e) {
            err("createEvents: " + e.getMessage());
        }
    }

    // ── 9. Insert sample data ─────────────────────────────────────────────────

    private static void insertSampleData() {
        print("Checking and inserting sample data...");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {

            // Only seed if department table is empty
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM department");
            rs.next();
            if (rs.getInt(1) > 0) {
                ok("Sample data already exists — skipping.");
                return;
            }

            // ── departments ───────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO department(name, description) VALUES
                ('Cardiology',     'Heart and cardiovascular system'),
                ('Neurology',      'Brain and nervous system'),
                ('Orthopedics',    'Bones, joints and muscles'),
                ('Pediatrics',     'Children healthcare'),
                ('Dermatology',    'Skin conditions and treatment'),
                ('Ophthalmology',  'Eye care'),
                ('ENT',            'Ear, Nose and Throat'),
                ('General Surgery','Surgical procedures'),
                ('Emergency',      'Emergency and trauma care'),
                ('Radiology',      'Imaging and diagnostics')
                """);

            // ── doctors ───────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO doctor(first_name,last_name,specialization,department_id,phone,email,salary,join_date) VALUES
                ('Arjun',     'Sharma',     'Cardiologist',         1,'9811000001','arjun.sharma@clinic.com',   150000,'2018-03-01'),
                ('Priya',     'Mehta',      'Neurologist',          2,'9811000002','priya.mehta@clinic.com',    145000,'2019-06-15'),
                ('Ravi',      'Kumar',      'Orthopaedic Surgeon',  3,'9811000003','ravi.kumar@clinic.com',     140000,'2020-01-10'),
                ('Sunita',    'Verma',      'Paediatrician',        4,'9811000004','sunita.verma@clinic.com',   130000,'2017-09-20'),
                ('Vikram',    'Patel',      'Dermatologist',        5,'9811000005','vikram.patel@clinic.com',   125000,'2021-04-05'),
                ('Ananya',    'Singh',      'Ophthalmologist',      6,'9811000006','ananya.singh@clinic.com',   135000,'2016-11-30'),
                ('Deepak',    'Joshi',      'ENT Specialist',       7,'9811000007','deepak.joshi@clinic.com',   120000,'2022-02-14'),
                ('Lakshmi',   'Nair',       'General Surgeon',      8,'9811000008','lakshmi.nair@clinic.com',   160000,'2015-07-25'),
                ('Rahul',     'Gupta',      'Emergency Physician',  9,'9811000009','rahul.gupta@clinic.com',    155000,'2019-12-01'),
                ('Meera',     'Iyer',       'Radiologist',         10,'9811000010','meera.iyer@clinic.com',     138000,'2020-08-18')
                """);

            // ── patients ──────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO patient(first_name,last_name,date_of_birth,gender,blood_group,phone,email,address,city) VALUES
                ('Amit',     'Chaudhary', '1990-05-12','Male',  'A+','9900000001','amit.c@email.com',   '12 MG Road',    'Delhi'),
                ('Kavita',   'Reddy',     '1985-08-23','Female','B+','9900000002','kavita.r@email.com', '45 Park Ave',   'Mumbai'),
                ('Suresh',   'Pillai',    '1978-11-30','Male',  'O-','9900000003','suresh.p@email.com', '7 Lake View',   'Chennai'),
                ('Neha',     'Kapoor',    '1995-02-14','Female','AB+','9900000004','neha.k@email.com',  '22 Elm St',     'Bangalore'),
                ('Rajesh',   'Mishra',    '1982-07-19','Male',  'B-','9900000005','rajesh.m@email.com', '9 Rose Garden', 'Hyderabad'),
                ('Pooja',    'Agarwal',   '1993-04-08','Female','A-','9900000006','pooja.a@email.com',  '3 Hill Crest',  'Pune'),
                ('Arun',     'Tiwari',    '1970-09-25','Male',  'O+','9900000007','arun.t@email.com',   '88 Green Lane', 'Kolkata'),
                ('Divya',    'Menon',     '1988-12-03','Female','B+','9900000008','divya.m@email.com',  '15 Sky High',   'Kochi'),
                ('Manish',   'Bhatia',    '1999-01-17','Male',  'A+','9900000009','manish.b@email.com', '67 River Rd',   'Jaipur'),
                ('Shalini',  'Dubey',     '1976-06-28','Female','O+','9900000010','shalini.d@email.com','30 Oak Street', 'Lucknow')
                """);

            // ── emergency contacts ────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO emergency_contact(patient_id,name,relationship,phone) VALUES
                (1,'Sunita Chaudhary','Spouse','9800000001'),
                (2,'Kiran Reddy',     'Spouse','9800000002'),
                (3,'Maya Pillai',     'Spouse','9800000003'),
                (4,'Raj Kapoor',      'Father','9800000004'),
                (5,'Sita Mishra',     'Spouse','9800000005')
                """);

            // ── rooms ─────────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO room(room_number,room_type,department_id,capacity,is_available,daily_rate) VALUES
                ('R101','General',  1,4,TRUE, 1500),
                ('R102','General',  1,4,TRUE, 1500),
                ('R201','Private',  2,1,TRUE, 4000),
                ('R202','Private',  2,1,FALSE,4000),
                ('R301','ICU',      9,1,TRUE, 8000),
                ('R302','ICU',      9,1,FALSE,8000),
                ('E001','Emergency',9,2,TRUE, 5000),
                ('R401','General',  3,4,TRUE, 1500),
                ('R501','Private',  4,1,TRUE, 3500),
                ('R601','General',  8,4,TRUE, 1500)
                """);

            // ── appointments ──────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO appointment(patient_id,doctor_id,appointment_date,appointment_time,status,reason) VALUES
                (1,1,'2024-01-10','09:00:00','Completed','Chest pain and shortness of breath'),
                (2,2,'2024-01-12','10:30:00','Completed','Persistent headache'),
                (3,3,'2024-01-15','11:00:00','Completed','Knee pain after jogging'),
                (4,4,'2024-01-18','14:00:00','Completed','Child fever and cough'),
                (5,5,'2024-01-20','15:30:00','Completed','Skin rash'),
                (6,1,'2024-02-05','09:30:00','Completed','Follow-up cardiology'),
                (7,2,'2024-02-08','11:00:00','Cancelled','General checkup'),
                (8,3,'2024-02-12','14:30:00','Completed','Back pain'),
                (9,4,'2024-02-15','09:00:00','Completed','Ear infection'),
                (10,5,'2024-02-20','10:00:00','Completed','Acne treatment'),
                (1,2,'2024-03-05','09:00:00','Scheduled','Neurology referral'),
                (2,1,'2024-03-08','10:30:00','Scheduled','Cardiac review')
                """);

            // ── visits ────────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO visit(appointment_id,visit_date,symptoms,diagnosis,treatment,weight,blood_pressure,temperature) VALUES
                (1,'2024-01-10','Chest pain, breathlessness',       'Angina pectoris',         'Nitrates prescribed',     72,  '140/90','37.2'),
                (2,'2024-01-12','Severe headache, nausea',          'Migraine',                'Sumatriptan, rest',        58,  '120/80','37.0'),
                (3,'2024-01-15','Knee pain, swelling',              'Medial meniscus tear',    'Physiotherapy, NSAIDs',   85,  '118/76','36.8'),
                (4,'2024-01-18','Fever 102F, cough, runny nose',    'Upper respiratory tract', 'Paracetamol, cough syrup',22,  '100/65','38.5'),
                (5,'2024-01-20','Itchy red rash on forearm',        'Contact dermatitis',      'Topical corticosteroid',  65,  '115/75','36.9'),
                (6,'2024-02-05','Follow-up, mild chest discomfort', 'Stable angina',           'Continue medication',      73,  '138/88','37.1'),
                (8,'2024-02-12','Lower back pain, radiating to leg','Lumbar disc herniation',  'Physiotherapy, diclofenac',86, '122/80','36.7'),
                (9,'2024-02-15','Ear pain, discharge',              'Acute otitis media',      'Antibiotics ear drops',   32,  '102/68','38.2'),
                (10,'2024-02-20','Oily skin, acne',                 'Acne vulgaris',           'Benzoyl peroxide gel',    60,  '110/72','36.5')
                """);

            // ── medicines ─────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO medicine(name,generic_name,category,unit,unit_price,stock_quantity,min_stock_level) VALUES
                ('Aspirin 75mg',       'Aspirin',          'Cardiovascular', 'Tablet',  2.50, 500, 50),
                ('Paracetamol 500mg',  'Paracetamol',      'Analgesic',      'Tablet',  1.50, 800, 100),
                ('Amoxicillin 500mg',  'Amoxicillin',      'Antibiotic',     'Capsule', 8.00, 200, 30),
                ('Omeprazole 20mg',    'Omeprazole',       'Antacid',        'Capsule', 5.00, 300, 30),
                ('Atorvastatin 10mg',  'Atorvastatin',     'Cardiovascular', 'Tablet',  12.00,150, 20),
                ('Metformin 500mg',    'Metformin',        'Antidiabetic',   'Tablet',  3.50, 400, 50),
                ('Amlodipine 5mg',     'Amlodipine',       'Antihypertensive','Tablet', 6.00, 250, 25),
                ('Cetirizine 10mg',    'Cetirizine',       'Antihistamine',  'Tablet',  4.00, 180, 20),
                ('Diclofenac 50mg',    'Diclofenac',       'NSAID',          'Tablet',  3.00, 350, 40),
                ('Sumatriptan 50mg',   'Sumatriptan',      'Antimigraine',   'Tablet',  25.00, 60, 10),
                ('Betamethasone Cream','Betamethasone',    'Corticosteroid', 'Tube',    35.00, 40,  5),
                ('Benzoyl Peroxide 5%','Benzoyl Peroxide', 'Dermatological', 'Tube',    45.00, 30,  5),
                ('Cough Syrup',        'Dextromethorphan', 'Antitussive',    'Bottle',  55.00,100, 15),
                ('Ciprofloxacin 500mg','Ciprofloxacin',   'Antibiotic',     'Tablet',  15.00,120, 20),
                ('Insulin Glargine',   'Insulin Glargine', 'Antidiabetic',   'Vial',   350.00, 25,  5)
                """);

            // ── prescriptions ─────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO prescription(visit_id,prescribed_date,instructions) VALUES
                (1,'2024-01-10','Take Aspirin daily; avoid strenuous activity'),
                (2,'2024-01-12','Take Sumatriptan at migraine onset; rest in dark room'),
                (3,'2024-01-15','Take Diclofenac after meals; apply ice pack to knee'),
                (4,'2024-01-18','Paracetamol every 6 hrs for fever; cough syrup 3x daily'),
                (5,'2024-01-20','Apply cream twice daily; avoid irritants'),
                (6,'2024-02-05','Continue Aspirin; add Atorvastatin at night'),
                (8,'2024-02-12','Diclofenac 3x daily; physiotherapy twice a week'),
                (9,'2024-02-15','Complete antibiotic course; warm compress'),
                (10,'2024-02-20','Apply gel once daily at night; gentle cleanser')
                """);

            // ── prescription items ────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO prescription_item(prescription_id,medicine_id,dosage,frequency,duration_days,quantity) VALUES
                (1,1,'75mg','Once daily',30,30),
                (2,10,'50mg','As needed',7,5),
                (3,9,'50mg','Thrice daily',5,15),
                (4,2,'500mg','Every 6 hours',5,20),
                (4,13,'10ml','3 times daily',5,1),
                (5,11,'Thin layer','Twice daily',14,1),
                (6,1,'75mg','Once daily',30,30),
                (6,5,'10mg','Once nightly',30,30),
                (7,9,'50mg','Thrice daily',7,21),
                (8,3,'500mg','Twice daily',7,14),
                (9,12,'Thin layer','Once nightly',28,1)
                """);

            // ── lab tests ─────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO lab_test(test_name,description,normal_range,unit,price) VALUES
                ('Complete Blood Count','CBC measures red/white blood cells','4000-11000','cells/uL',300),
                ('Lipid Profile',       'Cholesterol, triglycerides',        '<200 total chol','mg/dL',500),
                ('Blood Sugar Fasting', 'Glucose levels after fasting',      '70-110','mg/dL',150),
                ('ECG',                 'Electrocardiogram',                  'Normal sinus rhythm','N/A',400),
                ('MRI Brain',           'Magnetic resonance imaging',         'Normal brain structure','N/A',5000),
                ('X-Ray Knee',          'Knee joint radiograph',              'No fracture/dislocation','N/A',600),
                ('Urine Culture',       'Bacterial culture of urine',         'No growth','N/A',350),
                ('Thyroid Profile',     'TSH, T3, T4 levels',                '0.4-4.0 TSH','uIU/mL',700),
                ('HbA1c',               'Glycated hemoglobin',                '<5.7%','%',450),
                ('Liver Function Test', 'ALT, AST, bilirubin',               'Normal range','U/L',500)
                """);

            // ── lab reports ───────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO lab_report(visit_id,test_id,test_date,result,is_normal,remarks) VALUES
                (1,4,'2024-01-10','ST depression noted',      FALSE,'Suggests ischemia'),
                (1,2,'2024-01-10','Total cholesterol 230 mg/dL',FALSE,'Borderline high'),
                (2,5,'2024-01-12','Normal brain parenchyma',  TRUE, 'No structural lesion'),
                (3,6,'2024-01-15','Medial joint space narrowing',FALSE,'Consistent with meniscus tear'),
                (6,3,'2024-02-05','Fasting glucose 115 mg/dL',FALSE,'Pre-diabetic range'),
                (8,6,'2024-02-12','L4-L5 disc bulge',         FALSE,'Mild prolapse')
                """);

            // ── billing ───────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO billing(patient_id,visit_id,bill_date,total_amount,discount,tax,status) VALUES
                (1,1,'2024-01-10',3500,200,350,'Paid'),
                (2,2,'2024-01-12',2500,0,  250,'Paid'),
                (3,3,'2024-01-15',4000,500,300,'Partial'),
                (4,4,'2024-01-18',1200,0,  120,'Paid'),
                (5,5,'2024-01-20',1800,0,  180,'Paid'),
                (6,6,'2024-02-05',2800,300,210,'Pending'),
                (8,7,'2024-02-12',3200,0,  320,'Pending'),
                (9,8,'2024-02-15',1500,0,  150,'Paid'),
                (10,9,'2024-02-20',2100,200,161,'Paid')
                """);

            // ── payments ──────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO payment(bill_id,amount,payment_date,payment_method,reference_number) VALUES
                (1,3650,'2024-01-10','Card',  'TXN001'),
                (2,2750,'2024-01-12','Cash',  NULL),
                (3,1800,'2024-01-15','Online','UPI001'),
                (4,1320,'2024-01-18','Cash',  NULL),
                (5,1980,'2024-01-20','Card',  'TXN002'),
                (8,1650,'2024-02-15','Cash',  NULL),
                (9,2061,'2024-02-20','Online','UPI002')
                """);

            // ── insurance ─────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO insurance(patient_id,provider_name,policy_number,coverage_amount,valid_from,valid_to) VALUES
                (1,'LIC Health Shield',  'LIC-HS-0001',500000,'2023-01-01','2025-12-31'),
                (2,'Star Health Plus',   'SHP-0002',   300000,'2022-06-01','2024-05-31'),
                (3,'HDFC Ergo Optima',   'HEO-0003',   400000,'2023-03-01','2026-02-28'),
                (5,'Bajaj Allianz Health','BAH-0005',  250000,'2024-01-01','2026-12-31'),
                (7,'New India Assurance','NIA-0007',   350000,'2023-07-01','2025-06-30')
                """);

            // ── suppliers ─────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO supplier(name,contact_person,phone,email,address) VALUES
                ('MedPharm Distributors',  'Rajan Shah',  '9700000001','rajan@medpharm.com', 'Delhi Industrial Area'),
                ('HealthWay Pharma',       'Anita Mehta', '9700000002','anita@healthway.com','Mumbai Pharma Zone'),
                ('Global Meds Pvt Ltd',    'Sanjay Roy',  '9700000003','sanjay@globalmeds.in','Bangalore Hub')
                """);

            // ── inventory ─────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO inventory(medicine_id,supplier_id,quantity,purchase_date,unit_cost,expiry_date,batch_number) VALUES
                (1,1,500,'2024-01-01', 1.80,'2025-12-31','B001'),
                (2,1,800,'2024-01-01', 1.00,'2025-06-30','B002'),
                (3,2,200,'2024-01-05', 5.50,'2025-09-30','B003'),
                (5,2,150,'2024-01-05', 8.00,'2025-12-31','B004'),
                (6,3,400,'2024-01-10', 2.00,'2026-03-31','B005'),
                (9,1,350,'2024-01-10', 1.80,'2025-08-31','B006'),
                (10,2,60,'2024-01-15',18.00,'2025-10-31','B007'),
                (11,3,40,'2024-01-15',22.00,'2025-07-31','B008'),
                (15,2,25,'2024-01-20',250.00,'2024-12-31','B009')
                """);

            // ── diseases ──────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO disease(name,icd_code,description,category) VALUES
                ('Type 2 Diabetes Mellitus', 'E11','Insulin resistance disorder',            'Endocrine'),
                ('Hypertension',             'I10','High blood pressure',                    'Cardiovascular'),
                ('Angina Pectoris',          'I20','Chest pain due to coronary artery disease','Cardiovascular'),
                ('Migraine',                 'G43','Recurrent moderate to severe headaches',  'Neurological'),
                ('Asthma',                   'J45','Chronic airway inflammation',             'Respiratory'),
                ('Contact Dermatitis',       'L25','Skin inflammation from contact',          'Dermatological'),
                ('Acute Otitis Media',       'H66','Middle ear infection',                    'ENT'),
                ('Lumbar Disc Herniation',   'M51','Disc prolapse in lower back',             'Orthopaedic'),
                ('Acne Vulgaris',            'L70','Chronic inflammatory skin condition',     'Dermatological'),
                ('Hyperlipidaemia',          'E78','Elevated blood lipid levels',             'Metabolic')
                """);

            // ── patient_disease ───────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO patient_disease(patient_id,disease_id,diagnosed_date) VALUES
                (1,3,'2020-05-15'),(1,2,'2019-03-10'),
                (2,4,'2021-08-22'),(3,8,'2023-11-30'),
                (4,5,'2022-04-18'),(5,6,'2023-01-25'),
                (6,2,'2018-07-14'),(7,1,'2015-09-05'),
                (8,7,'2024-02-12'),(9,9,'2023-12-20'),
                (10,10,'2022-11-08')
                """);

            // ── medical_history ───────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO medical_history(patient_id,condition_name,diagnosed_date,notes) VALUES
                (1,'Angina Pectoris',      '2020-05-15','Managed with nitrates'),
                (1,'Hypertension',         '2019-03-10','On Amlodipine'),
                (2,'Migraine',             '2021-08-22','Triggered by stress'),
                (3,'Lumbar Disc Herniation','2023-11-30','L4-L5 level'),
                (7,'Type 2 Diabetes',      '2015-09-05','On Metformin and diet control')
                """);

            // ── feedback ──────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO feedback(patient_id,doctor_id,rating,comments) VALUES
                (1,1,5,'Excellent cardiologist, very thorough'),
                (2,2,4,'Good consultation, explained clearly'),
                (3,3,5,'Very experienced surgeon, highly recommend'),
                (4,4,5,'Great with children, very patient'),
                (5,5,4,'Good dermatologist, treatment worked well'),
                (6,1,5,'Follow up was very helpful'),
                (8,3,4,'Good diagnosis, recovery is progressing'),
                (9,4,5,'Quick diagnosis and effective treatment')
                """);

            // ── staff ─────────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO staff(first_name,last_name,role,department_id,phone,email,salary,join_date) VALUES
                ('Geeta',  'Sharma',  'Head Nurse',     1,'9600000001','geeta.s@clinic.com', 45000,'2018-01-15'),
                ('Raj',    'Kapoor',  'Lab Technician', 10,'9600000002','raj.k@clinic.com',   38000,'2019-06-01'),
                ('Priti',  'Sinha',   'Receptionist',   1,'9600000003','priti.s@clinic.com',  32000,'2020-03-10'),
                ('Mohan',  'Das',     'Ward Boy',       3,'9600000004','mohan.d@clinic.com',  25000,'2021-07-20'),
                ('Sundar', 'Rajan',   'Pharmacist',     8,'9600000005','sundar.r@clinic.com', 50000,'2017-11-05')
                """);

            // ── admissions ────────────────────────────────────────────────────
            stmt.execute("""
                INSERT INTO admission(patient_id,room_id,doctor_id,admission_date,discharge_date,reason,status) VALUES
                (1,2,1,'2024-01-10','2024-01-13','Cardiac monitoring','Discharged'),
                (3,8,3,'2024-01-15','2024-01-18','Post-op knee care','Discharged'),
                (6,4,1,'2024-02-05',NULL,'Cardiac observation','Active')
                """);

            ok("Sample data inserted for all tables.");

        } catch (SQLException e) {
            err("insertSampleData: " + e.getMessage());
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private static void print(String msg) {
        System.out.println(ColorUtil.CYAN + "  ► " + msg + ColorUtil.RESET);
    }

    private static void ok(String msg) {
        System.out.println(ColorUtil.BOLD_GREEN + "    ✔ " + msg + ColorUtil.RESET);
    }

    private static void err(String msg) {
        System.err.println(ColorUtil.BOLD_RED + "    ✘ ERROR: " + msg + ColorUtil.RESET);
    }

    private static void warn(String msg) {
        System.out.println(ColorUtil.BOLD_YELLOW + "    ⚠ " + msg + ColorUtil.RESET);
    }
}
