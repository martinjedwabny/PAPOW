package main.base.session;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import main.base.rules.VotingRule;
import main.io.criterion.Criterion;
import main.io.criterion.CriterionEmpty;

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
	private Criterion criterion;
	
	/**
	 * Empty constructor
	 */
	public SessionCommand() {
		this.inputPath = null;
		this.outputPath = null;
		this.rules = new Vector<VotingRule>();
		this.criterion = new CriterionEmpty();
	}

	/**
	 * Default constructor
	 * @param inputPath
	 * @param outputPath
	 * @param rules
	 * @param criterion
	 */
	public SessionCommand(String inputPath, String outputPath, List<VotingRule> rules, Criterion criterion) {
		super();
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.rules = rules;
		this.criterion = criterion;
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
	 * @return criterion
	 */
	public Criterion getCriterion() {
		return criterion;
	}
	
	/**
	 * @return true iff the file path refers to a json file
	 */
	public Boolean hasJsonInputFile () {
		return inputPath != null && inputPath != "" && 
				inputPath.indexOf('.') != -1 && 
				inputPath.substring(inputPath.lastIndexOf('.')).equals(".json");
	}

	/**
	 * @return true iff the file path refers to an object file
	 */
	public Boolean hasObjectInputFile () {
		return inputPath != null && inputPath != "" && !hasJsonInputFile();
	}

	/**
	 * @return true iff there is an output path specified
	 */
	public Boolean hasTxtOutputPath () {
		return outputPath != null && outputPath != "" && 
				outputPath.indexOf('.') != -1 && 
						outputPath.substring(outputPath.lastIndexOf('.')).equals(".txt");
	}
	/**
	 * @return true iff there is an output path specified
	 */
	public Boolean hasOutputPath () {
		return outputPath != null && outputPath != "" && !hasTxtOutputPath();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "eCommand [inputPath=" + inputPath + ", outputPath=" + outputPath + ", rules=" + rules + "]";
	}
}
