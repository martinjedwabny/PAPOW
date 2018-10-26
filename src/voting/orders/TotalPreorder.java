package voting.orders;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Total Preorder
 * 
 * An order that is:
 *	-	Transitive	(for all a,b,c aRb ^ bRc -> aRc)
 *	-	Connex 		(for all a,b aRb or bRa)
 *	-	Reflexive 	(for all a, aRa)
 * 
 * For more information: 
 * 	-	https://en.wikipedia.org/wiki/Weak_ordering
 *
 * @param <T> the type of object to order
 */
public class TotalPreorder<T> {

	/**
	 * rankForElement for each element, its rank in the total preorder (path-depth from a least element)
	 */
	protected Map<T, Integer> rankForElement;

	/**
	 * elementsForRank for each rank, the elements in the rank (for efficiency purposes)
	 */
	protected Map<Integer, HashSet<T>> elementsForRank;

	/**
	 * Constructor
	 * @param ranking: a map that relates each element to a preference level.
	 * @precondition: all values in the map are greater than 0.
	 * @precondition: if key i has value r, then there are r-1 keys with value less than r.
	 * @example: [{a,1},{b,1},{c,3},{d,4},{e,4},{f,6}]. (not necessarily ordered by value)
	 * @return PartialOrder.
	 */
	public TotalPreorder(Map<T, Integer> rankForElement) {
		this.rankForElement = rankForElement;
		this.elementsForRank = groupByRank(rankForElement);
	}
	
	/**
	 * Given a map from element to rank, reverse the map from rank to set
	 * of elements with that rank on the original map.
	 * @param rankForElement
	 * @return elementsForRank
	 */
	private TreeMap<Integer, HashSet<T>> groupByRank(Map<T, Integer> rankForElement){
		TreeMap<Integer, HashSet<T>> elementsForRank = new TreeMap<Integer, HashSet<T>>();
		rankForElement.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach((entry) -> {
			T element = entry.getKey();
			Integer rank = entry.getValue();
			if (!elementsForRank.containsKey(rank))
				elementsForRank.put(rank, new HashSet<T>());
			elementsForRank.get(rank).add(element);
		});
		return elementsForRank;
	}
	
	/**
	 * Remove all the elements of the set s from the ballot
	 * @param s
	 */
	public void removeAll(Set<T> s) {
		for (T element : s) {
			this.elementsForRank.get(this.rankForElement.get(element)).remove(element);
			this.rankForElement.remove(element);
		}
		Integer effectiveRank = 1;
		for (Map.Entry<Integer, HashSet<T>> entry : this.elementsForRank.entrySet()) {
			if (entry.getKey() != effectiveRank)
				for (T element : entry.getValue())
					this.rankForElement.put(element, effectiveRank);
			effectiveRank += entry.getValue().size();
		}
		this.elementsForRank = groupByRank(this.rankForElement);
	}

	/**
	 * @return Set of elements
	 */
	public Set<T> getElements() {
		return this.rankForElement.keySet();
	}

	/**
	 * @return Number of elements
	 */
	public Integer getElementsCount() {
		return this.rankForElement.keySet().size();
	}

	/**
	 * @param e1
	 * @param e2
	 * @return rank(e1) == rank(e2)
	 */
	public boolean equalRank(T e1, T e2) {
		return this.rankForElement.get(e1) == this.rankForElement.get(e2);
	}

	/**
	 * @param e1
	 * @param e2
	 * @return rank(e1) > rank(e2)
	 */
	public boolean higherRank(T e1, T e2) {
		return this.rankForElement.get(e1) > this.rankForElement.get(e2);
	}

	/**
	 * @param e1
	 * @param e2
	 * @return rank(e1) >= rank(e2)
	 */
	public boolean higherOrEqualRank(T e1, T e2) {
		return this.rankForElement.get(e1) >= this.rankForElement.get(e2);
	}

	/**
	 * Get the elements in a specific rank
	 * @param rank
	 * @return Element set for the rank
	 */
	public Set<T> getElements(Integer rank) {
		if (!this.elementsForRank.containsKey(rank))
			return new HashSet<T>();
		return this.elementsForRank.get(rank);
	}
	
	/**
	 * Get the valid ranks
	 * @return All valid ranks
	 */
	public Set<Integer> getRanks() {
		return this.elementsForRank.keySet();
	}
	
	/**
	 * Get the rank of a specific element
	 * @param element
	 * @return Rank for element
	 */
	public Integer getRank(T element) {
		return this.rankForElement.get(element);
	}
	
	/**
	 * Get the total amount of ranks
	 * @return Number of ranks
	 */
	public Integer getRanksCount() {
		return this.elementsForRank.keySet().size();
	}

	@Override
	public String toString() {
		return elementsForRank.toString();
	}

}