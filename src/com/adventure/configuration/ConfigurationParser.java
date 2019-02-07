package com.adventure.configuration;

import org.apache.log4j.Logger;

import com.adventure.grid.Coordinates;
import com.adventure.grid.Grid;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

public class ConfigurationParser {
	
	private static Logger logger = Logger.getLogger(ConfigurationParser.class);

	public static Grid parse(File configFile) throws ConfigurationException {
		
		Grid grid = null;
				
		BufferedReader reader = null;
		try {
			reader = Files.newBufferedReader(configFile.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new ConfigurationException("Failed to open input configuration file: " + configFile.getPath());
		}
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arguments = line.split("-");
				
				String type = arguments[0].trim();
				
				if (!Arrays.stream(Types.values()).anyMatch((t) -> t.name().equals(type))) {
					throw new ConfigurationException("Type " + type + " is forbidden.");
				}
				
				logger.trace("Type: " + type);
				switch(type) {
					case("C"):
						if (grid != null) {
							throw new ConfigurationException("Duplicate declaration of the grid.");
						}
						
						if (arguments.length != 3) {
							throw new ConfigurationException("Wrong number of arguments for Grid (C) settings (" + arguments.length + " instead of 3)");
						}
						
						String width = arguments[1].trim();
						String height = arguments[2].trim();
						
						grid = new Grid(width, height);
						
						break;
						
					case("M"):
						if (grid == null) {
							throw new ConfigurationException("Grid must be defined before any Mountains.");
						}
					
						if (arguments.length != 3) {
							throw new ConfigurationException("Wrong number of arguments for Mountain (M) settings (" + arguments.length + " instead of 3)");
						}
						
						String xm = arguments[1].trim();
						String ym = arguments[2].trim();
					
						grid.addMountain(new Coordinates(xm, ym));
						break;
						
					case("T"):
						if (grid == null) {
							throw new ConfigurationException("Grid must be defined before any Treasures.");
						}
					
						if (arguments.length != 4) {
							throw new ConfigurationException("Wrong number of arguments for Treasure (T) settings (" + arguments.length + " instead of 4)");
						}
						
						String xt = arguments[1].trim();
						String yt = arguments[2].trim();
					
						grid.addTreasure(new Coordinates(xt, yt));
						break;
						
					case("A"):
						if (grid == null) {
							throw new ConfigurationException("Grid must be defined before any Adventurers.");
						}
					
						if (arguments.length != 6) {
							throw new ConfigurationException("Wrong number of arguments for Adventurer (A) settings (" + arguments.length + " instead of 6)");
						}
						
						throw new NotImplementedException();
				}
			}
		} catch (IOException e) {
			throw new ConfigurationException("Failed to read lines of configuration file: " + configFile.getPath());
		}
		
		if (grid == null) {
			throw new ConfigurationException("Grid has not been initialized.");
		}
		
		return grid;
	}

}
