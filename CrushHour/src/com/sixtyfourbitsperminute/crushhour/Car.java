package com.sixtyfourbitsperminute.crushhour;

//this is a comment

public class Car extends Vehicle {
	int length = 2;

	public Car (boolean direction, Coordinate position){
		this.horizontal = direction;
		this.position.x = position.x;
		this.position.y = position.y;
	}
	
	public Car (boolean direction, int xPosition, int yPosition){
		this.horizontal = direction;
		this.position.x = xPosition;
		this.position.y = yPosition;
	}
}
