package main.base;

/**
 * Voter
 */
public class Voter {
	
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Voter [" + name + "]";
	}
	
}
