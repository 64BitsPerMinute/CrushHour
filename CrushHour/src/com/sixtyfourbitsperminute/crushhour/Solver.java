package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 * 
 * This method contains the method calls that will result in a brute force solving 
 * of the puzzle.
 */
public class Solver {

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
	
	/**
	 * This method implements a simple, non recursive breadth first search algorithm 
	 * as an alternative way to search a Rush Hour Grid for a solution. It does this 
	 * by adding a "root" grid to a queue, then adding each child of the root grid to 
	 * the queue as well. In this case, a child of the root grid is each move that can 
	 * be executed from each vehicle on the grid. These children are then evaluated in 
	 * turn providing us not only with a solution, but the shortest path solution.
	 * @param grid The grid to be evaluated.
	 * @return The solved grid with a list of steps.
	 */
	public Grid BFS (Grid grid){
		if(grid == null){
			return null;
		}
		if (grid.playerCanExit()) {
			return grid;
		}
		Queue<Grid> queue = new LinkedList<Grid>();
		ArrayList<Grid> listOfGrids = new ArrayList<Grid>();
		listOfGrids.add(grid);
		queue.add(grid);
		int count = 0;
		while(!queue.isEmpty()) {
			Grid parent = queue.remove();
			Grid child = null;
			for(char c : parent.vehicles.keySet()){
				Vehicle current = parent.vehicles.get(c);
				ArrayList<Move> currentMoves = current.getPossibleMoves(parent);
				for(int i = 0; i < currentMoves.size(); i++){
					if(parent.executeMove(currentMoves.get(i)).nullIfPreviousState() == null){
						System.out.println("in null if previous state");
						continue;
					} else {
						count++;
						child = parent.executeMove(currentMoves.get(i));
						System.out.println("created new child");
						if(child.playerCanExit()){
//							System.out.println("found solution");
							
							return child;
						} else if(!listOfGrids.contains(child)){
//							System.out.println("adding child to queue at position: " + count);
							queue.add(child);
							listOfGrids.add(child);
						} else {
//							System.out.println("queue already contained child.");
						}
					}
				}
			}
		}
		return null;
	}
}
