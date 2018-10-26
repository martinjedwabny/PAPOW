package io.criterion;

import java.util.Vector;
import java.util.Map;

public class CriterionOr extends Criterion {
	
	/**
	 * Subcriteria that compose the 'OR' clause.
	 * 
	 * If the subcriteria are [s1,s2,...,sn],
	 * This criterion represents: s1 or s2 or ... or sn.
	 */
	private Vector<Criterion> subcriteria;

	/**
	 * Default empty constructor.
	 */
	public CriterionOr() {
		super();
		this.subcriteria = new Vector<Criterion>();
	}

	/**
	 * Default filled constructor.
	 * @param subcriteria
	 */
	public CriterionOr(Vector<Criterion> subcriteria) {
		super();
		this.subcriteria = subcriteria;
	}

	/** 
	 * Takes a profile and returns true iff the condition is satisfied
	 * @param profile the key value map to compare
	 * @return b s1 or s2 or ... or sn.
	 */
	@Override
	public Boolean satisfies(Map<String, String> profile) {
		if (this.subcriteria.isEmpty())
			return true;
		for (Criterion c : this.subcriteria)
			if (c.satisfies(profile))
				return true;
		return false;
	}
	
	/**
	 * Add a criterion to the subcriteria
	 * @param c the criterion to add
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
			ans = "EmptyOr";
		else {
			ans = "(";
			for (int i = 0; i < this.subcriteria.size(); i++) {
				ans += this.subcriteria.get(i);
				if (i < this.subcriteria.size()-1)
					ans += " or ";
			}
			ans += ")";
		}
		return ans;
	}

}
