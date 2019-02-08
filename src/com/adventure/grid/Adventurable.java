package com.adventure.grid;

import com.adventure.adventurer.Adventurer;

public interface Adventurable {
	
	public void addAdventurer(Adventurer adventurer) throws GridException;
	
	public boolean hasAdventurer();
	
	public void removeAdventurer() throws GridException;
	
	public Adventurer getAdventurer();
}
