package com.adventure.output;

/**
 * An exception to gather all exceptions happening during output creation.
 * @author Jean
 *
 */
public class OutputException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public OutputException(String string) {
		super(string);
	}
}
