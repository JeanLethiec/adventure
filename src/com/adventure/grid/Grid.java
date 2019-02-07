package com.adventure.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JLC2
 *
 */
public class Grid {
	private int width;
	private int height;
	
	// Used ArrayList for performance reasons: better access to mid-list elements
	private List<Frame> frames = new ArrayList<Frame>();
	
	public Grid(int iWidth, int iHeight) {
		width = iWidth;
		height = iHeight;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public List<Frame> getFrames() {
		return frames;
	}
	
	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}
}
