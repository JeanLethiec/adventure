package com.adventure.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

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
		logger.debug("Initializing frames creation in grid...");
		int x = 0;
		while (x < width) {
			int y = 0;
			while (y < height) {
				logger.trace("Working on X" + x + " ; Y" + y);
				addFrame(new StandardFrame(new Coordinates(x, y)));
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
	
	public void setHeight(int height) throws ConfigurationException {
		if (height > 0) {
			this.height = height;
		} else {
			throw new ConfigurationException("Grid height must be positive.");
		}
	}
	
	public void setWidth(int width) throws ConfigurationException {
		if (width > 0) {
			this.width = width;
		} else {
			throw new ConfigurationException("Grid height must be positive.");
		}
	}
	
	public List<Frame> getFrames() {
		return frames;
	}
	
	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}
	
	public void addFrame(Frame frame) {
		this.frames.add(frame);
	}
	
	public void replaceFrame(Coordinates xy, Frame newFrame) throws Exception {
		List<Frame> correspondingFrames = this.frames.stream().filter(x -> x.getXy().equals(xy)).collect(Collectors.toList());
		
		if (correspondingFrames.isEmpty()) {
			throw new Exception("Trying to replace a non-existent frame: " + xy.toString());
		}
	}
}
