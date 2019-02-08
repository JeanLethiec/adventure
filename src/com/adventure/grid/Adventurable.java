package com.adventure.grid;

import com.adventure.adventurer.Adventurer;
import com.adventure.adventurer.ImpossibleMovementException;

public interface Adventurable {
	
	public void addAdventurer(Adventurer adventurer) throws GridException, ImpossibleMovementException;
	
	public boolean hasAdventurer();
	
	public void removeAdventurer() throws GridException;
	
	public Adventurer getAdventurer();
}
