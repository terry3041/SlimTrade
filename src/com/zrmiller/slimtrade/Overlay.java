package com.zrmiller.slimtrade;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zrmiller.slimtrade.buttons.MenuButton;
import com.zrmiller.slimtrade.panels.CharacterWindow;
import com.zrmiller.slimtrade.panels.HistoryWindow;
import com.zrmiller.slimtrade.panels.BasicMenuWindow;
import com.zrmiller.slimtrade.panels.OptionWindow;
import com.zrmiller.slimtrade.panels.StashWindow;
import com.zrmiller.slimtrade.panels.Menubar;

public class Overlay {

	//Pseudo Constants
	public static Dimension screenSize;
	public static int screenWidth;
	public static int screenHeight;
	public static FlowLayout flowLeft;
	public static FlowLayout flowCenter;
	public static FlowLayout flowRight;
	
	//PANELS
	public static CharacterWindow characterPanel;
	public static HistoryWindow historyPanel;
	public static Menubar menubar;
	public static JButton menubarShowButton;
	public static MessageManager messageManager;
	public static OptionWindow optionPanel;
	public static StashWindow stashWindow;
	
	public Overlay(){
		
		//Initialize Pseudo Constants
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.width;
		screenHeight = screenSize.height;
		flowLeft = new FlowLayout(FlowLayout.LEFT, 0, 0);
		flowCenter = new FlowLayout(FlowLayout.CENTER, 0, 0);
		flowRight = new FlowLayout(FlowLayout.RIGHT, 0, 0);
		
		//Initialize Screen Frame
		JFrame screenFrame = new JFrame();
		screenFrame.setLayout(null);
		screenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenFrame.setUndecorated(true);
		screenFrame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		screenFrame.setAlwaysOnTop(true);
		Container screenContainer = screenFrame.getContentPane();
		
		//TEMP VIEWABLE FRAME
		
		//TEMP EXIT BUTTON IN UPPER LEFT CORNER
//		JButton TEMPEXITBUTTON = new JButton();
//		TEMPEXITBUTTON.setBounds(0, 0, 20, 20);
//		screenContainer.add(TEMPEXITBUTTON);
//		TEMPEXITBUTTON.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				screenFrame.dispose();
//			}
//		});
		
		//Menu Bar Show Button
		menubarShowButton = new JButton();
		menubarShowButton.setBounds(0, (int)(screenSize.getHeight()-MenuButton.height), MenuButton.height, MenuButton.height);
		menubarShowButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent e) {
//		    	if(e.getButton() == MouseEvent.BUTTON3){
//		    		System.out.println("!");
//		    		System.exit(0);
//		    	}
		    	menubarShowButton.setVisible(false);
		    	Overlay.menubar.setVisible(true);
		    }
		});
		screenContainer.add(menubarShowButton);
		
		//Menu Bar+
		menubar = new Menubar();
		screenContainer.add(menubar);
		
		optionPanel = new OptionWindow();
		screenContainer.add(optionPanel);
		
		characterPanel = new CharacterWindow();
		screenContainer.add(characterPanel);
		
		historyPanel = new HistoryWindow();
		centerFrame(historyPanel);
		screenContainer.add(historyPanel);
		
		stashWindow = new StashWindow();
		screenContainer.add(stashWindow);
		
		//Message Manager - SHOULD ALWAYS BE LAST
		MessageManager msgManager = new MessageManager();
		screenContainer.add(msgManager);
		
		//Finish
		screenFrame.pack();
		screenFrame.setVisible(true);
	}
	
	public static void hideAllTempFrames(){
		Overlay.characterPanel.setVisible(false);
		Overlay.historyPanel.setVisible(false);
		Overlay.optionPanel.setVisible(false);
		Overlay.stashWindow.setVisible(false);
	}
	
	public static void centerFrame(JPanel panel){
		panel.setLocation(screenWidth/2-panel.getWidth()/2, screenHeight/2-panel.getHeight()/2);
	}
	
}