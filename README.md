# REFRESHER TRAINING (Daily Progress)


## Day 1 (31 July 2026)

### Learn Following DDL & DML Commands & basics SQL Commands

  #### DDL
  
   *Database Commands*

  - `CREATE DATABASE IF NOT EXISTS db_name;`
  - `CREATE DATABASE db_name;`
  - `USE db_name`
  - `SHOW DATABASES;`
  - `DROP DATABASE db_name;`
  - `DROP DATABASE IF EXISTS db_name;`
  
  *Table Commands*

  - `CREATE TABLE tb_name(
    col_name datatype constraints,
    col_name datatype constraints,
    ....
  );`
  
  - `CREATE TABLE Employees_Backup LIKE Employees;`
  - `SHOW TABLES;`
  - `DROP TABLE tb_name;`
  - `ALTER TABLE tb_name RENAME TO new_tb_name;`
  - `RENAME TABLE tb_name TO new_tb_name;`
  - `ALTER TABLE tb_name MODIFY COLUMN col_name newdatatype new_constraints;`
  - `ALTER TABLE tb_name MODIFY col_name newdatatype newconstranis;`
  - `ALTER TABLE tb_name RENAME COLUMN oldColName TO newcolname;`
  - `ALTER TABLE tb_name ADD newcolname datatype constraints;`
  - `ALTER TABLE tb_name DROP PRIMARY KEY;`
  - `ALTER TABLE tb_name ADD PRIMARY KEY (col_name);`
  - `ALTER TABLE tb_name ADD new_col datatype FIRST;`


  #### DML

  *SELECT Commands*

  - `SELECT * FROM Employees;`
  - `SELECT EmployeeName, Salary FROM Employees;`
  - `SELECT DISTINCT Department FROM Employees;`

  - `SELECT * FROM Employees WHERE Department = 'IT';`
  - `SELECT * FROM Employees WHERE Department = 'HR' AND Salary > 60000;`
  - `SELECT * FROM Employees WHERE Salary BETWEEN 55000 AND 70000;`
  - `SELECT * FROM Employees WHERE Department = 'HR' OR Department = 'Finance';`
  - `SELECT * FROM Employees WHERE Department IN ('HR', 'Finance', 'IT');`
  - `SELECT * FROM Employees WHERE Department NOT IN ('IT');`
  - `SELECT * FROM Employees WHERE Department <> 'IT';`
  - `SELECT * FROM Employees WHERE Department != 'IT';`

 *INSERT Commands*

  - `INSERT INTO tb_name (columns you want to add values) VALUES (values for these columns),(...),(..);`
  - `INSERT INTO Employees (EmployeeId, EmployeeName, Salary, JoiningDate, Email, Department) VALUES (101, 'Alice', 65000.50, '2024-01-10', 'alice@gmail.com', 'HR'), (102, 'Bob', 55000.00, '2023-07-15', 'bob@gmail.com', 'IT'), (103, 'Charlie', 72000.75, '2022-09-20', 'charlie@gmail.com', 'Finance'); `

  *UPDATE commands*
  - `SET SQL_SAFE_UPDATES = 0;`
  - `UPDATE Employees SET Salary = 60000.00 WHERE EmployeeName = 'Bob';`
  - `UPDATE Employees SET Salary = Salary+5000 WHERE Department = 'IT';`


  *DELETE Commands*

  - `SET SQL_SAFE_UPDATES = 0;`
  - `DELETE FROM Employees  WHERE EmployeeID = 102;`
  - `DELETE FROM Employees WHERE Salary < 60000;`
  - `DELETE FROM Employees WHERE JoiningDate < '2023-01-01';`

  *ORDER BY Commands*

  - `SELECT * FROM Employees ORDER BY Salary ASC;`
  - `SELECT * FROM Employees ORDER BY Salary DESC;`
  - `SELECT * FROM Employees ORDER BY Department ASC, Salary DESC;`

  *LIMIT Commands*

  - `SELECT * FROM Employees LIMIT 3;`
  - `SELECT * FROM Employees ORDER BY Salary DESC LIMIT 2;`
  - `SELECT * FROM Employees ORDER BY EmployeeID ASC LIMIT 3 OFFSET 3;`

  *LIKE Commands*

  - `SELECT * FROM Employees WHERE EmployeeName LIKE 'A%';`
  - `SELECT * FROM Employees WHERE EmployeeName LIKE '%li%';`
  - `SELECT * FROM Employees WHERE EmployeeName LIKE '_____';`
  



## Holiday Learnings(Sat&Sun)

Learned About following sql concepts and wrote queries
  - Triggers
  - Views
  - Store Procedures
  - Indexing
  - Normalization
  - User Define Functions
  - Transactions

  


  ## Day 2 (3 Aug 2026)


- Learned about the ER Diagrams, how we can make that. what are its components and what each component represents also I revised the joins, group by, having, store procedures, views, and triggers
 

 - Created a MySQL Sole Based HealthCliniApp application where i implemented the features of mysql which i learned.

  [Code Of HealthClinicApp](https://github.com/rudresh-sharma/BridgeLabz-Training/blob/Refresher-Training/day2/HEALTHCLINICAPP.sql)  <br><br>

 ##### Structure of Health Clinic App

  **Four Tables**

 - Patient
 - Department
 - Doctor
 - Appointment


 ##### Here Patient and Doctor table primary keys are foreign key in Appointment table and Department primary key is of doctor
 **Features My HealthClinicApp Supported** :

  🟢 Patient Features
  -  ✅ Search patient by ID
  -  ✅ Search patient by phone number
  -  ✅ Update patient details
  -  ✅ Delete patient
  -  ✅ View all patients
  -  ✅ Find patients by age
  -  ✅ Find patients born after a given date
  -  ✅ Find patients who visited a particular doctor
  -  ✅ Find patients with no appointments
 
 🟢 Doctor Features

  -  ✅ Search doctor by ID
  -  ✅ Search doctor by specialty
  -  ✅ Update doctor's specialty
  -  ✅ Update doctor's consultation fee
  -  ✅ Delete doctor
  -  ✅ View all doctors
  -  ✅ List doctors by department
  -  ✅ Count doctors in each department


  🟢 Appointment Features
  -  ✅ Book appointment
  -  ✅ Cancel appointment
  -  ✅ Reschedule appointment
  -  ✅ Complete appointment
  -  ✅ View today's appointments
  -  ✅ View tomorrow's appointments
  -  ✅ View appointments for any date
  -  ✅ View appointments between two dates
  -  ✅ View appointment history of a patient
  -  ✅ View appointment history of a doctor
  -  ✅ Count appointments by status

 🟢 Department Features
  -  ✅ Add department
  -  ✅ Update department name
  -  ✅ Delete department
  -  ✅ View all departments
  -  ✅ Count doctors in each department