package main.io.reader;

import main.base.rules.VotingRule;
import main.base.rules.iterative.InstantRunoff;
import main.base.rules.scoring.BordaFair;
import main.base.rules.scoring.BordaOptimistic;
import main.base.rules.scoring.BordaPessimistic;
import main.base.rules.scoring.Copeland;
import main.base.rules.scoring.KApproval;

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