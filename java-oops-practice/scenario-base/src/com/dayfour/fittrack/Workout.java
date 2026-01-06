package com.dayfour.fittrack;

public abstract class Workout implements ITrackable {

    protected String type;
    protected int duration; // minutes
    protected int caloriesBurned;

    public Workout(String type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public abstract void calculateCalories();

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void startWorkout() {
        System.out.println(type + " workout started...");
    }

    public void stopWorkout() {
        System.out.println(type + " workout finished.");
    }
}
