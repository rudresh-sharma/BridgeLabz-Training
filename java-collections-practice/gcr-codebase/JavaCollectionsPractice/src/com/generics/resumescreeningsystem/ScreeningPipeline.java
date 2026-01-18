package com.generics.resumescreeningsystem;

import java.util.List;

class ScreeningPipeline {

    public static void processResumes(List<? extends JobRole> roles) {
        System.out.println("Processing resumes for roles:");
        for (JobRole role : roles) {
            System.out.println("- " + role.getRoleName());
        }
    }
}