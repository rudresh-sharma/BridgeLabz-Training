package com.datastructure.graphs.courses;

import java.util.*;

public class CourseGraph {

    private Map<String, List<String>> adjList;

    public CourseGraph() {
        adjList = new HashMap<>();
    }

    // Add course (vertex)
    public void addCourse(String course) {
        adjList.putIfAbsent(course, new ArrayList<>());
    }

    // Add prerequisite (directed edge)
    public void addPrerequisite(String from, String to) {
        adjList.get(from).add(to);
    }

    // ---------------- CYCLE DETECTION ----------------
    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> stack = new HashSet<>();

        for (String course : adjList.keySet()) {
            if (detectCycleDFS(course, visited, stack)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycleDFS(String course,
                                   Set<String> visited,
                                   Set<String> stack) {
        if (stack.contains(course)) return true;
        if (visited.contains(course)) return false;

        visited.add(course);
        stack.add(course);

        for (String next : adjList.get(course)) {
            if (detectCycleDFS(next, visited, stack)) {
                return true;
            }
        }
        stack.remove(course);
        return false;
    }

    // ---------------- PREREQUISITES BEFORE A COURSE ----------------
    public Set<String> getAllPrerequisites(String target) {
        Set<String> result = new HashSet<>();

        for (String course : adjList.keySet()) {
            dfsPrerequisite(course, target, new HashSet<>(), result);
        }
        return result;
    }

    private boolean dfsPrerequisite(String current, String target,
                                    Set<String> visited,
                                    Set<String> result) {
        if (current.equals(target)) return true;
        if (!visited.add(current)) return false;

        for (String next : adjList.get(current)) {
            if (dfsPrerequisite(next, target, visited, result)) {
                result.add(current);
                return true;
            }
        }
        return false;
    }

    // ---------------- TOPOLOGICAL SORT ----------------
    public List<String> topologicalSort() {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String course : adjList.keySet()) {
            if (!visited.contains(course)) {
                topoDFS(course, visited, stack);
            }
        }

        List<String> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }

    private void topoDFS(String course,
                         Set<String> visited,
                         Stack<String> stack) {
        visited.add(course);

        for (String next : adjList.get(course)) {
            if (!visited.contains(next)) {
                topoDFS(next, visited, stack);
            }
        }
        stack.push(course);
    }
}
