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
	
	public Coordinates(int iX, int iY) throws ImpossibleCoordinatesException {
		setX(iX);
		setY(iY);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) throws ImpossibleCoordinatesException {
		if (x < 0) {
			throw new ImpossibleCoordinatesException("X cannot be negative.");
		}
		this.x = x;
	}
	
	public void setY(int y) throws ImpossibleCoordinatesException {
		if (y < 0) {
			throw new ImpossibleCoordinatesException("Y cannot be negative.");
		}
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
