package com.zrmiller.slimtrade.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.zrmiller.slimtrade.Overlay;
import com.zrmiller.slimtrade.dialog.BasicWindowDialog;
import com.zrmiller.slimtrade.panels.BasicPanel;
import com.zrmiller.slimtrade.panels.GridPanel;


public class StashGridOverlay extends BasicWindowDialog{
	
	private static final long serialVersionUID = 1L;
	//STATICS
	private static Point windowPos = new Point(0, 0);
	private static Dimension containerSize = new Dimension(400,400);
	private static Point gridPos = new Point(0, 0);
	private static Dimension gridSize;
	private static int gridWidth;
	private static int gridHeight;
	
	//SIZES
	private final int minSize = 200;
	private int infoPanelHeight = 32;
	private int buttonWidth = 80;
	private int buttonHeight = 20;
	private int buttonSpacingX = 20;
	private int buttonMarginTop = (infoPanelHeight-buttonHeight)/2;

	//RESIZING
	private int snapSize = 1;
	private int startingX;
	private int startingY;
	private int startingContainerWidth;
	private int startingContainerHeight;
	
	//GRID
	private GridPanel grid;
	private int bufferThin = 5;
	private int bufferThick = 12;
	
	//TODO : right and bottom edges of grids don't shows
	public StashGridOverlay(){
		super("Stash Overlay");
		this.setVisible(false);
		this.setLocation(windowPos);
		this.setMinimumSize(new Dimension(minSize, minSize));
//		this.setSnapSize(snapSize);
		gridWidth = containerSize.width-bufferThin-bufferThick;
		gridHeight = containerSize.height-bufferThin-bufferThick-infoPanelHeight;
		StashGridOverlay.setDefaultGridSize(new Dimension(gridWidth, gridHeight));
		container.setLayout(new BorderLayout());
		//TODO : Move clear background to BasicMenuWindow?
		container.setBackground(new Color(1.0f,1.0f,1.0f,0.25f));
		container.setBounds(0, 0, containerSize.width, containerSize.height);
		
		grid = new GridPanel(gridWidth, gridHeight);
		grid.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		grid.setLineColor(Color.GREEN);
		container.add(grid, BorderLayout.CENTER);
		
		BasicPanel topSpacer = new BasicPanel(gridWidth+bufferThick+bufferThin, bufferThin);
		container.add(topSpacer, BorderLayout.PAGE_START);
		
		BasicPanel leftSpacer = new BasicPanel(bufferThin, gridHeight);
		container.add(leftSpacer, BorderLayout.LINE_START);
		
		BasicPanel rightPullBar = new BasicPanel(bufferThick, gridHeight);
		rightPullBar.setBackground(Color.DARK_GRAY);
		Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		rightPullBar.setBorder(b);
		container.add(rightPullBar, BorderLayout.LINE_END);
		
		//BOTTOM
		BasicPanel bottomPullBar = new BasicPanel(gridWidth, bufferThick);
		bottomPullBar.setBackground(Color.DARK_GRAY);
		BasicPanel infoPanel = new BasicPanel(containerSize.width, infoPanelHeight);
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, buttonSpacingX, buttonMarginTop));
		JButton resetButton = new JButton("Reset");
		infoPanel.add(resetButton);
		resetButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		
		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		
		infoPanel.add(saveButton);
		
		BasicPanel bottomContainer = new BasicPanel(containerSize.width, bufferThick+infoPanelHeight);
		bottomPullBar.setBorder(b);
		
		bottomContainer.setLayout(new BorderLayout());
		bottomContainer.add(bottomPullBar, BorderLayout.PAGE_START);
		bottomContainer.add(infoPanel, BorderLayout.PAGE_END);
		container.add(bottomContainer, BorderLayout.PAGE_END);
		
		this.resizeStashWindow(minSize*2, minSize*2);
		
		//Width Adjust
		rightPullBar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				startingX = e.getXOnScreen();
		    	startingContainerWidth = getContainerWidth();
		    	startingContainerHeight = getContainerHeight();
		    }
		});
		
		rightPullBar.addMouseMotionListener(new java.awt.event.MouseAdapter() {
		    public void mouseDragged(java.awt.event.MouseEvent e) {
		    	int dis = startingX-e.getXOnScreen();
		    	resizeStashWindow(startingContainerWidth-dis, startingContainerHeight);
		    }
		});
		
		//Height Adjust
		bottomPullBar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
		    	startingY = e.getYOnScreen();
		    	startingContainerWidth = getContainerWidth();
		    	startingContainerHeight = getContainerHeight();
		    }
		});
		
		bottomPullBar.addMouseMotionListener(new java.awt.event.MouseAdapter() {
		    public void mouseDragged(java.awt.event.MouseEvent e) {
		    	int dis = startingY-e.getYOnScreen();
		    	resizeStashWindow(startingContainerWidth, startingContainerHeight-dis);
		    }
		});
		
		//Reset Button
		resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				reset();
			}
		});
		
		//Save Button
		saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					saveDataLocally();
					ObjectOutputStream stash = new ObjectOutputStream(new FileOutputStream("stash.pref"));
					stash.writeObject(StashGridOverlay.windowPos);
					stash.writeObject(StashGridOverlay.containerSize);
					stash.writeObject(StashGridOverlay.gridPos);
					stash.writeObject(StashGridOverlay.gridSize);
					stash.close();
					Overlay.stashHelperContainer.updateBounds();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		//TODO : Better way to implement extension of buttons from parent BasicMenuWindow?
		closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				reset();
				hideStashWindow();
		    }
		});
	}
	
	private int getContainerWidth(){
		return container.getWidth();
	}
	
	private int getContainerHeight(){
		return container.getHeight();
	}
	
	private void hideStashWindow(){
		this.setVisible(false);
	}
	
	//WINDOW GETTERS/SETTERS
	public static void setDefaultWinPos(Point pos){
		StashGridOverlay.windowPos = pos;
	}
	
	public static void setDefaultContainerSize(Dimension size){
		StashGridOverlay.containerSize = size;
	}
	
	public static Point getWinPos(){
		return StashGridOverlay.windowPos;
	}
	
	public static Dimension getContainerSize(){
		return StashGridOverlay.containerSize;
	}
	
	//GRID GETTERS/SETTERS
	public static void setDefaultGridPos(Point pos){
		StashGridOverlay.gridPos = pos;
	}
	
	public static void setDefaultGridSize(Dimension size){
		StashGridOverlay.gridSize = size;
	}
	
	public static Point getGridPos(){
		return StashGridOverlay.gridPos;
	}
	
	public static Dimension getGridSize(){
		return StashGridOverlay.gridSize;
	}
	
	private void saveDataLocally(){
		setDefaultWinPos(this.getLocationOnScreen());
		setDefaultContainerSize(this.getSize());
		setDefaultGridPos(grid.getLocationOnScreen());
		setDefaultGridSize(grid.getSize());
	}
	
	public void reset(){
		this.setLocation(windowPos);
		this.resizeStashWindow(containerSize.width, containerSize.height);
	}
	
//	private Point getWindowLocation(){
//		return this.getLocation();
//	}
	
//	public static void setwindowPosition(Point p){
//		StashGridOverlay.windowPos = p;
//	}
	
	public void refresh(){
		this.revalidate();
		this.repaint();
	}
	
	public void resizeStashWindow(int width, int height){
		int w = width<this.getMinimumSize().width ? this.getMinimumSize().width : width;
		int h = height<this.getMinimumSize().height ? this.getMinimumSize().height : height;
		int gridWidth = w-bufferThin-bufferThick;
		int gridHeight = h-bufferThin-bufferThick-infoPanelHeight;
		//HMMMMMMMMMMMM
//		this.setWidth(w);
//		this.setHeight(h);
		this.resizeWindow(w, h);
		this.revalidate();
		this.repaint();
		grid.resizeGrid(gridWidth, gridHeight);
	}
}