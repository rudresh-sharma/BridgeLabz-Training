CREATE DATABASE IF NOT EXISTS health_clinic;

 USE health_clinic;
 
 DROP TABLE IF EXISTS doctor;
 DROP TABLE IF EXISTS appointment;
 DROP TABLE IF EXISTS patient;
 
 
 CREATE TABLE patient (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    phone      VARCHAR(15),
    dob        DATE
);


CREATE TABLE doctor (
    doctor_id  INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    specialty  VARCHAR(50)
);


CREATE TABLE appointment (
    appointment_id    INT PRIMARY KEY AUTO_INCREMENT,
    patient_id        INT NOT NULL,
    doctor_id         INT NOT NULL,
    appointment_date  DATETIME NOT NULL,
    status             VARCHAR(20) DEFAULT 'Scheduled',
    FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
    FOREIGN KEY (doctor_id)  REFERENCES doctor(doctor_id)
);


INSERT INTO doctor (name, specialty) VALUES
('Dr. Alice Smith', 'Cardiology'),
('Dr. Bob Jones', 'Pediatrics'),
('Dr. Charlie Brown', 'Dermatology'),
('Dr. Diana Prince', 'Neurology'),
('Dr. Evan Wright', 'Orthopedics');



INSERT INTO patient (name, phone, dob) VALUES
('John Doe', '9876543210', '1985-04-12'),
('Jane Smith', '8765432109', '1990-08-23'),
('Michael Johnson', '7654321098', '1978-11-05'),
('Emily Davis', '6543210987', '1995-02-17'),
('David Brown', '5432109876', '1982-06-30'),
('Sarah Miller', '9123456780', '1988-09-14'),
('James Wilson', '8234567891', '1973-01-25'),
('Amanda Moore', '7345678912', '2000-12-01'),
('Robert Taylor', '6456789123', '1965-07-19'),
('Lisa Anderson', '5567891234', '1992-03-08');

DELETE FROM patient
WHERE patient_id BETWEEN 21 AND 30;

INSERT INTO appointment (patient_id, doctor_id, appointment_date, status) VALUES
(1, 1, '2026-08-10 09:00:00', 'Scheduled'),
(2, 2, '2026-08-10 10:30:00', 'Scheduled'),
(3, 3, '2026-08-11 14:00:00', 'Scheduled'),
(4, 4, '2026-08-11 15:30:00', 'Completed'),
(5, 5, '2026-08-12 11:00:00', 'Scheduled'),
(6, 1, '2026-08-12 16:15:00', 'Cancelled'),
(7, 2, '2026-08-13 09:30:00', 'Scheduled'),
(8, 3, '2026-08-13 13:00:00', 'Scheduled'),
(9, 4, '2026-08-14 10:00:00', 'Scheduled'),
(10, 5, '2026-08-14 14:45:00', 'Scheduled');




-- PRINTING PATIENT DATA WITH CORRESPONDING TO DOCTOR NAME AND APPOINTMENT DATE

SELECT p.name AS patient, d.name AS doctor, a.appointment_date
FROM appointment a
JOIN patient p ON a.patient_id = p.patient_id
JOIN doctor d  ON a.doctor_id  = d.doctor_id
ORDER BY a.appointment_date;




-- UPDATING PATIENT PHONE NUMBER USING PATIENT ID

SET SQL_SAFE_UPDATES = 0;

UPDATE patient
SET phone = '9999999999'
WHERE  patient_id = 1;
SELECT ROW_COUNT();



-- CREATING UNNORMALIZED SCHEMA

CREATE TABLE appointment_bad (
    appt_id           INT PRIMARY KEY AUTO_INCREMENT,
    patient_name      VARCHAR(100),
    patient_phone     VARCHAR(15),
    doctor_name       VARCHAR(100),
    doctor_specialty  VARCHAR(50),
    appt_date         DATETIME
);



-- PROBLEM SOLVING 3NF IS THAT SINCE IN DOCTOR TABLE 
-- STARTING 3NF IN DOCTOR TABLE 

-- adding department name column in doctor table  to create 3nf based schema 

ALTER TABLE doctor ADD COLUMN department_name VARCHAR(50);

-- updating department name on basis of speciality

UPDATE doctor 
SET department_name = 'Heart Department'
WHERE specialty = 'Cardiology';

UPDATE doctor 
SET department_name = 'Child Care'
WHERE specialty = 'Pediatrics';

UPDATE doctor 
SET department_name = 'Skin Department'
WHERE specialty = 'Dermatology';

UPDATE doctor 
SET department_name = 'Brain Department'
WHERE specialty = 'Neurology';

UPDATE doctor 
SET department_name = 'Bone Department'
WHERE specialty = 'Orthopedics';




-- Re-enable safe updates
SET SQL_SAFE_UPDATES = 1;


-- STILL PROBLEM THAT "What if the Cardiology department gets renamed? How many
--  doctor rows need updating if we have 10 cardiologists?"

CREATE TABLE department (
    department_id   INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(50) NOT NULL
);


ALTER TABLE doctor ADD COLUMN department_id INT;
ALTER TABLE doctor ADD FOREIGN KEY (department_id) REFERENCES department(department_id);


INSERT INTO department (department_name) VALUES 
('Heart Department'),
('Child Care'),
('Skin Department'),
('Brain Department'),
('Bone Department');

-- Disable safe updates temporarily if MySQL blocks your WHERE clause
SET SQL_SAFE_UPDATES = 0;

UPDATE doctor SET department_id = 1 WHERE specialty = 'Cardiology';
UPDATE doctor SET department_id = 2 WHERE specialty = 'Pediatrics';
UPDATE doctor SET department_id = 3 WHERE specialty = 'Dermatology';
UPDATE doctor SET department_id = 4 WHERE specialty = 'Neurology';
UPDATE doctor SET department_id = 5 WHERE specialty = 'Orthopedics';


-- PRINTING DOCTOR NAME WITH HIS SPECIALTY AND DEPARTMENT 

SELECT d.name, d.specialty, dep.department_name
FROM doctor d JOIN department dep ON d.department_id = dep.department_id;



-- TOPIC 3 — INDEXING LIVE DEMO

-- PROBLEM WITH FOLLOWING SLOW INSERTION MYSQL OVERLOADED AND STOPS AUTOMATICALLY 

-- START TRANSACTION;

-- DELIMITER $$
-- CREATE PROCEDURE seed_appointments(IN n INT)
-- BEGIN
--     DECLARE i INT DEFAULT 0;
--     WHILE i < n DO
--         INSERT INTO appointment (patient_id, doctor_id, appointment_date, status)
--         VALUES (
--             1 + FLOOR(RAND() * 2),
--             1 + FLOOR(RAND() * 2),
--             DATE_ADD('2026-01-01', INTERVAL FLOOR(RAND()*365) DAY),
--             'Scheduled'
--         );
--         SET i = i + 1;
--     END WHILE;
-- END$$
-- DELIMITER ;

-- COMMIT;

-- DROP PROCEDURE IF EXISTS seed_appointments;
-- CALL seed_appointments(50000);


-- Remove old appointments (optional)
TRUNCATE TABLE appointment;

-- Insert 50,000 random appointments
INSERT INTO appointment
(patient_id, doctor_id, appointment_date, status)

SELECT
    FLOOR(1 + RAND() * 10) AS patient_id,
    FLOOR(1 + RAND() * 5) AS doctor_id,
    DATE_ADD('2026-01-01', INTERVAL FLOOR(RAND() * 365) DAY) AS appointment_date,
    'Scheduled' AS status

FROM information_schema.columns c1
CROSS JOIN information_schema.columns c2
LIMIT 50000;


-- SELECT
-- TABLE_NAME,
-- COLUMN_NAME,
-- DATA_TYPE
-- FROM information_schema.columns
-- WHERE TABLE_SCHEMA = 'health_clinic';



-- Verify the number of rows
SELECT COUNT(*) AS TotalAppointments
FROM appointment;

EXPLAIN
SELECT * FROM appointment WHERE patient_id = 1;


SHOW INDEX FROM appointment;
SELECT * FROM appointment;
SELECT ROW_COUNT();
SELECT * FROM patient;
SELECT * FROM Doctor;



-- ==============================================================
-- 				UTILITIES 
-- =================================================================



-- Printing all the doctors nane with their speciality 

SELECT D.name , D.specialty
FROM Doctor D;



-- Printing ALL Patients of Neurology specialist 

SELECT DISTINCT P.name AS Name, P.phone AS PhoneNumber
FROM appointment A
INNER JOIN patient P ON A.patient_id = P.patient_id
INNER JOIN doctor D ON D.doctor_id = A.doctor_id
WHERE D.specialty = 'Neurology';
 


-- Printing Patient With Id using PROCEDURE

DELIMITER //

CREATE PROCEDURE getpatient(IN ptId INT)
BEGIN
	SELECT P.name, P.dob, P.phone
    FROM patient P
    WHERE P.patient_id = ptId;
END //

DELIMITER ;

DROP PROCEDURE IF EXISTS getpatient;
CALL getpatient(1);


SELECT * FROM patient;




-- Printing Doctor Details Using Doctor ID 

DELIMITER //

CREATE PROCEDURE getdoctor(IN docId INT)
BEGIN
	SELECT D.name, D.specialty
    FROM doctor D
    WHERE D.doctor_id = docId;
END //

DELIMITER ;

CALL getdoctor(1);




-- 	PRINTING CARDIOLOGT TODAYS APPOINTMENT BY ORDER i.e

    DELIMITER //

    CREATE PROCEDURE showTodayCardiologiesPatientByTimeOfAppointment()
    BEGIN
        SELECT  P.name AS Name, P.phone AS PhoneNumber, D.specialty, A.appointment_date
        FROM appointment A
        INNER JOIN patient P ON A.patient_id = P.patient_id
        INNER JOIN doctor D ON D.doctor_id = A.doctor_id
        WHERE D.specialty = 'Cardiology' AND DATE(A.appointment_date) = CURDATE()
        ORDER BY A.appointment_date ASC;
    END //

    DELIMITER ;

    DROP PROCEDURE IF EXISTS showTodayCardiologiesPatientByTimeOfAppointment;
    CALL showTodayCardiologiesPatientByTimeOfAppointment();




-- printing number of appointments  doctor have by his id 

DELIMITER //

CREATE PROCEDURE noOfAppointments(IN docId INT)
BEGIN
    SELECT COUNT(appointment_id) AS NumberOfAppointments
    FROM appointment A
    WHERE doctor_id = docId AND DATE(A.appointment_date) = CURDATE();
END //

DELIMITER ;
DROP PROCEDURE IF EXISTS noOfAppointments;
CALL noOfAppointments(3);


SHOW PROCEDURE STATUS
WHERE Db = 'health_clinic';
SELECT * FROM patient;



SELECT * FROM patient;

