package com.sixtyfourbitsperminute.crushhour;

public class Truck extends Vehicle{
	int length = 3;

	public Truck (boolean direction, int[] position){
		this.horizontal = direction;
		this.position[0] = position[0];
		this.position[1] = position[1];
	}
}
