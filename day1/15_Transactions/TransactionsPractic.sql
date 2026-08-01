-- ============================================================
-- TRANSACTIONS (MySQL)
-- ============================================================

-- Sample Table

CREATE TABLE Accounts (
    AccountId INT PRIMARY KEY,
    HolderName VARCHAR(100),
    Balance DECIMAL(10,2)
);

INSERT INTO Accounts VALUES
(101,'Alice',10000),
(102,'Bob',5000),
(103,'Charlie',8000);

SELECT * FROM Accounts;


-- ============================================================
-- WHAT IS A TRANSACTION?
-- ------------------------------------------------------------
-- A Transaction is a group of SQL statements executed
-- as one single unit of work.
--
-- Either ALL statements succeed
-- OR
-- NONE of them succeed.
--
-- Used in:
-- • Banking Systems
-- • E-commerce
-- • Payment Gateways
-- • ATM Software
-- • Inventory Systems
-- ============================================================



-- ============================================================
-- 1. START TRANSACTION
-- ------------------------------------------------------------
-- Starts a new transaction.
-- ============================================================

START TRANSACTION;



-- ============================================================
-- 2. COMMIT
-- ------------------------------------------------------------
-- Permanently saves all changes.
-- ============================================================

START TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 101;

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 102;

COMMIT;

SELECT * FROM Accounts;



-- ============================================================
-- 3. ROLLBACK
-- ------------------------------------------------------------
-- Cancels all changes made after
-- START TRANSACTION.
-- ============================================================

START TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 2000
WHERE AccountId = 101;

UPDATE Accounts
SET Balance = Balance + 2000
WHERE AccountId = 102;

ROLLBACK;

SELECT * FROM Accounts;



-- ============================================================
-- 4. SAVEPOINT
-- ------------------------------------------------------------
-- Creates a checkpoint inside a transaction.
-- ============================================================

START TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 101;

SAVEPOINT S1;

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 102;

COMMIT;



-- ============================================================
-- 5. ROLLBACK TO SAVEPOINT
-- ------------------------------------------------------------
-- Rolls back only to the specified savepoint.
-- ============================================================

START TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 101;

SAVEPOINT S1;

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 102;

ROLLBACK TO S1;

COMMIT;

SELECT * FROM Accounts;



-- ============================================================
-- 6. MULTIPLE SAVEPOINTS
-- ============================================================

START TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 100
WHERE AccountId = 101;

SAVEPOINT A;

UPDATE Accounts
SET Balance = Balance - 200
WHERE AccountId = 102;

SAVEPOINT B;

UPDATE Accounts
SET Balance = Balance - 300
WHERE AccountId = 103;

ROLLBACK TO B;

COMMIT;

SELECT * FROM Accounts;



-- ============================================================
-- 7. AUTOCOMMIT
-- ============================================================

-- Check current status

SELECT @@autocommit;

-- Disable Auto Commit

SET autocommit = 0;

-- Enable Auto Commit

SET autocommit = 1;



-- ============================================================
-- 8. COMPLETE BANK TRANSFER
-- ============================================================

START TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 3000
WHERE AccountId = 101;

UPDATE Accounts
SET Balance = Balance + 3000
WHERE AccountId = 102;

COMMIT;



-- ============================================================
-- 9. FAILED TRANSACTION
-- ============================================================

START TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 3000
WHERE AccountId = 101;

-- Invalid Account

UPDATE Accounts
SET Balance = Balance + 3000
WHERE AccountId = 999;

ROLLBACK;



-- ============================================================
-- ACID PROPERTIES
-- ============================================================

/*

A → Atomicity
--------------
All operations succeed OR all fail.

Example:
Money Transfer

Debit ✔
Credit ✖

↓

Rollback everything.


------------------------------------------------------------

C → Consistency
----------------
Database remains valid before and after
every transaction.

Example

Balance cannot become negative.


------------------------------------------------------------

I → Isolation
--------------
Transactions should not interfere
with each other.

Example

Two users withdrawing money
at the same time.


------------------------------------------------------------

D → Durability
---------------
Once COMMIT is executed,
changes become permanent.

Even after:

• Power Failure
• System Crash
• Restart

*/


-- ============================================================
-- COMMIT vs ROLLBACK
-- ============================================================

/*

COMMIT
-------
• Saves changes permanently.
• Cannot be undone.

ROLLBACK
---------
• Cancels changes.
• Restores previous state.

*/


-- ============================================================
-- SAVEPOINT
-- ============================================================

/*

SAVEPOINT creates a checkpoint.

ROLLBACK TO SAVEPOINT

undoes only the changes made
after that checkpoint.

*/


-- ============================================================
-- INTERVIEW QUESTIONS
-- ============================================================

/*

Q1. What is a Transaction?

A Transaction is a group of SQL statements
executed as one single unit.

Either all succeed
or none succeed.


------------------------------------------------------------

Q2. Which command starts a transaction?

START TRANSACTION;


------------------------------------------------------------

Q3. Which command permanently saves changes?

COMMIT;


------------------------------------------------------------

Q4. Which command cancels changes?

ROLLBACK;


------------------------------------------------------------

Q5. What is SAVEPOINT?

A checkpoint inside a transaction.

Used for partial rollback.


------------------------------------------------------------

Q6. Difference between COMMIT and ROLLBACK?

COMMIT
-------
Permanently saves changes.

ROLLBACK
---------
Cancels changes.


------------------------------------------------------------

Q7. What are ACID Properties?

A → Atomicity
C → Consistency
I → Isolation
D → Durability


------------------------------------------------------------

Q8. What is Auto Commit?

Automatically commits every SQL statement.

Check:

SELECT @@autocommit;

Disable:

SET autocommit = 0;

Enable:

SET autocommit = 1;


------------------------------------------------------------

Q9. Why are Transactions important?

Because they maintain data integrity
when multiple SQL statements are executed
together.

Example:

• Banking
• ATM
• E-commerce
• Payments
• Inventory

*/


-- ============================================================
-- MOST IMPORTANT FOR INTERVIEWS
-- ============================================================

/*

★★★★★
START TRANSACTION
COMMIT
ROLLBACK
SAVEPOINT
ACID Properties

★★★★☆
ROLLBACK TO SAVEPOINT
Auto Commit
Bank Transfer Example

★★★☆☆
Multiple Savepoints

*/