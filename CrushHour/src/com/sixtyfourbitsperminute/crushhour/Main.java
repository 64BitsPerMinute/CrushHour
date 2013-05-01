package com.sixtyfourbitsperminute.crushhour;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String gridString = "bbxxxc\nexxdxc\neAAdxc\nexxdxx\nfxxxgg\nfxhhhx\n";
		Parser parser = new Parser(gridString);
		if(parser.fileCanCreateGrid()){
			Grid grid = parser.createGrid();
			System.out.println("Created grid");
		}

	}

}
