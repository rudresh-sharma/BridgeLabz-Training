package com.algorithms.utilityclasses.challenge;

import java.io.FileWriter;
import java.io.IOException;

public class LargeFileGenerator {
    public static void main(String[] args) {
        // Path to the file you want to generate
        String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-dsa-practice\\gcr-codebase\\src\\com\\algorithms\\utilityclasses\\challenge\\largefile.txt";

        // Content to write
        String content = "hello world "; // 12 characters including space

        // Target file size in bytes (100 MB)
        long targetSize = 100L * 1024 * 1024; // 100 MB in bytes

        try (FileWriter fw = new FileWriter(filePath)) {
            long bytesWritten = 0;

            while (bytesWritten < targetSize) {
                fw.write(content);
                bytesWritten += content.getBytes().length; // count actual bytes
            }

            System.out.println("100 MB file generated successfully at: " + filePath);

        } catch (IOException e) {
            System.out.println("Error generating file: " + e.getMessage());
        }
    }
}
