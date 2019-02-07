package com.adventure.grid;

public class Frame {
	private Coordinates xy;
	
	public Frame(Coordinates iXY) {
		xy = iXY;
	}
	
	public Coordinates getXy() {
		return xy;
	}
	
	public void setXy(Coordinates xy) {
		this.xy = xy;
	}
}
