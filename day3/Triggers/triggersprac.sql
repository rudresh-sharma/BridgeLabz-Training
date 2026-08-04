-- ============================================================
-- TRIGGERS (MySQL)
-- ============================================================

-- Sample Tables

CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100),
    Salary DECIMAL(10,2)
);

INSERT INTO Employees VALUES
(1,'Alice',50000),
(2,'Bob',60000);

CREATE TABLE EmployeeLogs (
    LogId INT AUTO_INCREMENT PRIMARY KEY,
    Message VARCHAR(255),
    LogTime DATETIME
);


-- ============================================================
-- WHAT IS A TRIGGER?
-- ------------------------------------------------------------
-- A Trigger is a special database object that
-- automatically executes when an event occurs
-- on a table.
--
-- Trigger executes automatically.
-- It cannot be called manually.
--
-- Events:
-- • INSERT
-- • UPDATE
-- • DELETE
--
-- Timing:
-- • BEFORE
-- • AFTER
-- ============================================================



-- ============================================================
-- TYPES OF TRIGGERS
-- ============================================================

/*

BEFORE INSERT
AFTER INSERT

BEFORE UPDATE
AFTER UPDATE

BEFORE DELETE
AFTER DELETE

*/


-- ============================================================
-- NEW AND OLD KEYWORDS
-- ============================================================

/*

NEW
----
Represents the new row.

Available in:
• INSERT
• UPDATE

Examples

NEW.EmployeeName
NEW.Salary


OLD
----
Represents the previous row.

Available in:
• UPDATE
• DELETE

Examples

OLD.EmployeeName
OLD.Salary

*/


-- ============================================================
-- 1. AFTER INSERT TRIGGER
-- ------------------------------------------------------------
-- Automatically logs whenever a new employee
-- is inserted.
-- ============================================================

DELIMITER //

CREATE TRIGGER AfterEmployeeInsert

AFTER INSERT
ON Employees

FOR EACH ROW

BEGIN

    INSERT INTO EmployeeLogs(Message, LogTime)

    VALUES(
        CONCAT('Employee Added : ', NEW.EmployeeName),
        NOW()
    );

END //

DELIMITER ;


-- Test

INSERT INTO Employees
VALUES
(3,'Charlie',70000);

SELECT * FROM EmployeeLogs;



-- ============================================================
-- 2. AFTER UPDATE TRIGGER
-- ------------------------------------------------------------
-- Logs whenever an employee salary changes.
-- ============================================================

DELIMITER //

CREATE TRIGGER AfterSalaryUpdate

AFTER UPDATE
ON Employees

FOR EACH ROW

BEGIN

    INSERT INTO EmployeeLogs(Message, LogTime)

    VALUES(

        CONCAT(
            OLD.EmployeeName,
            ' Salary changed from ',
            OLD.Salary,
            ' to ',
            NEW.Salary
        ),

        NOW()

    );

END //

DELIMITER ;


-- Test

UPDATE Employees
SET Salary = 75000
WHERE EmployeeId = 2;

SELECT * FROM EmployeeLogs;



-- ============================================================
-- 3. BEFORE INSERT TRIGGER
-- ------------------------------------------------------------
-- Prevent negative salary.
-- ============================================================

DELIMITER //

CREATE TRIGGER CheckSalary

BEFORE INSERT
ON Employees

FOR EACH ROW

BEGIN

    IF NEW.Salary < 0 THEN

        SET NEW.Salary = 0;

    END IF;

END //

DELIMITER ;


-- Test

INSERT INTO Employees
VALUES
(4,'David',-5000);

SELECT * FROM Employees;



-- ============================================================
-- 4. BEFORE UPDATE TRIGGER
-- ------------------------------------------------------------
-- Prevent updating salary to negative value.
-- ============================================================

DELIMITER //

CREATE TRIGGER PreventNegativeSalaryUpdate

BEFORE UPDATE
ON Employees

FOR EACH ROW

BEGIN

    IF NEW.Salary < 0 THEN

        SET NEW.Salary = OLD.Salary;

    END IF;

END //

DELIMITER ;


-- Test

UPDATE Employees
SET Salary = -1000
WHERE EmployeeId = 1;



-- ============================================================
-- 5. BEFORE DELETE TRIGGER
-- ------------------------------------------------------------
-- Log employee before deleting.
-- ============================================================

DELIMITER //

CREATE TRIGGER BeforeEmployeeDelete

BEFORE DELETE
ON Employees

FOR EACH ROW

BEGIN

    INSERT INTO EmployeeLogs(Message, LogTime)

    VALUES(

        CONCAT(
            'Deleting Employee : ',
            OLD.EmployeeName
        ),

        NOW()

    );

END //

DELIMITER ;


-- Test

DELETE FROM Employees
WHERE EmployeeId = 2;

SELECT * FROM EmployeeLogs;



-- ============================================================
-- 6. AFTER DELETE TRIGGER
-- ------------------------------------------------------------
-- Log employee after deletion.
-- ============================================================

DELIMITER //

CREATE TRIGGER AfterEmployeeDelete

AFTER DELETE
ON Employees

FOR EACH ROW

BEGIN

    INSERT INTO EmployeeLogs(Message, LogTime)

    VALUES(

        CONCAT(
            'Employee Deleted : ',
            OLD.EmployeeName
        ),

        NOW()

    );

END //

DELIMITER ;


-- ============================================================
-- SHOW ALL TRIGGERS
-- ============================================================

SHOW TRIGGERS;



-- ============================================================
-- VIEW TRIGGER SOURCE CODE
-- ============================================================

SHOW CREATE TRIGGER AfterEmployeeInsert;



-- ============================================================
-- DROP TRIGGER
-- ============================================================

DROP TRIGGER AfterEmployeeInsert;



-- ============================================================
-- OLD vs NEW
-- ============================================================

/*

INSERT

OLD -> Not Available

NEW -> Available



UPDATE

OLD -> Available

NEW -> Available



DELETE

OLD -> Available

NEW -> Not Available

*/


-- ============================================================
-- TRIGGER vs STORED PROCEDURE
-- ============================================================

/*

TRIGGER
--------

• Runs Automatically

• Event Driven

• Attached to a Table

• Cannot be called manually



PROCEDURE
---------

• Called using CALL

• Executed manually

• Independent object

• Used for business operations

*/


-- ============================================================
-- ADVANTAGES
-- ============================================================

/*

1. Automatic execution
2. Audit logging
3. Data validation
4. Enforce business rules
5. Maintain history tables

*/


-- ============================================================
-- DISADVANTAGES
-- ============================================================

/*

1. Difficult to debug
2. Hidden execution
3. Can reduce performance
4. Difficult to maintain in large systems

*/


-- ============================================================
-- INTERVIEW QUESTIONS
-- ============================================================

/*

Q1. What is a Trigger?

A Trigger is a database object that automatically
executes when an INSERT, UPDATE or DELETE occurs.


------------------------------------------------------------

Q2. Can we execute a Trigger manually?

No.

It executes automatically.


------------------------------------------------------------

Q3. How many Trigger types exist in MySQL?

Six

• BEFORE INSERT
• AFTER INSERT
• BEFORE UPDATE
• AFTER UPDATE
• BEFORE DELETE
• AFTER DELETE


------------------------------------------------------------

Q4. Difference between OLD and NEW?

OLD
----
Previous row values.

NEW
----
New row values.


------------------------------------------------------------

Q5. Which operations support OLD and NEW?

INSERT

OLD -> No

NEW -> Yes


UPDATE

OLD -> Yes

NEW -> Yes


DELETE

OLD -> Yes

NEW -> No


------------------------------------------------------------

Q6. Which command displays all Triggers?

SHOW TRIGGERS;


------------------------------------------------------------

Q7. Which command displays Trigger source code?

SHOW CREATE TRIGGER TriggerName;


------------------------------------------------------------

Q8. Which command deletes a Trigger?

DROP TRIGGER TriggerName;


------------------------------------------------------------

Q9. Why do we use Triggers?

• Automatic Logging
• Data Validation
• Audit Trail
• Business Rules
• History Maintenance

*/


-- ============================================================
-- MOST IMPORTANT FOR INTERVIEWS
-- ============================================================

/*

★★★★★
CREATE TRIGGER
BEFORE vs AFTER
OLD vs NEW
FOR EACH ROW

★★★★☆
Audit Logging
Salary Validation
SHOW TRIGGERS
SHOW CREATE TRIGGER

★★★☆☆
DROP TRIGGER
Trigger vs Procedure

*/