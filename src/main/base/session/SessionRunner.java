package main.base.session;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import main.base.Question;
import main.base.Vote;
import main.base.ordering.Ballot;
import main.base.rules.VotingRule;
import main.io.criterion.Criterion;

public class SessionRunner {
	/**
	 * Take a set of questions, rules and vote criterion,
	 * filter the votes that don't pass the criterion and
	 * run each voting rule for each question.
	 * @param session
	 * @param rules
	 * @param criterion
	 * @return votingResults a map that for each question and rule, yields the output of that voting
	 */
	public static SessionResult generateResults(SessionInput session, List<VotingRule> rules, Criterion criterion) {
		Map<Question, List<Vote>> validVotes = new LinkedHashMap<Question, List<Vote>>();
		Map<Question, Map<VotingRule, Ballot>> votingResults = new LinkedHashMap<Question, Map<VotingRule, Ballot>>();
		// Get results for each questions, parallelize computations
		session.getQuestions().parallelStream().forEach(question -> {
			generateResultsForQuestion(question, criterion, rules, validVotes, votingResults);
		});
		return new SessionResult(session.getQuestions(), rules, validVotes, votingResults, criterion);
	}

	/**
	 * Take a question, rules and vote criterion,
	 * filter the votes that don't pass the criterion and
	 * run each voting rule for the question.
	 * @param rules
	 * @param question
	 * @param criterion
	 * @return questionResults a map that for each rule, yields the output of that voting
	 */
	private static void generateResultsForQuestion(Question question, Criterion criterion, List<VotingRule> rules,
			Map<Question, List<Vote>> validVotes, Map<Question, Map<VotingRule, Ballot>> votingResults) {
		validVotes.put(question, new Vector<Vote>());
		votingResults.put(question, new LinkedHashMap<VotingRule, Ballot>());
		// Take votes that satisfy criterion and get ranking
		Vector<Ballot> rankings = new Vector<Ballot>();
		for (Vote v : question.getVotes())
			if (criterion.satisfies(voteCategoriesAsMap(v))) {
				rankings.add(v.getRanking());
				validVotes.get(question).add(v);
			}
		// Get results for each voting rule, parallelize computations
		rules.parallelStream().forEach(rule -> {
			votingResults.get(question).put(rule, new Ballot(rule.order(rankings)));
		});
	}

	/**
	 * Convert the categories in the vote to a map of <CategoryFamily, Category>.
	 * @param vote the vote
	 * @return Map of <CategoryFamily, Category>
	 */
	private static Map<String, String> voteCategoriesAsMap(Vote vote) {
		return vote.getCategories().entrySet().stream()
		          .collect(Collectors.toMap(e -> e.getKey().getDescription(), e -> e.getValue().getDescription()));
	}
}
