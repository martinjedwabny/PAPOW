package voting.rules.scoring;

public class BordaPessimistic extends PositionalScoringRule {

	/* Takes the amount of alternatives that are lesser, greater and equally ranked as itself
	 * as well as the rank of the alternative as outputs the score:
	 * score = (amount of lesser ranked alternatives)
	 * @return the score of the alternative
	 */
	public Double score(Integer less, Integer greater, Integer equal, Integer rank) {
		return less.doubleValue();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pessimistic Borda";
	}
}
