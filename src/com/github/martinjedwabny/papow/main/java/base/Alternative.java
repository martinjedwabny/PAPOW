package com.github.martinjedwabny.papow.main.java.base;

import java.io.Serializable;

public class Alternative implements Serializable, Comparable<Alternative> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name of the alternative
	 */
	private String name;

	/**
	 * Default Constructor
	 * @param name Name of the alternative
	 */
	public Alternative(String name) {
		super();
		this.name = name;
	}

	/** Get name
	 * @return name of the alternative
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Alternative other) {
		return this.getName().compareTo(other.getName());
	}
	
}
