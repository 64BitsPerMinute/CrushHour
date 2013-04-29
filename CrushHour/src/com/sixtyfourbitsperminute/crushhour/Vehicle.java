package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

	public boolean isHorizontal() {
		return horizontal;
	}

	public ArrayList<Move> getPossibleMoves(Grid grid){
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		//every time new move is created call move.doesNotSurpassGrid(); ?
		HashMap<Character, Vehicle> vehicles = grid.getVehicles();
		
		//Get all moves
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
		
		//Discard moves not on grid and of distance zero
		for (Iterator<Move> it=possibleMoves.iterator(); it.hasNext();) {
		    if ((!it.next().doesNotSurpassGrid())||it.next().getSteps()==0){
		        it.remove(); 		    	
		    }
		}
		
		
		//From moves, discard intersecting moves.
		
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

	public boolean intersects(Coordinate coordinate) {
		ArrayList<Coordinate> covered = this.getCoveredCoordinates();
		for (int i = 0; i < covered.size(); i++) {
			if (coordinate.equals(covered.get(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean intersects(ArrayList<Coordinate> coordinates) {
		for (int i = 0; i < coordinates.size(); i++) {
			if (this.intersects(coordinates.get(i))) {
				return true;
			}
		}
		return false;
	}

}
