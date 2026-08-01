


-- ===================== SUBQUERIES ==============

CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100) NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL,
    JoiningDate DATE NOT NULL,
    Email VARCHAR(100) UNIQUE,
    Department VARCHAR(50)
);



--  NOT IN , IN , Scalar Subqueries


SELECT EmployeeName, Salary
FROM Employees
WHERE Salary = (
    SELECT MAX(Salary)
    FROM Employees
); 




SELECT EmployeeName, Salary
FROM Employees
WHERE Salary = (
    SELECT MIN(Salary)
    FROM Employees
); 

SELECT EmployeeName, Salary
FROM Employees
WHERE Salary > (
    SELECT AVG(Salary)
    FROM Employees
); 

SELECT *
FROM Employees
WHERE DepartmentId IN (
    SELECT DepartmentId
    FROM Departments
);





SELECT *
FROM Employees
WHERE DepartmentId NOT IN (
    SELECT DepartmentId
    FROM Departments
);




--  _____EXISTS
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Departments;

CREATE TABLE Departments (
    DepartmentId INT PRIMARY KEY,
    DepartmentName VARCHAR(100)
);

INSERT INTO Departments
VALUES
(101, 'HR'),
(102, 'IT'),
(103, 'Finance'),
(104, 'Marketing');

CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100) NOT NULL,
    Salary DECIMAL(10,2),
    DepartmentId INT
);

INSERT INTO Employees
VALUES
(1, 'Alice',   55000, 101),
(2, 'Bob',     70000, 102),
(3, 'Charlie', 65000, 103),
(4, 'David',   80000, 102),
(5, 'Eva',     50000, 101),
(6, 'Frank',   90000, 999),   -- Invalid Department
(7, 'Grace',   75000, NULL),  -- No Department
(8, 'Henry',   60000, 104);


SELECT * FROM Employees E
WHERE EXISTS(
	SELECT * FROM Departments D
    WHERE D.DepartmentId = E.DepartmentId
);

SELECT * FROM Employees E
WHERE NOT EXISTS(
	SELECT * FROM Departments D
    WHERE D.DepartmentId = E.DepartmentId
);


SELECT * FROM Employees E
WHERE DepartmentId IN (
	SELECT DepartmentId
    FROM Departments
);



--  ANY

SELECT * FROM Employees E
WHERE Salary > ANY (
	SELECT Salary 
    FROM Employees
    WHERE DepartmentId = 101
);


SELECT * FROM Employees E
WHERE Salary > ALL (
	SELECT Salary 
    FROM Employees
    WHERE DepartmentId = 101
);



-- 	CORELATED SUBQUERIES

SELECT EmployeeName, Salary, DepartmentId
FROM Employees E
WHERE Salary >
(
    SELECT AVG(Salary)
    FROM Employees
    WHERE DepartmentId = E.DepartmentId
);