package com.day1.markmate.main;

import com.day1.markmate.model.Student;
import com.day1.markmate.processor.MarkProcessor;
import com.day1.markmate.util.CSVReaderUtil;
import com.day1.markmate.util.JsonWriterUtil;

import java.util.List;

public class MarkMateApp {

    public static void main(String[] args) {

        System.out.println("📘 MarkMate – Student Marksheet Generator");
        System.out.println("========================================");

        String inputCSV = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\scenario-based\\JavaIostreamsScneario\\src\\com\\day1\\markmate\\students_marks.csv";
        String outputJSON = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\scenario-based\\JavaIostreamsScneario\\src\\com\\day1\\markmate\\report_card.json";

        List<Student> students = CSVReaderUtil.readCSV(inputCSV);

        for (Student student : students) {
            MarkProcessor.process(student);
        }

        JsonWriterUtil.writeToJson(students, outputJSON);
    }
}
