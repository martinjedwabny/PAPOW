package test.base.orders;

import java.util.HashMap;

import org.junit.Test;

import junit.framework.Assert;
import main.base.ordering.TotalPreorder;

public class TotalPreorderTest {
	TotalPreorder<Character> example;

	public TotalPreorderTest() {
		super();
		HashMap<Character, Integer> rankForElement = new HashMap<Character, Integer>();
		rankForElement.put('a', 1);
		rankForElement.put('b', 2);
		rankForElement.put('c', 2);
		rankForElement.put('d', 4);
		rankForElement.put('e', 4);
		example = new TotalPreorder<Character>(rankForElement);	
	}
	
	@Test
	public void shouldGetRightRanks() throws Exception {
		Assert.assertEquals(example.getElementsCount(), new Integer(5));
		Assert.assertTrue(example.getElements().contains('a'));
		Assert.assertTrue(example.getElements().contains('b'));
		Assert.assertTrue(example.getElements().contains('c'));
		Assert.assertTrue(example.getElements().contains('d'));
		Assert.assertTrue(example.getElements().contains('e'));
		Assert.assertTrue(example.equalRank('b', 'c'));
		Assert.assertTrue(example.equalRank('d', 'e'));
		Assert.assertTrue(example.higherRank('b', 'a'));
		Assert.assertTrue(example.higherRank('e', 'a'));
	}
	
}
