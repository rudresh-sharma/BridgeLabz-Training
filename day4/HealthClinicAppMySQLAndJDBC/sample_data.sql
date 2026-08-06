USE health_clinic_db;


SHOW TABLES;


INSERT INTO department (department_id, name, description) VALUES
(1,'Cardiology','Heart and cardiovascular treatment'),
(2,'Neurology','Brain and nervous system'),
(3,'Orthopedics','Bones and joints'),
(4,'Pediatrics','Child healthcare'),
(5,'Dermatology','Skin diseases'),
(6,'General Medicine','General diagnosis'),
(7,'Emergency','Emergency care');

SELECT * FROM DEPARTMENT;


INSERT INTO doctor
(first_name,last_name,specialization,department_id,phone,email,salary,join_date,is_active)
VALUES
('Rajesh','Sharma','Cardiologist',1,'9876500001','rajesh.sharma@clinic.com',120000,'2020-01-15',TRUE),

('Anita','Verma','Neurologist',2,'9876500002','anita.verma@clinic.com',125000,'2019-03-12',TRUE),

('Vikram','Singh','Orthopedic Surgeon',3,'9876500003','vikram.singh@clinic.com',130000,'2021-07-01',TRUE),

('Priya','Nair','Pediatrician',4,'9876500004','priya.nair@clinic.com',110000,'2022-02-11',TRUE),

('Rahul','Gupta','Dermatologist',5,'9876500005','rahul.gupta@clinic.com',118000,'2018-05-18',TRUE),

('Sneha','Joshi','General Physician',6,'9876500006','sneha.joshi@clinic.com',90000,'2023-01-10',TRUE),

('Amit','Mishra','Emergency Specialist',7,'9876500007','amit.mishra@clinic.com',115000,'2021-11-05',TRUE),

('Neha','Kapoor','Cardiologist',1,'9876500008','neha.kapoor@clinic.com',122000,'2022-09-14',TRUE),

('Arun','Patel','Neurologist',2,'9876500009','arun.patel@clinic.com',128000,'2020-06-20',TRUE),

('Meera','Rao','General Physician',6,'9876500010','meera.rao@clinic.com',95000,'2024-01-01',TRUE);


SELECT * FROM DOCTOR;


UPDATE department SET head_doctor_id=1 WHERE department_id=1;
UPDATE department SET head_doctor_id=2 WHERE department_id=2;
UPDATE department SET head_doctor_id=3 WHERE department_id=3;
UPDATE department SET head_doctor_id=4 WHERE department_id=4;
UPDATE department SET head_doctor_id=5 WHERE department_id=5;
UPDATE department SET head_doctor_id=6 WHERE department_id=6;
UPDATE department SET head_doctor_id=7 WHERE department_id=7;



INSERT INTO patient
(first_name,last_name,date_of_birth,gender,blood_group,phone,email,address,city,is_active)
VALUES
('Rohan','Sharma','1998-04-15','Male','A+','9000000001','rohan@gmail.com','Shivaji Nagar','Bhopal',TRUE),

('Pooja','Verma','1995-09-20','Female','B+','9000000002','pooja@gmail.com','Arera Colony','Bhopal',TRUE),

('Aman','Singh','2000-01-05','Male','O+','9000000003','aman@gmail.com','MP Nagar','Bhopal',TRUE),

('Kavita','Patel','1988-12-10','Female','AB+','9000000004','kavita@gmail.com','LIG Colony','Indore',TRUE),

('Rahul','Yadav','1992-03-08','Male','B-','9000000005','rahul@gmail.com','Civil Lines','Jabalpur',TRUE),

('Sneha','Mishra','1999-07-14','Female','A-','9000000006','sneha@gmail.com','New Market','Bhopal',TRUE),

('Deepak','Jain','1985-06-30','Male','O-','9000000007','deepak@gmail.com','Nehru Nagar','Indore',TRUE),

('Neha','Gupta','1997-11-11','Female','A+','9000000008','neha@gmail.com','Vijay Nagar','Indore',TRUE),

('Vikas','Soni','1994-02-19','Male','AB-','9000000009','vikas@gmail.com','Gole Bazar','Jabalpur',TRUE),

('Anjali','Tiwari','2002-08-25','Female','O+','9000000010','anjali@gmail.com','Berasia Road','Bhopal',TRUE);




INSERT INTO emergency_contact
(patient_id,name,relationship,phone)
VALUES
(1,'Suresh Sharma','Father','9100000001'),
(2,'Anil Verma','Husband','9100000002'),
(3,'Sunita Singh','Mother','9100000003'),
(4,'Rakesh Patel','Husband','9100000004'),
(5,'Meena Yadav','Wife','9100000005'),
(6,'Mahesh Mishra','Father','9100000006'),
(7,'Priya Jain','Wife','9100000007'),
(8,'Rohit Gupta','Brother','9100000008'),
(9,'Sanjay Soni','Father','9100000009'),
(10,'Kiran Tiwari','Mother','9100000010');


INSERT INTO medical_history
(patient_id,condition_name,diagnosed_date,notes)
VALUES
(1,'Hypertension','2021-04-20','Under medication'),
(2,'Migraine','2019-08-10','Frequent headaches'),
(3,'Asthma','2015-02-18','Uses inhaler'),
(4,'Diabetes Type 2','2020-06-14','Controlled with diet'),
(5,'Arthritis','2018-11-09','Knee pain'),
(6,'Anemia','2023-01-05','Iron supplements'),
(7,'High Cholesterol','2022-05-18','Lifestyle modification advised'),
(8,'Thyroid Disorder','2021-10-21','Regular thyroid medication'),
(9,'Kidney Stones','2019-03-11','Recovered after treatment'),
(10,'Seasonal Allergy','2024-02-15','Dust allergy');




INSERT INTO room
(room_number,room_type,department_id,capacity,is_available,daily_rate)
VALUES
('G101','General',6,4,TRUE,1500.00),

('G102','General',6,4,TRUE,1500.00),

('P201','Private',1,1,TRUE,3500.00),

('P202','Private',2,1,FALSE,3500.00),

('ICU01','ICU',1,1,FALSE,8000.00),

('ICU02','ICU',2,1,TRUE,8000.00),

('ER01','Emergency',7,2,FALSE,5000.00),

('ER02','Emergency',7,2,TRUE,5000.00),

('P301','Private',3,1,TRUE,3600.00),

('G103','General',4,6,TRUE,1200.00);




INSERT INTO appointment
(patient_id,doctor_id,appointment_date,appointment_time,status,reason,notes)
VALUES
(1,1,'2026-08-01','09:00:00','Completed','Chest pain','ECG advised'),

(2,2,'2026-08-01','10:00:00','Completed','Frequent headache','MRI suggested'),

(3,3,'2026-08-02','11:00:00','Completed','Knee injury','X-Ray completed'),

(4,4,'2026-08-02','12:00:00','Completed','Routine child checkup','Vaccination completed'),

(5,5,'2026-08-03','14:00:00','Completed','Skin allergy','Medication prescribed'),

(6,6,'2026-08-03','15:00:00','Scheduled','Fever','Blood test required'),

(7,7,'2026-08-04','09:30:00','Cancelled','Road accident follow-up','Patient unavailable'),

(8,8,'2026-08-04','10:15:00','Completed','Heart checkup','Stable condition'),

(9,9,'2026-08-05','11:30:00','No-Show','Migraine consultation','Patient absent'),

(10,10,'2026-08-05','16:00:00','Scheduled','General weakness','Initial consultation');





INSERT INTO visit
(appointment_id,visit_date,symptoms,diagnosis,treatment,follow_up_date,
weight,blood_pressure,temperature,notes)
VALUES
(1,'2026-08-01',
'Chest pain while walking',
'Stable Angina',
'Medication and ECG',
'2026-08-15',
74.5,'130/85',98.4,
'Avoid heavy exercise'),

(2,'2026-08-01',
'Severe headache',
'Migraine',
'Pain management',
'2026-08-20',
60.0,'120/80',98.2,
'MRI advised'),

(3,'2026-08-02',
'Knee pain',
'Ligament strain',
'Painkiller + Physiotherapy',
'2026-08-18',
78.3,'122/81',98.6,
'No sports'),

(4,'2026-08-02',
'Routine examination',
'Healthy',
'Vitamin supplements',
NULL,
18.2,'95/60',98.7,
'Normal growth'),

(5,'2026-08-03',
'Skin itching',
'Allergic Dermatitis',
'Antihistamines',
'2026-08-17',
67.8,'118/79',98.3,
'Avoid allergens'),

(8,'2026-08-04',
'Heart palpitations',
'Normal ECG',
'Observation',
NULL,
70.2,'126/82',98.4,
'Healthy');









INSERT INTO prescription
(visit_id,prescribed_date,instructions)
VALUES
(1,'2026-08-01','Take medicines after meals'),

(2,'2026-08-01','Drink plenty of water'),

(3,'2026-08-02','Avoid heavy lifting'),

(4,'2026-08-02','Vitamin syrup once daily'),

(5,'2026-08-03','Avoid dust and pollen'),

(6,'2026-08-04','Regular heart checkup');




INSERT INTO medicine
(name,generic_name,category,unit,unit_price,stock_quantity,min_stock_level,description,is_active)
VALUES
('Paracetamol 500mg','Paracetamol','Tablet','Strip',25.00,500,50,'Pain reliever',TRUE),

('Amoxicillin 500mg','Amoxicillin','Capsule','Strip',95.00,250,40,'Antibiotic',TRUE),

('Cetirizine','Cetirizine','Tablet','Strip',30.00,300,50,'Anti-allergy medicine',TRUE),

('Ibuprofen','Ibuprofen','Tablet','Strip',45.00,280,40,'Painkiller',TRUE),

('Omeprazole','Omeprazole','Capsule','Strip',70.00,220,30,'Acidity medicine',TRUE),

('Vitamin C','Ascorbic Acid','Tablet','Bottle',120.00,150,20,'Supplement',TRUE),

('Insulin','Human Insulin','Injection','Vial',450.00,80,10,'Diabetes medicine',TRUE),

('Salbutamol Inhaler','Salbutamol','Inhaler','Piece',320.00,60,10,'Asthma inhaler',TRUE),

('Aspirin','Acetylsalicylic Acid','Tablet','Strip',40.00,400,40,'Blood thinner',TRUE),

('Atorvastatin','Atorvastatin','Tablet','Strip',110.00,180,30,'Cholesterol control',TRUE);











INSERT INTO prescription_item
(prescription_id,medicine_id,dosage,frequency,duration_days,quantity)
VALUES

(1,9,'75 mg','Once Daily',30,30),
(1,10,'10 mg','Once Daily',30,30),

(2,1,'500 mg','Twice Daily',5,10),
(2,4,'400 mg','When Required',3,6),

(3,4,'400 mg','Twice Daily',7,14),
(3,5,'20 mg','Once Daily',7,7),

(4,6,'1 Tablet','Once Daily',30,30),

(5,3,'10 mg','Once Daily',10,10),
(5,1,'500 mg','If Fever',5,10),

(6,9,'75 mg','Once Daily',30,30),
(6,10,'20 mg','Night',30,30),

(2,5,'20 mg','Morning',5,5),
(3,1,'500 mg','Morning & Night',5,10),
(5,6,'1 Tablet','Morning',15,15),
(1,5,'20 mg','Morning',30,30);






INSERT INTO lab_test
(test_name,description,normal_range,unit,price)
VALUES
('Complete Blood Count (CBC)','Measures blood components','4.5-11.0','10^9/L',450.00),

('Blood Sugar (Fasting)','Measures fasting glucose','70-99','mg/dL',250.00),

('Lipid Profile','Measures cholesterol levels','<200','mg/dL',700.00),

('Liver Function Test','Checks liver enzymes','Normal','U/L',900.00),

('Kidney Function Test','Checks kidney health','Normal','mg/dL',850.00),

('Urine Routine','Urine analysis','Normal','N/A',300.00),

('ECG','Heart electrical activity','Normal','N/A',600.00),

('X-Ray Chest','Chest imaging','Normal','N/A',1200.00),

('MRI Brain','Brain scan','Normal','N/A',6500.00),

('Vitamin D Test','Vitamin D level','30-100','ng/mL',1400.00);











INSERT INTO lab_report
(visit_id,test_id,test_date,result,is_normal,remarks)
VALUES
(1,7,'2026-08-01','Normal ECG',TRUE,'No abnormal rhythm'),

(1,3,'2026-08-01','LDL Slightly High',FALSE,'Reduce oily food'),

(2,9,'2026-08-01','No abnormalities detected',TRUE,'Healthy brain'),

(2,1,'2026-08-01','Normal CBC',TRUE,'All values normal'),

(3,8,'2026-08-02','Minor ligament swelling',FALSE,'Rest advised'),

(3,1,'2026-08-02','Normal CBC',TRUE,'Normal'),

(4,10,'2026-08-02','Vitamin D Low',FALSE,'Supplements advised'),

(5,6,'2026-08-03','All parameters normal',TRUE,'No infection'),

(5,2,'2026-08-03','92 mg/dL',TRUE,'Normal sugar'),

(6,7,'2026-08-04','Normal ECG',TRUE,'Patient stable');










INSERT INTO billing
(patient_id,visit_id,bill_date,total_amount,paid_amount,discount,tax,status,notes)
VALUES
(1,1,'2026-08-01',2500.00,2500.00,100.00,90.00,'Paid','Cardiology consultation'),

(2,2,'2026-08-01',7800.00,4000.00,200.00,180.00,'Partial','MRI charges'),

(3,3,'2026-08-02',3400.00,3400.00,100.00,120.00,'Paid','Orthopedic visit'),

(4,4,'2026-08-02',1500.00,1500.00,0.00,54.00,'Paid','Child consultation'),

(5,5,'2026-08-03',2200.00,1000.00,50.00,80.00,'Partial','Dermatology'),

(6,6,'2026-08-04',1800.00,0.00,0.00,65.00,'Pending','General consultation'),

(7,NULL,'2026-08-04',5000.00,0.00,0.00,180.00,'Cancelled','Cancelled appointment'),

(8,NULL,'2026-08-05',2600.00,2600.00,100.00,95.00,'Paid','Heart screening'),

(9,NULL,'2026-08-05',900.00,0.00,0.00,32.00,'Pending','Missed appointment'),

(10,NULL,'2026-08-05',1200.00,0.00,0.00,43.00,'Pending','Initial consultation');










INSERT INTO payment
(bill_id,amount,payment_date,payment_method,reference_number)
VALUES
(1,2500.00,'2026-08-01','Card','CARD10001'),

(2,4000.00,'2026-08-01','Online','UPI238912'),

(3,3400.00,'2026-08-02','Cash','CASH3400'),

(4,1500.00,'2026-08-02','Card','CARD1500'),

(5,1000.00,'2026-08-03','Online','UPI987123'),

(6,500.00,'2026-08-04','Cash','ADV500'),

(8,2600.00,'2026-08-05','Insurance','INS87654'),

(9,300.00,'2026-08-05','Online','UPI300'),

(10,500.00,'2026-08-05','Cash','ADV500B'),

(2,2000.00,'2026-08-06','Card','CARD2000');










INSERT INTO insurance
(patient_id,provider_name,policy_number,coverage_amount,valid_from,valid_to,is_active)
VALUES
(1,'Star Health','POL100001',500000,'2025-01-01','2028-01-01',TRUE),

(2,'HDFC ERGO','POL100002',300000,'2024-05-01','2027-05-01',TRUE),

(3,'ICICI Lombard','POL100003',250000,'2025-03-15','2028-03-15',TRUE),

(4,'Niva Bupa','POL100004',400000,'2026-01-01','2029-01-01',TRUE),

(5,'Care Health','POL100005',350000,'2025-08-01','2028-08-01',TRUE),

(6,'Star Health','POL100006',500000,'2026-01-15','2029-01-15',TRUE),

(7,'ACKO Health','POL100007',200000,'2025-10-01','2028-10-01',TRUE),

(8,'HDFC ERGO','POL100008',450000,'2024-12-01','2027-12-01',TRUE),

(9,'ICICI Lombard','POL100009',300000,'2025-06-10','2028-06-10',TRUE),

(10,'Care Health','POL100010',250000,'2026-02-01','2029-02-01',TRUE);










INSERT INTO staff
(first_name,last_name,role,department_id,phone,email,salary,join_date,is_active)
VALUES
('Ritu','Sharma','Nurse',1,'9891000001','ritu.sharma@clinic.com',45000,'2022-01-10',TRUE),

('Mohit','Verma','Receptionist',6,'9891000002','mohit.verma@clinic.com',32000,'2023-04-15',TRUE),

('Anjali','Patel','Lab Technician',2,'9891000003','anjali.patel@clinic.com',42000,'2021-07-20',TRUE),

('Deepak','Yadav','Pharmacist',6,'9891000004','deepak.yadav@clinic.com',40000,'2020-11-05',TRUE),

('Priya','Singh','Ward Nurse',4,'9891000005','priya.singh@clinic.com',44000,'2022-08-18',TRUE),

('Sanjay','Mishra','ICU Nurse',1,'9891000006','sanjay.mishra@clinic.com',48000,'2021-02-28',TRUE),

('Neha','Gupta','Radiology Technician',3,'9891000007','neha.gupta@clinic.com',46000,'2023-03-01',TRUE),

('Karan','Jain','Accountant',6,'9891000008','karan.jain@clinic.com',52000,'2019-12-15',TRUE),

('Pooja','Rao','Emergency Nurse',7,'9891000009','pooja.rao@clinic.com',47000,'2024-01-08',TRUE),

('Amit','Soni','Office Assistant',6,'9891000010','amit.soni@clinic.com',30000,'2024-05-20',TRUE);












INSERT INTO admission
(patient_id,room_id,doctor_id,admission_date,discharge_date,reason,status)
VALUES
(1,5,1,'2026-08-01','2026-08-05','Chest pain observation','Discharged'),

(2,6,2,'2026-08-01',NULL,'Neurological evaluation','Active'),

(3,9,3,'2026-08-02','2026-08-06','Knee surgery','Discharged'),

(4,10,4,'2026-08-02','2026-08-03','Child observation','Discharged'),

(5,3,5,'2026-08-03',NULL,'Skin allergy treatment','Active'),

(6,1,6,'2026-08-04',NULL,'High fever','Active'),

(7,7,7,'2026-08-04','2026-08-05','Accident case','Transferred'),

(8,4,8,'2026-08-05',NULL,'Heart monitoring','Active'),

(9,2,9,'2026-08-05',NULL,'Migraine observation','Active'),

(10,8,10,'2026-08-05',NULL,'General weakness','Active');



INSERT INTO supplier
(name,contact_person,phone,email,address,is_active)
VALUES
('MediCare Pharma','Rajesh Khanna','9811000001','sales@medicarepharma.com','Bhopal',TRUE),

('Apollo Distributors','Amit Verma','9811000002','contact@apollodist.com','Indore',TRUE),

('LifeLine Medical','Pooja Sharma','9811000003','info@lifeline.com','Jabalpur',TRUE),

('Sun Pharma Supply','Vikas Patel','9811000004','sales@sunpharma.com','Mumbai',TRUE),

('HealthCare Drugs','Neha Gupta','9811000005','orders@healthcaredrugs.com','Delhi',TRUE),

('MediPlus Traders','Rakesh Singh','9811000006','support@mediplus.com','Pune',TRUE),

('Global Medicines','Anil Mehta','9811000007','info@globalmed.com','Ahmedabad',TRUE),

('Wellness Pharma','Sneha Jain','9811000008','sales@wellness.com','Nagpur',TRUE),

('Prime Medical Supply','Deepak Rao','9811000009','contact@primemedical.com','Hyderabad',TRUE),

('National Pharma','Rahul Soni','9811000010','orders@nationalpharma.com','Chennai',TRUE);










INSERT INTO inventory
(medicine_id,supplier_id,quantity,purchase_date,unit_cost,expiry_date,batch_number)
VALUES
(1,1,500,'2026-07-01',18.00,'2028-07-01','PARA001'),

(2,2,250,'2026-07-02',70.00,'2028-07-02','AMOX001'),

(3,3,300,'2026-07-03',22.00,'2028-07-03','CET001'),

(4,4,200,'2026-07-04',30.00,'2028-07-04','IBU001'),

(5,5,180,'2026-07-05',50.00,'2028-07-05','OME001'),

(6,6,150,'2026-07-06',85.00,'2028-07-06','VITC001'),

(7,7,100,'2026-07-07',350.00,'2027-12-31','INS001'),

(8,8,120,'2026-07-08',250.00,'2028-07-08','SAL001'),

(9,9,300,'2026-07-09',28.00,'2028-07-09','ASP001'),

(10,10,200,'2026-07-10',80.00,'2028-07-10','ATOR001');










INSERT INTO disease
(name,icd_code,description,category)
VALUES
('Hypertension','I10','High blood pressure','Cardiovascular'),

('Diabetes Mellitus Type 2','E11','Chronic high blood sugar','Endocrine'),

('Asthma','J45','Chronic inflammatory airway disease','Respiratory'),

('Migraine','G43','Recurring severe headaches','Neurology'),

('Osteoarthritis','M19','Joint degeneration','Orthopedics'),

('Allergic Dermatitis','L23','Skin allergy caused by allergens','Dermatology'),

('Pneumonia','J18','Lung infection','Respiratory'),

('Anemia','D64','Low hemoglobin level','Hematology'),

('Hypothyroidism','E03','Underactive thyroid gland','Endocrinology'),

('COVID-19','U07.1','Coronavirus infection','Infectious Disease');












INSERT INTO patient_disease
(patient_id,disease_id,diagnosed_date,notes)
VALUES
(1,1,'2021-04-20','Blood pressure controlled with medication'),

(2,4,'2019-08-10','Migraine attacks once every month'),

(3,3,'2015-02-18','Uses inhaler regularly'),

(4,2,'2020-06-14','Diet and exercise recommended'),

(5,5,'2018-11-09','Pain mostly in left knee'),

(6,8,'2023-01-05','Taking iron supplements'),

(7,1,'2022-05-18','Regular BP monitoring'),

(8,9,'2021-10-21','Daily thyroid medication'),

(9,6,'2026-08-03','Seasonal skin allergy'),

(10,10,'2025-09-12','Recovered completely'),

(1,2,'2022-07-15','Borderline diabetic'),

(5,8,'2024-01-20','Mild anemia');










INSERT INTO feedback
(patient_id,doctor_id,rating,comments)
VALUES
(1,1,5,'Excellent care and clear explanation.'),

(2,2,4,'Doctor was very professional.'),

(3,3,5,'Treatment worked very well.'),

(4,4,5,'Very friendly with children.'),

(5,5,4,'Good consultation and medicine.'),

(6,6,5,'Quick diagnosis and treatment.'),

(7,7,3,'Waiting time was a little long.'),

(8,8,5,'Highly recommended cardiologist.'),

(9,9,4,'Helpful consultation.'),

(10,10,5,'Satisfied with overall service.');













INSERT INTO activity_log
(activity_type,description)
VALUES
('LOGIN','Administrator logged into the system'),

('PATIENT_REGISTER','New patient registered'),

('DOCTOR_ADDED','New doctor profile created'),

('APPOINTMENT_BOOKED','Appointment booked successfully'),

('VISIT_COMPLETED','Patient visit completed'),

('PRESCRIPTION_CREATED','Prescription generated'),

('LAB_REPORT_ADDED','Lab report uploaded'),

('PAYMENT_RECEIVED','Bill payment received'),

('INVENTORY_UPDATED','Medicine inventory updated'),

('LOGOUT','Administrator logged out');













INSERT INTO audit_log
(table_name,operation,record_id,description)
VALUES
('department','INSERT',1,'Department Cardiology created'),

('doctor','INSERT',1,'Doctor Rajesh Sharma added'),

('patient','INSERT',1,'Patient Rohan Sharma registered'),

('appointment','INSERT',1,'Appointment booked'),

('visit','INSERT',1,'Visit record created'),

('prescription','INSERT',1,'Prescription generated'),

('billing','UPDATE',2,'Bill partially paid'),

('payment','INSERT',2,'Online payment received'),

('inventory','UPDATE',1,'Medicine stock increased'),

('feedback','INSERT',1,'Patient submitted feedback');   