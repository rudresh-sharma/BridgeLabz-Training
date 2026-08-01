-- ============================================================
-- VIEWS (MySQL)
-- ============================================================

-- Sample Table
CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100),
    Department VARCHAR(50),
    Salary DECIMAL(10,2),
    Email VARCHAR(100)
);

INSERT INTO Employees VALUES
(1,'Alice','HR',50000,'alice@gmail.com'),
(2,'Bob','IT',65000,'bob@gmail.com'),
(3,'Charlie','Finance',70000,'charlie@gmail.com'),
(4,'David','IT',60000,'david@gmail.com'),
(5,'Eva','Marketing',55000,'eva@gmail.com');


-- ============================================================
-- WHAT IS A VIEW?
-- ------------------------------------------------------------
-- A View is a virtual table.
-- It does NOT store data.
-- It stores only a SELECT query.
-- Whenever a View is queried, MySQL executes the stored query.
-- ============================================================


-- ============================================================
-- 1. CREATE VIEW
-- ============================================================

CREATE VIEW EmployeeView AS
SELECT EmployeeId,
       EmployeeName,
       Department
FROM Employees;


-- View the data
SELECT *
FROM EmployeeView;


-- ============================================================
-- 2. VIEW WITH WHERE CLAUSE
-- ============================================================

CREATE VIEW ITEmployees AS
SELECT *
FROM Employees
WHERE Department = 'IT';


SELECT *
FROM ITEmployees;


-- ============================================================
-- 3. VIEW WITH JOIN
-- ============================================================

CREATE TABLE Departments (
    DepartmentId INT PRIMARY KEY,
    DepartmentName VARCHAR(50)
);

CREATE TABLE EmployeeDetails (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100),
    DepartmentId INT
);

INSERT INTO Departments VALUES
(101,'HR'),
(102,'IT'),
(103,'Finance');

INSERT INTO EmployeeDetails VALUES
(1,'Alice',101),
(2,'Bob',102),
(3,'Charlie',103);

CREATE VIEW EmployeeDepartmentView AS
SELECT
    E.EmployeeId,
    E.EmployeeName,
    D.DepartmentName
FROM EmployeeDetails E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId;

SELECT *
FROM EmployeeDepartmentView;


-- ============================================================
-- 4. REPLACE / MODIFY A VIEW
-- ============================================================

CREATE OR REPLACE VIEW EmployeeView AS
SELECT
    EmployeeId,
    EmployeeName,
    Department,
    Email
FROM Employees;


SELECT *
FROM EmployeeView;


-- ============================================================
-- 5. UPDATE DATA USING A VIEW
-- (Works only for simple updatable views)
-- ============================================================

UPDATE EmployeeView
SET EmployeeName = 'Alice Sharma'
WHERE EmployeeId = 1;


SELECT *
FROM Employees;


-- ============================================================
-- 6. INSERT USING A VIEW
-- (Works only for simple updatable views)
-- ============================================================

CREATE VIEW EmployeeBasic AS
SELECT
    EmployeeId,
    EmployeeName,
    Department
FROM Employees;


INSERT INTO EmployeeBasic
VALUES
(6,'John','HR');


SELECT *
FROM Employees;


-- ============================================================
-- 7. DELETE USING A VIEW
-- ============================================================

DELETE FROM EmployeeBasic
WHERE EmployeeId = 6;


SELECT *
FROM Employees;


-- ============================================================
-- 8. NON-UPDATABLE VIEW
-- Views containing GROUP BY, DISTINCT, Aggregate Functions,
-- UNION or Complex JOINs cannot usually be updated.
-- ============================================================

CREATE VIEW DepartmentSalary AS
SELECT
    Department,
    AVG(Salary) AS AverageSalary
FROM Employees
GROUP BY Department;


SELECT *
FROM DepartmentSalary;


-- The following operations will fail

-- INSERT INTO DepartmentSalary VALUES (...);

-- UPDATE DepartmentSalary
-- SET AverageSalary = 70000;

-- DELETE FROM DepartmentSalary
-- WHERE Department = 'IT';


-- ============================================================
-- 9. DROP VIEW
-- ============================================================

DROP VIEW EmployeeBasic;

DROP VIEW ITEmployees;

DROP VIEW EmployeeView;

DROP VIEW DepartmentSalary;

DROP VIEW EmployeeDepartmentView;


-- ============================================================
-- TABLE vs VIEW
-- ============================================================

/*

TABLE
-----
• Stores actual data.
• Occupies storage.
• Independent object.
• Always updatable.

VIEW
----
• Stores only a SELECT query.
• Very little storage (stores definition only).
• Depends on underlying table(s).
• Can be updatable or read-only.

*/


-- ============================================================
-- ADVANTAGES OF VIEWS
-- ============================================================

/*

1. Security
   Hide sensitive columns like Salary, Password, Aadhaar, etc.

2. Simplicity
   Avoid writing long JOIN queries repeatedly.

3. Reusability
   Multiple users can use the same view.

4. Data Abstraction
   Users don't need to know the actual table structure.

*/


-- ============================================================
-- DISADVANTAGES OF VIEWS
-- ============================================================

/*

1. Normal Views do not improve performance.
2. Complex Views can be slower.
3. Some Views are read-only.

*/


-- ============================================================
-- INTERVIEW QUESTIONS
-- ============================================================

/*

Q1. What is a View?
Ans:
A View is a virtual table based on a SELECT query.

-------------------------------------------------------

Q2. Does a View store data?
Ans:
No.
It stores only the SQL query.

-------------------------------------------------------

Q3. Why do we use Views?

• Security
• Simplicity
• Reusability
• Data Abstraction

-------------------------------------------------------

Q4. Can we INSERT, UPDATE and DELETE using a View?

Yes, if it is a simple updatable view.

No, if it contains:
• GROUP BY
• DISTINCT
• Aggregate Functions
• UNION
• Complex JOINs

-------------------------------------------------------

Q5. Does dropping a View delete the table?

No.

Only the View is deleted.
The original table remains unchanged.

*/


-- ============================================================
-- MOST IMPORTANT FOR INTERVIEWS
-- ============================================================

/*

★★★★★
CREATE VIEW
CREATE OR REPLACE VIEW
DROP VIEW
Simple Updatable Views
View vs Table

★★★★☆
Views with WHERE
Views with JOIN
Advantages of Views

★★★☆☆
Read-only Views
Security using Views

*/