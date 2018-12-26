package main.java.base;

import java.io.Serializable;
import main.java.base.ordering.Ballot;

/**
 * Vote
 */
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Voter voter;
	private Ballot ranking;
	
	/**
	 * Default constructor
	 * @param voter
	 * @param ranking
	 */
	public Vote(Voter voter, Ballot ranking) {
		super();
		this.voter = voter;
		this.ranking = ranking;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vote [ranking=" + ranking + ", voter=" + voter + "]";
	}
	
}
