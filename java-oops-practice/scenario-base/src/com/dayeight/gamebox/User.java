package com.dayeight.gamebox;

import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Game> ownedGames = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    // Encapsulated: only via this method
    public void buyGame(Game game) {
        ownedGames.add(game);
        System.out.println(game.getTitle() + " added to " + name + "'s library.");
    }

    public void showLibrary() {
        System.out.println("\n" + name + "'s Games:");
        for (Game g : ownedGames) {
            System.out.println("- " + g.getTitle());
        }
    }
}
