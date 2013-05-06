package com.sixtyfourbitsperminute.crushhour;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 *
 * This class extends the Vehicle class and represents a car, of length two, 
 * that can be moved around on the game board.
 */
public class Car extends Vehicle {
	
	/**
	 * This is a constructor for this class. It takes a boolean that tells you 
	 * whether or not the car is horizontally or vertically placed, and a coordinate 
	 * that gives the top most or left most position of the car on the grid.
	 * 
	 * @param direction A boolean holding the directionality of the car. 
	 * @param position A coordinate that holds the top most or left most position 
	 * of the car. 
	 */
	public Car (boolean direction, Coordinate position){
		this.horizontal = direction;
		this.position = new Coordinate(position.x, position.y);
		this.length = 2;
	}
	
	/**
	 * This is a constructor for this class. It takes a boolean that tells you 
	 * whether or not the car is horizontally or vertically placed, and two integers 
	 * that combined give the top most or left most position of the car on the grid. 
	 * 
	 * @param direction A boolean holding the directionality of the car.
	 * @param xPosition An integer holding the x position of the car. 
	 * @param yPosition An interger holding the y postion of the car. 
	 */
	public Car (boolean direction, int xPosition, int yPosition){
		this.horizontal = direction;
		this.position = new Coordinate(xPosition, yPosition);
		this.length = 2;
	}
}
