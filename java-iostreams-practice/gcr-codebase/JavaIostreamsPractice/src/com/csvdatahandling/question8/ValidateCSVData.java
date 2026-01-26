package com.csvdatahandling.question8;

import java.io.*;
import java.util.regex.Pattern;

public class ValidateCSVData {

    public static void main(String[] args) {

        String inputFile =
        "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question8\\employees_with_contact.csv";

        // Email regex (simple & practical)
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        // Phone regex: exactly 10 digits
        String phoneRegex = "^\\d{10}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String header = br.readLine(); // read header
            System.out.println("📄 Header: " + header);
            System.out.println("\n❌ Invalid Rows Found:\n");

            String line;
            int rowNumber = 1;
            boolean anyInvalid = false;

            while ((line = br.readLine()) != null) {
                rowNumber++;

                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");

                if (values.length < 4) {
                    System.out.println("Row " + rowNumber + " ❌ Invalid format: " + line);
                    anyInvalid = true;
                    continue;
                }

                String id = values[0].trim();
                String name = values[1].trim();
                String email = values[2].trim();
                String phone = values[3].trim();

                boolean validEmail = emailPattern.matcher(email).matches();
                boolean validPhone = phonePattern.matcher(phone).matches();

                if (!validEmail || !validPhone) {
                    anyInvalid = true;

                    System.out.println("Row " + rowNumber + " ❌ Invalid Data:");
                    System.out.println("   Data: " + line);

                    if (!validEmail) {
                        System.out.println("   ➤ Invalid Email: " + email);
                    }

                    if (!validPhone) {
                        System.out.println("   ➤ Invalid Phone (must be 10 digits): " + phone);
                    }

                    System.out.println();
                }
            }

            if (!anyInvalid) {
                System.out.println("✅ All rows are valid!");
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading CSV file: " + e.getMessage());
        }
    }
}
