package io.result;

import voting.Ballot;
import voting.Question;
import voting.Vote;
import voting.result.SessionResult;
import voting.rules.VotingRule;

/**
 * TerminalResultAdaptor
 * 
 * Adaptor from SessionResult to String.
 * Given a SessionResult, produce a multiline String to output the results
 * of the voting for each question and rule, as well as the vote selection criterion used.
 *
 */
public class SessionResultStringAdaptor {
	/**
	 * Helper strings for printing the output
	 */
	private static String tab = "\t";
	private static String newLine = "\n";
	private static String dashedLine = new String(new char[100]).replace("\0", "-") + newLine;
	
	/**
	 * Given a SessionResult, produce a multiline String to output the results
	 * of the voting for each question and rule, as well as the vote selection
	 * criterion used.
	 * @param sessionResult
	 * @return
	 */
	public static String buildOutput(SessionResult sessionResult) {
		StringBuilder output = new StringBuilder();
		output.append(dashedLine);
		output.append("Criterion: " + sessionResult.getCriterion().toString() + newLine);
		output.append(dashedLine);

		for (Question q : sessionResult.getQuestions())
			buildOutputForQuestion(q, sessionResult, output);
		
		return output.toString();
	}

	/**
	 * Given a SessionResult and a question, attach a multiline String to output the results
	 * of the voting for this question and each rule.
	 * @param q
	 * @param sessionResult
	 * @param output
	 */
	private static void buildOutputForQuestion(Question q, SessionResult sessionResult, StringBuilder output) {
		output.append("Question: '" + q.getDescription() + "'" + newLine + dashedLine + newLine);
		output.append(tab + "Votes:" + newLine);
		// Take votes and get ranking
		for (Vote v : sessionResult.getValidVotes().get(q))
			output.append(tab + tab + v + newLine);
		output.append(newLine);
		// Run each voting rule and print the output
		for (VotingRule rule : sessionResult.getVotingRules())
			buildOutputForQuestionAndRule(output, rule, sessionResult.getResults().get(q).get(rule));
		output.append(dashedLine);
	}
	
	/**
	 * Given a SessionResult, a question and a rule, attach a multiline String to output the results
	 * of the voting for the question and rule specified.
	 * @param output
	 * @param rule
	 * @param votingResult
	 */
	private static void buildOutputForQuestionAndRule(StringBuilder output, VotingRule rule, Ballot votingResult) {
		output.append(tab + rule.toString() + newLine);
		output.append(tab + tab + "Ranking " + votingResult + newLine + newLine);
	}
}
