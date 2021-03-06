package main.java.base.criterion;

import java.util.Map;

public class CriterionEquals extends Criterion {
	private static final long serialVersionUID = 1L;
	
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

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.key + "=" + this.value;
	}

	@Override
	public void removeKey(String key) {}

	@Override
	public void removeValue(String key, String value) {}

	@Override
	public void updateKey(String oldKey, String newKey) {
		if (this.key.equals(oldKey))
			this.key = newKey;
	}

	@Override
	public void updateValue(String oldKey, String oldValue, String newValue) {
		if (this.key.equals(oldKey) && this.value.equals(oldValue))
			this.value = newValue;
	}

}
