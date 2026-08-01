
-- ============== KEYS ===================

--   1. SUPER KEY

-- 	A Super Key is any column or combination of columns that can uniquely identify a row.

-- 2. CANDIDATE KEY

-- A Candidate Key is the smallest possible Super Key.
--      Every Candidate Key is a Super Key.
-- Every Super Key is NOT a Candidate Key.



-- 3 PRIMARY KEY
-- A Primary Key is the Candidate Key chosen to uniquely identify every row in a table.
-- We can choose only one of them as the Primary Key.
 

-- 4 ALTERNATE KEY
-- An Alternate Key is a Candidate Key that is not selected as the Primary Key. 


-- 5 COMPOSITE KEY
-- A Composite Key is a key made up of two or more columns that together uniquely identify a row.


CREATE TABLE Enrollments(
    StudentId INT,
    CourseId INT,

    PRIMARY KEY(StudentId, CourseId)
);






--  FOREIGN KEY 
DROP TABLE Departments;
DROP TABLE Employees;

CREATE TABLE Departments (
    DepartmentId INT PRIMARY KEY,
    DepartmentName VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Employees (
    EmployeeId INT AUTO_INCREMENT PRIMARY KEY,
    EmployeeName VARCHAR(100) NOT NULL,
    Salary DECIMAL(10,2) CHECK (Salary > 0),
    DepartmentId INT,
    FOREIGN KEY (DepartmentId) REFERENCES Departments(DepartmentId)
);

INSERT INTO Departments
VALUES
(101,'HR'),
(102,'IT'),
(103,'Finance');


INSERT INTO Employees
(EmployeeName,Salary,DepartmentId)
VALUES
('Rudresh',50000,101);

INSERT INTO Employees
(EmployeeName,Salary,DepartmentId)
VALUES
('Rahul',60000,102);

-- error 
DELETE FROM Departments
WHERE DepartmentId=101;


SELECT * FROM Customers;
DESCRIBE employees;




