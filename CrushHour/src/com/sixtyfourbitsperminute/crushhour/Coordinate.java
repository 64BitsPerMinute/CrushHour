package com.sixtyfourbitsperminute.crushhour;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 *
 * This class creates a Coordinate of an x and y paired integer for use in mapping 
 * vehicles to a grid.
 */
public class Coordinate {
	
	/**
	 * A variable that holds the x value of the coordinate.
	 */
	int x;
	
	/**
	 * A variable that holds the y value of the coordinate. 
	 */
	int y;
	
	/**
	 * This is the constructor for this class. It takes in two integers and maps 
	 * them to the x and y values in the Coordinate. 
	 * @param x The integer that will be mapped to the x value.
	 * @param y The integer that will be mapped to the y value. 
	 */
	public Coordinate(int x, int y) {
		this.x = new Integer(x);
		this.y = new Integer(y);
	}

	/**
	 * This is the overridden hash code method for the coordinate class. Implemented 
	 * so that .contains() can be called in lists.  
	 * @return An integer containing the hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * This is the overridden equals method for the coordinate class. Implemented 
	 * so that .contains() can be called in lists. 
	 * @return Whether or not two coordinate pairs are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public boolean isWithinGrid(Grid grid) {
		if(x>=0 && y>=0 && x<grid.getGridSize() && y<grid.getGridSize()){
			return true;
		}
		return false;
	}

	
}
