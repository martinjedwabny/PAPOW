package base;

import java.util.Vector;

public class Session {
	
	private Vector<Question> questions;
	private Vector<Vote> votes;
	private Vector<Voter> voters;
	private Vector<CategoryFamily> families;
	
	/**
	 * Default constructor
	 * @param questions
	 * @param votes
	 * @param voters
	 * @param families
	 */
	public Session(Vector<Question> questions, Vector<Vote> votes, Vector<Voter> voters,
			Vector<CategoryFamily> families) {
		super();
		this.questions = questions;
		this.votes = votes;
		this.voters = voters;
		this.families = families;
	}

	/**
	 * @return questions
	 */
	public Vector<Question> getQuestions() {
		return questions;
	}

	/**
	 * @return votes
	 */
	public Vector<Vote> getVotes() {
		return votes;
	}

	/**
	 * @return voters
	 */
	public Vector<Voter> getVoters() {
		return voters;
	}

	/**
	 * @return families of categories
	 */
	public Vector<CategoryFamily> getFamilies() {
		return families;
	}
	
}
