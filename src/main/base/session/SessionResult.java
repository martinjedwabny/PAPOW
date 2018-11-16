package main.base.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.base.Question;
import main.base.Vote;
import main.base.criterion.Criterion;
import main.base.ordering.Ballot;
import main.base.rules.VotingRule;

/**
 * SessionResult
 *
 * Represents the unformatted output of a Session (a voting session with a set of rules and questions).
 * 
 */
public class SessionResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Map<Question, List<Vote>> validVotes;
	Map<Question, Map<VotingRule, Ballot>> results;
	
	/**
	 * Empty constructor
	 */
	public SessionResult() {
		this.validVotes = new HashMap<Question, List<Vote>>();
		this.results = new HashMap<Question, Map<VotingRule, Ballot>>();
	}

	/**
	 * Default Constructor
	 * @param questions
	 * @param votingRules
	 * @param validVotes
	 * @param results
	 * @param criterion
	 */
	public SessionResult(List<Question> questions, List<VotingRule> votingRules, Map<Question, List<Vote>> validVotes,
			Map<Question, Map<VotingRule, Ballot>> results, Criterion criterion) {
		super();
		this.validVotes = validVotes;
		this.results = results;
	}
	
	/**
	 * @return the validVotes
	 */
	public Map<Question, List<Vote>> getValidVotes() {
		return validVotes;
	}

	/**
	 * @return the results
	 */
	public Map<Question, Map<VotingRule, Ballot>> getResults() {
		return results;
	}
}
