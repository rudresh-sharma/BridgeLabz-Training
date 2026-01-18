package com.generics.resumescreeningsystem;

public class ProductManager extends JobRole {

    public ProductManager() {
        super("Product Manager");
    }

    @Override
    public boolean isEligible(int experience, int score) {
        return experience >= 3 && score >= 65;
    }
}