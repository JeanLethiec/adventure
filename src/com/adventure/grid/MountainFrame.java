package com.adventure.grid;

public class MountainFrame extends Frame {

	public MountainFrame(Coordinates xy) {
		super(xy);
	}
	
	public String getRepresentation() {
		return "M - " + getCoordinates().getX() + " - " + getCoordinates().getY();
	}

}
