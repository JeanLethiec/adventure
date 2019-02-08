package com.adventure.grid;

import com.adventure.adventurer.Adventurer;

public class StandardFrame extends Frame implements Adventurable {
	
	private Adventurer currentAdventurer = null;

	public StandardFrame(Coordinates xy) {
		super(xy);
	}

	@Override
	public void addAdventurer(Adventurer adventurer) throws GridException {
		if (!hasAdventurer()) {
			currentAdventurer = adventurer;
		} else {
			throw new GridException("Tried to add an adventurer on a populated frame.");
		}
	}

	@Override
	public boolean hasAdventurer() {
		return currentAdventurer != null;
	}

	@Override
	public void removeAdventurer() throws GridException {
		if (hasAdventurer()) {
			currentAdventurer = null;
		} else {
			throw new GridException("Tried to remove a adventurer from a non-populated frame.");
		}
	}

	@Override
	public Adventurer getAdventurer() {
		return currentAdventurer;
	}
	
}
