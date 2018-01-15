package com.cognit.watson.nlc.utilities;

/**
 * @author Souad
 *
 */
public class Validator {

	/**
	 * check if value is empty or null
	 * 
	 * @param value
	 * @return true if value is empty or null ,false otherwise
	 */
	public static boolean isEmptyOrNull(String value) {

		return (value == null || value.isEmpty());

	}
}
