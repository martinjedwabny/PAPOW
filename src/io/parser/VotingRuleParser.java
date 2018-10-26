package io.parser;

import base.rules.VotingRule;
import base.rules.iterative.InstantRunoff;
import base.rules.scoring.BordaFair;
import base.rules.scoring.BordaOptimistic;
import base.rules.scoring.BordaPessimistic;
import base.rules.scoring.Copeland;
import base.rules.scoring.KApproval;

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
