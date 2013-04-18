package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;
import java.util.HashMap;

public class Solver {

	HashMap<Character, Vehicle> vehicles;
	
	public Grid bruteForce(Grid grid) {
		vehicles = grid.getVehicles();
		
		if (grid.playerCanExit()) {
			return grid;
		}
		
		// for each vehicle
		
		for (Character key : vehicles.keySet()) {
			
			// for each move
			ArrayList moves = vehicles.get(key).getPossibleMoves(grid);
			if (moves.isEmpty()) {
				continue;
			}

			for (int j = 0; j < moves.size(); j++) {
				Grid g = bruteForce(grid.executeMove(moves.get(j)));
				if (g != null) {
					return grid;
				}
			}

		}

		return null;

	}

	
}
