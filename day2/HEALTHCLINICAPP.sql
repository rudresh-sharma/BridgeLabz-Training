-- ==========================================================
-- SECTION 1 : DATABASE CREATION
-- ==========================================================

CREATE DATABASE IF NOT EXISTS healthclinicapp;
USE healthclinicapp;


-- ==========================================================
-- SECTION 2 : TABLE CREATION
-- ==========================================================


-- Department Table
CREATE TABLE department (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL UNIQUE
);


-- Doctor Table
CREATE TABLE doctor (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    specialty VARCHAR(100) NOT NULL,
    doctor_fees DECIMAL(10,2) NOT NULL,
    phone VARCHAR(15) UNIQUE,
    email VARCHAR(100) UNIQUE,
    department_id INT NOT NULL,

    CONSTRAINT fk_doctor_department
        FOREIGN KEY (department_id)
        REFERENCES department(department_id)
);



-- Patient Table
CREATE TABLE patient (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    gender ENUM('Male','Female','Other') NOT NULL,
    dob DATE NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE,
    blood_group VARCHAR(5),
    address VARCHAR(255)
);



-- Appointment Table
CREATE TABLE appointment (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,

    patient_id INT NOT NULL,

    doctor_id INT NOT NULL,

    appointment_date DATETIME NOT NULL,

    status ENUM('Scheduled','Completed','Cancelled')
        DEFAULT 'Scheduled',

    reason VARCHAR(255),

    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id),

    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
);



-- ==========================================================
-- SECTION 3 : SAMPLE DATA
-- ==========================================================

-- Insert Departments


INSERT INTO department (department_name)
VALUES
('Cardiology'),
('Neurology'),
('Orthopedics'),
('Dermatology'),
('Pediatrics');



-- Insert Doctors


INSERT INTO doctor
(name, specialty, doctor_fees, phone, email, department_id)
VALUES
('Dr. Alice Smith',    'Cardiologist', 2500.00, '9000000001', 'alice@clinic.com', 1),
('Dr. Bob Jones',      'Neurologist',  3000.00, '9000000002', 'bob@clinic.com',   2),
('Dr. Charlie Brown',  'Orthopedic',   1800.00, '9000000003', 'charlie@clinic.com',3),
('Dr. Diana Prince',   'Dermatologist',1500.00, '9000000004', 'diana@clinic.com', 4),
('Dr. Evan Wright',    'Pediatrician', 1200.00, '9000000005', 'evan@clinic.com',  5);



--  Insert  Patients


INSERT INTO patient
(name, gender, dob, phone, email, blood_group, address)
VALUES
('John Doe',        'Male',   '1985-04-12', '9876543210', 'john@gmail.com',    'A+',  'Bhopal'),
('Jane Smith',      'Female', '1990-08-23', '8765432109', 'jane@gmail.com',    'B+',  'Indore'),
('Michael Johnson', 'Male',   '1978-11-05', '7654321098', 'michael@gmail.com', 'O+',  'Jabalpur'),
('Emily Davis',     'Female', '1995-02-17', '6543210987', 'emily@gmail.com',   'AB+', 'Sagar'),
('David Brown',     'Male',   '1982-06-30', '5432109876', 'david@gmail.com',   'A-',  'Bhopal'),
('Sarah Miller',    'Female', '1988-09-14', '9123456780', 'sarah@gmail.com',   'B-',  'Indore'),
('James Wilson',    'Male',   '1973-01-25', '8234567891', 'james@gmail.com',   'O-',  'Gwalior'),
('Amanda Moore',    'Female', '2000-12-01', '7345678912', 'amanda@gmail.com',  'AB-', 'Bhopal'),
('Robert Taylor',   'Male',   '1965-07-19', '6456789123', 'robert@gmail.com',  'A+',  'Rewa'),
('Lisa Anderson',   'Female', '1992-03-08', '5567891234', 'lisa@gmail.com',    'O+',  'Satna');


-- Insert Appointments
INSERT INTO appointment
(patient_id, doctor_id, appointment_date, status, reason)
VALUES

(1,1,'2026-08-05 09:00:00','Completed','Chest Pain'),
(2,1,'2026-08-05 10:30:00','Scheduled','Heart Checkup'),
(3,2,'2026-08-06 11:00:00','Completed','Migraine'),
(4,2,'2026-08-06 12:30:00','Cancelled','Headache'),
(5,3,'2026-08-07 09:30:00','Scheduled','Knee Pain'),
(6,4,'2026-08-07 10:00:00','Completed','Skin Allergy'),
(7,5,'2026-08-08 11:30:00','Scheduled','Fever'),
(8,3,'2026-08-08 01:00:00','Completed','Fracture'),
(9,1,'2026-08-09 03:00:00','Scheduled','Heart Checkup'),
(10,5,'2026-08-09 04:00:00','Completed','Vaccination');



-- ==========================================================
-- SECTION 4 : CRUD OPERATIONS
-- ==========================================================

-- ==========================================================
-- DEPARTMENT CRUD OPERATIONS
-- ==========================================================

SELECT * FROM Department;

-- FEATURE 1: Add New Department

DELIMITER $$

CREATE PROCEDURE AddDepartment(
    IN p_department_name VARCHAR(100)
)
BEGIN
    INSERT INTO department(department_name)
    VALUES(p_department_name);
END $$

DELIMITER ;

CALL AddDepartment('ENT');


-- FEATURE 2:  Update Department Name

DELIMITER $$

CREATE PROCEDURE UpdateDepartmentName(
    IN p_department_id INT,
    IN p_new_name VARCHAR(100)
)
BEGIN
    UPDATE department
    SET department_name = p_new_name
    WHERE department_id = p_department_id;
END $$

DELIMITER ;

CALL UpdateDepartmentName(3,'Orthopedic Surgery');



-- FEATURE 3 : Delete Department

DELIMITER $$

CREATE PROCEDURE DeleteDepartment(
    IN p_department_id INT
)
BEGIN
    DELETE FROM department
    WHERE department_id = p_department_id;
END $$

DELIMITER ;

CALL DeleteDepartment(6);


-- 	FEATURE 4 : View All Departments

CREATE VIEW ViewAllDepartments AS
SELECT
    department_id,
    department_name
FROM department;

SELECT * FROM ViewAllDepartments;


-- 	FEATURE 5 : Count Doctors in Each Department

DELIMITER $$

CREATE FUNCTION GetDoctorCount(
    p_department_id INT
)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;

    SELECT COUNT(*)
    INTO total
    FROM doctor
    WHERE department_id = p_department_id;

    RETURN total;
END $$

DELIMITER ;


SELECT GetDoctorCount(1);




-- ==========================================================
-- DOCTOR CRUD OPERATIONS
-- ==========================================================

SELECT * FROM Doctor;

-- 	FEATURE 1 : Search Doctor by ID

DELIMITER $$

CREATE PROCEDURE SearchDoctorById(
    IN p_doctor_id INT
)
BEGIN
    SELECT *
    FROM doctor
    WHERE doctor_id = p_doctor_id;
END $$

DELIMITER ;

CALL SearchDoctorById(1);



-- FEATURE 2 : Search Doctor by Specialty

DELIMITER $$

CREATE PROCEDURE SearchDoctorBySpecialty(
    IN p_specialty VARCHAR(100)
)
BEGIN
    SELECT *
    FROM doctor
    WHERE specialty = p_specialty;
END $$

DELIMITER ;


CALL SearchDoctorBySpecialty('Cardiologist');


-- FEATURE 3 : Update Doctor's Specialty

DELIMITER $$

CREATE PROCEDURE UpdateDoctorSpecialty(
    IN p_doctor_id INT,
    IN p_specialty VARCHAR(100)
)
BEGIN
    UPDATE doctor
    SET specialty = p_specialty
    WHERE doctor_id = p_doctor_id;
END $$

DELIMITER ;

CALL UpdateDoctorSpecialty(2,'Neurosurgeon');


-- FEATURE 4 : Update Doctor's Consultation Fee

DELIMITER $$

CREATE PROCEDURE UpdateDoctorFees(
    IN p_doctor_id INT,
    IN p_new_fee DECIMAL(10,2)
)
BEGIN
    UPDATE doctor
    SET doctor_fees = p_new_fee
    WHERE doctor_id = p_doctor_id;
END $$

DELIMITER ;

CALL UpdateDoctorFees(1,3000.00);


-- FEATURE 5 : Delete Doctor

ALTER TABLE appointment
DROP FOREIGN KEY fk_appointment_doctor;

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_doctor
FOREIGN KEY (doctor_id)
REFERENCES doctor(doctor_id)
ON DELETE CASCADE;



DELIMITER ;
SET SQL_SAFE_UPDATES = 0; 
DELIMITER $$

CREATE PROCEDURE DeleteDoctor(
    IN p_doctor_id INT
)
BEGIN
    DELETE FROM doctor
    WHERE doctor_id = p_doctor_id;
END $$

DELIMITER ;


CALL DeleteDoctor(4);


-- FEATURE 6 : View All Doctors

CREATE VIEW ViewAllDoctors AS
SELECT
    doctor_id,
    name,
    specialty,
    doctor_fees,
    phone,
    email,
    department_id
FROM doctor;


SELECT * FROM ViewAllDoctors;


-- FEATURE 7 : List Doctors by Department

CREATE VIEW DoctorsByDepartment AS
SELECT
    d.department_name,
    doc.doctor_id,
    doc.name,
    doc.specialty,
    doc.doctor_fees,
    doc.phone,
    doc.email
FROM doctor doc
INNER JOIN department d
ON doc.department_id = d.department_id;


DELIMITER //

CREATE PROCEDURE getDoctorsByDepartment(
    IN deptName VARCHAR(100)
)
BEGIN
    SELECT *
    FROM DoctorsByDepartment
    WHERE department_name = deptName;
END //

DELIMITER ;


CALL getDoctorsByDepartment('Cardiology');



-- FEATURE 8 : Count Doctors in Each Department

CREATE VIEW DoctorCountByDepartment AS
SELECT
    d.department_id,
    d.department_name,
    COUNT(doc.doctor_id) AS total_doctors
FROM department d
LEFT JOIN doctor doc
ON d.department_id = doc.department_id
GROUP BY
    d.department_id,
    d.department_name;
    
    
SELECT * FROM DoctorCountByDepartment;



-- FEATURE 9  : GET BUSIEST DOCTOR ON GIVEN DATE

DELIMITER //

CREATE PROCEDURE busiestDoctor(IN wantDate DATE)
BEGIN
    SELECT
        d.doctor_id,
        d.name,
        d.specialty,
        COUNT(a.appointment_id) AS NoOfAppointments
    FROM doctor d
    JOIN appointment a
        ON d.doctor_id = a.doctor_id
    WHERE DATE(a.appointment_date) = wantDate
    GROUP BY
        d.doctor_id,
        d.name,
        d.specialty
    ORDER BY NoOfAppointments DESC
    LIMIT 1;
END //

DELIMITER ;

CALL busiestDoctor('2026-08-05');

-- ==========================================================
-- PATIENT CRUD OPERATIONS
-- ==========================================================

SELECT * FROM patient;

-- FEATURE 1 : Search Patient by ID

DELIMITER $$

CREATE PROCEDURE SearchPatientById(
    IN p_patient_id INT
)
BEGIN
    SELECT *
    FROM patient
    WHERE patient_id = p_patient_id;
END $$

DELIMITER ;

CALL SearchPatientById(1);


-- FEATURE 2 : Search Patient by Phone Number

DELIMITER $$

CREATE PROCEDURE SearchPatientByPhone(
    IN p_phone VARCHAR(15)
)
BEGIN
    SELECT *
    FROM patient
    WHERE phone = p_phone;
END $$

DELIMITER ;

CALL SearchPatientByPhone('9876543210');


-- FEATURE 3 : Update Patient Details

DELIMITER $$

CREATE PROCEDURE UpdatePatient(
    IN p_patient_id INT,
    IN p_name VARCHAR(100),
    IN p_gender ENUM('Male','Female','Other'),
    IN p_dob DATE,
    IN p_phone VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_blood_group VARCHAR(5),
    IN p_address VARCHAR(255)
)
BEGIN
    UPDATE patient
    SET
        name = p_name,
        gender = p_gender,
        dob = p_dob,
        phone = p_phone,
        email = p_email,
        blood_group = p_blood_group,
        address = p_address
    WHERE patient_id = p_patient_id;
END $$

DELIMITER ;

CALL UpdatePatient(
    1,
    'John Doe',
    'Male',
    '1985-04-12',
    '9876543210',
    'john_new@gmail.com',
    'A+',
    'Bhopal'
);


-- FEATURE 4 : Delete Patient

ALTER TABLE appointment
DROP FOREIGN KEY fk_appointment_patient;

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_patient
FOREIGN KEY (patient_id)
REFERENCES patient(patient_id)
ON DELETE CASCADE;

SET SQL_SAFE_UPDATES = 0;

DELIMITER $$

CREATE PROCEDURE DeletePatient(
    IN p_patient_id INT
)
BEGIN
    DELETE FROM patient
    WHERE patient_id = p_patient_id;
END $$

DELIMITER ;

CALL DeletePatient(10);


-- FEATURE 5 : View All Patients

CREATE VIEW ViewAllPatients AS
SELECT
    patient_id,
    name,
    gender,
    dob,
    phone,
    email,
    blood_group,
    address
FROM patient;

SELECT * FROM ViewAllPatients;


-- FEATURE 6 : Find Patients by Age

CREATE VIEW PatientAgeView AS
SELECT
    patient_id,
    name,
    gender,
    dob,
    TIMESTAMPDIFF(YEAR, dob, CURDATE()) AS age,
    phone,
    email,
    blood_group,
    address
FROM patient;

DELIMITER $$

CREATE PROCEDURE FindPatientsByAge(
    IN p_age INT
)
BEGIN
    SELECT *
    FROM PatientAgeView
    WHERE age = p_age;
END $$

DELIMITER ;

CALL FindPatientsByAge(40);


-- FEATURE 7 : Find Patients Born After a Given Date

DELIMITER $$

CREATE PROCEDURE FindPatientsBornAfter(
    IN p_date DATE
)
BEGIN
    SELECT *
    FROM patient
    WHERE dob > p_date;
END $$

DELIMITER ;

CALL FindPatientsBornAfter('1990-01-01');


-- FEATURE 8 : Find Patients Who Visited a Particular Doctor

CREATE VIEW PatientDoctorVisit AS
SELECT
    p.patient_id,
    p.name AS patient_name,
    d.doctor_id,
    d.name AS doctor_name,
    a.appointment_date,
    a.status,
    a.reason
FROM patient p
INNER JOIN appointment a
ON p.patient_id = a.patient_id
INNER JOIN doctor d
ON a.doctor_id = d.doctor_id;

DELIMITER $$

CREATE PROCEDURE FindPatientsByDoctor(
    IN p_doctor_id INT
)
BEGIN
    SELECT *
    FROM PatientDoctorVisit
    WHERE doctor_id = p_doctor_id;
END $$

DELIMITER ;

CALL FindPatientsByDoctor(1);


-- FEATURE 9 : Find Patients with No Appointments

CREATE VIEW PatientsWithoutAppointments AS
SELECT
    p.*
FROM patient p
LEFT JOIN appointment a
ON p.patient_id = a.patient_id
WHERE a.appointment_id IS NULL;

SELECT * FROM PatientsWithoutAppointments;






-- ==========================================================
-- APPOINTMENT CRUD OPERATIONS
-- ==========================================================

SELECT * FROM appointment;

-- FEATURE 1 : Book Appointment

DELIMITER $$

CREATE PROCEDURE BookAppointment(
    IN p_patient_id INT,
    IN p_doctor_id INT,
    IN p_appointment_date DATETIME,
    IN p_reason VARCHAR(255)
)
BEGIN
    INSERT INTO appointment
    (patient_id, doctor_id, appointment_date, status, reason)
    VALUES
    (p_patient_id, p_doctor_id, p_appointment_date, 'Scheduled', p_reason);
END $$

DELIMITER ;

CALL BookAppointment(
    1,
    2,
    '2026-08-15 10:00:00',
    'General Checkup'
);


-- FEATURE 2 : Cancel Appointment

DELIMITER $$

CREATE PROCEDURE CancelAppointment(
    IN p_appointment_id INT
)
BEGIN
    UPDATE appointment
    SET status = 'Cancelled'
    WHERE appointment_id = p_appointment_id;
END $$

DELIMITER ;

CALL CancelAppointment(2);


-- FEATURE 3 : Reschedule Appointment

DELIMITER $$

CREATE PROCEDURE RescheduleAppointment(
    IN p_appointment_id INT,
    IN p_new_date DATETIME
)
BEGIN
    UPDATE appointment
    SET appointment_date = p_new_date,
        status = 'Scheduled'
    WHERE appointment_id = p_appointment_id;
END $$

DELIMITER ;

CALL RescheduleAppointment(
    2,
    '2026-08-20 11:30:00'
);


-- FEATURE 4 : Complete Appointment

DELIMITER $$

CREATE PROCEDURE CompleteAppointment(
    IN p_appointment_id INT
)
BEGIN
    UPDATE appointment
    SET status = 'Completed'
    WHERE appointment_id = p_appointment_id;
END $$

DELIMITER ;

CALL CompleteAppointment(3);


-- FEATURE 5 : View Today's Appointments

CREATE VIEW TodayAppointments AS
SELECT
    a.appointment_id,
    p.name AS patient_name,
    d.name AS doctor_name,
    a.appointment_date,
    a.status,
    a.reason
FROM appointment a
INNER JOIN patient p
ON a.patient_id = p.patient_id
INNER JOIN doctor d
ON a.doctor_id = d.doctor_id
WHERE DATE(a.appointment_date) = CURDATE();

SELECT * FROM TodayAppointments;


-- FEATURE 6 : View Tomorrow's Appointments

CREATE VIEW TomorrowAppointments AS
SELECT
    a.appointment_id,
    p.name AS patient_name,
    d.name AS doctor_name,
    a.appointment_date,
    a.status,
    a.reason
FROM appointment a
INNER JOIN patient p
ON a.patient_id = p.patient_id
INNER JOIN doctor d
ON a.doctor_id = d.doctor_id
WHERE DATE(a.appointment_date) = CURDATE() + INTERVAL 1 DAY;

SELECT * FROM TomorrowAppointments;


-- FEATURE 7 : View Appointments for Any Date

CREATE VIEW AppointmentDetails AS
SELECT
    a.appointment_id,
    p.patient_id,
    p.name AS patient_name,
    d.doctor_id,
    d.name AS doctor_name,
    a.appointment_date,
    a.status,
    a.reason
FROM appointment a
INNER JOIN patient p
ON a.patient_id = p.patient_id
INNER JOIN doctor d
ON a.doctor_id = d.doctor_id;

DELIMITER $$

CREATE PROCEDURE GetAppointmentsByDate(
    IN p_date DATE
)
BEGIN
    SELECT *
    FROM AppointmentDetails
    WHERE DATE(appointment_date) = p_date;
END $$

DELIMITER ;

CALL GetAppointmentsByDate('2026-08-05');


-- FEATURE 8 : View Appointments Between Two Dates

DELIMITER $$

CREATE PROCEDURE GetAppointmentsBetweenDates(
    IN p_start DATE,
    IN p_end DATE
)
BEGIN
    SELECT *
    FROM AppointmentDetails
    WHERE DATE(appointment_date)
    BETWEEN p_start AND p_end;
END $$

DELIMITER ;

CALL GetAppointmentsBetweenDates(
    '2026-08-05',
    '2026-08-08'
);


-- FEATURE 9 : View Appointment History of a Patient

DELIMITER $$

CREATE PROCEDURE GetPatientAppointmentHistory(
    IN p_patient_id INT
)
BEGIN
    SELECT *
    FROM AppointmentDetails
    WHERE patient_id = p_patient_id
    ORDER BY appointment_date;
END $$

DELIMITER ;

CALL GetPatientAppointmentHistory(1);


-- FEATURE 10 : View Appointment History of a Doctor

DELIMITER $$

CREATE PROCEDURE GetDoctorAppointmentHistory(
    IN p_doctor_id INT
)
BEGIN
    SELECT *
    FROM AppointmentDetails
    WHERE doctor_id = p_doctor_id
    ORDER BY appointment_date;
END $$

DELIMITER ;

CALL GetDoctorAppointmentHistory(1);


-- FEATURE 11 : Count Appointments by Status

CREATE VIEW AppointmentStatusCount AS
SELECT
    status,
    COUNT(*) AS total_appointments
FROM appointment
GROUP BY status;

SELECT * FROM AppointmentStatusCount;
