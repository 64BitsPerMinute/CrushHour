package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 *
 * This class is the super class for both cars and trucks, containing the 
 * getters and setters and the rest of the global variables.
 */
public class Vehicle {
	
	/**
	 * A boolean revealing whether or not the vehicle is horizontally placed.
	 */
	boolean horizontal;
	
	/**
	 * An integer containing the length of the vehicle.
	 */
	int length;
	
	/**
	 * A Coordinate containing the position of the vehicle on the grid.
	 */
	Coordinate position;
	
	/**
	 * An identifier character for the vehicle.
	 */
	char identifier;

	/**
	 * This method returns the position of the vehicle on the grid.
	 * @return A Coordinate containing the position.
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * This method allows you to set the position of the vehicle on the grid, 
	 * useful for when vehicles move.
	 * @param position A Coordinate containing the new position of the vehicle.
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}

	/**
	 * This method returns whether or not the vehicle is horizontally placed. 
	 * True means the vehicle is horizontal, false means it is vertical. 
	 * @return A boolean containing the orientation. 
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * This method takes in a grid, containing a multitude of vehicles and checks to 
	 * see if the current vehicle has any valid moves. If it does it returns an 
	 * ArrayList of those moves, if not it returns an empty list. This is accomplished 
	 * by getting all of the possible moves the vehicle could make, and then 
	 * checking those moves against other vehicles and grid edges. 
	 * 
	 * @param grid The grid that the vehicle exists on.
	 * @return An ArrayList containing all of the valid moves.
	 */
	public ArrayList<Move> getPossibleMoves(Grid grid){
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		HashMap<Character, Vehicle> vehicles = grid.getVehicles();
		int gridSize = grid.getGridSize();
		if(horizontal){
			for(int x = 0;x<gridSize;x++){
				possibleMoves.add(new Move(this, x-position.x, gridSize));
			}
		} else {
			for(int y = 0;y<gridSize;y++){
				possibleMoves.add(new Move(this, y-position.y, gridSize));
			}
		}
		for (Iterator<Move> it=possibleMoves.iterator(); it.hasNext();) {
		    if ((!it.next().doesNotSurpassGrid())||it.next().getSteps()==0){
		        it.remove(); 		    	
		    }
		}
		ArrayList<Coordinate> gridCoveredCoordinates = grid.getCoveredCoordinatesExcludingVehicle(this);
		for (Iterator<Move> it=possibleMoves.iterator(); it.hasNext();) {
			boolean coordinateContained = false;
			for(Coordinate c : this.getCoveredCoordinatesAfterMove(it.next())){
				if(gridCoveredCoordinates.contains(c)){
					coordinateContained = true;
				}
			}
			if(coordinateContained){
				it.remove();
			}
		}		
		return possibleMoves;
	}

	/**
	 * This method returns all of the Coordinates on the grid that the current 
	 * vehicle covers.
	 * @return An ArrayList containing all covered Coordinates.
	 */
	public ArrayList<Coordinate> getCoveredCoordinates() {
		ArrayList<Coordinate> c = new ArrayList<Coordinate>();
		if (horizontal) {
			for (int i = 0; i < length; i++) {
				c.add(new Coordinate(position.x + i, position.y));
			}
		} else {
			for (int i = 0; i < length; i++) {
				c.add(new Coordinate(position.x, position.y + i));
			}
		}
		return c;
	}
	
	/**
	 * This method returns all of the Coordinates on the grid that WILL be 
	 * covered after a vehicle has executed a particular move.
	 * @param move The move that the vehicle will execute.
	 * @return An ArrayList containing the covered Coordinates.
	 */
	public ArrayList<Coordinate> getCoveredCoordinatesAfterMove(Move move) {
		ArrayList<Coordinate> c = new ArrayList<Coordinate>();
		if (horizontal) {
			for (int i = 0; i < length; i++) {
				c.add(new Coordinate(position.x + i + move.getSteps(), position.y));
			}
		} else {
			for (int i = 0; i < length; i++) {
				c.add(new Coordinate(position.x, position.y + i + move.getSteps()));
			}
		}
		return c;
	}

	/**
	 * This method returns whether or not a potential move will cause a car to 
	 * intersect with a particular Coordinate.
	 * @param coordinate The Coordinate being tested for intersection.
	 * @return A boolean containing whether or not the intersection will happen.
	 */
	public boolean intersects(Coordinate coordinate) {
		ArrayList<Coordinate> covered = this.getCoveredCoordinates();
		for (int i = 0; i < covered.size(); i++) {
			if (coordinate.equals(covered.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns whether or not a potential move will cause a car to 
	 * intersect with a list of different Coordinates.
	 * @param coordinates The list of Coordinate being tested for intersection.
	 * @return A boolean containing whether or not the intersection will happen.
	 */
	public boolean intersects(ArrayList<Coordinate> coordinates) {
		for (int i = 0; i < coordinates.size(); i++) {
			if (this.intersects(coordinates.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns a printable string containing all of the useful information 
	 * in a particular vehicle.
	 * @return A string containing the printable statement.
	 */
	public String toString(){
		return "[" + this.position.x + ", " + this.position.y + "], " + this.horizontal; 
	}

}
