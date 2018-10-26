package base.rules;

import java.util.List;

import base.Ballot;

/**
 * Voting rule
 * Abstract class that models any voting rule of the module
 *
 */
public abstract class VotingRule {
	
	/**
	 * Takes a list of ballots (voter rankings of alternatives)
	 * and outputs the calculated final rankings of alternatives
	 * according to the voting rule
	 * @param rankings a list of ballots
	 * @return a ballot that represents the calculated rankings
	 */
	public abstract Ballot order(List<Ballot> rankings);
}
