package voting;

import java.util.Set;
import java.util.Vector;

public class Question {
	private String description;
	private Vector<Alternative> alternatives;
	private Set<Vote> votes;
	
	/**
	 * Default constructor
	 * @param description
	 * @param alternatives
	 * @param votes
	 */
	public Question(String description, Vector<Alternative> alternatives, Set<Vote> votes) {
		super();
		this.description = description;
		this.alternatives = alternatives;
		this.votes = votes;
	}
	
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return alternatives
	 */
	public Vector<Alternative> getAlternatives() {
		return alternatives;
	}

	/**
	 * Set the votes property
	 * @param votes
	 */
	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	/**
	 * @return votes
	 */
	public Set<Vote> getVotes() {
		return votes;
	}

	/**
	 * Add an alternative
	 * @param a alternative to add
	 */
	public void addAlternative(Alternative a) {
		alternatives.add(a);
	}
	
	/**
	 * Add a vote
	 * @param v the vote to add
	 */
	public void addVote(Vote v) {
		votes.add(v);
	}
	
	/**
	 * Remove a vote
	 * @param v the vote to remove
	 */
	public void removeVote(Vote v) {
		votes.remove(v);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [description=" + description + ", alternatives=" + alternatives + ", votes=" + votes + "]";
	}
}
