package com.csvdatahandling.question11;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class StudentCSVGenerator {
    public static void main(String[] args) {
        String fileName = "students_large.csv";
        long targetSizeBytes = 500L * 1024 * 1024; // 500 MB
        Random random = new Random();

        String[] firstNames = {"John","Emma","Liam","Olivia","Noah","Ava","Ethan","Sophia","Mason","Isabella"};
        String[] lastNames = {"Smith","Johnson","Brown","Williams","Jones","Garcia","Miller","Davis","Wilson","Taylor"};
        String[] genders = {"Male","Female"};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write header
            writer.write("StudentID,Name,Age,Gender,Grade,Email\n");
            
            long writtenBytes = "StudentID,Name,Age,Gender,Grade,Email\n".getBytes().length;
            int id = 1;
            
            while (writtenBytes < targetSizeBytes) {
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                String name = firstName + " " + lastName;
                int age = 18 + random.nextInt(5); // 18-22
                String gender = genders[random.nextInt(genders.length)];
                String grade = String.valueOf(50 + random.nextInt(51)); // 50-100
                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + id + "@school.com";

                String row = id + "," + name + "," + age + "," + gender + "," + grade + "," + email + "\n";
                writer.write(row);
                writtenBytes += row.getBytes().length;
                id++;
            }
            
            System.out.println("500MB+ student CSV generated: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
