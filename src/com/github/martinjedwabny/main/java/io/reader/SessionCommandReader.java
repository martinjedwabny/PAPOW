package com.github.martinjedwabny.main.java.io.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.martinjedwabny.main.java.base.criterion.Criterion;
import com.github.martinjedwabny.main.java.base.rules.VotingRule;
import com.github.martinjedwabny.main.java.base.session.SessionCommand;

public class SessionCommandReader {

	private static String inputPathKey = "-f";
	private static String outputPathKey = "-o";
	private static String votingRuleKey = "-v";
	private static String criterionKey = "-c";
	
	/**
	 * Build a command with the arguments of the input as a String array
	 * For example: [-f,json.txt,-v,[pb,ir]] 
	 * @param args arguments of the input
	 * @return command an instance of Command class
	 */
	public static SessionCommand read(String[] args) {
		Map<String, String> options = argumentsToMap(args);
		String inputPath = options.getOrDefault(inputPathKey, "");
		String outputPath = options.getOrDefault(outputPathKey, "");
		List<VotingRule> rules = parseRules(options.getOrDefault(votingRuleKey, null));
		Criterion criterion = parseCriterion(options.getOrDefault(criterionKey, null));
		Set<Criterion> criteria = new LinkedHashSet<Criterion>();
		criteria.add(criterion);
		return new SessionCommand(inputPath, outputPath, rules, criteria);
	}
	
	/**
	 * Take an array of string arguments and convert it to a map
	 * For example: [-f,json.txt,-v,[pb,ir]] -> { <'-f', 'json.txt'>, <'-v', '[pb,ir]'> }
	 * @param args
	 * @return map of <String,String>
	 */
	private static Map<String, String> argumentsToMap(String[] args) {
	    List<String> argsList = new ArrayList<String>();  
	    Map<String, String> optList = new HashMap<String, String>();
	    for (int i = 0; i < args.length; i++) {
	        switch (args[i].charAt(0)) {
	        case '-':
	            if (args[i].length() < 2) {
	                throw new IllegalArgumentException("Not a valid argument: "+args[i]);
	            } else {
	                if (args.length-1 == i)
	                    throw new IllegalArgumentException("Expected arg after: "+args[i]);
	                optList.put(args[i], args[i+1]);
	                i++;
	            }
	            break;
	        default:
	            argsList.add(args[i]);
	            break;
	        }
	    }
	    return optList;
	}

	/**
	 * Given a string of voting rules specified in the options
	 * construct the objects representing those rules.
	 * @param votingRule String
	 * @return list of voting rules to use in the calculations
	 */
	private static List<VotingRule> parseRules(String votingRule) {
		if (votingRule == null)
			return null;
		List<VotingRule> rules = new ArrayList<VotingRule>();
		String[] ruleCodes = null;
		try {
			ruleCodes = votingRule.substring(1, votingRule.length()-1).split(",");
		} catch (Exception e) {
			System.out.println("Problem parsing voting rules.");
			e.printStackTrace();
			return rules;
		}
		for (String ruleCode : ruleCodes)
			rules.add(VotingRuleReader.read(ruleCode));
		return rules;
	}

	/**
	 * Given a string criterion for filtering votes
	 * construct the object representing that criterion.
	 * @param criterion String
	 * @return criterion specified
	 */
	private static Criterion parseCriterion(String criterion) {
		return CriterionReader.read(criterion);
	}
}
