
-- =============== JOINTS =========================

-- ========== 1. INNNER JOIN =====================


CREATE TABLE Departments(
    DepartmentId INT PRIMARY KEY,
    DepartmentName VARCHAR(50)
); 


INSERT INTO Departments
VALUES
(101,'HR'),
(102,'IT'),
(103,'Finance'),
(104,'Marketing');

CREATE TABLE Employees(
    EmployeeId INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeName VARCHAR(100),
    Salary DECIMAL(10,2),
    DepartmentId INT,

    FOREIGN KEY (DepartmentId)
    REFERENCES Departments(DepartmentId)
);

INSERT INTO Employees(EmployeeName,Salary,DepartmentId)
VALUES
('Rudresh', 100000, 101)
('Alice',50000,101),
('Bob',70000,102),
('Charlie',65000,101),
('David',80000,103);



SELECT
    E.EmployeeName,
    D.DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId;


SELECT
    E.EmployeeName,
    E.Salary,
    D.DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId;



SELECT
    D.DepartmentName,
    E.EmployeeName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
ORDER BY DepartmentName ASC;



--  ========= 2 LEFT JOIN ===============
-- Return all rows from the left table, and matching rows from the right table.

-- If there is no match, SQL fills the right-side columns with NULL.-- 



INSERT INTO Employees
(EmployeeName, Salary, DepartmentId)
VALUES
('Eve', 45000, NULL);



SELECT
    E.EmployeeName,
    D.DepartmentName
FROM Employees E
LEFT JOIN Departments D
ON E.DepartmentId = D.DepartmentId;


SELECT
    E.EmployeeName,
    D.DepartmentName
FROM  Departments D
LEFT JOIN Employees E
ON E.DepartmentId = D.DepartmentId;


SELECT
    E.EmployeeName,
    E.Salary,
    D.DepartmentName
FROM Employees E
LEFT JOIN Departments D
ON E.DepartmentId = D.DepartmentId;



-- =========== RIGHT JOIN ==========

SELECT
    E.EmployeeName,
    D.DepartmentName
FROM Employees E
RIGHT JOIN Departments D
ON E.DepartmentId = D.DepartmentId;



--============= FULL (OUTER) JOIN==========

SELECT
    E.EmployeeName,
    D.DepartmentName
FROM Employees E
LEFT JOIN Departments D
ON E.DepartmentId = D.DepartmentId

UNION

SELECT
    E.EmployeeName,
    D.DepartmentName
FROM Employees E
RIGHT JOIN Departments D
ON E.DepartmentId = D.DepartmentId; 
 
 
 
 SELECT *
FROM Employees, Departments;



-- 	============ CROSS JOIN =========

CREATE TABLE Colors (
    ColorName VARCHAR(20)
);

INSERT INTO Colors
VALUES
('Red'),
('Blue'),
('Black');


CREATE TABLE Sizes (
    SizeName VARCHAR(10)
);

INSERT INTO Sizes
VALUES
('S'),
('M'),
('L');


SELECT * 
FROM Colors
CROSS JOIN Sizes;




-- ============ SELF JOIN ==========
DROP TABLE Employees;

SELECT
    E.EmployeeName,
    M.EmployeeName
FROM Employees E
LEFT JOIN Employees M
ON E.ManagerId = M.EmployeeId;











