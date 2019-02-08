package com.adventure.adventurer;

import java.util.Arrays;
import java.util.LinkedList;

import com.adventure.configuration.ConfigurationException;

/**
 * 
 * @author JLC2
 *
 */
public class Adventurer {
	
	private String name;
	private String orientation;
	private LinkedList<String> actions;

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

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) throws ConfigurationException {
		if (!Arrays.stream(Orientations.values()).anyMatch((t) -> t.name().equals(orientation))) {
			throw new ConfigurationException("Orientation " + orientation + " is forbidden.");
		}
		this.orientation = orientation;
	}

	public LinkedList<String> getActions() {
		return actions;
	}

	public void setActions(LinkedList<String> actions) {
		this.actions = actions;
	}
	
	public void setActions(String actions) throws ConfigurationException {
		this.actions = new LinkedList<String>();
		for (int i = 0; i < actions.length(); i++){
		    char action = actions.charAt(i);
		    if (!Arrays.stream(Actions.values()).anyMatch((t) -> t.name().charAt(0) == action)) {
				throw new ConfigurationException("Action " + action + " is forbidden.");
			}
		    this.actions.add(String.valueOf(action));
		}
	}
	
	public String getCurrentAction() {
		return getActions().get(0);
	}
	
	public void popCurrentAction() {
		getActions().removeFirst();
	}

	public void	execute() {
		String action = getCurrentAction();
		
		// TODO: Perform action
		
		popCurrentAction();
	}
}
