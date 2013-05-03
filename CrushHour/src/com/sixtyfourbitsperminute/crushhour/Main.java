package com.sixtyfourbitsperminute.crushhour;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 * 
 * This is the main method of the program, where the heavy lifting happens.
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String gridString = GridStrings.getRandomGrid();
		Parser parser = new Parser(gridString);
		if(parser.fileCanCreateGrid()){
			//Grid grid = parser.createGrid();
			System.out.println("Created grid");
		}

	}

}
