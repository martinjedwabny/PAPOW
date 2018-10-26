package io.criterion;

import java.util.Map;

public class CriterionEquals extends Criterion {
	
	private String key;
	private String value;

	/**
	 * Default constructor
	 * @param key
	 * @param value
	 */
	public CriterionEquals(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/* 
	 * Returns true if the map matches the value in the key to the value in this object
	 */
	@Override
	public Boolean satisfies(Map<String, String> profile) {
		return profile.containsKey(this.key) && profile.get(this.key).toLowerCase().equals(this.value.toLowerCase());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.key + "=" + this.value;
	}

}
