package com.adventure.tst;

import com.adventure.grid.Grid;

import junit.framework.TestCase;

/**
 * 
 * @author JLC2
 *
 */
public class GridTest extends TestCase {
		
	public void testInitializeMap() throws Exception {
		Grid grid = new Grid(3, 4);
		
		assertEquals(grid.getWidth(), 3);

		assertEquals(grid.getHeight(), 4);
		
		assertEquals(grid.getFrames().size(), 12);
	}
}
