package main.java.io.writer;

import main.java.base.Question;
import main.java.base.Vote;
import main.java.base.ordering.Ballot;
import main.java.base.rules.VotingRule;
import main.java.base.session.Session;

/**
 * SessionStringWriter
 * 
 * Adaptor from SessionResult to String.
 * Given a SessionResult, produce a multiline String to output the results
 * of the voting for each question and rule, as well as the vote selection criterion used.
 *
 */
public class SessionStringWriter {
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
	 * @param sessionResult SessionResult
	 * @return sessionResult String
	 */
	public static String write(Session session) {
		if (session == null)
			return null;
		StringBuilder output = new StringBuilder();
		output.append(dashedLine);
		if (session.getCommand() != null && session.getCommand().getCriterion() != null)
			output.append("Criterion: " + session.getCommand().getCriterion().toString() + newLine);
		output.append(dashedLine);
		for (Question q : session.getInput().getQuestions())
			buildOutputForQuestion(q, session, output);
		return output.toString();
	}

	/**
	 * Given a SessionResult and a question, attach a multiline String to output the results
	 * of the voting for this question and each rule.
	 * @param q
	 * @param sessionResult
	 * @param output
	 */
	private static void buildOutputForQuestion(Question q, Session session, StringBuilder output) {
		output.append("Question: '" + q.getDescription() + "'" + newLine + dashedLine + newLine);
		output.append(tab + "Votes:" + newLine);
		// Take votes and get ranking
		for (Vote v : session.getResult().getValidVotes().get(q))
			output.append(tab + tab + v + newLine);
		output.append(newLine);
		// Run each voting rule and print the output
		for (VotingRule rule : session.getCommand().getRules())
			buildOutputForQuestionAndRule(output, rule, session.getResult().getResults().get(q).get(rule));
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
