package com.adventure.grid.frame;

import com.adventure.grid.coordinates.Coordinates;

/**
 * A mountain frame in the Grid that Adventurers cannot cross.
 * @author Jean
 *
 */
public class MountainFrame extends Frame {

	public MountainFrame(Coordinates xy) {
		super(xy);
	}
	
	public String getRepresentation() {
		return "M - " + getCoordinates().getX() + " - " + getCoordinates().getY();
	}

}
