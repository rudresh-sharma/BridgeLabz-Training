-- ============================================================
-- USER DEFINED FUNCTIONS (MySQL)
-- ============================================================

-- Sample Table
CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100),
    Salary DECIMAL(10,2),
    Department VARCHAR(50)
);

INSERT INTO Employees VALUES
(1,'Alice',50000,'HR'),
(2,'Bob',65000,'IT'),
(3,'Charlie',70000,'Finance'),
(4,'David',60000,'IT'),
(5,'Eva',55000,'Marketing');


-- ============================================================
-- WHAT IS A FUNCTION?
-- ------------------------------------------------------------
-- A User Defined Function (UDF) is a database object that
-- accepts input parameters and MUST return exactly one value.
--
-- Similar to a method in Java that returns a value.
--
-- Functions are mainly used for:
-- • Calculations
-- • Data Formatting
-- • Business Logic
-- • Reusable Expressions
-- ============================================================



-- ============================================================
-- DELIMITER
-- ------------------------------------------------------------
-- Functions contain multiple SQL statements,
-- therefore change the delimiter temporarily.
-- ============================================================

DELIMITER //

-- Function Here

DELIMITER ;



-- ============================================================
-- 1. SIMPLE FUNCTION
-- ============================================================

DELIMITER //

CREATE FUNCTION SquareNumber(
    Number INT
)
RETURNS INT
DETERMINISTIC
BEGIN

    RETURN Number * Number;

END //

DELIMITER ;


SELECT SquareNumber(8);



-- ============================================================
-- 2. FUNCTION RETURNING STRING
-- ============================================================

DELIMITER //

CREATE FUNCTION FullGreeting(
    Name VARCHAR(100)
)
RETURNS VARCHAR(200)
DETERMINISTIC
BEGIN

    RETURN CONCAT('Welcome ',Name);

END //

DELIMITER ;


SELECT FullGreeting('Rudresh');



-- ============================================================
-- 3. FUNCTION USING TABLE DATA
-- ============================================================

DELIMITER //

CREATE FUNCTION BonusSalary(
    Salary DECIMAL(10,2)
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN

    RETURN Salary * 1.10;

END //

DELIMITER ;


SELECT
    EmployeeName,
    Salary,
    BonusSalary(Salary) AS NewSalary
FROM Employees;



-- ============================================================
-- 4. FUNCTION USING IF
-- ============================================================

DELIMITER //

CREATE FUNCTION SalaryCategory(
    Salary DECIMAL(10,2)
)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN

    IF Salary >= 60000 THEN
        RETURN 'High';
    ELSE
        RETURN 'Normal';
    END IF;

END //

DELIMITER ;


SELECT
    EmployeeName,
    SalaryCategory(Salary)
FROM Employees;



-- ============================================================
-- 5. FUNCTION USING CASE
-- ============================================================

DELIMITER //

CREATE FUNCTION Grade(
    Marks INT
)
RETURNS CHAR(1)
DETERMINISTIC
BEGIN

    RETURN
    CASE
        WHEN Marks >= 90 THEN 'A'
        WHEN Marks >= 75 THEN 'B'
        WHEN Marks >= 60 THEN 'C'
        ELSE 'F'
    END;

END //

DELIMITER ;


SELECT Grade(82);



-- ============================================================
-- 6. USING FUNCTION INSIDE SELECT
-- ============================================================

SELECT
    EmployeeName,
    BonusSalary(Salary)
FROM Employees;



-- ============================================================
-- 7. USING FUNCTION INSIDE WHERE
-- ============================================================

SELECT *
FROM Employees
WHERE BonusSalary(Salary) > 70000;



-- ============================================================
-- 8. USING FUNCTION INSIDE ORDER BY
-- ============================================================

SELECT
    EmployeeName,
    BonusSalary(Salary)
FROM Employees
ORDER BY BonusSalary(Salary) DESC;



-- ============================================================
-- 9. USING FUNCTION INSIDE GROUP BY
-- ============================================================

SELECT
    SalaryCategory(Salary),
    COUNT(*)
FROM Employees
GROUP BY SalaryCategory(Salary);



-- ============================================================
-- 10. SHOW FUNCTIONS
-- ============================================================

SHOW FUNCTION STATUS;

SHOW FUNCTION STATUS
WHERE Db = DATABASE();



-- ============================================================
-- 11. VIEW FUNCTION SOURCE CODE
-- ============================================================

SHOW CREATE FUNCTION BonusSalary;



-- ============================================================
-- 12. DROP FUNCTION
-- ============================================================

DROP FUNCTION BonusSalary;



-- ============================================================
-- DETERMINISTIC
-- ------------------------------------------------------------
-- Same Input  -> Same Output
-- ============================================================

DELIMITER //

CREATE FUNCTION CubeNumber(
    Number INT
)
RETURNS INT
DETERMINISTIC
BEGIN

    RETURN Number * Number * Number;

END //

DELIMITER ;


SELECT CubeNumber(4);



-- ============================================================
-- FUNCTION EXAMPLES
-- ============================================================

-- Even / Odd

DELIMITER //

CREATE FUNCTION IsEven(
    Number INT
)
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN

    IF Number % 2 = 0 THEN
        RETURN 'Even';
    ELSE
        RETURN 'Odd';
    END IF;

END //

DELIMITER ;

SELECT IsEven(15);



-- Tax Calculator

DELIMITER //

CREATE FUNCTION TaxAmount(
    Salary DECIMAL(10,2)
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN

    RETURN Salary * 0.10;

END //

DELIMITER ;

SELECT TaxAmount(50000);



-- Grade Calculator

DELIMITER //

CREATE FUNCTION StudentGrade(
    Marks INT
)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN

    RETURN
    CASE
        WHEN Marks >= 90 THEN 'Excellent'
        WHEN Marks >= 75 THEN 'Good'
        WHEN Marks >= 60 THEN 'Average'
        ELSE 'Poor'
    END;

END //

DELIMITER ;

SELECT StudentGrade(82);



-- ============================================================
-- FUNCTION vs STORED PROCEDURE
-- ============================================================

/*

FUNCTION
--------
• Called using SELECT
• Must return exactly ONE value
• RETURN statement is mandatory
• Only IN parameters
• Mainly used for calculations
• Used inside SELECT, WHERE, ORDER BY, GROUP BY
• Should not modify table data

Example:

SELECT BonusSalary(50000);


------------------------------------------------------------

STORED PROCEDURE
----------------
• Called using CALL
• May return zero, one or many values
• RETURN is optional
• Supports IN, OUT and INOUT parameters
• Can perform INSERT, UPDATE, DELETE
• Used for business operations

Example:

CALL AddEmployee(...);

*/


-- ============================================================
-- DETERMINISTIC vs NON-DETERMINISTIC
-- ============================================================

/*

DETERMINISTIC
-------------
Same Input -> Same Output

Example

SquareNumber(5)

Always returns

25


NON-DETERMINISTIC
-----------------

Output may change even with same input.

Examples

NOW()

CURDATE()

RAND()

*/


-- ============================================================
-- ADVANTAGES
-- ============================================================

/*

1. Reusable
2. Reduces duplicate code
3. Easy maintenance
4. Can be used inside SQL queries
5. Improves readability

*/


-- ============================================================
-- DISADVANTAGES
-- ============================================================

/*

1. Returns only one value.
2. Cannot return result sets like procedures.
3. Should not perform database modifications.
4. Complex functions become difficult to maintain.

*/


-- ============================================================
-- INTERVIEW QUESTIONS
-- ============================================================

/*

Q1. What is a User Defined Function?

A database object that accepts parameters
and returns exactly one value.

------------------------------------------------------------

Q2. Difference between Function and Procedure?

Function
--------
• SELECT
• Returns exactly one value
• RETURN mandatory
• Used for calculations

Procedure
---------
• CALL
• Can return multiple values
• Used for business logic
• Supports INSERT, UPDATE, DELETE

------------------------------------------------------------

Q3. Can a Function perform INSERT, UPDATE or DELETE?

Technically, functions have restrictions and are intended
for computations. In practice, use Stored Procedures for
database modifications.

------------------------------------------------------------

Q4. Which keyword is mandatory in every Function?

RETURN

------------------------------------------------------------

Q5. Why do we write DETERMINISTIC?

Because for the same input,
the function always returns the same output.

------------------------------------------------------------

Q6. How do you execute a Function?

SELECT FunctionName(arguments);

Example

SELECT SquareNumber(10);

------------------------------------------------------------

Q7. Which command shows all Functions?

SHOW FUNCTION STATUS;

------------------------------------------------------------

Q8. Which command displays Function source code?

SHOW CREATE FUNCTION FunctionName;

------------------------------------------------------------

Q9. Which command deletes a Function?

DROP FUNCTION FunctionName;

*/


-- ============================================================
-- MOST IMPORTANT FOR INTERVIEWS
-- ============================================================

/*

★★★★★
CREATE FUNCTION
RETURNS
RETURN
DETERMINISTIC
Function vs Procedure

★★★★☆
IF
CASE
Using Functions inside SELECT
Using Functions inside WHERE

★★★☆☆
SHOW FUNCTION STATUS
SHOW CREATE FUNCTION
DROP FUNCTION

*/