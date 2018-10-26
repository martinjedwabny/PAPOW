package voting;

import java.util.Map;

/**
 * Vote
 */
public class Vote {
	
	private Voter voter;
	private Ballot ranking;
	private Map<CategoryFamily, Category> categories;
	
	/**
	 * Default constructor
	 * @param voter
	 * @param ranking
	 * @param categories
	 */
	public Vote(Voter voter, Ballot ranking, Map<CategoryFamily, Category> categories) {
		super();
		this.voter = voter;
		this.ranking = ranking;
		this.categories = categories;
	}

	/**
	 * @return voter
	 */
	public Voter getVoter() {
		return voter;
	}

	/**
	 * @return ranking
	 */
	public Ballot getRanking() {
		return ranking;
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
		return "Vote [ranking=" + ranking + ", voter=" + voter + ", categories=" + categories + "]";
	}
	
}
