package main.java.base.session;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import main.java.base.CategoryFamily;
import main.java.base.Question;
import main.java.base.Vote;
import main.java.base.Voter;
import main.java.base.criterion.Criterion;
import main.java.base.ordering.Ballot;
import main.java.base.rules.VotingRule;

public class SessionRunner {
	/**
	 * Take a set of questions, rules and vote criterion,
	 * filter the voters that don't pass the criterion and
	 * run each voting rule for each question.
	 * @param session
	 * @param rules
	 * @param criteria
	 * @return votingResults a map that for each question and rule, yields the output of that voting
	 */
	public static SessionResult generateResults(SessionInput session, List<VotingRule> rules, Set<Criterion> criteria) {
		Map<Criterion, List<Voter>> validVoters = new LinkedHashMap<Criterion, List<Voter>>();
		Map<Question, Map<Criterion, Map<VotingRule, Ballot>>> votingResults = new LinkedHashMap<Question, Map<Criterion, Map<VotingRule, Ballot>>>();
		// Get results for each criterion
		for (Criterion criterion : criteria)
			generateResultForCriterion(session, criterion, rules, validVoters, votingResults);
		return new SessionResult(validVoters, votingResults);
	}

	/**
	 * Take a criterion and voting rules,
	 * filter the voters that don't pass the criterion and
	 * run each voting rule for the question.
	 * @param session
	 * @param criterion
	 * @param rules
	 * @param validVoters
	 * @param votingResults
	 * @return questionResults a map that for each rule, yields the output of that voting
	 */
	private static void generateResultForCriterion(SessionInput session, Criterion criterion, List<VotingRule> rules,
			Map<Criterion, List<Voter>> validVoters, Map<Question, Map<Criterion, Map<VotingRule, Ballot>>> votingResults) {
		validVoters.put(criterion, new Vector<Voter>());
		for (Voter voter : session.getVoters())
			if (criterion.satisfies(voterCategoriesAsMap(voter)))
				validVoters.get(criterion).add(voter);
		for (Question question : session.getQuestions()) {
			if (!votingResults.containsKey(question))
				votingResults.put(question, new LinkedHashMap<Criterion, Map<VotingRule, Ballot>>());
			votingResults.get(question).put(criterion, new LinkedHashMap<VotingRule, Ballot>());
			// Take votes that satisfy criterion and get ranking
			Vector<Ballot> rankings = new Vector<Ballot>();
			for (Vote v : question.getVotes())
				if (validVoters.get(criterion).contains(v.getVoter()))
					rankings.add(v.getRanking());
			// Get results for each voting rule
			for (VotingRule rule : rules)
				votingResults.get(question).get(criterion).put(rule, rule.order(rankings));
		}
	}

	/**
	 * Convert the categories in the voter to a map of <CategoryFamily, Category>.
	 * @param voter
	 * @return Map of <CategoryFamily, Category>
	 */
	private static Map<String, String> voterCategoriesAsMap(Voter v) {
		Map<String, String> categoriesStringMap = new HashMap<String, String>();
		for (CategoryFamily f : v.getCategories().keySet())
			categoriesStringMap.put(f.getDescription(), v.getCategories().get(f).getDescription());
		return categoriesStringMap;
	}
}
