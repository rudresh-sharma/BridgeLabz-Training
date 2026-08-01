-- ============================================================
-- STORED PROCEDURES (MySQL)
-- ============================================================

-- Sample Table
CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100),
    Department VARCHAR(50),
    Salary DECIMAL(10,2)
);

INSERT INTO Employees VALUES
(1,'Alice','HR',50000),
(2,'Bob','IT',65000),
(3,'Charlie','Finance',70000),
(4,'David','IT',60000),
(5,'Eva','Marketing',55000);


-- ============================================================
-- WHAT IS A STORED PROCEDURE?
-- ------------------------------------------------------------
-- A Stored Procedure is a named block of SQL statements
-- stored in the database.
--
-- It is similar to a method/function in programming.
--
-- Advantages:
-- • Reusable
-- • Faster execution
-- • Better security
-- • Reduces duplicate SQL
-- • Centralized business logic
-- ============================================================



-- ============================================================
-- DELIMITER
-- ------------------------------------------------------------
-- Procedures contain multiple SQL statements.
-- So we temporarily change the delimiter.
-- ============================================================

DELIMITER //

-- Procedure goes here

DELIMITER ;



-- ============================================================
-- 1. PROCEDURE WITHOUT PARAMETERS
-- ============================================================

DELIMITER //

CREATE PROCEDURE GetEmployees()
BEGIN
    SELECT *
    FROM Employees;
END //

DELIMITER ;

CALL GetEmployees();



-- ============================================================
-- 2. PROCEDURE WITH INPUT PARAMETER (IN)
-- ============================================================

DELIMITER //

CREATE PROCEDURE GetEmployeesByDepartment(
    IN DeptName VARCHAR(50)
)
BEGIN
    SELECT *
    FROM Employees
    WHERE Department = DeptName;
END //

DELIMITER ;

CALL GetEmployeesByDepartment('IT');



-- ============================================================
-- 3. PROCEDURE WITH MULTIPLE INPUT PARAMETERS
-- ============================================================

DELIMITER //

CREATE PROCEDURE EmployeesAboveSalary(
    IN MinimumSalary DECIMAL(10,2),
    IN Dept VARCHAR(50)
)
BEGIN
    SELECT *
    FROM Employees
    WHERE Salary >= MinimumSalary
      AND Department = Dept;
END //

DELIMITER ;

CALL EmployeesAboveSalary(60000,'IT');



-- ============================================================
-- 4. INSERT USING PROCEDURE
-- ============================================================

DELIMITER //

CREATE PROCEDURE AddEmployee(
    IN Id INT,
    IN Name VARCHAR(100),
    IN Dept VARCHAR(50),
    IN Sal DECIMAL(10,2)
)
BEGIN
    INSERT INTO Employees
    VALUES(Id,Name,Dept,Sal);
END //

DELIMITER ;

CALL AddEmployee(6,'John','HR',48000);



-- ============================================================
-- 5. UPDATE USING PROCEDURE
-- ============================================================

DELIMITER //

CREATE PROCEDURE UpdateSalary(
    IN EmpId INT,
    IN NewSalary DECIMAL(10,2)
)
BEGIN
    UPDATE Employees
    SET Salary = NewSalary
    WHERE EmployeeId = EmpId;
END //

DELIMITER ;

CALL UpdateSalary(2,75000);



-- ============================================================
-- 6. DELETE USING PROCEDURE
-- ============================================================

DELIMITER //

CREATE PROCEDURE DeleteEmployee(
    IN EmpId INT
)
BEGIN
    DELETE FROM Employees
    WHERE EmployeeId = EmpId;
END //

DELIMITER ;

CALL DeleteEmployee(5);



-- ============================================================
-- 7. OUT PARAMETER
-- ------------------------------------------------------------
-- Used to return a value from a procedure.
-- ============================================================

DELIMITER //

CREATE PROCEDURE EmployeeCount(
    OUT TotalEmployees INT
)
BEGIN
    SELECT COUNT(*)
    INTO TotalEmployees
    FROM Employees;
END //

DELIMITER ;

CALL EmployeeCount(@Total);

SELECT @Total;



-- ============================================================
-- 8. INOUT PARAMETER
-- ------------------------------------------------------------
-- Works as both input and output.
-- ============================================================

DELIMITER //

CREATE PROCEDURE IncreaseSalary(
    INOUT Amount DECIMAL(10,2)
)
BEGIN
    SET Amount = Amount + 5000;
END //

DELIMITER ;

SET @Salary = 45000;

CALL IncreaseSalary(@Salary);

SELECT @Salary;



-- ============================================================
-- 9. SHOW ALL PROCEDURES
-- ============================================================

SHOW PROCEDURE STATUS;

-- Procedures of Current Database

SHOW PROCEDURE STATUS
WHERE Db = DATABASE();



-- ============================================================
-- 10. VIEW PROCEDURE CODE
-- ============================================================

SHOW CREATE PROCEDURE GetEmployees;



-- ============================================================
-- 11. DROP PROCEDURE
-- ============================================================

DROP PROCEDURE GetEmployees;



-- ============================================================
-- PARAMETER TYPES
-- ============================================================

/*

IN
--
Input only.

Example

CALL GetEmployeesByDepartment('IT');


OUT
---
Returns a value.

Example

CALL EmployeeCount(@Total);

SELECT @Total;


INOUT
-----
Acts as both Input and Output.

Example

SET @Salary = 45000;

CALL IncreaseSalary(@Salary);

SELECT @Salary;

*/



-- ============================================================
-- PROCEDURE vs FUNCTION
-- ============================================================

/*

PROCEDURE
---------
• Called using CALL
• Can return zero, one or many values
• Can use IN, OUT and INOUT parameters
• Can perform INSERT, UPDATE and DELETE
• May or may not return a value

FUNCTION
--------
• Called inside SQL statements
• Must return exactly one value
• Only IN parameters
• Mainly used for calculations
• Must return a value

*/



-- ============================================================
-- ADVANTAGES
-- ============================================================

/*

1. Code Reusability
2. Better Performance
3. Better Security
4. Centralized Business Logic
5. Less Network Traffic

*/



-- ============================================================
-- DISADVANTAGES
-- ============================================================

/*

1. Debugging is difficult.
2. Database dependent.
3. Complex procedures become hard to maintain.

*/



-- ============================================================
-- INTERVIEW QUESTIONS
-- ============================================================

/*

Q1. What is a Stored Procedure?

A Stored Procedure is a named block of SQL statements
stored inside the database.

------------------------------------------------------------

Q2. Why do we use Stored Procedures?

• Reusability
• Performance
• Security
• Centralized Business Logic

------------------------------------------------------------

Q3. How do you execute a procedure?

CALL ProcedureName();

------------------------------------------------------------

Q4. What are IN, OUT and INOUT parameters?

IN     -> Input parameter
OUT    -> Output parameter
INOUT  -> Both Input and Output

------------------------------------------------------------

Q5. Which keyword is used to create a procedure?

CREATE PROCEDURE

------------------------------------------------------------

Q6. Which command deletes a procedure?

DROP PROCEDURE ProcedureName;

------------------------------------------------------------

Q7. Which command displays procedure code?

SHOW CREATE PROCEDURE ProcedureName;

------------------------------------------------------------

Q8. Why is DELIMITER used?

Because procedures contain multiple SQL statements.
Changing the delimiter prevents MySQL from ending
the procedure definition at the first semicolon.

*/



-- ============================================================
-- MOST IMPORTANT FOR INTERVIEWS
-- ============================================================

/*

★★★★★
CREATE PROCEDURE
CALL
IN Parameter
OUT Parameter
INOUT Parameter
DELIMITER
Procedure vs Function

★★★★☆
INSERT Procedure
UPDATE Procedure
DELETE Procedure
SHOW CREATE PROCEDURE

★★★☆☆
SHOW PROCEDURE STATUS
DROP PROCEDURE

*/