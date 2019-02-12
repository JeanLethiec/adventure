package com.adventure.adventurer;

/**
 * An exception occurring when an adventurer tries to make an impossible movement in the grid.
 * @author Jean
 *
 */
public class ImpossibleMovementException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ImpossibleMovementException(String string) {
		super(string);
	}
}
