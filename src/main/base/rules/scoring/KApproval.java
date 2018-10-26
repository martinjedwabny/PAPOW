package main.base.rules.scoring;

public class KApproval extends PositionalScoringRule {
	
	/**
	 * the threshold that the alternatives rank has to pass in order to increase the score by 1
	 */
	private Integer k;
	
	/**
	 * Default constructor
	 * Takes a number 'k', the score of an alternative is the amount of voters that rank
	 * the alternative amongst the k best. For a single voter,
	 * score = 1 iff the alternative is amongst the k best
	 * score = 0 iff the alternative is not amongst the k best
	 * @param k the threshold that the alternatives rank has to pass in order to increase the score by 1
	 */
	public KApproval(Integer k) {
		super();
		this.k = k;
	};
	
	/* Takes the amount of alternatives that are lesser, greater and equally ranked as itself
	 * as well as the rank of the alternative as outputs the score:
	 * score = 1 iff the alternative is amongst the k best
	 * score = 0 iff the alternative is not amongst the k best
	 * @return the score of the alternative
	 */
	public Double score(Integer less, Integer greater, Integer equal, Integer rank) {
		return rank <= k ? 1.0 : 0.0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return k+"-approval";
	}

}
