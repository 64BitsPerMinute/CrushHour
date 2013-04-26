package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;

public class Vehicle {
	boolean horizontal;
	int length;
	Coordinate position;
	
	public Coordinate getPosition() {
		return position;
	}
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public boolean isHorizontal(){
		return horizontal;
	}
	
	public ArrayList<Move> getPossibleMoves(Grid grid){
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		//every time new move is created call move.doesNotSurpassGrid(); 
		
		return possibleMoves;
	}
	
	public ArrayList<Coordinate> coveredCoordinates(){
		ArrayList<Coordinate> c = new ArrayList<Coordinate>();
		
		
		
		
		return c;
	}
	
	
	public boolean intersects(Coordinate c){
		for(int i = 0; i<length; i++){
			if(horizontal){
				
			} else {
				
			}
		}
		
		
		return false;
	}
	
}
