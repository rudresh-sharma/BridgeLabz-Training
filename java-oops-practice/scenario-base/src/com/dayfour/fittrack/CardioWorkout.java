package com.dayfour.fittrack;

public class CardioWorkout extends Workout {

    public CardioWorkout(int duration) {
        super("Cardio", duration);
    }

    public void calculateCalories() {
        caloriesBurned = duration * 8; // Polymorphism
    }
}
