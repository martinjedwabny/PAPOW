package main.java.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import main.java.base.ordering.Ballot;

/**
 * Vote
 */
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	 * Copy constructor
	 * @param voter
	 * @param ranking
	 * @param categories
	 */
	public Vote(Vote vote) {
		super();
		this.voter = vote.voter;
		this.ranking = new Ballot(vote.getRanking());
		this.categories = new HashMap<CategoryFamily, Category>(vote.getCategories());
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
	 * @param ranking the ranking to set
	 */
	public void setRanking(Ballot ranking) {
		this.ranking = ranking;
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
		return "Vote [ranking=" + ranking + ", voter=" + voter + ", categories=" + categories + "]";
	}
	
}
