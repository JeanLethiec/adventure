package com.adventure.tst;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.adventure.adventurer.Adventurer;
import com.adventure.configuration.ConfigurationParser;
import com.adventure.grid.Grid;
import junit.framework.TestCase;

/**
 * 
 * @author JLC2
 *
 */
public class GridTest extends TestCase {
	private static Logger logger = Logger.getLogger(GridTest.class);

	private List<String> standardContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "M - 2 - 3", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 2 - 1 - S - AADADAGGA");	
	
	public void testInitializeMap() throws Exception {
		Grid grid = new Grid(3, 4);
		
		assertEquals(grid.getWidth(), 3);

		assertEquals(grid.getHeight(), 4);
		
		assertEquals(grid.getFrames().size(), 12);
	}
	
	public void testUseGrid() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(standardContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		List<Adventurer> adventurers = grid.getAdventurers();
		Adventurer adventurer = adventurers.get(0);
		assertEquals("A", adventurer.getCurrentAction());
		adventurer.execute();
		assertEquals("A", adventurer.getCurrentAction());
		adventurer.execute();
		assertEquals("D", adventurer.getCurrentAction());
	}
}
