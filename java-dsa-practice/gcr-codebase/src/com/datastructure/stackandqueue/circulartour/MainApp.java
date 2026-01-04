package com.datastructure.stackandqueue.circulartour;

public class MainApp {
    public static void main(String[] args) {

        PetrolPump[] pumps = {
                new PetrolPump(4, 6),
                new PetrolPump(6, 5),
                new PetrolPump(7, 3),
                new PetrolPump(4, 5)
        };

        int start = CircularTour.findStartingPoint(pumps);

        if (start == -1)
            System.out.println("No possible tour");
        else
            System.out.println("Start from petrol pump: " + start);
    }
}
