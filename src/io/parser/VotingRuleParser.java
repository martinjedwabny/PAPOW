package io.parser;

import voting.rules.VotingRule;
import voting.rules.iterative.InstantRunoff;
import voting.rules.scoring.BordaFair;
import voting.rules.scoring.KApproval;
import voting.rules.scoring.BordaOptimistic;
import voting.rules.scoring.BordaPessimistic;
import voting.rules.scoring.Copeland;

public class VotingRuleParser {
	/**
	 * Given a rule specified as a String code,
	 * construct the object representing that rule.
	 * @param ruleCode
	 * @return rule Object representing the rule
	 */
	public static VotingRule parseRule(String ruleCode) {
		if (ruleCode.equals("pb"))
			return new BordaPessimistic();
		if (ruleCode.equals("ir"))
			return new InstantRunoff();
		if (ruleCode.equals("fb"))
			return new BordaFair();
		if (ruleCode.equals("ob"))
			return new BordaOptimistic();
		if (ruleCode.equals("co"))
			return new Copeland();
		if (ruleCode.startsWith("k"))
			return new KApproval(Integer.parseInt(ruleCode.substring(1)));
		return null;
	}
}
