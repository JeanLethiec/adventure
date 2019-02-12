package com.adventure.grid.frame;

import com.adventure.adventurer.Adventurer;
import com.adventure.adventurer.ImpossibleMovementException;
import com.adventure.grid.GridException;
import com.adventure.grid.coordinates.Coordinates;

/**
 * A standard frame in the Grid that Adventurers can cross freely.
 * @author Jean
 *
 */
public class StandardFrame extends Frame implements Adventurable {
	
	private Adventurer currentAdventurer = null;

	public StandardFrame(Coordinates xy) {
		super(xy);
	}

	@Override
	public void addAdventurer(Adventurer adventurer) throws GridException, ImpossibleMovementException {
		if (!hasAdventurer()) {
			currentAdventurer = adventurer;
			adventurer.setCoordinates(getCoordinates());
		} else {
			if (getAdventurer() == adventurer) {
				throw new ImpossibleMovementException("Adventurer " + adventurer.getName() + " stays on position at " + adventurer.getCoordinates());
			} else {
				throw new ImpossibleMovementException("Tried to add " + adventurer.getName() + " on a populated frame: " + getCoordinates() + " - " + getAdventurer().getName());
			}
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
