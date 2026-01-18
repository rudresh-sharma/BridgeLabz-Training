package com.generics.mealplangenerator;

public class Meal<T extends MealType> {

    private String userName;
    private T mealType;

    public Meal(String userName, T mealType) {
        this.userName = userName;
        this.mealType = mealType;
    }

    public String toString() {
        return userName + " | " + mealType.getMealDescription();
    }
}