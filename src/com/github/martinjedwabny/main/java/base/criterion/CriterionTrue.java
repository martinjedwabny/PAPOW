package com.github.martinjedwabny.main.java.base.criterion;

import java.util.Map;

public class CriterionTrue extends Criterion {
	private static final long serialVersionUID = 1L;

	/* 
	 * Always returns true
	 */
	@Override
	public Boolean satisfies(Map<String, String> profile) {
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ANY";
	}

	@Override
	public void removeKey(String key) {}

	@Override
	public void removeValue(String key, String value) {}


	@Override
	public void updateKey(String oldKey, String newKey) {}

	@Override
	public void updateValue(String oldKey, String oldValue, String newValue) {}
}
