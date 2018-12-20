package com.zrmiller.slimtrade.oldfiles;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zrmiller.slimtrade.datatypes.MessageType;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		ChatParser parser = new ChatParser();
		long st = System.nanoTime();
		parser.update();
		long et = System.nanoTime();
		System.out.println("Program launched in " + (et-st)/1000000 + " miliseconds.");
		

		//Screen Frame + Screen Panel
		JFrame screenFrame = new JFrame();
		
		screenFrame.setLayout(null);
		screenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenFrame.setUndecorated(true);
		screenFrame.setBackground(new Color(1.0f,1.0f,1.0f,0f));
		screenFrame.setAlwaysOnTop(true);
		screenFrame.pack();
		Container screen = screenFrame.getContentPane();
		
		//Screen Components
		MenuBar menuBar = new MenuBar();
		MessageManager msgManager = new MessageManager(screenFrame);
		//msgManager.setLocation(500, 0);
		screen.add(menuBar);
		screen.add(msgManager);

		screenFrame.pack();
		//Once everything else is done, show screen overlay
		screenFrame.setVisible(true);
		
		
		//Global Buttons
		int min = 1;
		int max = 12;
		//double x = (int)(Math.random()*((max-min)+1))+min;
		//double y = (int)(Math.random()*((max-min)+1))+min;
		//TradeOffer t = new TradeOffer(MessageType.INCOMING_TRADE, "PLAYERNAME", "ITEM", 0, "CHAOS", 24, "STASHTABNAME", (int)x, (int)y);
		menuBar.plusButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	double x = (int)(Math.random()*((max-min)+1))+min;
				double y = (int)(Math.random()*((max-min)+1))+min;
				TradeOffer t = new TradeOffer(MessageType.INCOMING_TRADE, "", "PLAYERNAME", "ITEM", 0, "alch", 2, "STASHTABNAME", (int)x, (int)y);
				msgManager.addMessage(t);
				//2nd msg
				x = (int)(Math.random()*((max-min)+1))+min;
				y = (int)(Math.random()*((max-min)+1))+min;
				t = new TradeOffer(MessageType.OUTGOING_TRADE, "",  "PLAYERNAME", "APPRENTICE_CARTOGRAPHERS_SEXTANT", 14, "CHAOSMONEY", 10, "STASHTABNAME", (int)x, (int)y);
				msgManager.addMessage(t);
				//3rd msg
				t = new TradeOffer(MessageType.INCOMING_TRADE, "",  "PLAYERNAME", "exITEM", 5, "exchaos", 500, "STASHTABNAME", (int)x, (int)y);
				msgManager.addMessage(t);
				x = (int)(Math.random()*((max-min)+1))+min;
				y = (int)(Math.random()*((max-min)+1))+min;
				//4th msg
				t = new TradeOffer(MessageType.OUTGOING_TRADE, "",  "PLAYERNAME", "alch", 5000, "chaos", 5, "STASHTABNAME", (int)x, (int)y);
				msgManager.addMessage(t);
				x = (int)(Math.random()*((max-min)+1))+min;
				y = (int)(Math.random()*((max-min)+1))+min;
				}
		});
		
		//SET TO 1 TO RUN CHAT SCANNER
		
		boolean scanChat = true;
		
		while(scanChat){
			//System.out.println("Update");
			boolean update = parser.update();
			if(update){
				while(parser.messageQueue>0){
					msgManager.addMessage(parser.tradeHistory[parser.getFixedIndex(0-parser.messageQueue)]);
					parser.messageQueue--;
				}
				update = false;
			}
					
			Thread.sleep(500);
		}
	}
}