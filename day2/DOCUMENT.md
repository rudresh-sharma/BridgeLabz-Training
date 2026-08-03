# 🏥 Health Clinic Management System

A MySQL-based DBMS project for managing a health clinic. The system manages **Departments, Doctors, Patients, and Appointments** using SQL tables, foreign keys, stored procedures, views, and functions.

---

## 📌 Features

### 🟢 Department Module
- Add Department
- Update Department
- Delete Department
- View All Departments
- Count Doctors in a Department

### 🩺 Doctor Module
- Search Doctor by ID
- Search Doctor by Specialty
- Update Doctor Specialty
- Update Consultation Fee
- Delete Doctor
- View All Doctors
- List Doctors by Department
- Count Doctors by Department

### 👤 Patient Module
- Search Patient by ID
- Search Patient by Phone Number
- Update Patient Details
- Delete Patient
- View All Patients
- Find Patients by Age
- Find Patients Born After a Date
- Find Patients Who Visited a Doctor
- Find Patients Without Appointments

### 📅 Appointment Module
- Book Appointment
- Cancel Appointment
- Reschedule Appointment
- Complete Appointment
- View Today's Appointments
- View Tomorrow's Appointments
- View Appointments by Date
- View Appointments Between Two Dates
- Patient Appointment History
- Doctor Appointment History
- Count Appointments by Status

---

# 🗄️ Database Schema

## Tables
- Department
- Doctor
- Patient
- Appointment

## Relationships

```
Department (1) ───────< Doctor (N)

Doctor (1) ───────────< Appointment (N)

Patient (1) ──────────< Appointment (N)
```

---

# 🛠 Database Objects

## Stored Procedures

### Department
| Procedure | Usage |
|----------|-------|
| AddDepartment | `CALL AddDepartment('ENT');` |
| UpdateDepartmentName | `CALL UpdateDepartmentName(3,'Orthopedic Surgery');` |
| DeleteDepartment | `CALL DeleteDepartment(6);` |

### Doctor

| Procedure | Usage |
|----------|-------|
| SearchDoctorById | `CALL SearchDoctorById(1);` |
| SearchDoctorBySpecialty | `CALL SearchDoctorBySpecialty('Cardiologist');` |
| UpdateDoctorSpecialty | `CALL UpdateDoctorSpecialty(2,'Neurosurgeon');` |
| UpdateDoctorFees | `CALL UpdateDoctorFees(1,3000.00);` |
| DeleteDoctor | `CALL DeleteDoctor(4);` |
| GetDoctorsByDepartment | `CALL GetDoctorsByDepartment('Cardiology');` |

### Patient

| Procedure | Usage |
|----------|-------|
| SearchPatientById | `CALL SearchPatientById(1);` |
| SearchPatientByPhone | `CALL SearchPatientByPhone('9876543210');` |
| UpdatePatient | `CALL UpdatePatient(...);` |
| DeletePatient | `CALL DeletePatient(10);` |
| FindPatientsByAge | `CALL FindPatientsByAge(40);` |
| FindPatientsBornAfter | `CALL FindPatientsBornAfter('1990-01-01');` |
| FindPatientsByDoctor | `CALL FindPatientsByDoctor(1);` |

### Appointment

| Procedure | Usage |
|----------|-------|
| BookAppointment | `CALL BookAppointment(...);` |
| CancelAppointment | `CALL CancelAppointment(2);` |
| RescheduleAppointment | `CALL RescheduleAppointment(...);` |
| CompleteAppointment | `CALL CompleteAppointment(3);` |
| GetAppointmentsByDate | `CALL GetAppointmentsByDate('2026-08-05');` |
| GetAppointmentsBetweenDates | `CALL GetAppointmentsBetweenDates(...);` |
| GetPatientAppointmentHistory | `CALL GetPatientAppointmentHistory(1);` |
| GetDoctorAppointmentHistory | `CALL GetDoctorAppointmentHistory(1);` |

---

# 👁️ Views

## Department
- ViewAllDepartments

## Doctor
- ViewAllDoctors
- DoctorsByDepartment
- DoctorCountByDepartment

## Patient
- ViewAllPatients
- PatientAgeView
- PatientDoctorVisit
- PatientsWithoutAppointments

## Appointment
- TodayAppointments
- TomorrowAppointments
- AppointmentDetails
- AppointmentStatusCount

---

# 🔢 Functions

| Function | Purpose |
|----------|---------|
| GetDoctorCount | Returns the number of doctors in a department |

Usage

```sql
SELECT GetDoctorCount(1);
```

---

# 🔑 Database Concepts Used

- Database Creation
- DDL & DML
- CRUD Operations
- Primary Keys
- Foreign Keys
- Constraints
- AUTO_INCREMENT
- Referential Integrity
- ON DELETE CASCADE
- INNER JOIN
- LEFT JOIN
- GROUP BY
- Aggregate Functions
- Date Functions
- Views
- Stored Procedures
- Functions
- Database Normalization (1NF, 2NF, 3NF)

---

# 🚀 Technologies

- MySQL 8.x
- SQL
- MySQL Workbench

---

# 📂 Project Structure

```
HealthClinicManagementSystem/
│
├── healthclinicapp.sql
├── README.md
└── documentation/
```

---

# 👨‍💻 Author

**Rudresh Sharma**

B.Tech Computer Science (AI & ML)

Technocrats Institute of Technology Excellence
