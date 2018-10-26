package voting.rules.iterative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import voting.Alternative;
import voting.Ballot;

 /**
 * Instant Runoff:
 * For each iteration step, eliminate the alternatives (set) with the lowest amount of first places.
 * When there are no more alternatives left, return the alternatives sorted by the reverse order in which
 * they were eliminated.
 */
public class InstantRunoff extends IterativeVotingRule {
	
	/**
	 * the list of ordered set of alternatives to output. Lower order means better final ranking.
	 */
	List<Set<Alternative>> answer;
	
	/**
	 * the set of all alternatives eliminated by the iteration process so far.
	 */
	Set<Alternative> eliminated;
	
	 /**
	 * 
	 * the list of all current votes (this changes as in each iteration step, alternatives get eliminated).
	 */
	List<Ballot> votes;
	
	/**
	 * wins the amount of wins for each alternative according to the current rankings.
	 */
	Map<Alternative, Integer> wins;
	
	/**
	 * the amount of voters.
	 */
	Integer voterCount;
	
	/**
	 * the amount of original alternatives.
	 */
	Integer alternativeCount;

	/* (non-Javadoc)
	 * @see voting.rules.iterative.IterativeVotingRule#order(java.util.List)
	 */
	@Override
	public Ballot order(List<Ballot> rankings) {
		// Check for empty
		if (rankings.isEmpty() || rankings.get(0).getElementsCount() == 0)
			return new Ballot(new HashMap<Alternative, Integer>());
		// Not empty case, initialize properties
		answer = new Vector<Set<Alternative>>();
		eliminated = new HashSet<Alternative>();
		votes = new ArrayList<Ballot>(rankings.size());
		wins = new HashMap<Alternative, Integer>();
		voterCount = rankings.size(); 
		alternativeCount = rankings.get(0).getElementsCount();
		// Copy rankings
		for (Ballot ballot : rankings)
			votes.add(new Ballot(ballot));
		// Fill winning indexes and votes
		fillPropertiesWithInitialRankings(votes);
		// Execute the voting
		return super.order(votes);
	}

	/**
	 * Class initialization helper
	 * Fill the object properties according to the initial voter rankings of alternatives
	 * @param rankings the initial rankings submitted by the voters
	 */
	private void fillPropertiesWithInitialRankings(List<Ballot> rankings) {
		for (Ballot ranking : rankings)
			for (Alternative a : ranking.getElements(1))
				wins.put(a, wins.getOrDefault(a, 0)+1);
	}
	
	/* 
	 * Get the losers (alternatives with the least amount of 1st places)
	 * Remove them from all the rankings
	 * Recompute the wins for each voter
	 */
	@Override
	public void doIterationStep() {
		Set<Alternative> roundLosers = getLeastFirstPlaces();
		answer.add(0, roundLosers);
		eliminated.addAll(roundLosers);
		recomputeWinsAndVotes(roundLosers);
	}

	/**
	 * Recompute the amount of wins as well as the ballots/rankings
	 * of each voter by eliminating the losers of this round as well as the previous ones
	 * @param losers
	 */
	private void recomputeWinsAndVotes(Set<Alternative> roundLosers) {
		wins = new HashMap<Alternative, Integer>();
		for (Ballot ballot : this.votes) {
			ballot.removeAll(roundLosers);
			for (Alternative a : ballot.getElements(1))
				wins.put(a, wins.getOrDefault(a, 0)+1);
		}
	}

	
	/** 
	 * Get the alternatives with the least amount of 1st places
	 * @return alternative set containing the ones with the least amount of 1st places
	 */
	private Set<Alternative> getLeastFirstPlaces() {
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

	/* (non-Javadoc)
	 * @see voting.rules.iterative.IterativeVotingRule#getFinalOrder()
	 */
	@Override
	public Ballot getFinalOrder() {
		Map<Alternative, Integer> rankForElement = new HashMap<Alternative, Integer>();
		Integer rank = 1;
		for (Set<Alternative> rankElements : this.answer) {
			for (Alternative alternative : rankElements)
				rankForElement.put(alternative, rank);
			rank += rankElements.size();
		}
		return new Ballot(rankForElement);
	}

	/* (non-Javadoc)
	 * @see voting.rules.iterative.IterativeVotingRule#iterationFinished()
	 */
	@Override
	protected Boolean iterationFinished() {
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
