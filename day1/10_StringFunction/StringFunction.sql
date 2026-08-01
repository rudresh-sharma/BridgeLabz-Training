-- ==========================================
-- STRING FUNCTIONS (MySQL)
-- ==========================================

-- Sample Table
CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100),
    Email VARCHAR(100),
    Department VARCHAR(50)
);

INSERT INTO Employees
VALUES
(1,'Alice Johnson','alice@gmail.com','HR'),
(2,'Bob Smith','bob@yahoo.com','IT'),
(3,'Charlie Brown','charlie@gmail.com','Finance'),
(4,'David Miller','david@hotmail.com','IT'),
(5,'Eva Wilson','eva@gmail.com','Marketing');


-- ==========================================
-- 1. LENGTH()
-- Returns the number of characters in a string.
-- ==========================================
SELECT EmployeeName,
       LENGTH(EmployeeName) AS TotalCharacters
FROM Employees;


-- ==========================================
-- 2. UPPER()
-- Converts string to uppercase.
-- ==========================================
SELECT EmployeeName,
       UPPER(EmployeeName) AS UpperCaseName
FROM Employees;


-- ==========================================
-- 3. LOWER()
-- Converts string to lowercase.
-- ==========================================
SELECT EmployeeName,
       LOWER(EmployeeName) AS LowerCaseName
FROM Employees;


-- ==========================================
-- 4. CONCAT()
-- Combines multiple strings.
-- ==========================================
SELECT CONCAT(EmployeeName,' works in ',Department) AS EmployeeDetails
FROM Employees;


-- ==========================================
-- 5. LEFT()
-- Returns characters from the left.
-- ==========================================
SELECT EmployeeName,
       LEFT(EmployeeName,3) AS FirstThreeCharacters
FROM Employees;


-- ==========================================
-- 6. RIGHT()
-- Returns characters from the right.
-- ==========================================
SELECT EmployeeName,
       RIGHT(EmployeeName,4) AS LastFourCharacters
FROM Employees;


-- ==========================================
-- 7. SUBSTRING()
-- Extracts part of a string.
-- Syntax:
-- SUBSTRING(column,start,length)
-- ==========================================
SELECT EmployeeName,
       SUBSTRING(EmployeeName,1,5) AS FirstFiveCharacters
FROM Employees;

SELECT EmployeeName,
       SUBSTRING(EmployeeName,7,7) AS LastName
FROM Employees;


-- ==========================================
-- 8. TRIM()
-- Removes leading and trailing spaces.
-- ==========================================
SELECT TRIM('     Hello SQL     ') AS Result;


-- ==========================================
-- 9. LTRIM()
-- Removes leading spaces.
-- ==========================================
SELECT LTRIM('     Hello SQL');


-- ==========================================
-- 10. RTRIM()
-- Removes trailing spaces.
-- ==========================================
SELECT RTRIM('Hello SQL     ');


-- ==========================================
-- 11. REPLACE()
-- Replaces one substring with another.
-- ==========================================
SELECT Email,
       REPLACE(Email,'gmail.com','company.com') AS NewEmail
FROM Employees;


-- ==========================================
-- 12. REVERSE()
-- Reverses a string.
-- ==========================================
SELECT EmployeeName,
       REVERSE(EmployeeName) AS ReverseName
FROM Employees;


-- ==========================================
-- 13. LOCATE()
-- Returns position of a substring.
-- Syntax:
-- LOCATE(substring,string)
-- ==========================================
SELECT Email,
       LOCATE('@',Email) AS Position
FROM Employees;


-- ==========================================
-- 14. INSTR()
-- Returns position of a substring.
-- Syntax:
-- INSTR(string,substring)
-- ==========================================
SELECT EmployeeName,
       INSTR(EmployeeName,'Brown') AS Position
FROM Employees;


-- ==========================================
-- 15. LPAD()
-- Pads characters on the left.
-- ==========================================
SELECT LPAD(EmployeeId,5,'0') AS EmployeeCode
FROM Employees;


-- ==========================================
-- 16. RPAD()
-- Pads characters on the right.
-- ==========================================
SELECT RPAD(EmployeeName,20,'*') AS PaddedName
FROM Employees;


-- ==========================================
-- 17. REPEAT()
-- Repeats a string.
-- ==========================================
SELECT REPEAT('SQL ',5);


-- ==========================================
-- 18. SPACE()
-- Returns specified number of spaces.
-- ==========================================
SELECT CONCAT('Hello',SPACE(5),'World');


-- ==========================================
-- 19. REGEXP
-- Advanced pattern matching.
-- ==========================================

-- Names starting with A
SELECT *
FROM Employees
WHERE EmployeeName REGEXP '^A';

-- Names ending with n
SELECT *
FROM Employees
WHERE EmployeeName REGEXP 'n$';

-- Names containing "son"
SELECT *
FROM Employees
WHERE EmployeeName REGEXP 'son';

-- Names starting with A or B
SELECT *
FROM Employees
WHERE EmployeeName REGEXP '^[AB]';

-- Names containing digits
SELECT *
FROM Employees
WHERE EmployeeName REGEXP '[0-9]';


-- ==========================================
-- LIKE vs REGEXP
-- ==========================================

-- LIKE (Simple Pattern Matching)
-- %  -> Any number of characters
-- _  -> Exactly one character

SELECT *
FROM Employees
WHERE EmployeeName LIKE 'A%';


-- REGEXP (Advanced Pattern Matching)

SELECT *
FROM Employees
WHERE EmployeeName REGEXP '^[AB].*son$';


/*
=========================================================
SUMMARY
=========================================================

LENGTH()     -> Counts characters
UPPER()      -> Converts to uppercase
LOWER()      -> Converts to lowercase
CONCAT()     -> Combines strings
LEFT()       -> Returns left characters
RIGHT()      -> Returns right characters
SUBSTRING()  -> Extracts part of a string
TRIM()       -> Removes leading & trailing spaces
LTRIM()      -> Removes left spaces
RTRIM()      -> Removes right spaces
REPLACE()    -> Replaces text
REVERSE()    -> Reverses string
LOCATE()     -> Finds substring position
INSTR()      -> Finds substring position
LPAD()       -> Pads on the left
RPAD()       -> Pads on the right
REPEAT()     -> Repeats a string
SPACE()      -> Creates spaces
REGEXP       -> Advanced pattern matching

=========================================================
MOST IMPORTANT FOR INTERVIEWS
=========================================================

★★★★★
CONCAT()
SUBSTRING()
REPLACE()
TRIM()
REGEXP()

★★★★☆
UPPER()
LOWER()
LENGTH()
LOCATE()

★★★☆☆
LEFT()
RIGHT()
LPAD()
RPAD()

★★☆☆☆
REVERSE()
REPEAT()
SPACE()
LTRIM()
RTRIM()

=========================================================