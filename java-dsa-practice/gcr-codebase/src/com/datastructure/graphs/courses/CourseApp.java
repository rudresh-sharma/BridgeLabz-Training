package com.datastructure.graphs.courses;

public class CourseApp {

    public static void main(String[] args) {

        CourseService service = new CourseService();
        service.buildGraph();
        service.processQueries();
    }
}
