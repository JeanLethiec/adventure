package com.adventure.adventurer;

import java.util.Arrays;
import java.util.LinkedList;

import com.adventure.adventurer.action.Action;
import com.adventure.adventurer.action.Advance;
import com.adventure.adventurer.action.TurnLeft;
import com.adventure.adventurer.action.TurnRight;
import com.adventure.configuration.ConfigurationException;

/**
 * 
 * @author JLC2
 *
 */
public class Adventurer {
	
	private String name;
	private String orientation;
	private LinkedList<Action> actions;

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

	public LinkedList<Action> getActions() {
		return actions;
	}

	public void setActions(LinkedList<Action> actions) {
		this.actions = actions;
	}
	
	public void setActions(String actions) throws ConfigurationException {
		this.actions = new LinkedList<Action>();
		for (int i = 0; i < actions.length(); i++){
		    char action = actions.charAt(i);
		    switch (action) {
		    	case('A'): 
		    		this.actions.add(new Advance());
		    		break;
		    	case('G'):
		    		this.actions.add(new TurnLeft());
		    		break;
		    	case('D'):
		    		this.actions.add(new TurnRight());
		    		break;
		    	default:
		    		throw new ConfigurationException("Action " + action + " is forbidden.");
		    }
		}
	}
	
	public Action getCurrentAction() {
		return getActions().get(0);
	}
	
	public void popCurrentAction() {
		getActions().removeFirst();
	}

	public void	execute() {
		Action action = getCurrentAction();
		
		// TODO: Perform action
		
		popCurrentAction();
	}
}
