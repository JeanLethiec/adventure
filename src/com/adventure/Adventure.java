package com.adventure;

import java.io.File;
import java.util.List;

import com.adventure.adventurer.Adventurer;
import com.adventure.adventurer.ImpossibleMovementException;
import com.adventure.configuration.ConfigurationParser;
import com.adventure.grid.Grid;
import com.adventure.grid.GridException;
import com.adventure.output.OutputException;
import com.adventure.output.OutputWriter;
import com.adventure.configuration.ConfigurationException;

/**
 * The entry point of the program, starting an adventure where adventurers travel through a grid and gather treasures.
 * @author JLC2
 *
 */
public class Adventure {
	
	public static void main(String[] args) throws ConfigurationException, ConfigurationException, GridException, ImpossibleMovementException, OutputException {
		try {
			if (args.length != 1) {
				throw new ConfigurationException("This program expects a single argument: the configuration input file.");
			}
			
			String configFilePath = args[0];
			
			File configFile = new File(configFilePath);
			
			Grid grid = ConfigurationParser.parse(configFile);
			
			play(grid);
			
			OutputWriter.write(grid);
		} catch (Exception e) {
			System.out.println("An error occured: " + e.getMessage());
		}
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
