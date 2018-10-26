package main.io.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.base.rules.VotingRule;
import main.io.command.Command;
import main.io.criterion.Criterion;

public class CommandParser {

	private static String fileNameKey = "-f";
	private static String votingRuleKey = "-v";
	private static String criterionKey = "-c";
	
	/**
	 * Build a command with the arguments of the input as a String array
	 * For example: [-f,json.txt,-v,[pb,ir]] 
	 * @param args arguments of the input
	 * @return command an instance of Command class
	 */
	public static Command parseCommand(String[] args) {
		Map<String, String> options = argumentsToMap(args);
		if (options.get(fileNameKey) == null || options.get(votingRuleKey) == null) {
			System.out.println("Problem: be sure to include " + 
					fileNameKey + " (fileName) and " + votingRuleKey + " (voting rules) tags");
			return null;
		}
		String fileName = options.get(fileNameKey);
		List<VotingRule> rules = parseRules(options);
		Criterion criterion = parseCriterion(options);
		return new Command(fileName, rules, criterion);
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
	 * Given a map of command options, get the voting rules specified in the options
	 * and construct the objects representing those rules.
	 * @param options
	 * @return list of voting rules to use in the calculations
	 */
	private static List<VotingRule> parseRules(Map<String, String> options) {
		List<VotingRule> rules = new ArrayList<VotingRule>();
		String[] ruleCodes = null;
		try {
			ruleCodes = options.get(votingRuleKey).substring(1, options.get(votingRuleKey).length()-1).split(",");
		} catch (Exception e) {
			System.out.println("Problem parsing voting rules.");
			return rules;
		}
		for (String ruleCode : ruleCodes)
			rules.add(VotingRuleParser.parseRule(ruleCode));
		return rules;
	}

	/**
	 * Given a map of command options, parse the criterion for filtering votes
	 * and construct the object representing that criterion.
	 * @param options
	 * @return criterion specified
	 */
	private static Criterion parseCriterion(Map<String, String> options) {
		String scriteria = null;
		if (options.get(criterionKey) != null && options.get(criterionKey).length() > 2)
			scriteria = options.get(criterionKey);
		return CriterionParser.parseCriterion(scriteria);
	}

	
}
