package com.dayeight.gamerzone;

import java.util.ArrayList;

public class GamerZoneMain {
		
		public static void main(String[] args) {
			ArrayList<PlayerData> players = new ArrayList<>();
			
			players.add(new PlayerData("Player1", 200));
			players.add(new PlayerData("Player2", 450));
			players.add(new PlayerData("Player3", 120));
			players.add(new PlayerData("Player4", 980));
			players.add(new PlayerData("Player5", 760));
			
			
			GamingQuickSort.quickSort(players, 0, players.size()-1);
			
			System.out.println("_____________LeaderBoard____________");
			System.out.printf("%-20s%-5s\n","Name","Score");
			
			for(PlayerData p : players) {
				System.out.printf("%-20s%-5d\n",p.getName(),p.getScore());
			}
		}
	
	
	
	
	
	
	
	
	
}
