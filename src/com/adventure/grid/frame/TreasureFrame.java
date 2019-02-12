package com.adventure.grid.frame;

import com.adventure.configuration.ConfigurationException;
import com.adventure.grid.coordinates.Coordinates;

/**
 * A frame carrying one or many treasures, that Adventurers can cross freely.
 * @author Jean
 *
 */
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
	
	public boolean hasTreasure() {
		return getNbTreasures() > 0;
	}
	
	public void addTreasure() {
		this.nbTreasures++;
	}
	
	public void removeTreasure() {
		this.nbTreasures--;
	}
	
	public String getRepresentation() {
		return "T - " + getCoordinates().getX() + " - " + getCoordinates().getY() + " - " + getNbTreasures();
	}
}
