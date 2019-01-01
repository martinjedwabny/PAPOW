package com.github.martinjedwabny.test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.martinjedwabny.test.java.base.AlternativeTest;
import com.github.martinjedwabny.test.java.base.BallotTest;
import com.github.martinjedwabny.test.java.base.CategoryFamilyTest;
import com.github.martinjedwabny.test.java.base.CategoryTest;
import com.github.martinjedwabny.test.java.base.QuestionTest;
import com.github.martinjedwabny.test.java.base.orders.TotalPreorderTest;
import com.github.martinjedwabny.test.java.base.rules.VotingRuleTest;
import com.github.martinjedwabny.test.java.io.CriterionTest;

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
