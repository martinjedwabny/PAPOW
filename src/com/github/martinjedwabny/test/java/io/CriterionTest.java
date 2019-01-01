package com.github.martinjedwabny.test.java.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.github.martinjedwabny.main.java.base.criterion.*;

public class CriterionTest {

	private List<Map<String,String>> predicates;

	public CriterionTest() {
		Map<String, String> p1 = new HashMap<String, String>();
		Map<String, String> p2 = new HashMap<String, String>();	p2.put("a","1");
		Map<String, String> p3 = new HashMap<String, String>(); for (int i = 0; i < 50; i++) p3.put(""+i, ""+i);
		this.predicates = Arrays.asList(p1,p2,p3);
	}
	
	/* Empty Criterion */
	@Test
	public void ShouldPassEmptyCriterion() {
		Criterion c1 = new CriterionTrue();
		compareCriterionToPredicates(c1, new Boolean[] {true, true, true});
	}
	
	/* Equals Criterion */
	@Test
	public void ShouldPassEqualsCriterion() {
		Criterion c1 = new CriterionEquals("a","1");
		Criterion c2 = new CriterionEquals("a","2");
		Criterion c3 = new CriterionEquals("44","44");
		compareCriterionToPredicates(c1, new Boolean[] {false, true, false});
		compareCriterionToPredicates(c2, new Boolean[] {false, false, false});
		compareCriterionToPredicates(c3, new Boolean[] {false, false, true});
	}
	
	/* Or Criterion */
	@Test
	public void ShouldPassOrCriterion() {
		CriterionOr c1 = new CriterionOr();
		CriterionOr c2 = new CriterionOr();
		c2.addCriterion(new CriterionEquals("a","1"));
		CriterionOr c3 = new CriterionOr();
		c3.addCriterion(new CriterionEquals("a","1"));
		c3.addCriterion(new CriterionEquals("1","1"));
		compareCriterionToPredicates(c1, new Boolean[] {true, true, true});
		compareCriterionToPredicates(c2, new Boolean[] {false, true, false});
		compareCriterionToPredicates(c3, new Boolean[] {false, true, true});
	}
	
	/* And Criterion */
	@Test
	public void ShouldPassAndCriterion() {
		CriterionAnd c1 = new CriterionAnd();
		CriterionAnd c2 = new CriterionAnd();
		c2.addCriterion(new CriterionEquals("a","1"));
		CriterionAnd c3 = new CriterionAnd();
		c3.addCriterion(new CriterionEquals("a","1"));
		c3.addCriterion(new CriterionEquals("1","1"));
		compareCriterionToPredicates(c1, new Boolean[] {false, false, false});
		compareCriterionToPredicates(c2, new Boolean[] {false, true, false});
		compareCriterionToPredicates(c3, new Boolean[] {false, false, false});
	}
	
	/* Complex Criterion */
	@Test
	public void ShouldPassComplexCriterion() {
		CriterionTrue c1 = new CriterionTrue();
		CriterionEquals c2 = new CriterionEquals("1", "1");
		CriterionEquals c3 = new CriterionEquals("2", "2");
		CriterionEquals c4 = new CriterionEquals("3", "3");
		CriterionAnd c5 = new CriterionAnd(new Vector<Criterion>(Arrays.asList(c1,c2,c3,c4)));
		CriterionAnd c6 = new CriterionAnd();
		CriterionOr c7 = new CriterionOr(new Vector<Criterion>(Arrays.asList(c5,c6))); // = (false) or (true and (1=1) and (2=2) and (3=3))
		compareCriterionToPredicates(c7, new Boolean[] {false, false, true});
	}

	public void compareCriterionToPredicates(Criterion criterion, Boolean[] shouldAnswer) {
		for (int i = 0; i < this.predicates.size(); i++)
			Assert.assertEquals(criterion.satisfies(this.predicates.get(i)), shouldAnswer[i]);
	}
}
