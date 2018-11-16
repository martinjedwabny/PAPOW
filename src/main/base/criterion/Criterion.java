package main.base.criterion;

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
}
