package com.github.martinjedwabny.papow.main.java.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String description;
	private Vector<Alternative> alternatives;
	private Set<Vote> votes;

	/**
	 * Empty constructor
	 */
	public Question() {
		super();
		this.description = "";
		this.alternatives = new Vector<Alternative>();
		this.votes = new HashSet<Vote>();
	}
	
	/**
	 * Description only constructor
	 * @param description
	 */
	public Question(String description) {
		super();
		this.description = description;
		this.alternatives = new Vector<Alternative>();
		this.votes = new HashSet<Vote>();
	}
	
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
	 * Copy constructor
	 * @param question
	 */
	public Question(Question question) {
		super();
		this.description = question.getDescription();
		this.alternatives = new Vector<Alternative>(question.getAlternatives());
		this.votes = question.getVotes().stream().map(Vote::new).collect(Collectors.toSet());
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
	 * @return alternatives
	 */
	public Vector<Alternative> getAlternatives() {
		return alternatives;
	}

	/**
	 * @param alternatives the alternatives to set
	 */
	public void setAlternatives(Vector<Alternative> alternatives) {
		this.alternatives = alternatives;
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
	 * Add an alternative, also insert it as the last choice in every vote
	 * @param a alternative to add
	 */
	public void addAlternative(Alternative a) {
		if (!this.getAlternatives().contains(a)) {
			votes.forEach(v -> v.getRanking().addLast(a));
			alternatives.add(a);
		}
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
	
	/**
	 * Remove an alternative, along with any occurence in the votes
	 * @param a alternative to remove
	 */
	public void removeAlternative(Alternative a) {
		alternatives.removeIf(aa -> aa.equals(a));
		votes.forEach(v -> v.getRanking().remove(a));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [description=" + description + ", alternatives=" + alternatives + ", votes=" + votes + "]";
	}
}
