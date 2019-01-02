package test.java.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import main.java.base.Alternative;
import main.java.base.ordering.Ballot;

public class BallotTest {
	Alternative a = new Alternative("a");
	Alternative b = new Alternative("b");
	Alternative c = new Alternative("c");
	Alternative d = new Alternative("d");
	private Ballot ballot;

	@Before
	public void createBallot() throws Exception {
		Map<Alternative, Integer> rankForElement = new HashMap<Alternative, Integer>();
		rankForElement.put(a, 1);
		rankForElement.put(b, 2);
		rankForElement.put(c, 2);
		rankForElement.put(d, 3);
		ballot = new Ballot(rankForElement);
	}

	@Test
	public void shouldGetElements() throws Exception {
		Assert.assertEquals(ballot.getElements().contains(a), true);
		Assert.assertEquals(ballot.getElements().contains(b), true);
		Assert.assertEquals(ballot.getElements().contains(c), true);
		Assert.assertEquals(ballot.getElements().contains(d), true);
		Assert.assertEquals(ballot.getElements().contains(new Alternative("e")), false);
	}

	@Test
	public void shouldGetRanks() throws Exception {
		Assert.assertEquals(ballot.getRanks().contains(1), true);
		Assert.assertEquals(ballot.getRanks().contains(2), true);
		Assert.assertEquals(ballot.getRanks().contains(3), true);
		Assert.assertEquals(ballot.getRanks().contains(4), false);
	}

	@Test
	public void shouldGetRank() throws Exception {
		Assert.assertEquals((int)ballot.getRank(a), 1);
		Assert.assertEquals((int)ballot.getRank(b), 2);
		Assert.assertEquals((int)ballot.getRank(c), 2);
		Assert.assertEquals((int)ballot.getRank(d), 3);
	}

	@Test
	public void shouldGetRanksCount() throws Exception {
		Assert.assertEquals((int)ballot.getRanksCount(), 3);
	}

}
