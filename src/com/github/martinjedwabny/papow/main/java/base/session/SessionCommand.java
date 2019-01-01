package com.github.martinjedwabny.papow.main.java.base.session;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.github.martinjedwabny.papow.main.java.base.Category;
import com.github.martinjedwabny.papow.main.java.base.CategoryFamily;
import com.github.martinjedwabny.papow.main.java.base.criterion.Criterion;
import com.github.martinjedwabny.papow.main.java.base.criterion.CriterionTrue;
import com.github.martinjedwabny.papow.main.java.base.rules.VotingRule;

/**
 * SessionCommand
 * 
 * Represents the action (user categorizarion, rules and 
 * additional information) to be performed by the session.
 *
 */
public class SessionCommand implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String inputPath;
	private String outputPath;
	private List<VotingRule> rules;
	private Set<Criterion> criteria;
	
	/**
	 * Empty constructor
	 */
	public SessionCommand() {
		this.inputPath = null;
		this.outputPath = null;
		this.rules = new Vector<VotingRule>();
		this.criteria = new LinkedHashSet<Criterion>();
		this.criteria.add(new CriterionTrue());
	}

	/**
	 * Default constructor
	 * @param inputPath
	 * @param outputPath
	 * @param rules
	 * @param criteria
	 */
	public SessionCommand(String inputPath, String outputPath, List<VotingRule> rules, Set<Criterion> criteria) {
		super();
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.rules = rules;
		this.criteria = criteria;
	}

	/**
	 * @return inputPath
	 */
	public String getInputPath() {
		return inputPath;
	}

	/**
	 * @return the outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * @return rules
	 */
	public List<VotingRule> getRules() {
		return rules;
	}

	/**
	 * @return criteria
	 */
	public Set<Criterion> getCriteria() {
		return criteria;
	}

	/**
	 * @param inputPath the inputPath to set
	 */
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(List<VotingRule> rules) {
		this.rules = rules;
	}

	/**
	 * @param criteria the criterion to set
	 */
	public void setCriteria(Set<Criterion> criteria) {
		this.criteria = criteria;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Command [inputPath=" + inputPath + ", outputPath=" + outputPath + ", rules=" + rules + "]";
	}

	/**
	 * Remove family from session input
	 * @param family
	 */
	public void removeFamily(CategoryFamily family) {
		for (Criterion c : this.criteria)
			c.removeKey(family.getDescription());
	}

	/**
	 * Remove category from family in session input
	 * @param family
	 * @param category
	 */
	public void removeCategory(CategoryFamily family, Category category) {
		for (Criterion c : this.criteria)
			c.removeValue(family.getDescription(), category.getDescription());
	}
}
