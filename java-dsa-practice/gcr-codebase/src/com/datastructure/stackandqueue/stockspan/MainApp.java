package com.datastructure.stackandqueue.stockspan;

public class MainApp {
    public static void main(String[] args) {

        int[] price = {100, 80, 60, 70, 60, 75, 85};

        int[] span = StockSpan.calculateSpan(price);

        System.out.print("Prices: ");
        for (int p : price)
            System.out.print(p + " ");

        System.out.print("\nSpan:   ");
        for (int s : span)
            System.out.print(s + " ");
    }
}
