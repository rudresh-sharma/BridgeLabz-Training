package com.dayfour.fittrack;

public class StrengthWorkout extends Workout {

    public StrengthWorkout(int duration) {
        super("Strength", duration);
    }

    public void calculateCalories() {
        caloriesBurned = duration * 6; // Polymorphism
    }
}
