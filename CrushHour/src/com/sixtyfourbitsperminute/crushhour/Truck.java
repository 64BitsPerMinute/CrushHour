package com.sixtyfourbitsperminute.crushhour;

public class Truck extends Vehicle{
	int length = 3;

	public Truck (boolean direction, Coordinate position){
		this.horizontal = direction;
		this.position = new Coordinate(position.x, position.y);
	}
	
	public Truck (boolean direction, int xPosition, int yPosition){
		this.horizontal = direction;
		this.position = new Coordinate(xPosition, yPosition);
	}
}
