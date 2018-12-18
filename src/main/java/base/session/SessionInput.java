package main.java.base.session;

import java.io.Serializable;
import java.util.Vector;

import main.java.base.Alternative;
import main.java.base.Category;
import main.java.base.CategoryFamily;
import main.java.base.Question;
import main.java.base.Vote;
import main.java.base.Voter;

/**
 * SessionInput
 * 
 * The session data to be processed by voting rules.
 * Stores the static data to be loaded and used.
 *
 */
public class SessionInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Vector<Question> questions;
	private Vector<Vote> votes;
	private Vector<Voter> voters;
	private Vector<CategoryFamily> families;
	private Vector<Alternative> alternatives;
	
	/**
	 * Empty constructor
	 */
	public SessionInput() {
		super();
		this.questions = new Vector<Question>();
		this.votes = new Vector<Vote>();
		this.voters = new Vector<Voter>();
		this.families = new Vector<CategoryFamily>();
		this.alternatives = new Vector<Alternative>();
	}

	/**
	 * Default constructor
	 * @param questions
	 * @param votes
	 * @param voters
	 * @param families
	 * @param alternatives
	 */
	public SessionInput(Vector<Question> questions, Vector<Vote> votes, Vector<Voter> voters,
			Vector<CategoryFamily> families, Vector<Alternative> alternatives) {
		super();
		this.questions = questions;
		this.votes = votes;
		this.voters = voters;
		this.families = families;
		this.alternatives = alternatives;
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

	/**
	 * @return the alternatives
	 */
	public Vector<Alternative> getAlternatives() {
		return alternatives;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @param votes the votes to set
	 */
	public void setVotes(Vector<Vote> votes) {
		this.votes = votes;
	}

	/**
	 * @param voters the voters to set
	 */
	public void setVoters(Vector<Voter> voters) {
		this.voters = voters;
	}

	/**
	 * @param families the families to set
	 */
	public void setFamilies(Vector<CategoryFamily> families) {
		this.families = families;
	}

	/**
	 * @param alternatives the alternatives to set
	 */
	public void setAlternatives(Vector<Alternative> alternatives) {
		this.alternatives = alternatives;
	}

	/**
	 * Add voter
	 * @param voter
	 */
	public void addVoter(Voter voter) {
		this.voters.add(voter);
	}

	/**
	 * Remove voter from session input, along with any vote made by that voter in any question
	 * @param voter
	 */
	public void removeVoter(Voter voter) {
		this.voters.remove(voter);
		this.questions.forEach(q -> q.getVotes().removeIf(vv ->vv.getVoter().equals(voter)));
	}

	/**
	 * Add alternative
	 * @param alternative
	 */
	public void addAlternative(Alternative alternative) {
		this.alternatives.add(alternative);
	}

	/**
	 * Remove alternative from session input, along with any appearance in any question
	 * @param alternative
	 */
	public void removeAlternative(Alternative alternative) {
		this.alternatives.remove(alternative);
		this.questions.forEach(q -> {
			q.removeAlternative(alternative);
		});
	}

	/**
	 * Add question
	 * @param question
	 */
	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	/**
	 * Remove question from session input
	 * @param question
	 */
	public void removeQuestion(Question question) {
		this.questions.remove(question);
	}

	/**
	 * Remove family from session input
	 * @param family
	 */
	public void removeFamily(CategoryFamily family) {
		if (family == null)
			return;
		this.getFamilies().remove(family);
		for (Vote v : this.getVotes())
			if (v.getCategories().containsKey(family))
				v.getCategories().remove(family);
	}

	/**
	 * Remove category from family in session input
	 * @param family
	 * @param category
	 */
	public void removeCategory(CategoryFamily family, Category category) {
		if (family == null || category == null)
			return;
		for (Vote v : this.getVotes())
			if (v.getCategories().containsKey(family) && v.getCategories().get(family).equals(category))
				v.getCategories().remove(family);
		family.getPossibilities().remove(category);
		if (family.getPossibilities().isEmpty())
			removeFamily(family);
	}
	
}
