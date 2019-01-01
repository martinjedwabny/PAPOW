package com.github.martinjedwabny.papow.main.java.base.ordering;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.github.martinjedwabny.papow.main.java.base.Alternative;

/** Ballot
 * A class that models user rankings as Total Preorders of Alternatives 
 * and provides additional access methods related to voting contexts.
 */
public class Ballot extends TotalPreorder<Alternative> implements Serializable {
	private static final long serialVersionUID = 1L;

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
	
	@Override
	protected Map<Integer, Set<Alternative>> groupByRank(Map<Alternative, Integer> rankForElement) {
		Map<Integer, Set<Alternative>> elementsForRank = new TreeMap<Integer, Set<Alternative>>();
		rankForElement.entrySet().stream().forEach((entry) -> {
			Alternative element = entry.getKey();
			Integer rank = entry.getValue();
			if (!elementsForRank.containsKey(rank))
				elementsForRank.put(rank, new TreeSet<Alternative>());
			elementsForRank.get(rank).add(element);
		});
		return elementsForRank;
	}
}
