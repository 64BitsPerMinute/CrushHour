package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 * 
 * This method contains the method calls that will result in a brute force solving 
 * of the puzzle.
 */
public class Solver {

	/**
	 * A HashMap containing the list of vehicles on the grid.
	 */
	
	
	/**
	 * This method checks every move on the grid, for every vehicle, executes 
	 * each and recursively checks the resultant grid for a brute force solution 
	 * to the puzzle.
	 * @param grid The grid to be solved.
	 * @return The solved grid, if it is solvable. 
	 */
	public Grid bruteForce(Grid grid) {
		if(grid == null){
			return null;
		}
		HashMap<Character, Vehicle> vehicles = grid.getVehicles();
		if (grid.playerCanExit()) {
			return grid;
		}
		// for each vehicle
		for (Character key : vehicles.keySet()) {
			// for each move
			ArrayList<Move> moves = vehicles.get(key).getPossibleMoves(grid);
			if (moves.isEmpty()) {
				continue;
			}
			for (int j = 0; j < moves.size(); j++) {
				Grid g = bruteForce(grid.executeMove(moves.get(j)).nullIfPreviousState());
				if (g != null) {
					return g;
				}
			}
		}
		return null;
	}	
}
