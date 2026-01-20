package com.dayeight.FoodFest;

public class FoodFestMain {

    public static void main(String[] args) {

        // Zone A (already sorted by footfall)
        StallData[] zoneA = {
                new StallData("Burger Hub", 120),
                new StallData("Pizza Point", 300),
                new StallData("Pasta Place", 500)
        };

        // Zone B (already sorted by footfall)
        StallData[] zoneB = {
                new StallData("Taco Town", 150),
                new StallData("Noodle Nest", 400),
                new StallData("Sweet Corner", 600)
        };

        // Combine both zones into one master array
        StallData[] masterList = new StallData[zoneA.length + zoneB.length];

        int index = 0;
        for (StallData s : zoneA)
            masterList[index++] = s;

        for (StallData s : zoneB)
            masterList[index++] = s;

        // Apply Merge Sort on combined data
        MergeSortUtil.mergeSort(masterList, 0, masterList.length - 1);

        // Print Final Leaderboard
        System.out.println("=== FoodFest Stall Performance (Sorted by Footfall) ===");
        for (StallData s : masterList) {
            System.out.println(s);
        }
    }
}
