package com.dayeight.gamerzone;

import java.util.ArrayList;

public class GamingQuickSort {
	
	public static void quickSort(ArrayList<PlayerData> players, int start, int end) {
		
		if(end<=start) return;
		
		int pivot = partition(players, start,end);
		quickSort(players,start,pivot-1);
		quickSort(players,pivot+1,end);
				
	}
	
	public static int partition(ArrayList<PlayerData> players, int start, int end) {
	
		PlayerData pivot = players.get(end);
		int i = start-1;
		
		for(int j=start; j<=end-1; j++ ) {
			if(players.get(j).getScore()>pivot.getScore()) {
				i++;
				PlayerData p = players.get(i);
				players.set(i, players.get(j));
				players.set(j, p);
			}
		}
		
		
		i++;
		PlayerData d = players.get(i);
		players.set(i, players.get(end));
		players.set(end, d);
		
		
		return i;
	}
	
	
	
}
