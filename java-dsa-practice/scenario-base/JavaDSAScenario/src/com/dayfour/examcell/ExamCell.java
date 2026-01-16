/*
5. ExamCell – Student Rank Generator (Merge Sort)
Story: An online exam system collects scores from multiple test centers. To publish a
state-level rank list, student scores are merged and sorted using Merge Sort, ensuring
performance and accuracy.
Key Concepts:
● Merging pre-sorted center-wise scores
● Sorting across centers
● High efficiency for big data
*/



package com.dayfour.examcell;
public class ExamCell {

    public static void main(String[] args) {

        // Pre-sorted center-wise results
        Student[] center1 = {
                new Student(101, "Amit", 95),
                new Student(102, "Neha", 88),
                new Student(103, "Ravi", 75)
        };

        Student[] center2 = {
                new Student(201, "Pooja", 92),
                new Student(202, "Karan", 85),
                new Student(203, "Simran", 70)
        };

        Student[] center3 = {
                new Student(301, "Rahul", 90),
                new Student(302, "Sneha", 80),
                new Student(303, "Vikas", 72)
        };

        // Merge all center results into one big array
        Student[] allStudents = new Student[
                center1.length + center2.length + center3.length];

        int index = 0;
        for (Student s : center1) allStudents[index++] = s;
        for (Student s : center2) allStudents[index++] = s;
        for (Student s : center3) allStudents[index++] = s;

        // Final state-level ranking
        StudentMergeSort.mergeSort(allStudents, 0, allStudents.length - 1);

        System.out.println("🏆 State-Level Rank List");
        int rank = 1;
        for (Student s : allStudents) {
            System.out.println("Rank " + rank++ + " → " + s);
        }
    }
}
