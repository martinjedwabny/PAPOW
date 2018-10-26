package base;

public class Alternative {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
