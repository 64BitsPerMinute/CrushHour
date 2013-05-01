package com.sixtyfourbitsperminute.crushhour;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 * 
 * This class holds the information necessary to execute a move by one vehicle on 
 * a given grid.
 */
public class Move {
	
	/**
	 * The vehicle that will be moving on the grid.
	 */
	Vehicle vehicle;
	
	/**
	 * An int that holds the number of steps that the move will take. Positive 
	 * integers move to the left or down, negative integers move to the right or up.
	 */
	int steps; 
	
	/**
	 * An int that holds the size of the grid.
	 */
	int gridSize;
	
	/**
	 * The constructor for this class. It takes in the vehicle that will be moving, 
	 * the number of steps the move contains and the size of the grid in order to 
	 * check the validity of a move.
	 * @param vehicle A vehicle holding the car or truck that will be moving.
	 * @param steps An int that holds the number of steps necessary.
	 * @param gridSize An int that holds the size of the grid.
	 */
	public Move (Vehicle vehicle, int steps, int gridSize){
		this.vehicle = vehicle;
		this.steps = steps;
		this.gridSize = gridSize;
	}

	/**
	 * This method that returns the vehicle associated with the move.
	 * @return The vehicle that will be moving.
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * This method returns the number of steps to be taken.
	 * @return An int containing the steps.
	 */
	public int getSteps() {
		return steps;
	}
	
	/**
	 * This method checks to make sure that the intended number of moves to be 
	 * taken by the car do not move the car off of the grid. It returns true if 
	 * the move will stay within the grid, false if it moves the car off of the 
	 * grid.
	 * @return A boolean containing whether or not the move is valid.
	 */
	public boolean doesNotSurpassGrid(){
		int moveable = 0;
		if(this.vehicle.isHorizontal()){
			moveable = this.vehicle.position.x;
		} else {
			moveable = this.vehicle.position.y;
		}
		if(moveable + this.steps + this.vehicle.length < this.gridSize && steps > 0){
			return true;
		} else if (moveable + this.steps > 0 && steps < 0){
			return true;
		} else {
			return false;
		}
	}

}
