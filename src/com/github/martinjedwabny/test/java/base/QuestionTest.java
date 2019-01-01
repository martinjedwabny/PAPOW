package com.github.martinjedwabny.test.java.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import org.junit.Test;

import com.github.martinjedwabny.main.java.base.*;
import com.github.martinjedwabny.main.java.base.ordering.Ballot;

import junit.framework.Assert;

public class QuestionTest {
	
	Question q;
	
	public QuestionTest() {
		Alternative a1 = new Alternative("a1"), a2 = new Alternative("a2");
		Category c1 = new Category("France"), c2 = new Category("Algeria");
		CategoryFamily f = new CategoryFamily("Country", new HashSet<Category>(Arrays.asList(c1, c2)));
		Map<CategoryFamily, Category> fc1 = new HashMap<CategoryFamily, Category>();fc1.put(f, c1);
		Map<CategoryFamily, Category> fc2 = new HashMap<CategoryFamily, Category>();fc2.put(f, c2);
		Voter v1 = new Voter("v1", fc1), v2 = new Voter("v2", fc2);
		Map<Alternative, Integer> r1 = new HashMap<Alternative, Integer>();r1.put(a1, 1);r1.put(a2, 2);
		Map<Alternative, Integer> r2 = new HashMap<Alternative, Integer>();r2.put(a1, 2);r2.put(a2, 1);
		Vote vote1 = new Vote(v1, new Ballot(r1)), vote2 = new Vote(v2, new Ballot(r2));
		this.q = new Question("Question name", new Vector<Alternative>(Arrays.asList(a1,a2)), new HashSet<Vote>(Arrays.asList(vote1, vote2)));
	}

	@Test
	public void shouldAddAlternative() throws Exception {
		int a = q.getAlternatives().size();
		q.addAlternative(new Alternative("c"));
		int b = q.getAlternatives().size();
		Assert.assertEquals(a, b-1);
	}

	@Test
	public void shouldPreserveVotesOnAddAndRemove() throws Exception {
		int a = q.getVotes().size();
		Vote vote2 = q.getVotes().iterator().next();
		Vote vote3 = new Vote(vote2.getVoter(), vote2.getRanking());
		q.addVote(vote3);
		int b = q.getVotes().size();
		q.removeVote(vote3);
		int c = q.getVotes().size();
		Assert.assertEquals(q.getVotes().contains(vote2), true);
		Assert.assertEquals(q.getVotes().contains(vote3), false);
		Assert.assertEquals(a, b-1);
		Assert.assertEquals(b-1, c);
	}

}
