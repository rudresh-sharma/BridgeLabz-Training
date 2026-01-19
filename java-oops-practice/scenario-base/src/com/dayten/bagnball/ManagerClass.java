package com.dayten.bagnball;

public class ManagerClass {

    private Bag[] bags;

    public ManagerClass(int noOfBags) {
        bags = new Bag[noOfBags];
    }

    public Bag[] getBags() {
        return bags;
    }

    // Add ball to a specific bag
    public void addAtBag(Ball ball, int bagNumber) {
        if (bagNumber < 1 || bagNumber > bags.length) {
            System.out.println("Invalid bag number");
            return;
        }

        Bag bag = bags[bagNumber - 1];

        if (bag.addBall(ball)) {
            System.out.println("Ball added successfully to Bag " + bagNumber);
        } else {
            System.out.println("Not enough capacity in this bag!");
        }
    }

    // Remove ball from a bag
    public void removeFromBag(String ballId, int bagNumber) {
        if (bagNumber < 1 || bagNumber > bags.length) {
            System.out.println("Invalid bag number");
            return;
        }

        Bag bag = bags[bagNumber - 1];

        if (bag.removeBall(ballId)) {
            System.out.println("Ball removed successfully.");
        } else {
            System.out.println("Ball not found in this bag.");
        }
    }

    // Display all bags and ball count
    public void displayBagSummary() {
        for (int i = 0; i < bags.length; i++) {
            System.out.println("Bag " + (i + 1) +
                    " | ID: " + bags[i].getUniqueId() +
                    " | Remaining Capacity: " + bags[i].getCapacity() +
                    " | Ball Count: " + bags[i].getBallCount());
        }
    }
}
