package com.dayten.bagnball;

import java.util.ArrayList;

public class Bag {
    private String uniqueId;
    private String color;
    private int capacity;
    private ArrayList<Ball> balls;

    public Bag(String uniqueId, String color, int capacity) {
        this.uniqueId = uniqueId;
        this.color = color;
        this.capacity = capacity;
        this.balls = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    // Add ball to bag
    public boolean addBall(Ball ball) {
        if (ball.getSize() <= capacity) {
            balls.add(ball);
            capacity -= ball.getSize();
            return true;
        } else {
            return false;
        }
    }

    // Remove ball by ID
    public boolean removeBall(String ballId) {
        for (Ball b : balls) {
            if (b.getUniqueId().equals(ballId)) {
                capacity += b.getSize();
                balls.remove(b);
                return true;
            }
        }
        return false;
    }

    // Display balls
    public void displayBalls() {
        if (balls.isEmpty()) {
            System.out.println("No balls in this bag.");
            return;
        }

        for (Ball b : balls) {
            System.out.println("Ball ID: " + b.getUniqueId() +
                               ", Color: " + b.getColor() +
                               ", Size: " + b.getSize());
        }
    }

    public int getBallCount() {
        return balls.size();
    }
}
