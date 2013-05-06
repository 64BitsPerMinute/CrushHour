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
					OSCMessage message1 = new OSCMessage("/ch/grid");
					message1.addArgument(currentPuzzle.gridToString());
					send(message1);
					
				} else {
					System.out.print("I can't parse that! ");
				}
			}
		});
		reciever.startListening();
		
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
}