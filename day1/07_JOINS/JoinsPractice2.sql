CREATE DATABASE IF NOT EXISTS LearnJoin;

USE LearnJoin;

DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Departments;


-- ===========================
-- Departments
-- ===========================

CREATE TABLE Departments (
    DepartmentId INT PRIMARY KEY,
    DepartmentName VARCHAR(50) NOT NULL
);

INSERT INTO Departments VALUES
(101,'IT'),
(102,'HR'),
(103,'Sales'),
(104,'Finance'),
(105,'Marketing');

-- ===========================
-- Employees
-- ===========================

CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(50) NOT NULL,
    Salary INT NOT NULL,
    DepartmentId INT,

    CONSTRAINT fk_employee_department
    FOREIGN KEY (DepartmentId)
    REFERENCES Departments(DepartmentId)
);

INSERT INTO Employees VALUES
(1,'Alice',50000,101),
(2,'Bob',65000,101),
(3,'Charlie',55000,102),
(4,'David',70000,103),
(5,'Eva',60000,NULL),
(6,'Frank',75000,104),
(7,'Grace',58000,102),
(8,'Henry',62000,NULL);

-- ===========================
-- Projects
-- ===========================

CREATE TABLE Projects (
    ProjectId INT PRIMARY KEY,
    ProjectName VARCHAR(50) NOT NULL,
    EmployeeId INT,

    CONSTRAINT fk_project_employee
    FOREIGN KEY (EmployeeId)
    REFERENCES Employees(EmployeeId)
);

INSERT INTO Projects VALUES
(1,'Website',1),
(2,'Mobile App',2),
(3,'Payroll',4),
(4,'Recruitment',3),
(5,'AI System',7);


-- 1. Show all data from Departments
SELECT * FROM Departments;

-- 2. Show all data from Employees
SELECT * FROM Employees;

-- 3. Show all data from Projects
SELECT * FROM Projects;


-- =============== INNER JOIN ==================


-- Show every employee along with their department name.

SELECT EmployeeName, DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId;




-- Show Employee Name  ,Salary, Department Name

SELECT EmployeeName, Salary, DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId;


-- Show employees who work in the IT department.

SELECT EmployeeName, Salary, DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
WHERE DepartmentName = 'IT';


-- Show employees whose salary is greater than 60000 along with department names.


SELECT EmployeeName, Salary, DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
WHERE Salary > 60000;



-- Display employees ordered by Department Name Employee Name

SELECT E.EmployeeName, E.Salary, D.DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
ORDER BY D.DepartmentName ASC, E.EmployeeName ASC;



-- Count the number of employees in each department.

SELECT D.DepartmentName, COUNT(E.EmployeeID) AS NoOfEmployee
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName;

-- Find the average salary of each department.

SELECT D.DepartmentName, AVG(E.Salary) AS AverageSalary
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName;



-- Find the highest-paid employee in every department.


SELECT D.DepartmentName, MAX(E.Salary) AS HighestSalary
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName;



-- Find the lowest-paid employee in every department.

SELECT D.DepartmentName, MIN(E.Salary) AS LowestSalary
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName;



-- Show only departments having more than one employee.

SELECT D.DepartmentName
FROM Employees E
INNER JOIN Departments D
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName
HAVING COUNT(E.EmployeeId) > 1;




-- =============== LEFT JOIN ==================


-- Show all employees along with department names.
-- Employees without departments should also appear.

SELECT E.EmployeeName, D.DepartmentName
FROM Employees E 
LEFT JOIN Departments D 
ON E.DepartmentId = D.DepartmentId;


-- Find employees who are not assigned to any department.

SELECT E.EmployeeName, D.DepartmentName
FROM Employees E 
LEFT JOIN Departments D 
ON E.DepartmentId = D.DepartmentId
WHERE E.DepartmentId IS NULL;



-- Show every department along with employee names.

SELECT  D.DepartmentName, E.EmployeeName
FROM Departments D 
LEFT JOIN Employees E 
ON E.DepartmentId = D.DepartmentId;


-- Find departments that have no employees.

SELECT  D.DepartmentName, E.EmployeeName
FROM Departments D 
LEFT JOIN Employees E 
ON E.DepartmentId = D.DepartmentId
WHERE E.EmployeeId IS NULL;

-- Count employees in every department.
-- Departments having zero employees should also appear.
SELECT  D.DepartmentName, COUNT(E.EmployeeId) NoOfEmployee
FROM Departments D 
LEFT JOIN Employees E 
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName;

-- Find departments where average salary is greater than 55000.

SELECT  D.DepartmentName
FROM Departments D 
LEFT JOIN Employees E 
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName
HAVING AVG(E.Salary) > 55000;


-- Show department names even if no employee exists.

SELECT  D.DepartmentName, COUNT(E.EmployeeID) AS NoOfEmployee
FROM Departments D 
LEFT JOIN Employees E 
ON E.DepartmentId = D.DepartmentId
GROUP BY D.DepartmentName;



-- =============== RIGHT JOIN ==================

-- Show every department with employee names.

SELECT D.DepartmentName, E.EmployeeName
FROM Employees E
RIGHT JOIN Departments D
ON E.DepartmentID = D.DepartmentId;

-- Find departments without employees using RIGHT JOIN.

SELECT D.DepartmentName
FROM Employees E
RIGHT JOIN Departments D
ON E.DepartmentID = D.DepartmentId
WHERE E.EmployeeID IS NULL;


-- Return all departments whether employees exist or not.

SELECT D.DepartmentName
FROM Employees E
RIGHT JOIN Departments D
ON E.DepartmentID = D.DepartmentId;


-- =================== CROSS JOIN =============

-- Generate every Employee-Department combination. 

SELECT E.EmployeeName, D.DepartmentName
FROM Employees E
CROSS JOIN Departments D;


-- Generate every Project-Department combination.

SELECT  D.DepartmentName, P.ProjectName
FROM Projects P
CROSS JOIN Departments D;


-- Generate every Employee-Project combination.

SELECT  E.EmployeeName, P.ProjectName
FROM Projects P
CROSS JOIN Employees E;




-- =================== SELF JOIN ================

CREATE TABLE EmployeeManager(
EmployeeId INT PRIMARY KEY,
EmployeeName VARCHAR(50),
ManagerId INT,
FOREIGN KEY (ManagerId)
REFERENCES EmployeeManager(EmployeeId)
);

INSERT INTO EmployeeManager VALUES
(1,'Alice',NULL),
(2,'Bob',1),
(3,'Charlie',1),
(4,'David',2),
(5,'Eva',2),
(6,'Frank',3),
(7,'Grace',3),
(8,'Henry',4); 

-- Show every employee with their manager's name.


SELECT 
    E.EmployeeName AS Employee, 
    M.EmployeeName AS Manager
FROM EmployeeManager E
INNER JOIN EmployeeManager M 
ON E.ManagerId = M.EmployeeId;



-- Find employees having no manager.

SELECT 
    E.EmployeeName AS Employee, 
    IFNULL(M.EmployeeName, 'No Manager / CEO') AS Manager
FROM EmployeeManager E
LEFT JOIN EmployeeManager M 
ON E.ManagerId = M.EmployeeId
WHERE E.ManagerId IS NULL;


-- Find all employees managed by Alice.

SELECT 
    E.EmployeeName AS Employee, 
    M.EmployeeName AS Manager
FROM EmployeeManager E
INNER JOIN EmployeeManager M 
ON E.ManagerId = M.EmployeeId
WHERE M.EmployeeName = 'Alice';


-- Find managers managing more than one employee.

SELECT 
    M.EmployeeName AS Manager,
    COUNT(E.EmployeeId) AS NoOfEmployeesManaged
FROM EmployeeManager E
INNER JOIN EmployeeManager M 
ON E.ManagerId = M.EmployeeId
GROUP BY M.ManagerId, M.EmployeeName
HAVING NoOfEmployeesManaged>1;



-- Find the manager with the highest number of employees.

SELECT 
    M.EmployeeName AS Manager,
    COUNT(E.EmployeeId) AS NoOfEmployeesManaged
FROM EmployeeManager E
INNER JOIN EmployeeManager M 
ON E.ManagerId = M.EmployeeId
GROUP BY M.EmployeeId, M.EmployeeName
ORDER BY NoOfEmployeesManaged DESC
LIMIT 1;


-- Show manager name and number of employees under each manager.

SELECT M.EmployeeName, COUNT(E.EmployeeId) AS NoOfEmployee
FROM EmployeeManager E
INNER JOIN EmployeeManager M
ON E.ManagerId = M.ManagerID
GROUP BY M.ManagerId, M.EmployeeName;



-- LEVEL 6 Mixed


-- Show employee, department and project together. 


SELECT * 
FROM Employees E
LEFT JOIN Departments D ON E.DepartmentId = D.DepartmentId
LEFT JOIN Projects P ON E.EmployeeId = P.EmployeeId;




-- SELECT * 
-- FROM Employees E
-- LEFT JOIN Departments D
-- ON E.DepartmentId = D.DepartmentId


-- UNION

-- SELECT *
-- FROM Employees E
-- RIGHT JOIN Projects P
-- ON P.EmployeeId = E.EmployeeId
-- ;


-- SELECT * FROM Employees, Departments, Projects;




-- Find employees who have no project.

SELECT E.EmployeeName,P.ProjectId, P.ProjectName
FROM Employees E
LEFT JOIN Projects P
ON E.EmployeeId = P.EmployeeId
WHERE P.ProjectId IS NULL;




-- Find departments where nobody earns less than 55000.



SELECT D.DepartmentName
FROM Departments D
LEFT JOIN Employees E
ON D.DepartmentId = E.DepartmentId
GROUP BY D.DepartmentId, D.DepartmentName
HAVING MIN(E.Salary) > 55000 OR MIN(E.Salary) IS NULL;


-- find department where no employee is present

SELECT D.DepartmentName, COUNT(E.EmployeeId) AS NoOfEmployee
FROM Departments D
LEFT JOIN Employees E
ON D.DepartmentId = E.DepartmentID
GROUP BY D.DepartmentId, D.DepartmentName
HAVING NoOfEmployee <1;
 
 
 
SELECT * FROM Employees E
RIGHT JOIN Departments D
ON E.DepartmentId = D.DepartmentId;