package com.adventure.configuration;

/**
 * An exception that occurs during Configuration setup.
 * @author Jean
 *
 */
public class ConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ConfigurationException(String string) {
		super(string);
	}

}
