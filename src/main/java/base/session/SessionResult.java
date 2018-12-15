package main.java.base.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.base.Question;
import main.java.base.Vote;
import main.java.base.criterion.Criterion;
import main.java.base.ordering.Ballot;
import main.java.base.rules.VotingRule;

/**
 * SessionResult
 *
 * Represents the unformatted output of a Session (a voting session with a set of rules and questions).
 * 
 */
public class SessionResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Map<Question, Map<Criterion, List<Vote>>> validVotes;
	Map<Question, Map<Criterion, Map<VotingRule, Ballot>>> results;
	
	/**
	 * Empty constructor
	 */
	public SessionResult() {
		this.validVotes = new HashMap<Question, Map<Criterion, List<Vote>>>();
		this.results = new HashMap<Question, Map<Criterion, Map<VotingRule, Ballot>>>();
	}

	/**
	 * Default Constructor
	 * @param validVotes
	 * @param results
	 */
	public SessionResult(Map<Question, Map<Criterion, List<Vote>>> validVotes, 
			Map<Question, Map<Criterion, Map<VotingRule, Ballot>>> results) {
		super();
		this.validVotes = validVotes;
		this.results = results;
	}
	
	/**
	 * @return the validVotes
	 */
	public Map<Question, Map<Criterion, List<Vote>>> getValidVotes() {
		return validVotes;
	}

	/**
	 * @return the results
	 */
	public Map<Question, Map<Criterion, Map<VotingRule, Ballot>>> getResults() {
		return results;
	}
}
