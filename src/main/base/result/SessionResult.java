package main.base.result;

import java.util.List;
import java.util.Map;

import main.base.Ballot;
import main.base.Question;
import main.base.Vote;
import main.base.rules.VotingRule;
import main.io.criterion.Criterion;

/**
 * SessionResult
 *
 * Represents the unformatted output of a Session (a voting session with a set of rules and questions).
 * 
 */
public class SessionResult {
	List<Question> questions;
	List<VotingRule> votingRules;
	Map<Question, List<Vote>> validVotes;
	Map<Question, Map<VotingRule, Ballot>> results;
	Criterion criterion;
	
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
		this.questions = questions;
		this.votingRules = votingRules;
		this.validVotes = validVotes;
		this.results = results;
		this.criterion = criterion;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * @return the votingRules
	 */
	public List<VotingRule> getVotingRules() {
		return votingRules;
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

	/**
	 * @return the criterion
	 */
	public Criterion getCriterion() {
		return criterion;
	}
}
