package com.adventure.grid.frame;

import com.adventure.grid.coordinates.Coordinates;

/**
 * A frame in a Grid, characterized by its coordinates.
 * @author Jean
 *
 */
public abstract class Frame {
	private Coordinates coordinates;
	
	public Frame(Coordinates iXY) {
		coordinates = iXY;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Coordinates xy) {
		this.coordinates = xy;
	}
}
