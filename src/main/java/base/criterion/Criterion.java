package main.java.base.criterion;

import java.io.Serializable;
import java.util.Map;

/**
 * Criterion
 * 
 * An object that evaluates a profile (as Map<String, String>)
 * and returns true if certain conditions are met according to their keys and values.
 * 
 * This is a recursively composed object, the class itself is abstract
 * and requires subclasses to be instantiated.
 * 
 * CriterionEmpty and CriterionEquals are the base cases.
 * CriterionOr and CriterionAnd are the recursive cases.
 */
public abstract class Criterion implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Return true iff certain conditions are met according to the keys and values of the input.
	 * @param profile a profile (as Map<String, String>)
	 * @return true iff certain conditions are met according to their keys and values
	 */
	public abstract Boolean satisfies(Map<String, String> profile);

	/**
	 * Delete the key from all subcriteria if any exist in the profile.
	 * @param key to remove
	 */
	public abstract void removeKey(String key);
	
	/**
	 * Delete the entry with matching key and value from all subcriteria if any exist in the profile.
	 * @param key to remove
	 * @param value to remove
	 */
	public abstract void removeValue(String key, String value);

	/**
	 * Update the key in all subcriteria if any exist in the profile.
	 * @param oldKey
	 * @param newKey
	 */
	public abstract void updateKey(String oldKey, String newKey);

	/**
	 * Update the value with matching key from all subcriteria if any exist in the profile.
	 * @param oldKey
	 * @param oldValue
	 * @param newValue
	 */
	public abstract void updateValue(String oldKey, String oldValue, String newValue);
}
