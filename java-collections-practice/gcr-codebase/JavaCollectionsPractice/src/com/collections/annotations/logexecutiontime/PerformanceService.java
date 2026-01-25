package com.collections.annotations.logexecutiontime;

public class PerformanceService {

    @LogExecutionTime
    public void fastTask() {
        for (int i = 0; i < 1_000; i++) {
            // small task
        }
    }

    @LogExecutionTime
    public void slowTask() {
        for (int i = 0; i < 50_000_000; i++) {
            // heavy task
        }
    }

    public void normalTask() {
        System.out.println("This method is not timed.");
    }
}
