package com.adventure.grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.adventure.adventurer.Adventurer;
import com.adventure.adventurer.ImpossibleMovementException;
import com.adventure.adventurer.Adventurer.ActionTypes;
import com.adventure.configuration.ConfigurationException;

/**
 * 
 * @author JLC2
 *
 */
public class Grid {
	private static Logger logger = Logger.getLogger(Grid.class);
	
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
		
		try {
			initializeFrames();
		} catch (ImpossibleCoordinatesException e) {
			throw new ConfigurationException("Programmation exception: Coordinates should not be negative at this point.");
		}
	}

	private void initializeFrames() throws ImpossibleCoordinatesException {
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
	
	public Frame getFrame(Coordinates xy) throws GridException {
		List<Frame> correspondingFrames = getFrames().stream().filter(x -> x.getCoordinates().compareTo(xy) == 0).collect(Collectors.toList());
		
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
	
	public void addAdventurer(Adventurer adventurer, Coordinates xy) throws GridException, ImpossibleMovementException {	
		Frame frame = getFrame(xy);
		if (frame instanceof Adventurable) {
			((Adventurable) frame).addAdventurer(adventurer);
		} else {
			throw new GridException("Cannot add adventurer on a non-adventurable frame.");
		}
	}
	
	public void moveAdventurer(Adventurer adventurer, Coordinates nextCoordinates) throws ImpossibleMovementException, GridException {	
		Frame frame = null;
		try {
			frame = getFrame(nextCoordinates);
		} catch (GridException e) {
			throw new ImpossibleMovementException("Cannot move adventurer " + adventurer.getName() + " outside of the map.");
		}
		if (frame instanceof Adventurable) {
			Coordinates currentCoordinates = adventurer.getCoordinates(); 
			((Adventurable) frame).addAdventurer(adventurer);
			((Adventurable) getFrame(currentCoordinates)).removeAdventurer();
			if (frame instanceof TreasureFrame) {
				adventurer.grabTreasure((TreasureFrame) frame); 
			}
		} else {
			throw new ImpossibleMovementException("Cannot move adventurer " + adventurer.getName() + " on non-adventurable frame.");
		}
	}
	
	public List<Frame> getAdventurableFrames() {
		return getFrames().stream().filter(x -> x instanceof Adventurable).collect(Collectors.toList());
	}
	
	public List<Frame> getPopulatedFrames() {
		return getAdventurableFrames().stream().filter(x -> ((Adventurable) x).hasAdventurer()).collect(Collectors.toList());
	}
	
	public List<Adventurer> getAdventurers() {
		return getPopulatedFrames().stream().map(x -> ((Adventurable) x).getAdventurer()).sorted(Comparator.comparing(Adventurer::getOrder)).collect(Collectors.toList());
	}
	
	public Adventurer getAdventurer(String name) throws GridException {
		List<Adventurer> matched = getAdventurers().stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
		if (matched.isEmpty()) {
			throw new GridException("No adventurer found for name: " + name);
		}
		return matched.get(0);
	}
	
	public void	activateAdventurer(Adventurer adventurer) throws GridException {
		ActionTypes action = adventurer.getCurrentAction();
		
		logger.info(adventurer.getName() + " executing action: " + action);
		switch(action) {
			case Advance:				
				Coordinates nextCoordinates;
				try {
					nextCoordinates = adventurer.getPotentialNextCoordinates();
					moveAdventurer(adventurer, nextCoordinates);
				} catch (ImpossibleCoordinatesException e1) {
					logger.info("Adventurer is at edge of map: " + e1.getMessage());
				} catch (ImpossibleMovementException e) {
					logger.info(e.getMessage());
				}
				
				break;
			case TurnRight:
				adventurer.turn(action);
				break;
			case TurnLeft:
				adventurer.turn(action);
				break;
			default:
				logger.info("Adventurer " + adventurer.getName() + " has no more actions to perform.");
				break;
		}
		
		adventurer.popCurrentAction();
	}
}
