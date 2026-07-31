CREATE DATABASE IF NOT EXISTS CompanyDB;

USE CompanyDB;


CREATE TABLE Employee(
	EmployeeId INT ,
    Name VARCHAR(50),
    Salary DECIMAL(10,2),
    JoiningDate DATE
);

ALTER TABLE Employee ADD Email VARCHAR(100);

ALTER TABLE Employee ADD PhoneNumber VARCHAR(15);

ALTER TABLE Employee MODIFY Name VARCHAR(100);

-- ALTER TABLE Employee 
--     ADD Email VARCHAR(100),
--     ADD PhoneNumber VARCHAR(15),
--     MODIFY COLUMN Name VARCHAR(100);


ALTER TABLE Employee
CHANGE Name EmployeeName VARCHAR(100);

ALTER TABLE Employee RENAME TO Employees;

ALTER TABLE Employees ADD Department VARCHAR(50);

ALTER TABLE Employees DROP COLUMN PhoneNumber;

CREATE TABLE Departments(
	DepartmentId INT,
    DepartmentName VARCHAR(50),
    Location VARCHAR(100)
);


ALTER TABLE Departments RENAME COLUMN Location TO OfficeLocation;

ALTER TABLE Departments MODIFY OfficeLocation VARCHAR(150);

ALTER TABLE Departments ADD Budget DECIMAL(12,2);

ALTER TABLE Departments DROP COLUMN Budget;


-- ALTER TABLE Departments
--     RENAME COLUMN Location TO OfficeLocation,
--     MODIFY OfficeLocation VARCHAR(150),
--     ADD Budget DECIMAL(12,2),
--     DROP COLUMN Budget;


SELECT * FROM Employees;
SELECT * FROM Departments;





-- ==================================================

-- -------STARTING DML PRACTICE FROM HERE--------- 
-- ==========================================


INSERT INTO Employees 
(EmployeeId, EmployeeName, Salary, JoiningDate, Email, Department)
VALUES
(101, 'Alice', 65000.50, '2024-01-10', 'alice@gmail.com', 'HR'),
(102, 'Bob', 55000.00, '2023-07-15', 'bob@gmail.com', 'IT'),
(103, 'Charlie', 72000.75, '2022-09-20', 'charlie@gmail.com', 'Finance');

SELECT * FROM Employees;

SELECT EmployeeName, Salary FROM Employees;

SELECT DISTINCT Department FROM Employees;

-- SELECT Department, COUNT(*)
-- FROM Employees
-- GROUP BY Department;



SELECT * FROM Employees WHERE Department = 'IT';

SELECT * FROM Employees WHERE Salary > 60000;


SELECT * FROM Employees WHERE JoiningDate > '2023-01-01';

SELECT * FROM Employees WHERE Department = 'HR' AND Salary > 60000;

SELECT * FROM Employees WHERE Salary BETWEEN 55000 AND 70000;

SELECT * FROM Employees WHERE Department = 'HR' OR Department = 'Finance'; 
SELECT * FROM Employees WHERE Department IN ('HR', 'Finance', 'IT');

SELECT * FROM Employees WHERE Department NOT IN ('IT');	

SELECT * FROM Employees WHERE Department <> 'IT';

SELECT * FROM Employees WHERE Department != 'IT';

SET SQL_SAFE_UPDATES = 0;
UPDATE Employees SET Salary = 60000.00 WHERE EmployeeName = 'Bob';
UPDATE Employees SET Department = 'Management' WHERE EmployeeId = 101;
UPDATE Employees SET Salary = Salary+5000 WHERE Department = 'IT';
UPDATE Employees SET Email = 'charlie_new@gmail.com' WHERE EmployeeID = 103;


DELETE FROM Employees  WHERE EmployeeID = 102;
DELETE FROM Employees WHERE Department = 'Finance';
DELETE FROM Employees WHERE Salary < 60000;
DELETE FROM Employees WHERE JoiningDate < '2023-01-01';




--    =========== ORDER BY ===========

SELECT * FROM Employees ORDER BY Salary ASC;
SELECT * FROM Employees ORDER BY Salary DESC;
SELECT * FROM Employees ORDER BY EmployeeName ASC;
SELECT * FROM Employees ORDER BY JoiningDate DESC;
SELECT * FROM Employees ORDER BY Department ASC, Salary DESC;


-- =============== LIMIT ============
INSERT INTO Employees 
(EmployeeId, EmployeeName, Salary, JoiningDate, Email, Department)
VALUES
(104, 'David', 45000.00, '2023-11-05', 'david@gmail.com', 'IT'),
(105, 'Emma', 85000.00, '2021-03-15', 'emma@gmail.com', 'Finance'),
(106, 'Frank', 62000.00, '2025-06-20', 'frank@gmail.com', 'IT'),
(107, 'Grace', 95000.00, '2020-08-12', 'grace@gmail.com', 'Management'),
(108, 'Hannah', 53000.00, '2024-09-01', 'hannah@gmail.com', 'HR'),
(109, 'Ian', 71000.00, '2023-05-18', 'ian@gmail.com', 'Finance');

SELECT * FROM Employees LIMIT 3;
SELECT * FROM Employees ORDER BY Salary DESC LIMIT 2;
SELECT * FROM Employees ORDER BY EmployeeID ASC LIMIT 3 OFFSET 3;
SELECT * FROM Employees ORDER BY JoiningDate DESC LIMIT 1;
SELECT * FROM Employees ORDER BY Salary DESC LIMIT 1 OFFSET 1;


-- ================== LIKE BY ===================== 
SELECT * FROM Employees WHERE EmployeeName LIKE 'A%';
SELECT * FROM Employees WHERE EmployeeName LIKE '%e';
SELECT * FROM Employees WHERE EmployeeName LIKE '%li%';
SELECT * FROM Employees WHERE Department LIKE 'M%';
SELECT * FROM Employees WHERE Email LIKE '%gmail.com';
SELECT * FROM Employees WHERE EmployeeName LIKE '_____';


-- ================= NULL IS NOT NULL ==================

UPDATE Employees
SET Email = NULL
WHERE EmployeeId = 101;

UPDATE Employees
SET Department = NULL
WHERE EmployeeId = 103; 


SELECT * FROM Employees WHERE Email IS NULL;
SELECT * FROM Employees WHERE Email IS NOT NULL;
SELECT * FROM Employees WHERE Department IS NULL;
SELECT * FROM Employees WHERE Department IS NOT NULL;
SELECT * FROM Employees WHERE Email IS NULL AND Salary > 60000;
SELECT * FROM Employees WHERE Department IS NULL OR Salary > 70000;

