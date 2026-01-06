/*
 * 7. Counting Sort - Sort Student Ages
Problem Statement:
A school collects students’ ages (ranging from 10 to 18) and wants them sorted. Implement Counting Sort for this task.
Hint:
Create a count array to store the frequency of each age.
Compute cumulative frequencies to determine positions.
Place elements in their correct positions in the output array.

 */


package com.algorithms.sortingalgorithms.countingsort;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        Student[] students = new Student[n];

        System.out.println("Enter student ages (10 to 18):");
        for (int i = 0; i < n; i++) {
            int age;
            while (true) {
                System.out.print("Enter age for student " + (i + 1) + " (10 to 18): ");
                age = sc.nextInt();

                if (age >= 10 && age <= 18)
                    break;
                else
                    System.out.println("❌ Invalid age! Please enter between 10 and 18.");
            }
            students[i] = new Student(age);
        }

        CountingSort.sort(students);

        System.out.println("\nSorted Student Ages:");
        for (Student s : students) {
            System.out.print(s.getAge() + " ");
        }
    }
}
