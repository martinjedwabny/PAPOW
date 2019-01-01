package com.github.martinjedwabny.papow.main.java.base.rules.iterative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.github.martinjedwabny.papow.main.java.base.Alternative;
import com.github.martinjedwabny.papow.main.java.base.ordering.Ballot;
import com.github.martinjedwabny.papow.main.java.base.rules.VotingRule;

 /**
 * Instant Runoff:
 * For each iteration step, eliminate the alternatives (set) with the lowest amount of first places.
 * When there are no more alternatives left, return the alternatives sorted by the reverse order in which
 * they were eliminated.
 */
public class InstantRunoff extends VotingRule {
	private static final long serialVersionUID = 1L;

	/* 
	 * Execute an iteration step (in which rankings are computed)
	 * until a certain codition is achieved
	 */
	public Ballot order(List<Ballot> rankings) {
		// Check for empty
		if (rankings.isEmpty() || rankings.get(0).getElementsCount() == 0)
			return new Ballot(new HashMap<Alternative, Integer>());
		// Not empty case, initialize properties
		Vector<Set<Alternative>> answer = new Vector<Set<Alternative>>();
		HashSet<Alternative> eliminated = new HashSet<Alternative>();
		ArrayList<Ballot> votes = new ArrayList<Ballot>(rankings.size());
		HashMap<Alternative, Integer> wins = new HashMap<Alternative, Integer>();
		Integer voterCount = rankings.size(); 
		Integer alternativeCount = rankings.get(0).getElementsCount();
		// Copy rankings
		for (Ballot ballot : rankings)
			votes.add(new Ballot(ballot));
		// Fill winning indexes and votes
		fillPropertiesWithInitialRankings(rankings, wins);
		// Execute the voting
		while (!iterationFinished(eliminated, alternativeCount))
			doIterationStep(answer, eliminated, wins, voterCount, votes);
		return getFinalOrder(answer);
	}

	/**
	 * Class initialization helper
	 * Fill the object properties according to the initial voter rankings of alternatives
	 * @param rankings the initial rankings submitted by the voters
	 */
	private void fillPropertiesWithInitialRankings(List<Ballot> rankings, HashMap<Alternative, Integer> wins) {
		for (Ballot ranking : rankings)
			for (Alternative a : ranking.getElements(1))
				wins.put(a, wins.getOrDefault(a, 0)+1);
	}
	
	/* 
	 * Get the losers (alternatives with the least amount of 1st places)
	 * Remove them from all the rankings
	 * Recompute the wins for each voter
	 */
	public void doIterationStep(List<Set<Alternative>> answer, HashSet<Alternative> eliminated, HashMap<Alternative, Integer> wins, Integer voterCount, ArrayList<Ballot> votes) {
		Set<Alternative> roundLosers = getLeastFirstPlaces(wins, voterCount);
		answer.add(0, roundLosers);
		eliminated.addAll(roundLosers);
		recomputeWinsAndVotes(roundLosers, wins, votes);
	}

	/**
	 * Recompute the amount of wins as well as the ballots/rankings
	 * of each voter by eliminating the losers of this round as well as the previous ones
	 * @param losers
	 */
	private void recomputeWinsAndVotes(Set<Alternative> roundLosers, HashMap<Alternative, Integer> wins, ArrayList<Ballot> votes) {
		wins.clear();
		for (Ballot ballot : votes) {
			ballot.removeAll(roundLosers);
			for (Alternative a : ballot.getElements(1))
				wins.put(a, wins.getOrDefault(a, 0)+1);
		}
	}

	
	/** 
	 * Get the alternatives with the least amount of 1st places
	 * @return alternative set containing the ones with the least amount of 1st places
	 */
	private Set<Alternative> getLeastFirstPlaces(HashMap<Alternative, Integer> wins, Integer voterCount) {
		Integer min = voterCount+1;
		Set<Alternative> ans = new HashSet<Alternative>();
		for (Alternative a : wins.keySet())
			if (wins.get(a) < min) {
				min = wins.get(a);
				ans = new HashSet<Alternative>();
				ans.add(a);
			} else if (wins.get(a) == min){
				ans.add(a);
			}
		return ans;
	}

	/** Gets the final order of alternatives
	 * @return Ballot representing the final order
	 */
	public Ballot getFinalOrder(List<Set<Alternative>> answer) {
		Map<Alternative, Integer> rankForElement = new HashMap<Alternative, Integer>();
		Integer rank = 1;
		for (Set<Alternative> rankElements : answer) {
			for (Alternative alternative : rankElements)
				rankForElement.put(alternative, rank);
			rank += rankElements.size();
		}
		return new Ballot(rankForElement);
	}

	/** Returns true iff a certain condition is achieved (all positions in the ranking are determined)
	 * @return Boolean whether the iteration is finished
	 */
	protected Boolean iterationFinished(HashSet<Alternative> eliminated, int alternativeCount) {
		return eliminated.size() == alternativeCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InstantRunoff";
	}

}
