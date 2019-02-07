package com.adventure.grid;

/**
 * 
 * @author JLC2
 *
 */
public class Coordinates {
	int x;
	int y;
	
	public Coordinates(int iX, int iY) {
		x = iX;
		y = iY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
