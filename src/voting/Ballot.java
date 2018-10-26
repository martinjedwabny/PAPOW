package voting;

import java.util.HashMap;
import java.util.Map;
import voting.orders.TotalPreorder;

/** Ballot
 * A class that models user rankings as Total Preorders of Alternatives 
 * and provides additional access methods related to voting contexts.
 */
public class Ballot extends TotalPreorder<Alternative> {
	
	/**
	 * Default constructor
	 * Takes a map from an Alternative to a particular rank (Integer) and outputs a Ballot
	 * @param rankForElement the map from Alternative to rank
	 */
	public Ballot(Map<Alternative, Integer> rankForElement) {
		super(rankForElement);
	}
	
	/**
	 * Copy constructor
	 * @param ballot the ballot to be copied
	 */
	public Ballot(Ballot ballot) {
		super(new HashMap<Alternative, Integer>(ballot.rankForElement));
	}
}
