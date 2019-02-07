package com.adventure.grid;

import com.adventure.configuration.ConfigurationException;

/**
 * 
 * @author JLC2
 *
 */
public class Coordinates implements Comparable<Coordinates> {
	int x;
	int y;
	
	public Coordinates(String iX, String iY) throws ConfigurationException {
		try {
			x = Integer.valueOf(iX);
		} catch (NumberFormatException e) {
			throw new ConfigurationException("Bad value in coordinates for X: " + iX);
		}
		
		try {
			y = Integer.valueOf(iY);
		} catch (NumberFormatException e) {
			throw new ConfigurationException("Bad value in coordinates for Y: " + iY);
		}
	}
	
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

	@Override
	public int compareTo(Coordinates o) {
		if (x == o.getX() && y == o.getY()) {
			return 0;
		} else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return getX() + "/" + getY();
 	}
}
