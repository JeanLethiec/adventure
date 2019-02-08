package com.adventure.adventurer;

public class ImpossibleMovementException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ImpossibleMovementException(String string) {
		super(string);
	}
}
