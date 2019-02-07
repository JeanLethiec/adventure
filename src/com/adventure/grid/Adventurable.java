package com.adventure.grid;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface Adventurable {
	public default void addAdventurer() {
		throw new NotImplementedException();
	}
	
	public default void hasAdventurer() {
		throw new NotImplementedException();
	}
	
	public default void removeAdventurer() {
		throw new NotImplementedException();
	}
}
