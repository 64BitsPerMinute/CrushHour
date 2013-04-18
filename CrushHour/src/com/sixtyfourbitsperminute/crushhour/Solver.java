package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;

public class Solver {

	public Grid bruteForce(Grid grid) {
		if (checkIfSolved(grid)) {
			return grid;
		}
		
		// for each vehicle
		ArrayList<Vehicle> vehicles = grid.getVehicleList();
		for (int i = 0; i < vehicles.size(); i++) {

			// for each move
			ArrayList moves = vehicles.get(i).getPossibleMoves(grid);
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

	private boolean checkIfSolved(Grid grid) {
		return false;

	}
}
