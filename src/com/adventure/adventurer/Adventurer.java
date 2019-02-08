package com.adventure.adventurer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.adventure.configuration.ConfigurationException;
import com.adventure.configuration.ConfigurationParser;
import com.adventure.grid.Coordinates;
import com.adventure.grid.GridException;

/**
 * 
 * @author JLC2
 *
 */
public class Adventurer {
	
	private static Logger logger = Logger.getLogger(Adventurer.class);

	public enum ActionTypes {
		TurnLeft,
		TurnRight,
		Advance
	}
	
	private String name;
	private Orientations orientation;
	private LinkedList<ActionTypes> actions;
	private Coordinates coordinates;

	public Adventurer(String name, String orientation, String actionsString) throws ConfigurationException {
		setName(name);
		setOrientation(orientation);
		setActions(actionsString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Orientations getOrientation() {
		return orientation;
	}
	
	private void setOrientation(Orientations e) {
		this.orientation = e;
	}

	public void setOrientation(String orientation) throws ConfigurationException {
		try {
			this.orientation = Arrays.stream(Orientations.values()).filter((t) -> t.name().equals(orientation)).collect(Collectors.toList()).get(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ConfigurationException("Orientation " + orientation + " is forbidden.");
		}
	}

	public LinkedList<ActionTypes> getActions() {
		return actions;
	}

	public void setActions(LinkedList<ActionTypes> actions) {
		this.actions = actions;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public Coordinates getPotentialNextCoordinates() throws GridException {
		Coordinates coordinates = getCoordinates();
		Coordinates nextCoordinates = null;
		
		if (getCurrentAction().equals(ActionTypes.Advance)) {
			switch (getOrientation()) {
				case S:
					nextCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() + 1);
					break;
				case O:
					nextCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() - 1);
					break;
				case N:
					nextCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() - 1);
					break;
				case E:
					nextCoordinates = new Coordinates(coordinates.getX(), coordinates.getY() + 1);
					break;
			}
		} else {
			return coordinates;
		}
		
		if (nextCoordinates == null) {
			throw new GridException("Potential new coordinates should not be null, orientation is: " + getOrientation());
		}
		
		return nextCoordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setActions(String actions) throws ConfigurationException {
		this.actions = new LinkedList<ActionTypes>();
		for (int i = 0; i < actions.length(); i++){
		    char action = actions.charAt(i);
		    switch (action) {
		    	case('A'): 
		    		this.actions.add(ActionTypes.Advance);
		    		break;
		    	case('G'):
		    		this.actions.add(ActionTypes.TurnLeft);
		    		break;
		    	case('D'):
		    		this.actions.add(ActionTypes.TurnRight);
		    		break;
		    	default:
		    		throw new ConfigurationException("Action " + action + " is forbidden.");
		    }
		}
	}
	
	public ActionTypes getCurrentAction() {
		return getActions().get(0);
	}
	
	public void popCurrentAction() {
		getActions().removeFirst();
	}

	public void turn(ActionTypes action) throws GridException {
		switch(action) {
			case TurnLeft:
				switch(getOrientation()) {
					case S:
						setOrientation(Orientations.E);
						break;
					case O:
						setOrientation(Orientations.S);
						break;
					case N:
						setOrientation(Orientations.O);
						break;
					case E:
						setOrientation(Orientations.N);
						break;
				}
				break;
			case TurnRight:
				switch(getOrientation()) {
					case S:
						setOrientation(Orientations.O);
						break;
					case O:
						setOrientation(Orientations.N);
						break;
					case N:
						setOrientation(Orientations.E);
						break;
					case E:
						setOrientation(Orientations.S);
						break;
				}
				break;
			default:
				throw new GridException("Turning is not compatible with: " + action);
		}
	}
}
