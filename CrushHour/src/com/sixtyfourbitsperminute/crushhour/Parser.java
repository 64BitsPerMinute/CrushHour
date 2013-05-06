package com.sixtyfourbitsperminute.crushhour;

import java.util.HashMap;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 * 
 * This method parses a String of text that represents a grid and turns it into 
 * a Grid object if the grid is valid.
 */
public class Parser {
	
	/**
	 * A String containing the unparsed grid.
	 */
	String gridString;
	
	/**
	 * An array of Strings containing each separated line of the grid.
	 */
	String[] gridLines;
	
	/**
	 * An int containing the length of the grid.
	 */
	int gridSize;
	
	/**
	 * A two dimensional array of chars containing the fully separated grid.
	 */
	char[][] grid;
	
	/**
	 * A HashMap containing the list of potential vehicles on the grid.
	 */
	HashMap<Character, Vehicle> vehicleMap = new HashMap<Character, Vehicle>();

	/**
	 * This is the constructor for this class which takes in a String containing 
	 * a potenial grid and sets it on a global variable.
	 * @param gridString A string containing the unparsed grid.
	 */
	public Parser (String gridString){
		this.gridString = gridString;
	}
	
	/**
	 * This method returns whether or not the grid is square because only square 
	 * grids are valid in this version of the game.
	 * @return A boolean containing whether or not the grid is square.
	 */
	public boolean isSquareGrid(){
		if(this.gridString.length() == 0){
			return false;
		}
		String[] gridLines = this.gridString.split("Z");
		//System.out.println(gridLines);
		for(int i = 0; i < gridLines.length; i++){
			gridLines[i].trim();
		}
		if(gridLines[0].length() == gridLines.length && 
				gridLines[1].length() == gridLines.length &&
				gridLines[2].length() == gridLines.length &&
				gridLines[3].length() == gridLines.length &&
				gridLines[4].length() == gridLines.length &&
				gridLines[4].length() == gridLines.length){
			//System.out.println("Inside if statement in isSquareGrid.");
			this.gridLines = gridLines;
			this.gridSize = gridLines.length;
			readStringInto2DArray();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method reads the previously lines of the grid into a two dimensional 
	 * char array, in the global variables.
	 */
	public void readStringInto2DArray(){
		this.grid = new char[this.gridSize][this.gridSize];
		for(int i = 0; i < this.gridSize; i++){
			for(int j = 0; j < this.gridSize; j++){
				char current = this.gridLines[i].charAt(j);
				this.grid[i][j] = current;
			}
		}
	}

	/**
	 * This method checks through every character in the grid to see if it is 
	 * a non "empty space" character, then checks to see if that character is 
	 * attached to a legal car or truck, then checks to ensure that the vehicle 
	 * is not repeated anywhere else on the grid. If, at any point, one of these 
	 * conditions is false the method returns false, causing the parse to return 
	 * false, meaning the potential grid is invalid.
	 * @return A boolean containing whether or not all of the potential vehicles 
	 * are legal.
	 */
	public boolean allVehiclesAreLegal(){
		for(int row = 0; row < this.gridSize; row++){
			for(int column = 0; column < this.gridSize; column++){
				char current = this.grid[row][column];
				//System.out.println("Current character: " + current + ", and current position: [" + row + ", " + column + "]");
				int[] position = {row, column};
				char[] neighbors = {'?', '?', '?', '?', '?', '?', '?', '?'};
				neighbors = readInNeighbors(neighbors, row, column);
				//System.out.println(neighbors);
				if(current == 'x'){
					continue;
				} else {
					if(isTruck(current, neighbors, position)){
						//System.out.println("In Truck if statement");
						boolean direction = getDirection(current, neighbors);
						addVehicleToMap(current, direction, position, 3);
					} else if(isCar(current, neighbors, position)){
						//System.out.println("In car if statement");
						boolean direction = getDirection(current, neighbors);
						addVehicleToMap(current, direction, position, 2);
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * This method returns whether the vehicle in question is horizontally placed 
	 * or vertically placed on the grid. Returning true is horizontal, false is 
	 * vertical.
	 * @param current The character representation of the current vehicle.
	 * @param neighbors The characters immediately surrounding the current char.
	 * @return A boolean containing the vehicle's orientation.
	 */
	public boolean getDirection (char current, char[] neighbors){
		if(current == neighbors[4] || current == neighbors[6]){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method checks to see if the current character in the grid is part of 
	 * a truck on the grid, and whether or not it has a duplicate somewhere.
	 * @param current The current character in the grid.
	 * @param neighbors The characters immediately surrounding the current character.
	 * @param position The position that the current character is at in the array.
	 * @return A boolean containing whether or not the character is part of a truck.
	 */
	public boolean isTruck(char current, char[] neighbors, int[] position){
		if(current == neighbors[0] && current == neighbors[1]){
			int[] positionTwo = {(position[0]-1), position[1]};
			int[] positionThree = {(position[0]-2), position[1]};
			if(!letterExistsElsewhere(current, position, positionTwo, positionThree)){
				//System.out.println("truck position 1");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[2] && current == neighbors[3]){
			int[] positionTwo = {(position[0]+1), position[1]};
			int[] positionThree = {(position[0]+2), position[1]};
			if(!letterExistsElsewhere(current, position, positionTwo, positionThree)){
				//System.out.println("truck position 2");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[0] && current == neighbors[2]){
			int[] positionTwo = {(position[0]-1), position[1]};
			int[] positionThree = {(position[0]+1), position[1]};
			if(!letterExistsElsewhere(current, position, positionTwo, positionThree)){
				//System.out.println("truck position 3");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[4] && current == neighbors[5]){
			int[] positionTwo = {position[0], (position[1]-1)};
			int[] positionThree = {position[0], (position[1]-2)};
			if(!letterExistsElsewhere(current, position, positionTwo, positionThree)){
				//System.out.println("truck position 4");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[6] && current == neighbors[7]){
			int[] positionTwo = {position[0], (position[1]+1)};
			int[] positionThree = {position[0], (position[1]+2)};
			if(!letterExistsElsewhere(current, position, positionTwo, positionThree)){
				//System.out.println("truck position 5");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[4] && current == neighbors[6]){
			int[] positionTwo = {position[0], (position[1]-1)};
			int[] positionThree = {position[0], (position[1]+1)};
			if(!letterExistsElsewhere(current, position, positionTwo, positionThree)){
				//System.out.println("truck position 6");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * This method checks to see if the current character in the grid is part of 
	 * a car on the grid, and whether or not it has a duplicate somewhere.
	 * @param current The current character in the grid.
	 * @param neighbors The characters immediately surrounding the current character.
	 * @param position The position that the current character is at in the array.
	 * @return A boolean containing whether or not the character is part of a car.
	 */
	public boolean isCar(char current, char[] neighbors, int[] position){
		if(current == neighbors[0]){
			int[] positionTwo = {(position[0]-1), position[1]};
			if(!letterExistsElsewhere(current, position, positionTwo, null)){
				//System.out.println("car position 1");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[2]){
			int[] positionTwo = {(position[0]+1), position[1]};
			if(!letterExistsElsewhere(current, position, positionTwo, null)){
				//System.out.println("car position 2");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[4]){
			int[] positionTwo = {position[0], (position[1]-1)};
			//System.out.println(positionTwo[0] + "," + positionTwo[1]);
			if(!letterExistsElsewhere(current, position, positionTwo, null)){
				//System.out.println("car position 3");
				return true;
			} else {
				return false;
			}
		} else if (current == neighbors[6]){
			int[] positionTwo = {position[0], (position[1]+1)};
			if(!letterExistsElsewhere(current, position, positionTwo, null)){
				//System.out.println("car position 4");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * This method checks to see if the current character exists anywhere else 
	 * in the grid except the locations that are part of the potenial vehicle. 
	 * If the character does occur elsewhere, then the vehicle is not legal, the 
	 * grid is invalid, and the method returns false. 
	 * @param current The current character in the grid.
	 * @param positionOne The position that the current character is in on the grid.
	 * @param positionTwo The position of a neighboring character that is part of a 
	 * potential car or truck.
	 * @param positionThree The position of a neighboring character that is part of 
	 * a potential truck.
	 * @return A boolean containing whether or not the character exists somewhere 
	 * else in the grid.
	 */
	public boolean letterExistsElsewhere(char current, int[] positionOne, int[] positionTwo, int[] positionThree){
		for(int i = 0; i < this.grid.length; i++){
			for(int j = 0; j < this.grid.length; j++){
				if(positionThree == null){
					if((i == positionOne[0] && j == positionOne[1]) || (i == positionTwo[0] && j == positionTwo[1])){
						continue;
					} else if (this.grid[i][j] == current){
						//System.out.println("Comparing current character to: " + this.grid[i][j]);
						return true;
					}
				} else if ((i == positionOne[0] && j == positionOne[1]) || (i == positionTwo[0] && j == positionTwo[1]) 
						|| (i == positionThree[0] && j == positionThree[1])){
					continue;
				} else {
					if (this.grid[i][j] == current){
						//System.out.println("Comparing current character to: " + this.grid[i][j]);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * This method takes an array of characters set to question marks and fills 
	 * it, where they exist, with the neighboring characters of the current grid 
	 * position. 
	 * @param neighbors The array of characters that are neighbors to the current 
	 * position.
	 * @param row An int holding the current row of the position.
	 * @param column An int holding the current column of the position.
	 * @return An array containing the neighbors of the current position.
	 */
	public char[] readInNeighbors(char[] neighbors, int row, int column){
		if(row-1 >= 0){
			neighbors[0] = this.grid[row-1][column];
		}
		if(row-2 >= 0){
			neighbors[1] = this.grid[row-2][column];
		}
		if(row+1 < this.gridSize){
			neighbors[2] = this.grid[row+1][column];
		}
		if(row+2 < this.gridSize){
			neighbors[3] = this.grid[row+2][column];
		}
		if(column-1 >= 0){
			neighbors[4] = this.grid[row][column-1];
		}
		if(column-2 >= 0){
			neighbors[5] = this.grid[row][column-2];
		}
		if(column+1 < this.gridSize){
			neighbors[6] = this.grid[row][column+1];
		}
		if(column+2 < this.gridSize){
			neighbors[7] = this.grid[row][column+2];
		}
		return neighbors;
	}
	
	/**
	 * This method adds the newly found vehicle on the grid to the list of vehicles 
	 * that have already been found, if it has not already been added. 
	 * @param current The current character in the grid.
	 * @param direction The orientation of the newly found vehicle.
	 * @param position The position of the newly found vehicle. 
	 * @param length The length of the newly found vehicle.
	 */
	public void addVehicleToMap(char current, boolean direction, int[] position, int length){
		Vehicle vehicle;
		if(length == 3){
			vehicle = new Truck(direction, position[1], position[0]);
		} else {
			vehicle = new Car(direction, position[1], position[0]);
		}
		if(!vehicleMap.containsKey(current)){
			vehicleMap.put(current, vehicle);
		}
	}
	
	/**
	 * This method returns the "user" car, or the car that must be escaped from 
	 * the grid.
	 * @return A Vehicle containing the User Car.
	 */
	public Vehicle findUserCar(){
		return vehicleMap.get('A');
	}
	
	/**
	 * This method checks to ensure that the potential grid has a "user" car, or 
	 * the car that must be escaped from the grid. If the grid does nto contain a 
	 * User Car, the method returns false and the grid is invalid. 
	 * @return A boolean containing whether or not a User Car exists.
	 */
	public boolean gridHasUserCar(){
		if(vehicleMap.containsKey('A')){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method systemically checks through the grid, using the other methods 
	 * in the class, to ensure that the potential grid can greate a valid, legal 
	 * Grid object. If any of the previous methods fail, this method fails and 
	 * the grid is invalid.
	 * @return A boolean containing the validity of the potential grid.
	 */
	public boolean fileCanCreateGrid(){
		if(isSquareGrid()){
			System.out.println("is square");
			if(allVehiclesAreLegal()){
				System.out.println("all vehicles legal");
				if(gridHasUserCar()){
					System.out.println("has user car");
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * This method checks to ensure that a valid grid can be created and, if it 
	 * can, it creates the grid. 
	 * @return The newly created grid.
	 */
	public Grid createGrid(){
		Grid grid = null;
		if(fileCanCreateGrid()){
			grid = new Grid(this.vehicleMap, this.gridSize);
		}
		return grid;
	}
}
