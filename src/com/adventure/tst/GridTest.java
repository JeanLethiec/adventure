package com.adventure.tst;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.adventure.Adventure;
import com.adventure.adventurer.Adventurer;
import com.adventure.adventurer.Adventurer.ActionTypes;
import com.adventure.adventurer.Orientations;
import com.adventure.configuration.ConfigurationParser;
import com.adventure.grid.Coordinates;
import com.adventure.grid.Grid;
import com.adventure.grid.GridException;
import com.adventure.grid.TreasureFrame;

import junit.framework.TestCase;

/**
 * 
 * @author JLC2
 *
 */
public class GridTest extends TestCase {
	private static Logger logger = Logger.getLogger(GridTest.class);

	private List<String> standardContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "M - 2 - 3", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 0 - 1 - S - AADADADAGDAGGA");	
	private List<String> twoAdventurersContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "M - 2 - 3", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 0 - 1 - S - AAA", "A - Roger - 0 - 2 - S - AAA");	
	private List<String> mountainContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "M - 2 - 3", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 - 2 - E - AADA");	
	private List<String> oneActionAdventurerContent = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "M - 2 - 3", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 0 - 1 - S - A");
	private List<String> edgeOfMapContent = Arrays.asList("C - 2 - 2", "A - South - 0 - 1 - S - A", "A - West - 0 - 0 - O - A", "A - East - 1 - 1 - E - A", "A - North - 1 - 0 - N - A");	

	public void testInitializeGrid() throws Exception {
		Grid grid = new Grid(3, 4);
		
		assertEquals(grid.getWidth(), 3);

		assertEquals(grid.getHeight(), 4);
		
		assertEquals(grid.getFrames().size(), 12);
	}
	
	public void testAdventurer() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(standardContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventurer adventurer = grid.getAdventurer("Lara");
		assertEquals(Adventurer.ActionTypes.Advance, adventurer.getCurrentAction());
		assertEquals("0/1", adventurer.getCoordinates().toString());
		assertEquals(Orientations.S, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.Advance, adventurer.getCurrentAction());
		assertEquals("0/2", adventurer.getCoordinates().toString());
		assertEquals(Orientations.S, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.TurnRight, adventurer.getCurrentAction());
		assertEquals("0/3", adventurer.getCoordinates().toString());
		assertEquals(Orientations.S, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.Advance, adventurer.getCurrentAction());
		assertEquals("0/3", adventurer.getCoordinates().toString());
		assertEquals(Orientations.O, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.TurnRight, adventurer.getCurrentAction());
		assertEquals("0/3", adventurer.getCoordinates().toString());
		assertEquals(Orientations.O, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.Advance, adventurer.getCurrentAction());
		assertEquals("0/3", adventurer.getCoordinates().toString());
		assertEquals(Orientations.N, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.TurnRight, adventurer.getCurrentAction());
		assertEquals("0/2", adventurer.getCoordinates().toString());
		assertEquals(Orientations.N, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.Advance, adventurer.getCurrentAction());
		assertEquals("0/2", adventurer.getCoordinates().toString());
		assertEquals(Orientations.E, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.TurnLeft, adventurer.getCurrentAction());
		assertEquals("1/2", adventurer.getCoordinates().toString());
		assertEquals(Orientations.E, adventurer.getOrientation());
		
		grid.activateAdventurer(adventurer);
		assertEquals(Adventurer.ActionTypes.TurnRight, adventurer.getCurrentAction());
		assertEquals("1/2", adventurer.getCoordinates().toString());
		assertEquals(Orientations.N, adventurer.getOrientation());
	}
	
	public void testTwoAdventurers() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(twoAdventurersContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventurer lara = grid.getAdventurer("Lara");
		Adventurer roger = grid.getAdventurer("Roger");

		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/1", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		
		assertEquals(Adventurer.ActionTypes.Advance, roger.getCurrentAction());
		assertEquals("0/2", roger.getCoordinates().toString());
		assertEquals(Orientations.S, roger.getOrientation());
		
		grid.activateAdventurer(lara);
		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/1", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		
		assertEquals(Adventurer.ActionTypes.Advance, roger.getCurrentAction());
		assertEquals("0/2", roger.getCoordinates().toString());
		assertEquals(Orientations.S, roger.getOrientation());
		
		grid.activateAdventurer(roger);
		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/1", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		
		assertEquals(Adventurer.ActionTypes.Advance, roger.getCurrentAction());
		assertEquals("0/3", roger.getCoordinates().toString());
		assertEquals(Orientations.S, roger.getOrientation());
		
		grid.activateAdventurer(lara);
		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/2", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		
		assertEquals(Adventurer.ActionTypes.Advance, roger.getCurrentAction());
		assertEquals("0/3", roger.getCoordinates().toString());
		assertEquals(Orientations.S, roger.getOrientation());
	}
	
	public void testAdventurerMountain() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(mountainContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventurer lara = grid.getAdventurer("Lara");

		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("1/2", lara.getCoordinates().toString());
		assertEquals(Orientations.E, lara.getOrientation());
		
		grid.activateAdventurer(lara);
		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("1/2", lara.getCoordinates().toString());
		assertEquals(Orientations.E, lara.getOrientation());
		
		grid.activateAdventurer(lara);
		assertEquals(Adventurer.ActionTypes.TurnRight, lara.getCurrentAction());
		assertEquals("1/2", lara.getCoordinates().toString());
		assertEquals(Orientations.E, lara.getOrientation());
		
		grid.activateAdventurer(lara);
		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("1/2", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.Nothing, lara.getCurrentAction());
		assertEquals("1/3", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
	}
	
	public void testNoActionAdventurer() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(oneActionAdventurerContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventurer lara = grid.getAdventurer("Lara");

		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/1", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.Nothing, lara.getCurrentAction());
		assertEquals("0/2", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
	}
	
	public void testAdventurerEdgeOfMap() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(edgeOfMapContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventurer south = grid.getAdventurer("South");
		Adventurer west = grid.getAdventurer("West");
		Adventurer east = grid.getAdventurer("East");
		Adventurer north = grid.getAdventurer("North");

		grid.activateAdventurer(south);
		grid.activateAdventurer(west);
		grid.activateAdventurer(east);
		grid.activateAdventurer(north);
		
		assertEquals("0/1", south.getCoordinates().toString());
		assertEquals("0/0", west.getCoordinates().toString());
		assertEquals("1/1", east.getCoordinates().toString());
		assertEquals("1/0", north.getCoordinates().toString());
	}
	
	private List<String> treasureContent = Arrays.asList(
			"C - 3 - 4", 
			"T - 0 - 3 - 2", 
			"T - 1 - 3 - 1", 
			"A - Lara - 0 - 2 - S - ADDADDA", 
			"A - Roger - 1 - 2 - S - A"
	);	
	
	public void testAdventurerTreasure() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(treasureContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventurer lara = grid.getAdventurer("Lara");
		Adventurer roger = grid.getAdventurer("Roger");

		Adventure.play(grid);
		
		assertEquals(2, lara.getTreasures());
		assertEquals(1, roger.getTreasures());

		List<TreasureFrame> frames = grid.getTreasures();
		for (TreasureFrame frame: frames) {
			assertEquals(0, frame.getNbTreasures());
		}
		
		assertEquals(Adventurer.ActionTypes.Nothing, roger.getCurrentAction());
		assertEquals(Adventurer.ActionTypes.Nothing, lara.getCurrentAction());

	}
	
	public void testAdventurerTreasure2() throws Exception {
		Grid grid = null;
		try {
			File configFile = ConfigurationTest.generateConfigurationFile(treasureContent);
			grid = ConfigurationParser.parse(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("KO: " + e.getMessage());
			fail();
		}
		
		Adventurer lara = grid.getAdventurer("Lara");
		Adventurer roger = grid.getAdventurer("Roger");

		assertEquals(Adventurer.ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/2", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		
		assertEquals(Adventurer.ActionTypes.Advance, roger.getCurrentAction());
		assertEquals("1/2", roger.getCoordinates().toString());
		assertEquals(Orientations.S, roger.getOrientation());
		
		assertEquals(2, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		assertEquals(1, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		
		grid.activateAdventurer(roger);
		assertEquals(Adventurer.ActionTypes.Nothing, roger.getCurrentAction());
		assertEquals("1/3", roger.getCoordinates().toString());
		assertEquals(Orientations.S, roger.getOrientation());
		assertEquals(1, roger.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(2, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());

		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.TurnRight, lara.getCurrentAction());
		assertEquals("0/3", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		assertEquals(1, lara.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(1, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.TurnRight, lara.getCurrentAction());
		assertEquals("0/3", lara.getCoordinates().toString());
		assertEquals(Orientations.O, lara.getOrientation());
		assertEquals(1, lara.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(1, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/3", lara.getCoordinates().toString());
		assertEquals(Orientations.N, lara.getOrientation());
		assertEquals(1, lara.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(1, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.TurnRight, lara.getCurrentAction());
		assertEquals("0/2", lara.getCoordinates().toString());
		assertEquals(Orientations.N, lara.getOrientation());
		assertEquals(1, lara.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(1, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.TurnRight, lara.getCurrentAction());
		assertEquals("0/2", lara.getCoordinates().toString());
		assertEquals(Orientations.E, lara.getOrientation());
		assertEquals(1, lara.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(1, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.Advance, lara.getCurrentAction());
		assertEquals("0/2", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		assertEquals(1, lara.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(1, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		
		grid.activateAdventurer(lara);
		assertEquals(ActionTypes.Nothing, lara.getCurrentAction());
		assertEquals("0/3", lara.getCoordinates().toString());
		assertEquals(Orientations.S, lara.getOrientation());
		assertEquals(2, lara.getTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(1, 3))).getNbTreasures());
		assertEquals(0, ((TreasureFrame) grid.getFrame(new Coordinates(0, 3))).getNbTreasures());
		
		assertEquals(1, roger.getTreasures());
	}
}
