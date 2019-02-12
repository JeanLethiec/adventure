package com.adventure.tst;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.adventure.Adventure;
import com.adventure.configuration.ConfigurationParser;
import com.adventure.grid.Grid;
import com.adventure.output.OutputWriter;

import junit.framework.TestCase;

/**
 * Tests relative to output file writing: existence, format and consistency.
 * @author Jean
 *
 */
public class OutputTest extends TestCase {
	private static Logger logger = Logger.getLogger(OutputTest.class);

	private List<String> input = Arrays.asList("C - 3 - 4", "M - 1 - 0", "M - 2 - 1", "T - 0 - 3 - 2", "T - 1 - 3 - 3", "A - Lara - 1 - 1 - S - AADADAGGA");
	private List<String> expectedOutput = Arrays.asList("C - 3 - 4", "M - 1 - 0", "M - 2 - 1", "T - 1 - 3 - 2", "A - Lara - 0 - 3 - S - 3");

	private List<String> input2 = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 0 - 1 - S - AAA", "A - Roger - 0 - 2 - S - AAA");	
	private List<String> expectedOutput2 = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 1", "T - 1 - 3 - 1", "A - Lara - 0 - 2 - S - 0", "A - Roger - 0 - 3 - S - 1");

	
	public void testAdventureOutputWriting() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(input);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventure.play(grid);
		
		String outputPath = File.createTempFile("outputAdventure", ".tmp").getPath();
		
		OutputWriter.write(grid, outputPath);
		
		assertTrue(Files.exists(Paths.get(outputPath)));
		
		BufferedReader reader = Files.newBufferedReader(Paths.get(outputPath), Charset.forName("UTF-8"));
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			assertEquals(expectedOutput.get(count), line);
			count++;
		}
	}
	
	public void testTwoAdventurersOutputWriting() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(input2);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventure.play(grid);
		
		String outputPath = File.createTempFile("outputAdventure", ".tmp").getPath();
		
		OutputWriter.write(grid, outputPath);
		
		assertTrue(Files.exists(Paths.get(outputPath)));
		
		BufferedReader reader = Files.newBufferedReader(Paths.get(outputPath), Charset.forName("UTF-8"));
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			assertEquals(expectedOutput2.get(count), line);
			count++;
		}
	}
}
