package com.sixtyfourbitsperminute.crushhour;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import com.illposed.osc.*;

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
		Communicator communicator = new Communicator();
		
		communicator.startListening();
	
//		if(!communicator.portsAreValid()){
//			System.out.println("Invalid Ports!");
//			System.exit(1);
//		}
//		
		
//		//String gridString = GridStrings.getGrid(1);
//		String gridString = "bxxcccZbxxdxeZAAxdfeZgggxfeZxxhxiiZjjhkkxZ";
////		String gridString = "xxxxxxZxxxxxbZAAxxxbZxxxxxxZxxxxxxZxxxxxxZ";
//		Parser parser = new Parser(gridString);
//		if(parser.fileCanCreateGrid()){
//			Grid grid = parser.createGrid();
//			System.out.println("Created grid");
//			System.out.println(grid.gridToString());
//			communicator.sendToGrid(grid.gridToString());
//			Solver solve = new Solver();
//			Grid g = solve.bruteForce(grid);
//			if(g!=null){
//				System.out.println("Solution: " + g.gridToString());
//				communicator.sendToGrid(g.gridToString());
//			}else{
//				System.out.println("Solution not found");
//			}
//		}
//		
//		
		//send test message
		
		
		
//		Object payload[] = new Object[1];
//		payload[0] = "bbxxxcexxdxceAAdxcexxdxxfxxxggfxhhhx";
//		OSCMessage mesg = new OSCMessage("/ch/grid", payload);
//			try {
//				sender.send(mesg);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		
		
		
		
		
		for(;;){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		communicator.tearDown();
//		System.exit(1);
	}
	

}
