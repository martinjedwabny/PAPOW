package main.java.base;

import java.io.Serializable;

/**
 * Voter
 */
public class Voter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;

	/**
	 * Default constructor
	 * @param name
	 */
	public Voter(String name) {
		super();
		this.name = name;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Voter) && (((Voter) obj).getName().equals(this.name));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Voter [" + name + "]";
	}
	
}
