package com.adventure;

import java.io.File;
import java.util.List;

import com.adventure.adventurer.Adventurer;
import com.adventure.adventurer.ImpossibleMovementException;
import com.adventure.configuration.ConfigurationParser;
import com.adventure.grid.Grid;
import com.adventure.grid.GridException;
import com.adventure.configuration.ConfigurationException;

/**
 * 
 * @author JLC2
 *
 */
public class Adventure {
	
	public static void main(String[] args) throws ConfigurationException, ConfigurationException, GridException, ImpossibleMovementException {
		if (args.length != 1) {
			throw new ConfigurationException("This program expects a single argument: the configuration input file.");
		}
		
		String configFilePath = args[0];
		
		File configFile = new File(configFilePath);
		
		Grid grid = ConfigurationParser.parse(configFile);
		
		play(grid);
		
		System.out.println("End of the program.");
	}
	
	public static void play(Grid grid) throws GridException {
		List<Adventurer> adventurers = grid.getAdventurers();
		
		if (adventurers.isEmpty()) {
			return;
		}
		
		while(adventurers.stream().anyMatch(x -> x.isActive())) {
			for (Adventurer currentAdventurer: adventurers) {
				grid.activateAdventurer(currentAdventurer);
			}
		}
		
	}
	
	
}
