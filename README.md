# Refresher Training (Daily Progress)

Day-wise log of my BridgeLabz Refresher Training — SQL fundamentals, database design, and JDBC integration.

**Repo:** [BridgeLabz-Training (Refresher-Training branch)](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training)

---

## 📅 Day 1 — 31 July 2026

Focus: Core SQL — DDL, DML, and related concepts.

| Folder | Topic |
|---|---|
| `01_DDL` | Data Definition Language — `CREATE`, `ALTER`, `DROP` for databases & tables |
| `02_DML` | Data Manipulation Language — `SELECT`, `INSERT`, `UPDATE`, `DELETE` |
| `03_Constraints` | Table/column constraints |
| `04_Keys` | Primary keys, foreign keys |
| `05_Aggregate Functions` | `COUNT`, `SUM`, `AVG`, `MIN`, `MAX` |
| `06_GroupBy_Having` | `GROUP BY` and `HAVING` clauses |
| `07_JOINS` | SQL joins |
| `08_Subqueries` | Nested queries |
| `09_SetOperators` | `UNION`, `INTERSECT`, etc. |
| `10_StringFunction` | String functions |
| `11_Date_Function` | Date/time functions |
| `12_Views` | Creating and using views |
| `13_storeprocedur` | Stored procedures |
| `14_UserDefinedFunction` | User-defined functions |
| `15_Transactions` | Transaction control |
| `16_Trigger` | Triggers |
| `17_Indexing` | Indexing for performance |

📂 [`day1/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day1)

---

## 📅 Day 2 — 3 August 2026

Focus: ER diagrams and applying SQL concepts to build a real database application.

- Learned ER (Entity-Relationship) diagrams — components and what each represents.
- Revised joins, `GROUP BY`/`HAVING`, stored procedures, views, and triggers.
- Built **HealthClinicApp** — a MySQL-based console application implementing everything learned so far.

### 🏥 HealthClinicApp

**Tables:** `Patient`, `Department`, `Doctor`, `Appointment`
(Patient & Doctor primary keys are foreign keys in Appointment; Doctor references Department.)

**Features implemented:**

- **Patient** — search by ID/phone, update, delete, view all, filter by age or join date, find by doctor visited, find patients with no appointments
- **Doctor** — search by ID/specialty, update specialty/fee, delete, view all, list by department, count per department
- **Appointment** — book, cancel, reschedule, complete, view by day (today/tomorrow/date/range), patient/doctor history, count by status
- **Department** — add, update, delete, view all, count doctors per department

📄 [`day2/HEALTHCLINICAPP.sql`](https://github.com/rudresh-sharma/BridgeLabz-Training/blob/Refresher-Training/day2/HEALTHCLINICAPP.sql)
📂 [`day2/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day2)

---

## 📅 Day 3 — 4 August 2026

Focus: Advanced SQL practice and connecting Java to MySQL via JDBC.

| Folder | Topic |
|---|---|
| [`JointsLearning`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/JointsLearning) | Joins practice (`JoinsPracticeDay3.sql`) |
| [`StoreProcedure`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/StoreProcedure) | Stored procedure practice (`StoreProcedureLearnings.sql`) |
| [`Triggers`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/Triggers) | Trigger practice (`triggersprac.sql`) |
| [`MySQLPracticeWithJDBC`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/MySQLPracticeWithJDBC) | Maven Java project connecting to MySQL using JDBC |

### ☕ MySQLPracticeWithJDBC

A Maven-based Java project (`pom.xml`) that connects to a MySQL database using JDBC, under package `com.mysqlwithjdbc`, with a matching test package.

📂 [`day3/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3)

---

## 🛠️ Tech Stack

- **MySQL** — database design & querying
- **Java + JDBC** — database connectivity
- **Maven** — project/dependency management

## 📌 How to Use

1. Clone the repo and switch to the `Refresher-Training` branch:
   ```bash
   git clone https://github.com/rudresh-sharma/BridgeLabz-Training.git
   cd BridgeLabz-Training
   git checkout Refresher-Training
   ```
2. Browse `day1/`, `day2/`, `day3/` for SQL scripts and code from that day.
3. For `MySQLPracticeWithJDBC`, update your DB credentials and run with Maven:
   ```bash
   cd day3/MySQLPracticeWithJDBC
   mvn clean install
   ```