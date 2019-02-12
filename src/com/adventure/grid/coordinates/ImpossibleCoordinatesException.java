package com.adventure.grid.coordinates;

/**
 * An exception characterizing a bad input when initializing coordinates.
 * @author Jean
 *
 */
public class ImpossibleCoordinatesException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ImpossibleCoordinatesException(String string) {
		super(string);
	}
}
