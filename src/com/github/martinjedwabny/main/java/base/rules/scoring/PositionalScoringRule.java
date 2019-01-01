package com.github.martinjedwabny.main.java.base.rules.scoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.martinjedwabny.main.java.base.Alternative;
import com.github.martinjedwabny.main.java.base.ordering.Ballot;

/**
 * Positional scoring rule
 * 
 * A voting rule in which each alternative gets a score according to some function
 * that takes into account the rankings of the voters for each alternative
 * Having calculated such scores, the final rankings are induced by the notion
 * that an alternative is better than another iff the score is higher
 */
public abstract class PositionalScoringRule extends ScoringRule {
	private static final long serialVersionUID = 1L;

	protected Map<Alternative, Double> generateScores(List<Ballot> rankings) {
		Map<Alternative, Double> scores = new HashMap<Alternative, Double>();
		for (Ballot ranking : rankings) {
			Integer less = ranking.getElementsCount(), greater = 0, equal = 0;
			for (Integer rank : ranking.getRanks()) {
				Integer elementsInRank = ranking.getElements(rank).size();
				less -= elementsInRank;
				equal = elementsInRank;
				for (Alternative a : ranking.getElements(rank))
					scores.put(a, scores.getOrDefault(a, 0.0) + score(less, greater, equal, rank));
				greater += elementsInRank;
			}
		}
		return scores;
	}

	/**
	 * Calculates the score of an alternative for a single voter
	 * @param less the amount of lesser ranked alternatives for the voter
	 * @param greater the amount of greater ranked alternatives for the voter
	 * @param equal the amount of equally ranked alternatives for the voter
	 * @param rank the rank of the alternative for the voter
	 * @return
	 */
	public abstract Double score(Integer less, Integer greater, Integer equal, Integer rank);
	
}
