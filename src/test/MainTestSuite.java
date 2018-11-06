package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.base.AlternativeTest;
import test.base.BallotTest;
import test.base.CategoryFamilyTest;
import test.base.CategoryTest;
import test.base.QuestionTest;
import test.base.orders.TotalPreorderTest;
import test.base.rules.VotingRuleTest;
import test.io.CriterionTest;

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
