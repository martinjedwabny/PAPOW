package voting.rules;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import junit.framework.Assert;
import voting.Alternative;
import voting.Ballot;
import voting.rules.iterative.InstantRunoff;
import voting.rules.scoring.BordaFair;
import voting.rules.scoring.KApproval;
import voting.rules.scoring.BordaOptimistic;
import voting.rules.scoring.BordaPessimistic;
import voting.rules.scoring.Copeland;

public class VotingRuleTest {
	/**
	 * Test cases.
	 */
	private Alternative a = new Alternative("a"),
			b = new Alternative("b"),
			c = new Alternative("c");
	private Alternative ballotsArray0[][][] = {
			{{a},{b},{c}},{{a},{b},{c}},{{a},{b},{c}},{{a},{b},{c}},
			{{b},{c},{a}},{{b},{c},{a}},{{b},{c},{a}},
			{{c},{b},{a}},{{c},{b},{a}}};
	private Alternative ballotsArray1[][][] = {
			{{a,b},{},{c}},
			{{a,b},{},{c}},
			{{a},{b,c},{}},
			{{b,c},{},{a}}};
	private List<List<Ballot>> ballots = Arrays.asList(matrixToBallot(ballotsArray0), matrixToBallot(ballotsArray1));
	
	/**
	 * Convert a matrix of alternatives to a list of ballots.
	 * @param ballotsArray
	 * @return
	 */
	private List<Ballot> matrixToBallot(Alternative ballotsArray[][][]) {
		return Arrays.asList(ballotsArray).stream().map(ballotArray -> {
			HashMap<Alternative, Integer> ballotMap = new HashMap<Alternative, Integer>();
			for (int i = 0; i < ballotArray.length; i++)
				for (int j = 0; j < ballotArray[i].length; j++)
					ballotMap.put(ballotArray[i][j], i+1);
			return new Ballot(ballotMap);
		}).collect(Collectors.toList());
	}
	
	/**
	 * Compares the expected result of a voting rule to the actual result.
	 * @param result
	 * @param expected
	 */
	private void compareRuleToExpected(Ballot result, Alternative expected[][]) {
		for (int rank = 0; rank < expected.length; rank++)
			for (Alternative alternative : expected[rank])
				Assert.assertTrue("Result: " + result,rank+1 == result.getRank(alternative));
	}
	
	/**
	 * InstantRunoff
	 */
	@Test
	public void shouldValidateInstantRunoff() {
		InstantRunoff rule = new InstantRunoff();
		Alternative expected0[][] = {{b},{a},{c}};
		Alternative expected1[][] = {{a,b},{},{c}};
		compareRuleToExpected(rule.order(ballots.get(0)), expected0);
		compareRuleToExpected(rule.order(ballots.get(1)), expected1);
	}
	
	/**
	 * FairBorda
	 */
	@Test
	public void shouldValidateFairBorda() {
		BordaFair rule = new BordaFair();
		Alternative expected0[][] = {{b},{a},{c}};
		Alternative expected1[][] = {{a,b},{},{c}};
		compareRuleToExpected(rule.order(ballots.get(0)), expected0);
		compareRuleToExpected(rule.order(ballots.get(1)), expected1);
	}
	
	/**
	 * PessimisticBorda
	 */
	@Test
	public void shouldValidatePessimisticBorda() {
		BordaPessimistic rule = new BordaPessimistic();
		Alternative expected0[][] = {{b},{a},{c}};
		Alternative expected1[][] = {{a},{b},{c}};
		compareRuleToExpected(rule.order(ballots.get(0)), expected0);
		compareRuleToExpected(rule.order(ballots.get(1)), expected1);
	}
	
	/**
	 * OptimisticBorda
	 */
	@Test
	public void shouldValidateOptimisticBorda() {
		BordaOptimistic rule = new BordaOptimistic();
		Alternative expected0[][] = {{b},{a},{c}};
		Alternative expected1[][] = {{b},{a},{c}};
		compareRuleToExpected(rule.order(ballots.get(0)), expected0);
		compareRuleToExpected(rule.order(ballots.get(1)), expected1);
	}
	
	/**
	 * KApproval
	 */
	@Test
	public void shouldValidateKApproval() {
		KApproval rule = new KApproval(2);
		Alternative expected0[][] = {{b},{c},{a}};
		Alternative expected1[][] = {{b},{a},{c}};
		compareRuleToExpected(rule.order(ballots.get(0)), expected0);
		compareRuleToExpected(rule.order(ballots.get(1)), expected1);
	}
	
	/**
	 * Copeland
	 */
	@Test
	public void shouldValidateCopeland() {
		Copeland rule = new Copeland();
		Alternative expected0[][] = {{b},{c},{a}};
		Alternative expected1[][] = {{a,b},{},{c}};
		compareRuleToExpected(rule.order(ballots.get(0)), expected0);
		compareRuleToExpected(rule.order(ballots.get(1)), expected1);
	}

}
