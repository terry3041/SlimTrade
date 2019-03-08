package main.java.com.slimtrade.enums;

import main.java.com.slimtrade.gui.menubar.MenubarButton;
import main.java.com.slimtrade.gui.menubar.MenubarDialog;

public enum MenubarButtonLocation {
	
	SW("Bottom Left", 0, MenubarDialog.HEIGHT-MenubarButton.HEIGHT),
	SE("Bottom Right", MenubarButton.WIDTH-MenubarButton.HEIGHT, MenubarDialog.HEIGHT-MenubarButton.HEIGHT),
	NW("Top Left", 0, 0),
	NE("Top Right", MenubarButton.WIDTH-MenubarButton.HEIGHT, 0),
	;
	
	String name;
	int modX;
	int modY;
	
	MenubarButtonLocation(String name, int modX, int modY){
		this.name = name;
		this.modX = modX;
		this.modY = modY;
	}
	
	public String toString(){
		return this.name;
	}
	
	public int getModX(){
		return modX;
	}
	
	public int getModY(){
		return modY;
	}
	
}
