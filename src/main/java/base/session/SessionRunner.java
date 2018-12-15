package main.java.base.session;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import main.java.base.Question;
import main.java.base.Vote;
import main.java.base.criterion.Criterion;
import main.java.base.ordering.Ballot;
import main.java.base.rules.VotingRule;

public class SessionRunner {
	/**
	 * Take a set of questions, rules and vote criterion,
	 * filter the votes that don't pass the criterion and
	 * run each voting rule for each question.
	 * @param session
	 * @param rules
	 * @param criteria
	 * @return votingResults a map that for each question and rule, yields the output of that voting
	 */
	public static SessionResult generateResults(SessionInput session, List<VotingRule> rules, Set<Criterion> criteria) {
		Map<Question, Map<Criterion, List<Vote>>> validVotes = new LinkedHashMap<Question, Map<Criterion, List<Vote>>>();
		Map<Question, Map<Criterion, Map<VotingRule, Ballot>>> votingResults = new LinkedHashMap<Question, Map<Criterion, Map<VotingRule, Ballot>>>();
		// Get results for each questions, parallelize computations
		session.getQuestions().parallelStream().forEach(question -> {
			criteria.parallelStream().forEach(criterion -> {
				generateResult(question, criterion, rules, validVotes, votingResults);
			});
		});
		return new SessionResult(validVotes, votingResults);
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
	private static void generateResult(Question question, Criterion criterion, List<VotingRule> rules,
			Map<Question, Map<Criterion, List<Vote>>> validVotes, Map<Question, Map<Criterion, Map<VotingRule, Ballot>>> votingResults) {
		if (!validVotes.containsKey(question))
			validVotes.put(question, new LinkedHashMap<Criterion, List<Vote>>());
		validVotes.get(question).put(criterion, new Vector<Vote>());
		if (!votingResults.containsKey(question))
			votingResults.put(question, new LinkedHashMap<Criterion, Map<VotingRule, Ballot>>());
		votingResults.get(question).put(criterion, new LinkedHashMap<VotingRule, Ballot>());
		// Take votes that satisfy criterion and get ranking
		Vector<Ballot> rankings = new Vector<Ballot>();
		for (Vote v : question.getVotes())
			if (criterion.satisfies(voteCategoriesAsMap(v))) {
				rankings.add(v.getRanking());
				validVotes.get(question).get(criterion).add(v);
			}
		// Get results for each voting rule
		rules.stream().forEach(rule -> {
			votingResults.get(question).get(criterion).put(rule, rule.order(rankings));
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
