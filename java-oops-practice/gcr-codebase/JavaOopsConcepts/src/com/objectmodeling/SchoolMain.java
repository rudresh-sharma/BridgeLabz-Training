package com.objectmodeling;

public class SchoolMain {
    public static void main(String[] args) {

        // Create School
        School school = new School("Green Valley School");

        // Create Students
        Student john = new Student("John");
        Student emma = new Student("Emma");

        // Add students to school (Aggregation)
        school.addStudent(john);
        school.addStudent(emma);

        // Create Courses
        Course maths = new Course("Maths");
        Course science = new Course("Science");

        // Students enroll in courses (Association)
        john.enrollCourse(maths);
        john.enrollCourse(science);

        emma.enrollCourse(science);

        // Display
        school.showStudents();
        john.showCourses();
        emma.showCourses();

        maths.showStudents();
        science.showStudents();
    }
}
