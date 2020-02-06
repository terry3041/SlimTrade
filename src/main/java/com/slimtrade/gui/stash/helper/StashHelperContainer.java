package com.slimtrade.gui.stash.helper;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.slimtrade.App;
import com.slimtrade.core.managers.ColorManager;
import com.slimtrade.gui.basic.BasicDialog;

public class StashHelperContainer extends BasicDialog{
	//TODO : Should probably get spacing X directly from stash overlay left buffer
	private static final long serialVersionUID = 1L;
	public static int height = StashHelper.height;
	private int offsetY = 10;
	private int spacingX = 5;
	private int posX = 0;
	private int posY = 0;
	
	//TODO : Recheck all resizing
	public StashHelperContainer(){
		this.setBackground(ColorManager.CLEAR);
//		this.setFocusableWindowState(true);
//		this.setFocusable(true);
//		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.RED));
		this.setBounds(0, 0, height, height);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, spacingX, 0));
		this.pack();
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				repaint();
			}
		});
	}
	
	public void updateBounds(){
		posX = App.saveManager.stashSaveFile.windowX;
		posY = App.saveManager.stashSaveFile.windowY;
		int width = App.saveManager.stashSaveFile.width;
		this.setBounds(posX+10, posY-15, width, height);
	}

	public void updateBounds(int posX, int posY, int width){
		int x = posX-spacingX;
		int y = posY-height-offsetY;
		int w = width+spacingX;
		this.setBounds(x, y, w, height);
	}
	
	public void updateCellSize(int cellWidth, int cellHeight){
		
	}
	
//	public void refresh(){
//		this.revalidate();
//		this.repaint();
//	}
	
}
