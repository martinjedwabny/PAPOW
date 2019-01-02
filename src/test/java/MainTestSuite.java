package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.java.base.AlternativeTest;
import test.java.base.BallotTest;
import test.java.base.CategoryFamilyTest;
import test.java.base.CategoryTest;
import test.java.base.QuestionTest;
import test.java.base.orders.TotalPreorderTest;
import test.java.base.rules.VotingRuleTest;
import test.java.io.CriterionTest;

@RunWith(Suite.class)
@SuiteClasses({
	CriterionTest.class,
	AlternativeTest.class,
	BallotTest.class,
	CategoryFamilyTest.class,
	CategoryTest.class,
	QuestionTest.class,
	TotalPreorderTest.class,
	VotingRuleTest.class})
public class MainTestSuite {
}
