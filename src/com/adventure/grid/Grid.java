package com.adventure.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.adventure.adventurer.Adventurer;
import com.adventure.configuration.ConfigurationException;
import com.adventure.configuration.ConfigurationParser;

/**
 * 
 * @author JLC2
 *
 */
public class Grid {
	private static Logger logger = Logger.getLogger(ConfigurationParser.class);
	
	private int width;
	private int height;
	
	// Used ArrayList for performance reasons: better access to mid-list elements
	private List<Frame> frames = new ArrayList<Frame>();
	
	public Grid(int i, int j) throws ConfigurationException {
		this(String.valueOf(i), String.valueOf(j));
	}
	
	public Grid(String iWidth, String iHeight) throws ConfigurationException {
		try {
			setWidth(Integer.valueOf(iWidth));
		} catch (NumberFormatException e) {
			throw new ConfigurationException("Bad value in coordinates for Width: " + iWidth);
		}
		
		try {
			setHeight(Integer.valueOf(iHeight));
		} catch (NumberFormatException e) {
			throw new ConfigurationException("Bad value in coordinates for Height: " + iHeight);
		}
		
		initializeFrames();
	}

	private void initializeFrames() {
		//logger.debug("Initializing frames creation in grid...");
		int x = 0;
		while (x < width) {
			int y = 0;
			while (y < height) {
				//logger.trace("Working on X" + x + " ; Y" + y);
				frames.add(new StandardFrame(new Coordinates(x, y)));
				y++;
			}
			x++;
		}
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	private void setHeight(int height) throws ConfigurationException {
		if (height > 0) {
			this.height = height;
		} else {
			throw new ConfigurationException("Grid height must be positive.");
		}
	}
	
	private void setWidth(int width) throws ConfigurationException {
		if (width > 0) {
			this.width = width;
		} else {
			throw new ConfigurationException("Grid height must be positive.");
		}
	}
	
	public List<Frame> getFrames() {
		return frames;
	}
	
	private void setFrames(List<Frame> frames) {
		this.frames = frames;
	}
	
	// TODO: Optimize the retrieval of the frame
	private Frame getFrame(Coordinates xy) throws GridException {
		List<Frame> correspondingFrames = getFrames().stream().filter(x -> x.getXy().compareTo(xy) == 0).collect(Collectors.toList());
		
		if (correspondingFrames.isEmpty()) {
			throw new GridException("Trying to access a non-existent frame: " + xy);
		} else if (correspondingFrames.size() > 1) {
			throw new GridException("The grid has two frames with the same coordinates: " + xy);
		}
		
		return correspondingFrames.get(0);
	}
	
	private void replaceFrame(Coordinates xy, Frame newFrame) throws ConfigurationException, GridException {
		frames.set(frames.indexOf(getFrame(xy)), newFrame);
	}
	
	public void addMountain(Coordinates xy) throws ConfigurationException, GridException {
		MountainFrame mountain = new MountainFrame(xy);
		replaceFrame(xy, mountain);
	}
	
	public List<MountainFrame> getMountains() {
		return getFrames().stream().filter(x -> x instanceof MountainFrame).collect(Collectors.toList()).stream().map(x -> (MountainFrame) x).collect(Collectors.toList());
	}
	
	public void addTreasure(Coordinates xy, String nb) throws ConfigurationException, GridException {
		TreasureFrame treasure = new TreasureFrame(xy, nb);
		replaceFrame(xy, treasure);
	}
	
	public List<TreasureFrame> getTreasures() {
		return getFrames().stream().filter(x -> x instanceof TreasureFrame).collect(Collectors.toList()).stream().map(x -> (TreasureFrame) x).collect(Collectors.toList());
	}
	
	public void addAdventurer(Adventurer adventurer, Coordinates xy) throws GridException {	
		Frame frame = getFrame(xy);
		if (frame instanceof Adventurable) {
			((Adventurable) frame).addAdventurer(adventurer);
		} else {
			throw new GridException("Cannot add an adventurer on a Mountain frame.");
		}
	}
	
	public List<Adventurable> getAdventurableFrames() {
		return getFrames().stream().filter(x -> x instanceof Adventurable).collect(Collectors.toList()).stream().map(x -> (Adventurable) x).collect(Collectors.toList());
	}
	
	public List<Adventurable> getPopulatedFrames() {
		return getAdventurableFrames().stream().filter(x -> x.hasAdventurer()).collect(Collectors.toList());
	}
	
	public List<Adventurer> getAdventurers() {
		return getPopulatedFrames().stream().map(x -> x.getAdventurer()).collect(Collectors.toList());
	}
}
