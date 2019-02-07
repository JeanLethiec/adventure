package com.adventure.configuration;

import org.apache.log4j.Logger;

import com.adventure.grid.Grid;
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
						
						String x = arguments[1].trim();
						logger.trace("X: " + x);
						String y = arguments[2].trim();
						logger.trace("Y: " + y);
						
						grid = new Grid(x, y);
						
						break;
					case("M"):
						if (grid == null) {
							throw new ConfigurationException("Grid must be defined before any Mountains.");
						}
					case("T"):
						if (grid == null) {
							throw new ConfigurationException("Grid must be defined before any Treasures.");
						}
					case("A"):
						if (grid == null) {
							throw new ConfigurationException("Grid must be defined before any Adventurers.");
						}
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
