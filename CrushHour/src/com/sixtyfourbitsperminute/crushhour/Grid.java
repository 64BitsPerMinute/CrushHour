package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
	HashMap<Character, Vehicle> vehicles;
	ArrayList<Grid> previousGrids;
	int gridSize;
	
	public Grid executeMove(Object object) {

		return null;
	}
	
	public int getGridSize(){
		return gridSize;
	}
	public void setGridSize(int size){
		gridSize = size;
	}
	public HashMap<Character, Vehicle> getVehicles() {
		return vehicles;
	}
	public ArrayList<Grid> getPreviousGrids() {
		return previousGrids;
	}
	public void setPreviousGrids(ArrayList<Grid> previousGrids) {
		this.previousGrids = previousGrids;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((previousGrids == null) ? 0 : previousGrids.hashCode());
		result = prime * result
				+ ((vehicles == null) ? 0 : vehicles.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
		if (previousGrids == null) {
			if (other.previousGrids != null)
				return false;
		} else if (!previousGrids.equals(other.previousGrids))
			return false;
		if (vehicles == null) {
			if (other.vehicles != null)
				return false;
		} else if (!vehicles.equals(other.vehicles))
			return false;
		return true;
	}

	public boolean playerCanExit() {
		Car player = (Car) vehicles.get('a');
		for(int i = player.position[0]; i < gridSize; i++){
			
		}
		return false;
	}
	
	
}
