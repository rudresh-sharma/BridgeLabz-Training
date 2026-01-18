package com.generics.resumescreeningsystem;

public class DataScientist extends JobRole {

    public DataScientist() {
        super("Data Scientist");
    }

    @Override
    public boolean isEligible(int experience, int score) {
        return experience >= 1 && score >= 75;
    }
}