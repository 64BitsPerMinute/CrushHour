package com.sixtyfourbitsperminute.crushhour;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 *
 * This class extends the Vehicle class and represents a truck, of length three, 
 * that can be moved around on the game board.
 */
public class Truck extends Vehicle{
	
	/**
	 * A variable holding the length of the truck
	 */
	int length = 3;

	/**
	 * This is a constructor for this class. It takes a boolean that tells you 
	 * whether or not the truck is horizontally or vertically placed, and a coordinate 
	 * that gives the top most or left most position of the truck on the grid.
	 * 
	 * @param direction A boolean holding the directionality of the truck. 
	 * @param position A coordinate that holds the top most or left most position 
	 * of the truck. 
	 */
	public Truck (boolean direction, Coordinate position){
		this.horizontal = direction;
		this.position = new Coordinate(position.x, position.y);
	}
	
	/**
	 * This is a constructor for this class. It takes a boolean that tells you 
	 * whether or not the truck is horizontally or vertically placed, and two integers 
	 * that combined give the top most or left most position of the truck on the grid. 
	 * 
	 * @param direction A boolean holding the directionality of the truck.
	 * @param xPosition An integer holding the x position of the truck. 
	 * @param yPosition An interger holding the y postion of the truck. 
	 */
	public Truck (boolean direction, int xPosition, int yPosition){
		this.horizontal = direction;
		this.position = new Coordinate(xPosition, yPosition);
	}
}
