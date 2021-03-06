package main.java.io.reader;

import main.java.base.rules.VotingRule;
import main.java.base.rules.iterative.InstantRunoff;
import main.java.base.rules.scoring.BordaFair;
import main.java.base.rules.scoring.BordaOptimistic;
import main.java.base.rules.scoring.BordaPessimistic;
import main.java.base.rules.scoring.Copeland;
import main.java.base.rules.scoring.KApproval;

public class VotingRuleReader {
	/**
	 * Given a rule specified as a String code,
	 * construct the object representing that rule.
	 * @param ruleCode
	 * @return rule Object representing the rule
	 */
	public static VotingRule read(String ruleCode) {
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
