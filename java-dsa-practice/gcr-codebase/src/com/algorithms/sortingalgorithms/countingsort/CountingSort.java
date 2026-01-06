package com.algorithms.sortingalgorithms.countingsort;
public class CountingSort {

    public static void sort(Student[] students) {

        int minAge = 10;
        int maxAge = 18;
        int range = maxAge - minAge + 1;

        int[] count = new int[range];

        // Step 1: Count frequency
        for (Student s : students) {
            count[s.getAge() - minAge]++;
        }

        // Step 2: Cumulative frequency
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Output array
        Student[] output = new Student[students.length];

        for (int i = students.length - 1; i >= 0; i--) {
            int age = students[i].getAge();
            int pos = count[age - minAge] - 1;
            output[pos] = students[i];
            count[age - minAge]--;
        }

        // Copy back
        for (int i = 0; i < students.length; i++) {
            students[i] = output[i];
        }
    }
}
