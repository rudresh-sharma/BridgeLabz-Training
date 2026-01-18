package com.generics.mealplangenerator;

import java.util.*;

public class PersonalizedMealPlanSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Meal<? extends MealType>> meals = new ArrayList<>();

        System.out.print("Enter number of users: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nUser " + i);
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.println("Select Meal Type:");
            System.out.println("1. Vegetarian");
            System.out.println("2. Vegan");
            System.out.println("3. Keto");
            System.out.println("4. High Protein");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                meals.add(MealPlanGenerator.generateMealPlan(name, new VegetarianMeal()));
            } else if (choice == 2) {
                meals.add(MealPlanGenerator.generateMealPlan(name, new VeganMeal()));
            } else if (choice == 3) {
                meals.add(MealPlanGenerator.generateMealPlan(name, new KetoMeal()));
            } else if (choice == 4) {
                meals.add(MealPlanGenerator.generateMealPlan(name, new HighProteinMeal()));
            } else {
                System.out.println("Invalid choice");
                i--;
            }
        }

        System.out.println("\n---- Personalized Meal Plans ----");
        for (Meal<? extends MealType> meal : meals) {
            System.out.println(meal);
        }

        sc.close();
    }
}