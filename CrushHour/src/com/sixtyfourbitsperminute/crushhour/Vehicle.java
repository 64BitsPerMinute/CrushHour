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
		if(horizontal){
			for(int i = 0; i<length; i++){
				c.add(new Coordinate(position.x+i,position.y));
			}
		} else {
			for(int i = 0; i<length; i++){
				c.add(new Coordinate(position.x,position.y+i));
			}
		}
		return c;
	}
	
	
	public boolean intersects(Coordinate coordinate){
		ArrayList<Coordinate> covered = this.coveredCoordinates();
		for(int i = 0; i<covered.size(); i++){
			if(coordinate.equals(covered.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public boolean intersects(ArrayList<Coordinate> coordinates){
		for(int i = 0; i<coordinates.size(); i++){
			if(this.intersects(coordinates.get(i))){
				return true;
			}
		}
		return false;
	}
	
}
