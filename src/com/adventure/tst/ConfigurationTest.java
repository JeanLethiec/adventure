package com.adventure.tst;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.adventure.configuration.ConfigurationException;
import com.adventure.configuration.ConfigurationParser;

import junit.framework.TestCase;

/**
 * 
 * @author JLC2
 *
 */
public class ConfigurationTest extends TestCase {
	
	private static Logger logger = Logger.getLogger(ConfigurationTest.class);
	
	private List<String> standardContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA");
	private List<String> twoAdventurersContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA", "A - Roger - 2 - 3 - N - AAGA");
	
	private List<List<String>> badContents = new LinkedList<List<String>>() {{
		// On Grid
		add(Arrays.asList(                              "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 0 - 0"    ,              "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("B - 3 - 4"    ,              "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3Y - 4"   ,              "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4X"   ,              "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3"        ,              "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4 - 2",              "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4"    , "C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		
		// On Mountains
		add(Arrays.asList("C - 3 - 4"    , "M - 1Y - 1"    , "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4"    , "M - 1 - 1Y"    , "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4"    , "M - 1"         , "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4"    , "M - 1 - 1 - 1" , "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4"    , "M - 50 - 1"    , "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4"    , "M - 1 - 50"    , "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));

		// On Treasures.
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "B - 0 - 3 - 2"    , "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0X - 3 - 2"   , "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3X - 2"   , "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2X"   , "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3"        , "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 40 - 3 - 2"   , "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 40 - 2"   , "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA"));
		
		// On Adventurers
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "B - Lara - 1 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1X - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1X - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - SD - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - PPMMOO"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 1 - S - AADADAGGA - X"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 40 - 1 - S - AADADAGGA"));
		add(Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 40 - S - AADADAGGA"));
	}};
	
	public void testConfigurationParsing() throws Exception {
		for (List<String> badContent: badContents) {
			try {
				File configFile = generateConfigurationFile(badContent);
				ConfigurationParser.parse(configFile);
				fail();	
			} catch (ConfigurationException e) {
				logger.debug("OK: " + e.getMessage());
				continue;
			}
		}
	}
	
	public void testGenerateConfigurationFile() throws Exception {		
		File configFile = generateConfigurationFile(standardContent);
		
		BufferedReader reader = Files.newBufferedReader(configFile.toPath(), Charset.forName("UTF-8"));
		
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			assertEquals(standardContent.get(count), line);
			count++;
		}
	}
	
	public File generateConfigurationFile(List<String> lines) throws ConfigurationException {
		File configFile = null;
		try {
			configFile = File.createTempFile("adventure", ".tmp");
		} catch (IOException e) {
			throw new ConfigurationException("Failed to create temporary configuration file.");
		}
		
		try {
			Files.write(configFile.toPath(), lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new ConfigurationException("Failed to write in temporary configuration file.");
		}
		
		return configFile;
	}

}
