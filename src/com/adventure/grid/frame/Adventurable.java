package com.adventure.grid.frame;

import com.adventure.adventurer.Adventurer;
import com.adventure.adventurer.ImpossibleMovementException;
import com.adventure.grid.GridException;

/**
 * Specifies a specific behavior: classes implementing this interface can carry adventurers.
 * @author Jean
 *
 */
public interface Adventurable {
	
	public void addAdventurer(Adventurer adventurer) throws GridException, ImpossibleMovementException;
	
	public boolean hasAdventurer();
	
	public void removeAdventurer() throws GridException;
	
	public Adventurer getAdventurer();
}
