/**
 * 
 */
package main.java.base.rules.scoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.base.Alternative;
import main.java.base.ordering.Ballot;

/**
 * Copeland
 */
public class Copeland extends ScoringRule {
	private static final long serialVersionUID = 1L;

	/* 
	 * The score of each alternative is calculated as the amount of alternatives to which
	 * it is superior minus the amount to which it is inferior.
	 * @param rankings the voter ballots
	 * @return scores a map of scores for each alternative
	 */
	@Override
	protected Map<Alternative, Double> generateScores(List<Ballot> rankings) {
		Map<Alternative, Double> scores = new HashMap<Alternative, Double>();
		if (rankings.isEmpty())
			return scores;
		List<Alternative> alternatives = new ArrayList<Alternative>(rankings.get(0).getElements());
		int[][] points = new int[alternatives.size()][alternatives.size()];
		for (Ballot ballot : rankings)
			for (int i = 0; i < alternatives.size(); i++)
				for (int j = i+1; j < alternatives.size(); j++)
					if (ballot.higherRank(alternatives.get(j), alternatives.get(i)))
						points[i][j]++;
					else if (ballot.higherRank(alternatives.get(i), alternatives.get(j)))
						points[j][i]++;
		for (Alternative alternative : alternatives)
			scores.put(alternative, 0.0);
		for (int i = 0; i < alternatives.size(); i++)
			for (int j = 0; j < alternatives.size(); j++)
				if (points[i][j] > points[j][i]) {
					scores.put(alternatives.get(i), scores.get(alternatives.get(i)) + 1.0);
					scores.put(alternatives.get(j), scores.get(alternatives.get(j)) - 1.0);
				}
		return scores;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Copeland";
	}

}
