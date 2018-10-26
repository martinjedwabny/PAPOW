package voting;

public class Category {
	private String description;

	/**
	 * Default Constructor
	 * @param description
	 */
	public Category(String description) {
		super();
		this.description = description;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description;
	}
	
}
