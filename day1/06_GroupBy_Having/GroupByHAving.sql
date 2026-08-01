--           ============== GROUP BY  ======================
-- ==========================================================================-  

SELECT Department,
       COUNT(*) AS NumberOfEmployee
FROM Employees
GROUP BY Department;



SELECT Department, SUM(Salary) AS TotalSalary
FROM Employees
GROUP BY Department
ORDER BY TotalSalary DESC;




SELECT 
    Department, 
    COUNT(*) AS NumberOfEmployees,
    SUM(Salary) AS TotalSalary,
    AVG(Salary) AS AverageSalary,
    MAX(Salary) AS HighestSalary,
    MIN(Salary) AS LowestSalary
FROM Employees
GROUP BY Department
ORDER BY AverageSalary DESC;




SELECT YEAR(JoiningDate) AS JoiningYear, 
       COUNT(*) AS TotalEmployees,
       AVG(Salary) AS AverageSalary
FROM Employees
GROUP BY YEAR(JoiningDate)
ORDER BY JoiningYear ASC;




SELECT Department,
       YEAR(JoiningDate) AS JoiningYear,
       COUNT(*) AS NoOfEmployee,
       SUM(Salary) AS TotalSalary
FROM Employees
GROUP BY Department, YEAR(JoiningDate)
ORDER BY Department ASC,
         JoiningYear ASC;






-- = ============= HAVING CLAUSE
-- filters group while where filter row
-- SELECT Department, AVG(Salary)
-- FROM Employees
-- WHERE AVG(Salary) > 60000
-- GROUP BY Department; 

SELECT Department, AVG(Salary)
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 60000;


SELECT Department, COUNT(EmployeeId)
FROM Employees
GROUP BY Department
HAVING COUNT(EmployeeId) > 1;


SELECT Department, AVG(Salary)
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 60000;


SELECT Department, SUM(Salary)
FROM Employees
GROUP BY Department
HAVING SUM(Salary) > 120000;


SELECT Department, MAX(Salary)
FROM Employees
GROUP BY Department
HAVING MAX(Salary) > 70000;


SELECT Department, MIN(Salary)
FROM Employees
GROUP BY Department
HAVING MIN(Salary) < 55000;


SELECT Department, 
	AVG(Salary) AS AverageSalary,
    COUNT(EmployeeId) AS NoOfEmp
FROM Employees
GROUP BY Department
HAVING AverageSalary > 55000 AND NoOfEmp >1;

