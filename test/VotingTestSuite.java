
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import io.criterion.CriterionTest;
import voting.AlternativeTest;
import voting.BallotTest;
import voting.CategoryFamilyTest;
import voting.CategoryTest;
import voting.QuestionTest;
import voting.orders.TotalPreorderTest;
import voting.rules.VotingRuleTest;

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
public class VotingTestSuite {
}
