package com.generics.resumescreeningsystem;

public abstract class JobRole {

    protected String roleName;

    public JobRole(String roleName) {
        this.roleName = roleName;
    }

    public abstract boolean isEligible(int experience, int score);

    public String getRoleName() {
        return roleName;
    }
}