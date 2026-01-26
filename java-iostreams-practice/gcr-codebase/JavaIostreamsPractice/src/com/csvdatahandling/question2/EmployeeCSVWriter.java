package com.csvdatahandling.question2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeCSVWriter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Specify path inside the package folder
        String folderPath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question2"; // your folder
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // create folders if not exist
        }

        String filePath = folderPath + "/employees.csv"; // final CSV path

        try (FileWriter writer = new FileWriter(filePath, true)) { // append mode
            // Write header only if file is empty
            File file = new File(filePath);
            if (file.length() == 0) {
                writer.append("ID,Name,Department,Salary\n");
            }

            System.out.print("How many employees do you want to add? ");
            int n = sc.nextInt();
            sc.nextLine(); // consume newline

            for (int i = 1; i <= n; i++) {
                System.out.println("\nEnter details for employee " + i + ":");

                System.out.print("ID: ");
                String id = sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Department: ");
                String dept = sc.nextLine();

                System.out.print("Salary: ");
                String salary = sc.nextLine();

                // Write to CSV
                writer.append(id).append(",")
                      .append(name).append(",")
                      .append(dept).append(",")
                      .append(salary).append("\n");

                System.out.println("✅ Employee " + name + " added to CSV.");
            }

            System.out.println("\nAll data saved to " + filePath);

        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }

        sc.close();
    }
}
