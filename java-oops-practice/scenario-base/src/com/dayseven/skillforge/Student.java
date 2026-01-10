package com.dayseven.skillforge;
public class Student extends User implements ICertifiable {

    private double progress;   // % completed
    private double grade;

    public Student(String name, String email) {
        super(name, email);
        this.progress = 0;
        this.grade = 0;
    }

    // Operator logic
    public void updateProgress(double completed) {
        progress = progress + completed;   // +=
        if (progress > 100) progress = 100;
    }

    public void addMarks(double marks) {
        grade = grade + marks;   // +
    }

    @Override
    public void generateCertificate() {
        System.out.println("\n----- SkillForge Certificate -----");
        System.out.println("Student: " + name);
        System.out.println("Completion: " + progress + "%");
        System.out.println("Grade: " + grade);
        System.out.println("Status: " + (progress >= 80 ? "Certified" : "Not Eligible"));
    }
}
