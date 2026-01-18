package com.generics.resumescreeningsystem;

import java.util.*;

public class ResumeScreeningSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Resume<? extends JobRole>> resumes = new ArrayList<>();
        List<JobRole> roles = new ArrayList<>();

        System.out.print("Enter number of candidates: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nCandidate " + i);
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Experience (years): ");
            int experience = sc.nextInt();

            System.out.print("Skill Score (0-100): ");
            int score = sc.nextInt();
            sc.nextLine();

            System.out.println("Choose Role:");
            System.out.println("1. Software Engineer");
            System.out.println("2. Data Scientist");
            System.out.println("3. Product Manager");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                SoftwareEngineer se = new SoftwareEngineer();
                resumes.add(new Resume<>(name, experience, score, se));
                roles.add(se);
            } else if (choice == 2) {
                DataScientist ds = new DataScientist();
                resumes.add(new Resume<>(name, experience, score, ds));
                roles.add(ds);
            } else if (choice == 3) {
                ProductManager pm = new ProductManager();
                resumes.add(new Resume<>(name, experience, score, pm));
                roles.add(pm);
            } else {
                System.out.println("Invalid role selection");
                i--;
            }
        }

        System.out.println("\n---- Resume Screening Results ----");
        for (Resume<? extends JobRole> r : resumes) {
            ResumeScreeningUtil.screenResume(r);
        }

        System.out.println("\n---- Screening Pipeline ----");
        ScreeningPipeline.processResumes(roles);

        sc.close();
    }
}