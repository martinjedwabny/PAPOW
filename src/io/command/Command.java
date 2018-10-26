package io.command;

import java.util.List;

import base.rules.VotingRule;
import io.criterion.Criterion;

public class Command {
	
	private String fileName;
	private List<VotingRule> rules;
	private Criterion criterion;
	
	/**
	 * Default constructor
	 * @param fileName
	 * @param rules
	 * @param criterion
	 */
	public Command(String fileName, List<VotingRule> rules, Criterion criterion) {
		super();
		this.fileName = fileName;
		this.rules = rules;
		this.criterion = criterion;
	}

	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return rules
	 */
	public List<VotingRule> getRules() {
		return rules;
	}

	/**
	 * @return criterion
	 */
	public Criterion getCriterion() {
		return criterion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Command [fileName=" + fileName + ", rules=" + rules + "]";
	}
}
