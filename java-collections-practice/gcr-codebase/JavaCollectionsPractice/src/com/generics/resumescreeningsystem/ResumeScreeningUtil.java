package com.generics.resumescreeningsystem;

public class ResumeScreeningUtil {

    public static <T extends JobRole> void screenResume(Resume<T> resume) {

        if (resume.getJobRole().isEligible(
                resume.getExperience(), resume.getAiScore())) {

            System.out.println(resume + " → SHORTLISTED");
        } else {
            System.out.println(resume + " → REJECTED");
        }
    }
}