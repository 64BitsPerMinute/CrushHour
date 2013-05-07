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
		ArrayList<Coordinate> gridCovered = grid.getCoveredCoordinatesExcludingVehicle(this);
		if(horizontal){
			for(int x = position.x+1;x<gridSize;x++){
				if(gridCovered.contains(new Coordinate(x,position.y))){
					break;
				}
				if(x<5&&x>=0){
					possibleMoves.add(new Move(this, x-position.x, gridSize));
				}
			}
			for(int x = position.x-1;x>=0;x--){
				if(gridCovered.contains(new Coordinate(x,position.y))){
					break;
				}
				if(x<5&&x>=0){
				possibleMoves.add(new Move(this, x-position.x, gridSize));
				}
			}
		} else {
			for(int y = position.y+1;y<gridSize;y++){
				if(gridCovered.contains(new Coordinate(position.x,y))){
					break;
				}
				if(y<5&&y>=0){
				possibleMoves.add(new Move(this, y-position.y, gridSize));
				}
			}
			for(int y = position.y-1;y>=0;y--){
				if(gridCovered.contains(new Coordinate(position.x,y))){
					break;
				}
				if(y<5&&y>=0){
				possibleMoves.add(new Move(this, y-position.y, gridSize));
				}
			}
		}
		
		if(possibleMoves.size()==0){
			return possibleMoves;
		}
		for (Iterator<Move> it=possibleMoves.iterator(); it.hasNext();) {
		    if ((!it.next().doesNotSurpassGrid())){
		        it.remove(); 		    	
		    }
		}
		ArrayList<Coordinate> gridCoveredCoordinates = grid.getCoveredCoordinatesExcludingVehicle(this);
		for (Iterator<Move> it=possibleMoves.iterator(); it.hasNext();) {
			boolean coordinateContained = false;
			for(Coordinate c : this.getCoveredCoordinatesAfterMove(it.next())){
				if(gridCoveredCoordinates.contains(c)||(!c.isWithinGrid(grid))){
					coordinateContained = true;
				}
			}
			if(coordinateContained){
				it.remove();
			}
		}		
//		for (Iterator<Move> it=possibleMoves.iterator(); it.hasNext();) {
//			Move m = it.next();
//			if(m.steps==4||m.steps==-4){
//				for(Coordinate c : grid.getCoveredCoordinatesExcludingVehicle(this)){
//					if(horizontal){
//						if(c.y==this.position.y&&c.x==3){
//							it.remove();
//						}
//					}else{
//						if(c.x==this.position.x&&c.y==3){
//							it.remove(); //Catch Jumps
//						}
//					}
//				}
//			}
//		}
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

	
	/**
	 * This method overrides the hashCode() method in order to ensure that vehicles 
	 * in a list or map can be found using the .contains() method.
	 * @return an int containing the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (horizontal ? 1231 : 1237);
		result = prime * result + identifier;
		result = prime * result + length;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	
	/**
	 * This method overrides the equals() method in order to ensure that vehicles 
	 * can be found in a list or map using the .contains() method.
	 * @param obj The vehicle to be compared to.
	 * @return Whether or not the two vehicles are a match.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (horizontal != other.horizontal)
			return false;
		if (identifier != other.identifier)
			return false;
		if (length != other.length)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	
}
