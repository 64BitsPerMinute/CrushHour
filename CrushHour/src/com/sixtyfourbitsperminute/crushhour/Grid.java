package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;

public class Grid {
	ArrayList<Vehicle> vehicleList;
	ArrayList<Grid> previousGrids;
	
	
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
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
				+ ((vehicleList == null) ? 0 : vehicleList.hashCode());
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
		if (vehicleList == null) {
			if (other.vehicleList != null)
				return false;
		} else if (!vehicleList.equals(other.vehicleList))
			return false;
		return true;
	}
	public Grid executeMove(Object object) {
		
		return null;
	}
	
}
