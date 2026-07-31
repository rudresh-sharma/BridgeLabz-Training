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

