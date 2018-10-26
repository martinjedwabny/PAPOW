package test.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;
import main.base.*;

public class CategoryFamilyTest {
	
	Category c1 = new Category("France"), c2 = new Category("Algeria");
	Set<Category> possibilities = new HashSet<Category>(Arrays.asList(c1,c2));
	CategoryFamily f = new CategoryFamily("Country", possibilities);

	@Test
	public void shouldGetDescription() throws Exception {
		Assert.assertEquals(f.getDescription(), "Country");
	}

	@Test
	public void shouldSetDescription() throws Exception {
		f.setDescription("asd");
		Assert.assertEquals(f.getDescription(), "asd");
		f.setDescription("Country");
	}

	@Test
	public void shouldGetPossibilities() throws Exception {
		Assert.assertEquals(f.getPossibilities().contains(c1), true);
		Assert.assertEquals(f.getPossibilities().contains(c2), true);
		Assert.assertEquals(f.getPossibilities().contains(new Category("asd")), false);
	}

}
