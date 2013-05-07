package com.sixtyfourbitsperminute.crushhour;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;
import com.illposed.osc.OSCPortOut;

public class Communicator {
	Grid currentPuzzle = null;
	OSCPortOut sender = null;
	OSCPortIn reciever = null;

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
					Grid solved = s.bruteForce(currentPuzzle);
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
					Grid solved = s.bruteForce(currentPuzzle);
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
	
	public void sendToMaxSteps(int maxNumberOfSteps) {
		OSCMessage message1 = new OSCMessage("/ch/maxsteps");
		message1.addArgument(maxNumberOfSteps);
		send(message1);
		
	}
	
	public void sendToMaxSolutions(int maxNumberOfSolutions) {
		OSCMessage message1 = new OSCMessage("/ch/maxsolutions");
		message1.addArgument(maxNumberOfSolutions);
		send(message1);
		
	}

	public void sendToGrid(String stringToSend){
		OSCMessage message1 = new OSCMessage("/ch/grid");
		message1.addArgument(stringToSend);
		send(message1);
	}
	
	private void send(OSCMessage oscMessage){
		try {
			sender.send(oscMessage);
			System.out.println("Sent message!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean portsAreValid(){
		if(reciever == null || sender == null){
			return false;
		}
		return true;
	}

	
	public void startListening(){
		reciever.startListening();
	}
	public void tearDown() {
//		sender.close();
//		reciever.stopListening();
//		reciever.close();

		
	}
}