package com.dayeight.gamebox;

public class ArcadeGame extends Game {

    public ArcadeGame(String title, double price, double rating) {
        super(title, "Arcade", price, rating);
    }

    @Override
    public void download() {
        System.out.println(title + " (Arcade) downloaded.");
    }

    @Override
    public void playDemo() {
        System.out.println(title + " Arcade demo: 60 seconds of fast action!");
    }
}
