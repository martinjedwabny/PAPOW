package main.base.rules.scoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.base.Alternative;
import main.base.Ballot;
import main.base.rules.VotingRule;

/**
 * Scoring rule
 * 
 * A voting rule that generates a score for each alternative from the ballots.
 * Then, an alternative is preferred to another if its score is bigger.
 */
public abstract class ScoringRule extends VotingRule {
	
	/**
	 * The score for each alternative. 
	 */
	protected Map<Alternative, Double> scores;

	/* (non-Javadoc)
	 * @see voting.rules.VotingRule#order(java.util.List)
	 */
	public Ballot order(List<Ballot> rankings) {
		this.scores = this.generateScores(rankings);
		TreeMap<Double, List<Alternative>> alternativesByScore = orderAlternativesByScore(this.scores);
		Map<Alternative, Integer> rankForElement = rankAlternativesByOrder(alternativesByScore);
		return new Ballot(rankForElement);
	}

	/**
	 * Order alternatives by score.
	 * @param scores 
	 * @return map from score to alternative
	 */
	private TreeMap<Double, List<Alternative>> orderAlternativesByScore(Map<Alternative, Double> scores) {
		TreeMap<Double, List<Alternative>> alternativesByScore = new TreeMap<Double, List<Alternative>>(Collections.reverseOrder());
		scores.forEach((k,v) -> {
			if (!alternativesByScore.containsKey(v))
				alternativesByScore.put(v, new ArrayList<Alternative>());
			alternativesByScore.get(v).add(k);
		});
		return alternativesByScore;
	}

	/**
	 * Rank alternatives by the score order.
	 * @param alternativesByScore a map from score to list of alternatives
	 * @return map from alternative to rank
	 */
	private Map<Alternative, Integer> rankAlternativesByOrder(TreeMap<Double, List<Alternative>> alternativesByScore) {
		Map<Alternative, Integer> rankForElement = new HashMap<Alternative, Integer>();
		Integer rank = 1;
		for(Map.Entry<Double,List<Alternative>> entry : alternativesByScore.entrySet()) {
			for (Alternative a : entry.getValue())
				rankForElement.put(a, rank);
			rank += entry.getValue().size();
		}
		return rankForElement;
	}

	/**
	 * Calculates the vote for each alternative
	 * @param rankings the initial ballots of each voter
	 */
	protected abstract Map<Alternative, Double> generateScores(List<Ballot> rankings);

	/**
	 * Get the scores.
	 * @return the scores
	 */
	public Map<Alternative, Double> getScores() {
		return scores;
	}

}