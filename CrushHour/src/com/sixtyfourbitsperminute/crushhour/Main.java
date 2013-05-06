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
		String gridString = GridStrings.getRandomGrid();
		Parser parser = new Parser(gridString);
		if(parser.fileCanCreateGrid()){
			//Grid grid = parser.createGrid();
			System.out.println("Created grid");
		}
		
		//send test message
		OSCPortOut sender = null;
		OSCPortIn reciever = null;
		try {
			sender = new OSCPortOut();
			reciever = new OSCPortIn(57111);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		Object payload[] = new Object[1];
		payload[0] = "bbxxxcexxdxceAAdxcexxdxxfxxxggfxhhhx";
		OSCMessage mesg = new OSCMessage("/ch/grid", payload);
			try {
				sender.send(mesg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		reciever.addListener("/input", new OSCListener() {
			
			@Override
			public void acceptMessage(Date time, OSCMessage message) {
				System.out.println((Float)message.getArguments()[0]);
			}
		});
		reciever.startListening();
		for(;;){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
