-- ============================================================
-- DATE FUNCTIONS (MySQL)
-- ============================================================

-- Sample Table
CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100),
    Salary DECIMAL(10,2),
    JoiningDate DATE
);

INSERT INTO Employees VALUES
(1,'Alice',50000,'2021-01-15'),
(2,'Bob',60000,'2022-03-20'),
(3,'Charlie',55000,'2023-07-10'),
(4,'David',70000,'2020-11-05'),
(5,'Eva',65000,'2024-02-18');


-- ============================================================
-- 1. CURDATE()
-- Returns the current date.
-- ============================================================
SELECT CURDATE();


-- ============================================================
-- 2. CURTIME()
-- Returns the current time.
-- ============================================================
SELECT CURTIME();


-- ============================================================
-- 3. NOW()
-- Returns the current date and time.
-- ============================================================
SELECT NOW();


-- ============================================================
-- 4. YEAR()
-- Extracts the year from a date.
-- ============================================================
SELECT EmployeeName,
       YEAR(JoiningDate) AS JoiningYear
FROM Employees;


-- ============================================================
-- 5. MONTH()
-- Extracts the month number.
-- ============================================================
SELECT EmployeeName,
       MONTH(JoiningDate) AS JoiningMonth
FROM Employees;


-- ============================================================
-- 6. MONTHNAME()
-- Returns the month name.
-- ============================================================
SELECT EmployeeName,
       MONTHNAME(JoiningDate) AS MonthName
FROM Employees;


-- ============================================================
-- 7. DAY()
-- Returns the day of the month.
-- ============================================================
SELECT EmployeeName,
       DAY(JoiningDate) AS JoiningDay
FROM Employees;


-- ============================================================
-- 8. DAYNAME()
-- Returns the weekday name.
-- ============================================================
SELECT EmployeeName,
       DAYNAME(JoiningDate) AS WeekDay
FROM Employees;


-- ============================================================
-- 9. DATEDIFF()
-- Returns difference between two dates in DAYS.
-- Syntax:
-- DATEDIFF(date1, date2)
-- ============================================================
SELECT EmployeeName,
       DATEDIFF(CURDATE(), JoiningDate) AS DaysWorked
FROM Employees;


-- ============================================================
-- 10. DATE_ADD()
-- Adds an interval to a date.
-- ============================================================

-- Add 30 Days
SELECT DATE_ADD(CURDATE(), INTERVAL 30 DAY);

-- Add 2 Months
SELECT DATE_ADD(CURDATE(), INTERVAL 2 MONTH);

-- Add 1 Year
SELECT DATE_ADD(CURDATE(), INTERVAL 1 YEAR);


-- ============================================================
-- 11. DATE_SUB()
-- Subtracts an interval from a date.
-- ============================================================

-- Subtract 7 Days
SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY);

-- Subtract 1 Month
SELECT DATE_SUB(CURDATE(), INTERVAL 1 MONTH);

-- Subtract 1 Year
SELECT DATE_SUB(CURDATE(), INTERVAL 1 YEAR);


-- ============================================================
-- 12. TIMESTAMPDIFF()
-- Returns difference in Years, Months, Days, Hours, etc.
-- Syntax:
-- TIMESTAMPDIFF(unit, start_date, end_date)
-- ============================================================

-- Years
SELECT EmployeeName,
       TIMESTAMPDIFF(YEAR, JoiningDate, CURDATE()) AS ExperienceYears
FROM Employees;

-- Months
SELECT EmployeeName,
       TIMESTAMPDIFF(MONTH, JoiningDate, CURDATE()) AS ExperienceMonths
FROM Employees;

-- Days
SELECT EmployeeName,
       TIMESTAMPDIFF(DAY, JoiningDate, CURDATE()) AS ExperienceDays
FROM Employees;


-- ============================================================
-- 13. DATE_FORMAT()
-- Formats a date.
-- ============================================================

SELECT EmployeeName,
       DATE_FORMAT(JoiningDate,'%d-%m-%Y') AS FormattedDate
FROM Employees;

SELECT EmployeeName,
       DATE_FORMAT(JoiningDate,'%M %d, %Y') AS FormattedDate
FROM Employees;


-- Common Format Specifiers
-- %d -> Day (01-31)
-- %m -> Month Number (01-12)
-- %M -> Month Name
-- %Y -> Four-digit Year
-- %y -> Two-digit Year


-- ============================================================
-- 14. LAST_DAY()
-- Returns the last day of the month.
-- ============================================================
SELECT EmployeeName,
       LAST_DAY(JoiningDate) AS LastDay
FROM Employees;


-- ============================================================
-- 15. EXTRACT()
-- Extracts a specific part of a date.
-- ============================================================

-- Extract Year
SELECT EmployeeName,
       EXTRACT(YEAR FROM JoiningDate) AS YearValue
FROM Employees;

-- Extract Month
SELECT EmployeeName,
       EXTRACT(MONTH FROM JoiningDate) AS MonthValue
FROM Employees;

-- Extract Day
SELECT EmployeeName,
       EXTRACT(DAY FROM JoiningDate) AS DayValue
FROM Employees;


-- ============================================================
-- 16. MAKEDATE()
-- Creates a date from Year and Day Number.
-- ============================================================
SELECT MAKEDATE(2025,100);


-- ============================================================
-- 17. STR_TO_DATE()
-- Converts a string into a DATE.
-- ============================================================
SELECT STR_TO_DATE('15-08-2025','%d-%m-%Y');


-- ============================================================
-- 18. DATE()
-- Extracts only the DATE part from DATETIME.
-- ============================================================
SELECT DATE(NOW());


/*
============================================================
SUMMARY
============================================================

CURDATE()          -> Current Date
CURTIME()          -> Current Time
NOW()              -> Current Date & Time
YEAR()             -> Extract Year
MONTH()            -> Extract Month Number
MONTHNAME()        -> Month Name
DAY()              -> Day of Month
DAYNAME()          -> Weekday Name
DATEDIFF()         -> Difference in Days
DATE_ADD()         -> Add Days/Months/Years
DATE_SUB()         -> Subtract Days/Months/Years
TIMESTAMPDIFF()    -> Difference in Years/Months/Days/Hours
DATE_FORMAT()      -> Format Date
LAST_DAY()         -> Last Day of Month
EXTRACT()          -> Extract Year/Month/Day
MAKEDATE()         -> Create Date from Year & Day Number
STR_TO_DATE()      -> Convert String to Date
DATE()             -> Extract Date from DATETIME

============================================================
INTERVIEW QUESTIONS
============================================================

Q1. Difference between CURDATE() and NOW()?

CURDATE() -> Returns only the current date.
NOW()     -> Returns current date and time.


Q2. Difference between DATEDIFF() and TIMESTAMPDIFF()?

DATEDIFF()      -> Returns difference ONLY in days.

TIMESTAMPDIFF() -> Returns difference in
                   Years,
                   Months,
                   Days,
                   Hours,
                   Minutes,
                   Seconds.


Q3. Which function is used to calculate Age or Experience?

TIMESTAMPDIFF()

Example:

SELECT EmployeeName,
       TIMESTAMPDIFF(YEAR, JoiningDate, CURDATE()) AS Experience
FROM Employees;


Q4. Which function adds days/months/years?

DATE_ADD()


Q5. Which function subtracts days/months/years?

DATE_SUB()


Q6. Which function formats dates?

DATE_FORMAT()


============================================================
MOST IMPORTANT FOR INTERVIEWS
============================================================

★★★★★
NOW()
CURDATE()
TIMESTAMPDIFF()
DATEDIFF()
DATE_FORMAT()

★★★★☆
YEAR()
MONTH()
DATE_ADD()
DATE_SUB()
EXTRACT()

★★★☆☆
DAY()
DAYNAME()
MONTHNAME()
LAST_DAY()

★★☆☆☆
MAKEDATE()
STR_TO_DATE()
DATE()
CURTIME()

============================================================