package io.criterion;

import java.util.Vector;
import java.util.Map;

public class CriterionAnd extends Criterion {
	
	/**
	 * Subcriteria that compose the 'AND' clause.
	 * 
	 * If the subcriteria are [s1,s2,...,sn],
	 * This criterion represents: s1 and s2 and ... and sn.
	 */
	private Vector<Criterion> subcriteria;

	/**
	 * Default empty constructor
	 */
	public CriterionAnd() {
		super();
		this.subcriteria = new Vector<Criterion>();
	}

	/** 
	 * Default filled constructor
	 * @param subcriteria
	 */
	public CriterionAnd(Vector<Criterion> subcriteria) {
		super();
		this.subcriteria = subcriteria;
	}

	/** 
	 * Takes a profile and returns true iff the condition is satisfied
	 * @param profile the key value map to compare
	 * @return b s1 and s2 and ... and sn.
	 */
	@Override
	public Boolean satisfies(Map<String, String> profile) {
		if (this.subcriteria.isEmpty())
			return false;
		for (Criterion c : this.subcriteria)
			if (!c.satisfies(profile))
				return false;
		return true;
	}
	
	/**
	 * Add a criterion to the 'AND' clause
	 * @param c criterion
	 */
	public void addCriterion(Criterion c) {
		this.subcriteria.addElement(c);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ans = "";
		if (this.subcriteria.isEmpty())
			ans = "EmptyAnd";
		else {
			ans = "(";
			for (int i = 0; i < this.subcriteria.size(); i++) {
				ans += this.subcriteria.get(i);
				if (i < this.subcriteria.size()-1)
					ans += " and ";
			}
			ans += ")";
		}
		return ans;
	}

}
