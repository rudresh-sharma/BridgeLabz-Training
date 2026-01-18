package com.generics.resumescreeningsystem;

public class SoftwareEngineer extends JobRole {

    public SoftwareEngineer() {
        super("Software Engineer");
    }

    @Override
    public boolean isEligible(int experience, int score) {
        return experience >= 2 && score >= 70;
    }
}