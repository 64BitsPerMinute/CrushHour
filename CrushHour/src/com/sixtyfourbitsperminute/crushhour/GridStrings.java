package com.sixtyfourbitsperminute.crushhour;

import java.util.Random;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 * 
 * This class holds 50 potential puzzles that the GUI can interact with and that 
 * the user will be able to choose to have the program solve. 
 */
public class GridStrings {
	
	/**
	 * A String array containing all of the potential, premade grids.
	 */
	static String[] possibleGrids = {
			"bbxxxcZdxxexcZdAAexcZdxxexxZfxxxggZfxhhhxZ",
			"bxxcccZbxxdxeZAAxdfeZgggxfeZxxhxiiZjjhkkxZ",
			"xxxxxxZxxxxxxZxAAbxxZxccbxdZxexbxdZxeffxdZ",
			"bxxcxxZbxxcxxZbAAcxxZxxdeeeZxxdxxfZxxgggfZ",
			"bbxcxdZexxcfdZeAAcfgZehhhfgZixxxjjZixxxkkZ",
			"bbxcxxZddxcefZxAAgefZhhigefZjxigxxZjxxkkkZ",
			"xbccdeZxbxfdeZxAAfxgZxxhhxgZxxxixxZxxxixxZ",
			"xxxbbcZxxddecZAAfgecZhhfgiiZjjklllZmmknnnZ",
			"xbccddZxbxeffZAAxeghZijjjghZixkxglZixkxxlZ",
			"bbcxddZeecxxfZgAAxxfZghhhxfZgxxijjZkkxillZ",
			"bccdxxZbxxdxxZbAAdxxZxxefffZxxexxgZxxhhhgZ",
			"bccxxdZbxexxdZAAexxdZxxefffZxxxxgxZhhhxgxZ",
			"bbccdxZxxexdfZxgeAAfZhgxiifZhxxjkkZhlljmmZ",
			"bbcxxxZxxcxddZefAAghZefiighZxxjxkkZlljxxxZ",
			"xbbccxZddeefgZhiAAfgZhijkfgZhijkllZxmmnnxZ",
			"bbccdeZfxggdeZfhiAAeZxhijjjZxxixxxZkkxxxxZ",
			"bcccxxZbxddeeZAAfxxxZggfhxxZiiihjlZkkkhjlZ",
			"bbcdxxZeecdxxZfAAdxxZfgggxxZfhhxxxZiiixxxZ",
			"xxbccxZxxbxexZxdAAexZxdffgxZxhhhgxZxxxxxxZ",
			"bxxcccZbddexxZAAfexgZxxfxxgZxxhiigZxxhjjjZ",
			"bbcdxxZexcdxxZeAAdxxZefffxxZxxxxxxZxxxgggZ",
			"xxbcccZdxbeffZdAAexxZxgxehhZigjjxkZilllxkZ",
			"xxbbbcZxxdeecZxxdAAcZxxfghhZxxfgiiZxxjjjxZ",
			"xxbccxZxebxxxZdeAAfxZdggxfxZhhhxixZjjxxixZ",
			"bbcxddZeecxxfZgAAxhfZgiiihfZgjxkllZxjxkmmZ",
			"xbxcccZdbxefxZdAAefgZhiiifgZhxjxxkZxxjllkZ",
			"bccdxxZbeedxxZAAfdxgZxxfhhgZxxixxgZxxijjjZ",
			"bbbcxxZxxdceeZAAdxxxZfgdhhiZfgjjjiZkkllxiZ",
			"bbbxcxZxxdxcxZAAdxceZfgghheZfiijxkZllljxkZ",
			"bxcdddZbxcexxZbAAexxZffggxhZxxxxxhZiijjxhZ",
			"bbxcccZxxxdeeZfAAdxgZfxhiigZjjhxxgZxxhkkkZ",
			"bbcdeeZxxcdxxZAAcxxxZfgghhiZfxxjxiZkkxjxiZ",
			"xbcxddZxbcxxxZAAcxxxZeffgghZeiijkhZllljkhZ",
			"bxxcccZbxxdxeZAAxdfeZggghfeZxxihjjZkkillxZ",
			"xxbccdZxxbexdZAAbexdZfgggxxZfhhijxZkkxijxZ",
			"bcccddZbeffxgZbeAAxgZhhhixgZxxjikkZlljxxxZ",
			"bbcxddZeecxfgZhAAxfgZhiiifgZhxxjkkZllxjmmZ",
			"bxxcccZbddexxZAAfexgZxxfhhgZxxijjgZxxikkkZ",
			"xxbcccZxxbdxxZAAedxfZggehhfZijkkxfZijllxxZ",
			"bccxdxZbefxdgZbefAAgZhhhixgZxxjikkZlljmmxZ",
			"bcdddeZbcxxfeZbAAxfgZhhixfgZxxijjjZxxkkllZ",
			"bbbcdxZxeecdxZfAAgxxZfxhgiiZfxhjjkZlllxxkZ",
			"xbbccdZxxefxdZAAefxgZhxijjgZhxikkgZhllmmmZ",
			"bcccxxZbxdeffZAAdexgZhhiexgZxjixxkZxjlllkZ",
			"xbccddZxbeffgZAAexxgZhiijxgZhxxjkkZhlllxxZ",
			"bxcxddZbxcxefZAAcxefZxghhhfZigxjkkZilljxxZ",
			"xbbccxZxxdexxZAAdexxZffdghhZijjgxkZilllxkZ",
			"xxbbbcZxxdxxcZxxdAAcZeefgxxZhifgjjZhikkkxZ",
			"xbccddZxbxeffZAAxegxZhiiigjZhxkxgjZhxklljZ",
			"bbcxddZxxcxeeZAAfxxgZhxfiigZhjjkxlZhmmkxlZ",
	};
	
	/**
	 * This method returns a random grid to be solved.
	 * @return A string containing the grid.
	 */
	public static String getRandomGrid(){
		Random rand = new Random();
		int max = 50;
		int randomNum = rand.nextInt(max);
		System.out.println(randomNum);
		return possibleGrids[randomNum];
	}
	
	/**
	 * This method returns a specific grid fro the array, so that users can choose 
	 * the grid they want. 
	 * @param i The index of the grid in the array.
	 * @return A string containing the grid.
	 */
	public static String getGrid(int i){
		return possibleGrids[i];
	}
}
