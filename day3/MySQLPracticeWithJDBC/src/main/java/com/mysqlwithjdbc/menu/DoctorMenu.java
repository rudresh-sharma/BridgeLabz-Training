package com.mysqlwithjdbc.menu;

import java.util.List;
import java.util.Scanner;

import com.mysqlwithjdbc.dao.DoctorDAO;
import com.mysqlwithjdbc.model.Doctor;

public class DoctorMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final DoctorDAO doctorDAO = new DoctorDAO();

    public void start() {

        int choice;

        do {

            displayMenu();

            System.out.print("Enter Your Choice : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addDoctor();
                    break;

                case 2:
                    addDoctorUsingProcedure();
                    break;

                case 3:
                    viewAllDoctors();
                    break;

                case 4:
                    searchDoctorById();
                    break;

                case 5:
                    updateDoctor();
                    break;

                case 6:
                    deleteDoctor();
                    break;

                case 7:
                    System.out.println("Thank You...");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 7);

        scanner.close();
    }

    private void displayMenu() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("        HEALTH CLINIC - DOCTOR MENU");
        System.out.println("==============================================");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Doctor Using Procedure");
        System.out.println("3. View All Doctors");
        System.out.println("4. Search Doctor By ID");
        System.out.println("5. Update Doctor");
        System.out.println("6. Delete Doctor");
        System.out.println("7. Exit");
        System.out.println("==============================================");
    }

    private void addDoctor() {

        System.out.println("\n------ Add Doctor ------");

        System.out.print("Enter Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter Specialty : ");
        String specialty = scanner.nextLine();

        System.out.print("Enter Experience : ");
        int experience = scanner.nextInt();

        System.out.print("Enter Consultation Fee : ");
        double fee = scanner.nextDouble();
        scanner.nextLine();

        Doctor doctor = new Doctor(name, specialty, experience, fee);

        doctorDAO.addDoctor(doctor);
    }

    private void addDoctorUsingProcedure() {

        System.out.println("\n------ Add Doctor Using Procedure ------");

        System.out.print("Enter Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter Specialty : ");
        String specialty = scanner.nextLine();

        System.out.print("Enter Experience : ");
        int experience = scanner.nextInt();

        System.out.print("Enter Consultation Fee : ");
        double fee = scanner.nextDouble();
        scanner.nextLine();

        Doctor doctor = new Doctor(name, specialty, experience, fee);

        doctorDAO.addDoctorUsingProcedure(doctor);
    }

    private void viewAllDoctors() {

        System.out.println("\n------ Doctor List ------");

        List<Doctor> doctors = doctorDAO.getAllDoctors();

        if (doctors.isEmpty()) {
            System.out.println("No Doctors Found.");
            return;
        }

       System.out.printf("%-25s%-25s%-25s%-25s%-25s\n\n","DoctorID","Name","Speciality","Experience","Consulatation_Fees");
       
       for(Doctor d : doctors) {
    	   System.out.printf("%-25d%-25s%-25s%-25d%-25.2f\n",d.getDoctorId(), d.getName(), d.getSpecialty(),d.getExperience(),d.getConsultationFee());
       }
    
    }

    private void searchDoctorById() {

        System.out.println("\n------ Search Doctor ------");

        System.out.print("Enter Doctor ID : ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        Doctor doctor = doctorDAO.getDoctorById(doctorId);

        if (doctor != null)
            System.out.println(doctor);
        else
            System.out.println("Doctor Not Found.");
    }

    private void updateDoctor() {

        System.out.println("\n------ Update Doctor ------");

        System.out.print("Enter Doctor ID : ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter New Specialty : ");
        String specialty = scanner.nextLine();

        System.out.print("Enter New Experience : ");
        int experience = scanner.nextInt();

        System.out.print("Enter New Consultation Fee : ");
        double fee = scanner.nextDouble();
        scanner.nextLine();

        Doctor doctor = new Doctor();

        doctor.setDoctorId(doctorId);
        doctor.setName(name);
        doctor.setSpecialty(specialty);
        doctor.setExperience(experience);
        doctor.setConsultationFee(fee);

        doctorDAO.updateDoctor(doctor);
    }

    private void deleteDoctor() {

        System.out.println("\n------ Delete Doctor ------");

        System.out.print("Enter Doctor ID : ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        doctorDAO.deleteDoctor(doctorId);
    }

}