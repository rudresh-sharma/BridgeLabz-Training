package com.generics.mealplangenerator;

public class MealPlanGenerator {

    public static <T extends MealType> Meal<T> generateMealPlan(String name, T mealType) {
        return new Meal<>(name, mealType);
    }
}