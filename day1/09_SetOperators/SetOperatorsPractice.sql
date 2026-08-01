

-- ============= Set Operators=-===============


CREATE TABLE Employees_2024 (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100)
);

INSERT INTO Employees_2024 VALUES
(1,'Alice'),
(2,'Bob'),
(3,'Charlie'),
(4,'David');

CREATE TABLE Employees_2025 (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR(100)
);

INSERT INTO Employees_2025 VALUES
(3,'Charlie'),
(4,'David'),
(5,'Eva'),
(6,'Frank');



-- union 
SELECT EmployeeName
FROM Employees_2024

UNION

SELECT EmployeeName
FROM Employees_2025;


-- unioon al

SELECT EmployeeName
FROM Employees_2024

UNION ALL

SELECT EmployeeName
FROM Employees_2025;


-- intersect

SELECT E24.EmployeeId,
       E24.EmployeeName
FROM Employees_2024 E24
INNER JOIN Employees_2025 E25
ON E24.EmployeeId = E25.EmployeeId;

SELECT *
FROM Employees_2024
WHERE EmployeeId IN
(
    SELECT EmployeeId
    FROM Employees_2025
);


-- except

SELECT *
FROM Employees_2024
WHERE EmployeeId NOT IN
(
    SELECT EmployeeId
   FROM Employees_2025
);

 