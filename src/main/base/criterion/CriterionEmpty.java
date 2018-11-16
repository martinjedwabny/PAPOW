package main.base.criterion;

import java.util.Map;

public class CriterionEmpty extends Criterion {
	private static final long serialVersionUID = 1L;

	/* 
	 * Always returns true
	 */
	@Override
	public Boolean satisfies(Map<String, String> profile) {
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CriterionEmpty";
	}

}
