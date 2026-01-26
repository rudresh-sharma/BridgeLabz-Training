package com.csvdatahandling.question15;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class EncryptSalaryInEmployeeCsv {

    public static void main(String[] args) throws Exception {

        String inputCsv  = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question15\\employee_report.csv";
        String encryptedCsv = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question15\\employee_report_encrypted.csv";

        // 1️⃣ Read original CSV
        List<Employee> employees = readNormalCsv(inputCsv);

        // 2️⃣ Write encrypted CSV (only Salary encrypted)
        writeEncryptedCsv(employees, encryptedCsv);
        System.out.println("Salary encrypted and written to new CSV.");

        // 3️⃣ Read encrypted CSV and decrypt Salary
        List<Employee> decrypted = readDecryptedCsv(encryptedCsv);
        System.out.println("Decrypted data:");

        for (Employee e : decrypted) {
            System.out.println(
                    e.getEmployeeId() + " | " +
                    e.getName() + " | " +
                    e.getDepartment() + " | " +
                    e.getSalary()
            );
        }
    }

    // ============ READ NORMAL MYSQL CSV ============

    private static List<Employee> readNormalCsv(String filePath) throws Exception {

        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {

            String[] line;
            boolean isHeader = true;

            while ((line = reader.readNext()) != null) {

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                Employee e = new Employee();

                // Your exact column order:
                // 0 -> Employee ID
                // 1 -> Name
                // 2 -> Department
                // 3 -> Salary

                e.setEmployeeId(Integer.parseInt(line[0]));
                e.setName(line[1]);
                e.setDepartment(line[2]);
                e.setSalary(line[3]);   // normal salary

                employees.add(e);
            }
        }

        return employees;
    }

    // ============ WRITE CSV WITH ENCRYPTED SALARY ============

    private static void writeEncryptedCsv(List<Employee> employees, String filePath) throws Exception {

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            // Write same header as input
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            writer.writeNext(header);

            for (Employee e : employees) {

                // Encrypt only Salary
                String encryptedSalary = CryptoUtil.encrypt(e.getSalary());

                String[] row = {
                        String.valueOf(e.getEmployeeId()),
                        e.getName(),
                        e.getDepartment(),
                        encryptedSalary   // encrypted salary
                };

                writer.writeNext(row);
            }
        }
    }

    // ============ READ ENCRYPTED CSV AND DECRYPT SALARY ============

    private static List<Employee> readDecryptedCsv(String filePath) throws Exception {

        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {

            String[] line;
            boolean isHeader = true;

            while ((line = reader.readNext()) != null) {

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                Employee e = new Employee();
                e.setEmployeeId(Integer.parseInt(line[0]));
                e.setName(line[1]);
                e.setDepartment(line[2]);

                // Decrypt Salary
                String decryptedSalary = CryptoUtil.decrypt(line[3]);
                e.setSalary(decryptedSalary);

                employees.add(e);
            }
        }

        return employees;
    }
}
