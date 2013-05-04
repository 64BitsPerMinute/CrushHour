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
			"bbxxxc\ndxxexc\ndAAexc\ndxxexx\nfxxxgg\nfxhhhx\n",
			"bxxccc\nbxxdxe\nAAxdfe\nfffxfe\nxxhxii\njjhkkx\n",
			"xxxxxx\nxxxxxx\nxAAbxx\nxccbxd\nxexbxd\nxeffxd\n",
			"bxxcxx\nbxxcxx\nbAAcxx\nxxdeee\nxxdxxf\nxxgggf\n",
			"bbxcxd\nexxcfd\neAAcfg\nehhhfg\nixxxjj\nixxxkk\n",
			"bbxcxx\nddxcef\nxAAgef\nhhigef\njxigxx\njxxkkk\n",
			"xbccde\nxbxfde\nxAAfxg\nxxhhxg\nxxxixx\nxxxixx\n",
			"xxxbbc\nxxddec\nAAfgec\nhhfgii\njjklll\nmmknnn\n",
			"xbccdd\nxbxeff\nAAxegh\nijjjgh\nixkxgl\nixkxxl\n",
			"bbcxdd\neecxxf\ngAAxxf\nghhhxf\ngxxijj\nkkxill\n",
			"bccdxx\nbxxdxx\nbAAdxx\nxxefff\nxxexxg\nxxhhhg\n",
			"bccxxd\nbxexxd\nAAexxd\nxxefff\nxxxxgx\nhhhxgx\n",
			"bbccdx\nxxexdf\nxgeAAf\nhgxiif\nhxxjkk\nhlljmm\n",
			"bbcxxx\nxxcxdd\nefAAgh\nefiigh\nxxjxkk\nlljxxx\n",
			"xbbccx\nddeefg\nhiAAfg\nhijkfg\nhijkll\nxmmnnx\n",
			"bbccde\nfxggde\nfhiAAe\nxhijjj\nxxixxx\nkkxxxx\n",
			"bcccxx\nbxddee\nAAfxxx\nggfhxx\niiihjl\nkkkhjl\n",
			"bbcdxx\neecdxx\nfAAdxx\nfgggxx\nfhhxxx\niiixxx\n",
			"xxbccx\nxxbxex\nxdAAex\nxdffgx\nxhhhgx\nxxxxxx\n",
			"bxxccc\nbddexx\nAAfexg\nxxfxxg\nxxhiig\nxxhjjj\n",
			"bbcdxx\nexcdxx\neAAdxx\nefffxx\nxxxxxx\nxxxggg\n",
			"xxbccc\ndxbeff\ndAAexx\nxgxehh\nigjjxk\nilllxk\n",
			"xxbbbc\nxxdeec\nxxdAAc\nxxfghh\nxxfgii\nxxjjjx\n",
			"xxbccx\nxebxxx\ndeAAfx\ndggxfx\nhhhxix\njjxxix\n",
			"bbcxdd\neecxxf\ngAAxhf\ngiiihf\ngjxkll\nxjxkmm\n",
			"xbxccc\ndbxefx\ndAAefg\nhiiifg\nhxjxxk\nxxjllk\n",
			"bccdxx\nbeedxx\nAAfdxg\nxxfhhg\nxxixxg\nxxijjj\n",
			"bbbcxx\nxxdcee\nAAdxxx\nfgdhhi\nfgjjji\nkkllxi\n",
			"bbbxcx\nxxdxcx\nAAdxce\nfgghhe\nfiijxk\nllljxk\n",
			"bxcddd\nbxcexx\nbAAexx\nffggxh\nxxxxxh\niijjxh\n",
			"bbxccc\nxxxdee\nfAAdxg\nfxhiig\njjhxxg\nxxhkkk\n",
			"bbcdee\nxxcdxx\nAAcxxx\nfgghhi\nfxxjxi\nkkxjxi\n",
			"xbcxdd\nxbcxxx\nAAcxxx\neffggh\neiijkh\nllljkh\n",
			"bxxccc\nbxxdxe\nAAxdfe\nggghfe\nxxihjj\nkkillx\n",
			"xxbccd\nxxbexd\nAAbexd\nfgggxx\nfhhijx\nkkxijx\n",
			"bcccdd\nbeffxg\nbeAAxg\nhhhixg\nxxjikk\nlljxxx\n",
			"bbcxdd\neecxfg\nhAAxfg\nhiiifg\nhxxjkk\nllxjmm\n",
			"bxxccc\nbddexx\nAAfexg\nxxfhhg\nxxijjg\nxxikkk\n",
			"xxbccc\nxxbdxx\nAAedxf\nggehhf\nijkkxf\nijllxx\n",
			"bccxdx\nbefxdg\nbefAAg\nhhhixg\nxxjikk\nlljmmx\n",
			"bcddde\nbcxxfe\nbAAxfg\nhhixfg\nxxijjj\nxxkkll\n",
			"bbbcdx\nxeecdx\nfAAgxx\nfxhgii\nfxhjjk\nlllxxk\n",
			"xbbccd\nxxefxd\nAAefxg\nhxijjg\nhxikkg\nhllmmm\n",
			"bcccxx\nbxdeff\nAAdexg\nhhiexg\nxjixxk\nxjlllk\n",
			"xbccdd\nxbeffg\nAAexxg\nhiijxg\nhxxjkk\nhlllxx\n",
			"bxcxdd\nbxcxef\nAAcxef\nxghhhf\nigxjkk\nilljxx\n",
			"xbbccx\nxxdexx\nAAdexx\nffdghh\nijjgxk\nilllxk\n",
			"xxbbbc\nxxdxxc\nxxdAAc\neefgxx\nhifgjj\nhikkkx\n",
			"xbccdd\nxbxeff\nAAxegx\nhiiigj\nhxkxgj\nhxkllj\n",
			"bbcxdd\nxxcxee\nAAfxxg\nhxfiig\nhjjkxl\nhmmkxl\n",
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
