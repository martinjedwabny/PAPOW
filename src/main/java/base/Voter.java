package main.java.base;

import java.io.Serializable;
import java.util.Map;

/**
 * Voter
 */
public class Voter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Map<CategoryFamily, Category> categories;

	/**
	 * Default constructor
	 * @param name
	 */
	public Voter(String name, Map<CategoryFamily, Category> categories) {
		super();
		this.name = name;
		this.categories = categories;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the voter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Map<CategoryFamily, Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return categories
	 */
	public Map<CategoryFamily, Category> getCategories() {
		return categories;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
