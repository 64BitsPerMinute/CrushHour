package com.sixtyfourbitsperminute.crushhour;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;
import com.illposed.osc.OSCPortOut;

/**
 * @author Jonathan Thompson
 * @author Kelly Croswell
 * 
 * This class is the one that does all of the relaying of messages back and forth 
 * between the Java program and the Max MSP GUI. 
 */
public class Communicator {
	/**
	 * A Grid containing the puzzle to be solved.
	 */
	Grid currentPuzzle = null;
	
	/**
	 * The port of the sender.
	 */
	OSCPortOut sender = null;
	
	/**
	 * The port of the reciever.
	 */
	OSCPortIn reciever = null;

	/**
	 * This is the constructor method for this class. It initializes the ports 
	 * and adds the listeners to various GUI elements so that the program can 
	 * respond appropriately to activity from the user.
	 */
	public Communicator() {
		try {
			sender = new OSCPortOut();
			reciever = new OSCPortIn(57111);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		this.addListeners();
	}
	
	/**
	 * This method adds all of the listeners that are set up on the GUI to the 
	 * Java program so that anytime the user interacts with the GUI in a meaningful 
	 * way the Java program can respond with the appropriate information. This is 
	 * mainly used to send puzzles back and forth when a puzzle is selected, solved, 
	 * and the solution scrolled through.
	 */
	private void addListeners() {
		reciever.addListener("/input", new OSCListener() {
			@Override
			public void acceptMessage(Date time, OSCMessage message) {
				System.out.println((String)message.getArguments()[0]);
			}
		});
		reciever.addListener("/gridinput", new OSCListener() {
			@Override
			public void acceptMessage(Date time, OSCMessage message) {
				String mes = ((String)message.getArguments()[0]);
				//mes.concat("\n");
				mes = mes + "Z";
				System.out.println("Recieved " + mes);
				Parser parser = new Parser(mes);
				if(parser.fileCanCreateGrid()){
					currentPuzzle = parser.createGrid();
					sendToGrid(currentPuzzle.gridToString());
					Solver s = new Solver();
					//Grid solved = s.bruteForce(currentPuzzle);
					Grid solved = s.BFS(currentPuzzle);
					if(solved != null){
						solved.previousGrids.add(solved);
						System.out.println("Solved User Puzzle");
						sendToGrid(solved.gridToString());
						sendToMaxSteps(solved.previousGrids.size());
						currentPuzzle = solved;
					}else{
						System.out.print("I can't solve that! ");
					}
				} else {
					System.out.print("I can't parse that! ");
				}
			}
		});
		reciever.addListener("/presetnumber", new OSCListener() {
			@Override
			public void acceptMessage(Date time, OSCMessage message) {
				int mes = ((Integer)message.getArguments()[0]);
				//mes.concat("\n");
				//mes = mes + "Z";
				System.out.println("Solving Preset Number " + mes);
				Parser parser = new Parser(GridStrings.getGrid(mes-1));
				if(parser.fileCanCreateGrid()){
					
					currentPuzzle = parser.createGrid();
					sendToGrid(currentPuzzle.gridToString());
					Solver s = new Solver();
					//Grid solved = s.bruteForce(currentPuzzle);
					Grid solved = s.BFS(currentPuzzle);
					if(solved != null){
						solved.previousGrids.add(solved);
						System.out.println("Solved Preset Number " + mes);
						sendToGrid(solved.gridToString());
						sendToMaxSteps(solved.previousGrids.size());
						currentPuzzle = solved;
					}else{
						System.out.print("I can't solve that! ");
					}
					
				} else {
					System.out.print("I can't parse that! ");
				}
			}
		});
		reciever.addListener("/stepnumber", new OSCListener() {
			@Override
			public void acceptMessage(Date time, OSCMessage message) {
				int mes = ((Integer)message.getArguments()[0]);
				//mes.concat("\n");
				//mes = mes + "Z";
				System.out.println("Showing Step Number " + mes);
				if(currentPuzzle.previousGrids.size()<mes){
					System.out.print("Index Out Of Bounds!");
					return;
				}
				sendToGrid(currentPuzzle.previousGrids.get(mes-1).gridToString());
			}
		});
		
	}
	
	/**
	 * This method sends the number of steps a solution has to the GUI to be 
	 * printed out as data for the user.
	 * @param maxNumberOfSteps The number of steps the solution contains.
	 */
	public void sendToMaxSteps(int maxNumberOfSteps) {
		OSCMessage message1 = new OSCMessage("/ch/maxsteps");
		message1.addArgument(maxNumberOfSteps);
		send(message1);
		
	}
	
	/**
	 * This method sends the number of solutions a puzzle has to the GUI to be 
	 * relayed as data for the user. No longer a part of interface.
	 * @param maxNumberOfSolutions The number of solutions the puzzle has.
	 */
	public void sendToMaxSolutions(int maxNumberOfSolutions) {
		OSCMessage message1 = new OSCMessage("/ch/maxsolutions");
		message1.addArgument(maxNumberOfSolutions);
		send(message1);
		
	}

	/**
	 * This method sends a String containing a grid to the GUI to be interpreted 
	 * and displayed. 
	 * @param stringToSend A string containg the Grid to be sent.
	 */
	public void sendToGrid(String stringToSend){
		OSCMessage message1 = new OSCMessage("/ch/grid");
		message1.addArgument(stringToSend);
		send(message1);
	}
	
	/**
	 * This method is the one that actually sends a packet to the Max GUI to be 
	 * interpreted and displayed appropriately. 
	 * @param oscMessage The message to be sent to the GUI.
	 */
	private void send(OSCMessage oscMessage){
		try {
			sender.send(oscMessage);
			System.out.println("Sent message!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method checks to see if the ports that the sender and reciever are 
	 * using are valid and usable. 
	 * @return Whether or not the ports are usable.
	 */
	public boolean portsAreValid(){
		if(reciever == null || sender == null){
			return false;
		}
		return true;
	}
	
	/**
	 * This is the method that starts up the reciever so that the user execute 
	 * actions in the GUI
	 */
	public void startListening(){
		reciever.startListening();
	}
	
	/**
	 * This method is the tear down method for the Communicator, which closes the 
	 * ports and shuts down the reciever.
	 */
	public void tearDown() {
//		sender.close();
//		reciever.stopListening();
//		reciever.close();
	}
}