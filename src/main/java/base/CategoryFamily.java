package main.java.base;

import java.io.Serializable;
import java.util.Set;

public class CategoryFamily implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the family of categories (example: Country)
	 */
	private String description;
	
	/**
	 * Valid values for the category family (example: Country=France)
	 */
	private Set<Category> possibilities;
	
	/**
	 * Default constructor
	 * @param description
	 * @param possibilities
	 */
	public CategoryFamily(String description, Set<Category> possibilities) {
		super();
		this.description = description;
		this.possibilities = possibilities;
	}
	
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return possibilities
	 */
	public Set<Category> getPossibilities() {
		return possibilities;
	}
	
	/**
	 * Set the possibilities
	 * @param possibilities
	 */
	public void setPossibilities(Set<Category> possibilities) {
		this.possibilities = possibilities;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description;
	}
}
