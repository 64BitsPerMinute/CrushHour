package com.sixtyfourbitsperminute.crushhour;

import java.util.Random;

public class GridStrings {
	String[] possibleGrids = {
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
	};
	
	public String getRandomGrid(){
		Random rand = new Random();
		int max = 160;
		int randomNum = rand.nextInt(max);
		return possibleGrids[randomNum];
	}
}
