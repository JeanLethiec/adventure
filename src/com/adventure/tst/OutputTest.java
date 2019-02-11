package com.adventure.tst;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.adventure.Adventure;
import com.adventure.configuration.ConfigurationParser;
import com.adventure.grid.Grid;
import com.adventure.output.OutputWriter;

import junit.framework.TestCase;

public class OutputTest extends TestCase {
	private static Logger logger = Logger.getLogger(OutputTest.class);

	private List<String> standardContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "M - 2 - 3", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 2 - 1 - S - AADADAGGA");

	public void testAdventureOutputWriting() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(standardContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventure.play(grid);
		
		String outputPath = File.createTempFile("outputAdventure", ".tmp").getPath();
		
		OutputWriter.write(grid, outputPath);
		
		// TODO: Validate output existence and formatting.
		//assertTrue(Files.);
	}
}
