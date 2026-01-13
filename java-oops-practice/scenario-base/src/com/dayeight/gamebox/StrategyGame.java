package com.dayeight.gamebox;

public class StrategyGame extends Game {

    public StrategyGame(String title, double price, double rating) {
        super(title, "Strategy", price, rating);
    }

    @Override
    public void download() {
        System.out.println(title + " (Strategy) downloaded.");
    }

    @Override
    public void playDemo() {
        System.out.println(title + " Strategy demo: Play first 3 levels.");
    }
}
