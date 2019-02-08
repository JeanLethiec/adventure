package com.adventure.grid;

import com.adventure.configuration.ConfigurationException;

public class TreasureFrame extends StandardFrame implements Adventurable {
	
	private int nbTreasures;

	public TreasureFrame(Coordinates xy, String nb) throws ConfigurationException {
		super(xy);
		try {
			setNbTreasures(Integer.valueOf(nb));
		} catch (NumberFormatException e) {
			throw new ConfigurationException("Bad value for number of treasures: " + nb);
		}
	}

	public int getNbTreasures() {
		return nbTreasures;
	}

	public void setNbTreasures(int nbTreasures) {
		this.nbTreasures = nbTreasures;
	}
	
	public void addTreasure() {
		this.nbTreasures++;
	}
	
	public void removeTreasure() {
		this.nbTreasures--;
	}
}
