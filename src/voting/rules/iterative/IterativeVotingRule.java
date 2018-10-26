package voting.rules.iterative;

import java.util.List;

import voting.Ballot;
import voting.rules.VotingRule;

/**
 * Iterative voting rule
 * An abstract class of voting rule that executes an iteration step (in which rankings are computed)
 * until a certain condition is achieved (all positions in the ranking are determined)
 */
public abstract class IterativeVotingRule extends VotingRule {

	/* 
	 * Execute an iteration step (in which rankings are computed)
	 * until a certain codition is achieved
	 */
	public Ballot order(List<Ballot> rankings) {
		while (!iterationFinished())
			doIterationStep();
		return getFinalOrder();
	}
	
	/**
	 * Execute an iteration step
	 */
	protected abstract void doIterationStep();
	
	/** Returns true iff a certain condition is achieved (all positions in the ranking are determined)
	 * @return Boolean whether the iteration is finished
	 */
	protected abstract Boolean iterationFinished();
	
	
	/** Gets the final order of alternatives
	 * @return Ballot representing the final order
	 */
	public abstract Ballot getFinalOrder();

}
