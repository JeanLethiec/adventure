package com.adventure.grid;

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
