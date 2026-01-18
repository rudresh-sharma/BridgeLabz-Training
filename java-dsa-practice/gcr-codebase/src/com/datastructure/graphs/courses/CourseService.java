package com.datastructure.graphs.courses;

import java.util.*;

public class CourseService {

    private CourseGraph graph;
    private Scanner sc;

    public CourseService() {
        graph = new CourseGraph();
        sc = new Scanner(System.in);
    }

    public void buildGraph() {
        System.out.print("Enter number of courses: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter course names:");
        for (int i = 0; i < n; i++) {
            graph.addCourse(sc.nextLine());
        }

        System.out.print("Enter number of prerequisites: ");
        int p = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter prerequisites (A B means A -> B):");
        for (int i = 0; i < p; i++) {
            String from = sc.next();
            String to = sc.next();
            graph.addPrerequisite(from, to);
        }
    }

    public void processQueries() {

        // Task 2: Cycle detection
        System.out.println("\nCycle exists: " + graph.hasCycle());

        // Task 3: Prerequisites before a course
        System.out.print("\nEnter course to find prerequisites: ");
        String course = sc.next();
        System.out.println("Prerequisites: " +
                graph.getAllPrerequisites(course));

        // Task 4: Topological order
        System.out.println("\nValid course order:");
        System.out.println(graph.topologicalSort());
    }
}
