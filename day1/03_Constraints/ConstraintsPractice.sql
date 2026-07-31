
-- 	================== CONSTRAINTS ================== 


-- NOT NULL IS NULL IS NOT NULL
 
CREATE TABLE Students(
	StudentId INT NOT NULL,
    StudentName VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    Age int
);


INSERT INTO Students
(StudentId, StudentName, Email, Age)
VALUES
(1, 'Rahul', 'rahul@gmail.com', 20);

INSERT INTO Students
(StudentId, StudentName)
VALUES
(1, 'Rahul');

INSERT INTO Students
(StudentId, Email, Age)
VALUES
(1, 'rahul@gmail.com', 20);

INSERT INTO Students
(StudentId, StudentName, Email, Age)
VALUES
(NULL, 'Rahul', 'rahul@gmail.com', 20);


--  default 

 
CREATE TABLE Products (
    ProductId INT NOT NULL,
    ProductName VARCHAR(100) NOT NULL,
    Price DECIMAL(10,2) DEFAULT 0.00,
    Stock INT DEFAULT 0,
    Status VARCHAR(20) DEFAULT 'Available'
);

INSERT INTO Products (ProductId, ProductName)
VALUES (1, 'Wireless Mouse');


INSERT INTO Products (ProductId, ProductName, Price)
VALUES (2, 'Monitor', 15000);

INSERT INTO Products (ProductId, ProductName, Price, Stock, Status)
VALUES (1, 'Wireless Mouse', 25.50, 50, 'Available');

INSERT INTO Products (ProductId, ProductName, Price, Stock, Status)
VALUES (1, 'Wireless Mouse', NULL, NULL, NULL);




--  UNIQUE

CREATE TABLE Users(
    UserId INT NOT NULL ,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(100) NOT NULL
);


INSERT INTO Users
VALUES
(1,'rudresh','rudresh@gmail.com','12345');

INSERT INTO Users
VALUES
(2,'rudresh','another@gmail.com','12345');
 
 
INSERT INTO Users
VALUES
(3,'john','rudresh@gmail.com','12345');

INSERT INTO Users
VALUES
(4,'alice','alice@gmail.com','12345');



--  check 

CREATE TABLE Students (
    StudentId INT NOT NULL,
    StudentName VARCHAR(100) NOT NULL,
    Age INT CHECK (Age >= 18),
    Marks INT CHECK (Marks BETWEEN 0 AND 100),
    Gender VARCHAR(10) CHECK (Gender IN ('Male', 'Female', 'Other'))
);


INSERT INTO Students
VALUES
(1,'Rahul',20,85,'Male');

INSERT INTO Students
VALUES
(2,'Amit',17,90,'Male');

INSERT INTO Students
VALUES
(3,'Priya',22,110,'Female');

INSERT INTO Students
VALUES
(4,'Neha',21,95,'Girl');

UPDATE Students
SET Marks = 120
WHERE StudentId = 1;

UPDATE Students
SET Gender = 'Unknown'
WHERE StudentId = 1;

-- PRIMARY KEY
DROP TABLE Employees;


CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    Salary DECIMAL(10,2) CHECK (Salary > 0),
    Department VARCHAR(50) DEFAULT 'General'
);
 
INSERT INTO Employees
(EmployeeId, EmployeeName, Email, Salary)
VALUES
(101, 'Alice', 'alice@gmail.com', 60000);


INSERT INTO Employees
(EmployeeId, EmployeeName, Email, Salary)
VALUES
(101, 'Bob', 'bob@gmail.com', 50000);

INSERT INTO Employees
(EmployeeId, EmployeeName, Email, Salary)
VALUES
(NULL, 'Charlie', 'charlie@gmail.com', 70000);

INSERT INTO Employees
(EmployeeId, EmployeeName, Email, Salary)
VALUES
(102, 'David', 'alice@gmail.com', 80000);

INSERT INTO Employees
(EmployeeId, EmployeeName, Email, Salary)
VALUES
(103, 'Eva', 'eva@gmail.com', -1000);

ALTER TABLE Employees MODIFY Salary DECIMAL(10,2) CHECK(Salary>0) NOT NULL DEFAULT 20000;



CREATE TABLE Customers(
	CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerName VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    City VARCHAR(50) DEFAULT 'Bhopal'
);

INSERT INTO Customers(CustomerName, Email)
VALUES
('Rudresh', 'rudresh@gmail.com');

INSERT INTO Customers(CustomerName, Email)
VALUES
('Rahul', 'rahul@gmail.com');


INSERT INTO Customers(CustomerId, CustomerName, Email)
VALUES
(100, 'Amit', 'amit@gmail.com');

INSERT INTO Customers(CustomerName, Email)
VALUES
('Priya', 'priya@gmail.com');

DELETE FROM Customers
WHERE CustomerId = 2;

INSERT INTO Customers(CustomerName, Email)
VALUES
('Neha', 'neha@gmail.com');


INSERT INTO Customers(CustomerId, CustomerName)
VALUES (50, 'John');

DELETE FROM Customers
WHERE CustomerId = 50;

INSERT INTO Customers(CustomerName)
VALUES ('Alice');




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