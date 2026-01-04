package com.datastructure.stackandqueue.circulartour;

public class CircularTour {

    public static int findStartingPoint(PetrolPump[] pumps) {
        int start = 0;
        int balance = 0;
        int deficit = 0;

        for (int i = 0; i < pumps.length; i++) {
            balance += pumps[i].petrol - pumps[i].distance;

            if (balance < 0) {
                start = i + 1;
                deficit += balance;
                balance = 0;
            }
        }

        return (balance + deficit >= 0) ? start : -1;
    }
}
