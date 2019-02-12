package com.adventure.output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.adventure.adventurer.Adventurer;
import com.adventure.grid.Grid;
import com.adventure.grid.frame.MountainFrame;
import com.adventure.grid.frame.TreasureFrame;

/**
 * Functions allowing output file writing, presenting the final state of the grid.
 * @author Jean
 *
 */
public final class OutputWriter {
	private static Logger logger = Logger.getLogger(OutputWriter.class);

	public static void write(Grid grid, String path) throws OutputException {
		try {
			logger.info("Writing output to: " + path);
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			
			writer.println("C - " + grid.getWidth() + " - " + grid.getHeight());
			
			for (MountainFrame mountain: grid.getMountains()) {
				writer.println(mountain.getRepresentation());
			}
			
			for (TreasureFrame treasure: grid.getTreasures()) {
				if (treasure.hasTreasure()) {
					writer.println(treasure.getRepresentation());
				}
			}
			
			for (Adventurer adventurer: grid.getAdventurers()) {
				writer.println(adventurer.getRepresentation());
			}
				
			writer.close();
		} catch (FileNotFoundException e) {
			throw new OutputException("Failed to create output file.");
		} catch (UnsupportedEncodingException e) {
			throw new OutputException("Failed to create output file: encoding not supported.");
		}
	}
	
	public static void write(Grid grid) throws OutputException {
		write(grid, "output.txt");
	}
}
