
-- 05_Aggregate Functions

-- An Aggregate Function performs a calculation on multiple rows and returns one result.
--  1. COUNT()
-- 2. SUM()
-- 3. AVG()
-- 4. MIN()
-- 5. MAX()
CREATE TABLE Employees(
    EmployeeId INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeName VARCHAR(100) NOT NULL,
    Department VARCHAR(50),
    Salary DECIMAL(10,2),
    JoiningDate DATE
);

INSERT INTO Employees
(EmployeeName, Department, Salary, JoiningDate)
VALUES
('Alice','HR',50000,'2022-01-10'),
('Bob','IT',70000,'2021-03-15'),
('Charlie','Finance',60000,'2023-07-20'),
('David','IT',80000,'2020-11-05'),
('Eva','HR',55000,'2024-02-18'),
('Frank','Finance',NULL,'2022-09-12');




1. SELECT COUNT(*) FROM Employees
2. SELECT COUNT(*) FROM Employee WHERE Salary IS NOT NULL
3. SELECT SUM(Salary) FROM Employees
4. SELECT AVG(Salary) FROM Employees
5. SELECT Salaray FROM Employee ORDER BY Salary DESC LIMIT 1
6. SELECT Salary FROM Employee ORDER BY Salary ASC LIMIT 1
7. SELECT COUNT(*) FROM Employees WHERE Department = 'IT'
8. SELECT SUM(Salary) FROM Employees WHERE Department = 'HR'









