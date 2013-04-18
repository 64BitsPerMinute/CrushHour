package com.sixtyfourbitsperminute.crushhour;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
	
	String gridString;
	String[] gridLines;
	int gridSize;
	char[][] grid;

	public Parser (String gridString){
		this.gridString = gridString;
	}
	
	public boolean isSquareGrid(){
		String[] gridLines = this.gridString.split("\n");
		for(int i = 0; i < gridLines.length; i++){
			gridLines[i].trim();
		}
		if(gridLines[0].length() == gridLines.length){
			this.gridLines = gridLines;
			this.gridSize = gridLines.length;
			readStringInto2DArray();
			return true;
		} else {
			return false;
		}
	}
	
	public void readStringInto2DArray(){
		this.grid = new char[this.gridSize][this.gridSize];
		for(int i = 0; i < this.gridSize; i++){
			for(int j = 0; j < this.gridSize; j++){
				char current = this.gridLines[i].charAt(j);
				this.grid[i][j] = current;
			}
		}
	}
	
	public boolean allVehiclesAreLegal(){
		HashMap<Character, Integer[]> listOfChars = new HashMap<Character, Integer[]>();
		for(int row = 0; row < this.gridSize; row++){
			for(int column = 0; column < this.gridSize; column++){
				char current = this.grid[row][column];
				char charAbove, charTwoAbove, charBelow, charTwoBelow, charLeft, charTwoLeft, charRight, charTwoRight = 'x';
				if(row-1 > 0){
					charAbove = this.grid[row-1][column];
				}
				if(row-2 > 0){
					charTwoAbove = this.grid[row-1][column];
				}
				if(row+1 < this.gridSize){
					charBelow = this.grid[row+1][column];
				}
				if(row+2 < this.gridSize){
					charTwoBelow = this.grid[row+2][column];
				}
				if(column-1 > 0){
					charLeft = this.grid[row][column-1];
				}
				if(column-2 > 0){
					charTwoLeft = this.grid[row][column-2];
				}
				if(column+1 > 0){
					charRight = this.grid[row][column+1];
				}
				if(column+2 > 0){
					charTwoRight = this.grid[row][column+2];
				}
			}
		}
		return false;
	}
	
	public void addVehicleToMap(){
		
	}
	
	public Vehicle findUserCar(){
		
		return null;
	}
	
	public boolean fileCanCreateGrid(){
		
		return false;
	}
	
	public Grid createGrid(){
		
		return null;
	}
}
